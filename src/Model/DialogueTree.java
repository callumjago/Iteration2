package Model;

import java.util.ArrayList;

public class DialogueTree {

    TextNode root = null;
    TextNode current;

    public DialogueTree(ArrayList<String> textList, ArrayList<String> textTypes) {
        createTree(textList, textTypes);
    }

    private void createTree(ArrayList<String> textList, ArrayList<String> textTypes) {

        root = new TextNode(textList.get(0),textTypes.get(0));
        current = root;
        String prevTextType = textTypes.get(0);

        int answerCount = 0;
        for(int i = 1; i < textList.size(); i++) {
            if (prevTextType == "S") {
                current.addChild(new TextNode(textList.get(i),textTypes.get(i)));
                prevTextType = textTypes.get(i);
            } else if (prevTextType == "Q") {
                answerCount = 0;
                while(textTypes.get(i) == "A" && i < textList.size()) {
                    current.addChild(new TextNode(textList.get(i),"A"));
                    answerCount++;
                    i++;
                }
            } else if (prevTextType == "A") {
                for(int j = 0; j < answerCount; j++) {
                    current.addChild(new TextNode(textList.get(i),textTypes.get(i)));
                    i++;
                }
            } else {
                // throw error, incorrect type assigned to text
            }
        }
    }

    public String getRootText() {
        return root.getText();
    }
}
