package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DialogueTree {

    TextNode root;
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
                while(i < textList.size() && textTypes.get(i) == "A") {
                    current.addChild(new TextNode(textList.get(i),"A"));
                    answerCount++;
                    i++;
                }
                for(TextNode answer : current.getChildren()){
                    answer.addChild(new TextNode(textList.get(i),textTypes.get(i)));
                    i++;
                }
            } else if (prevTextType == "A") { // TODO might need to fix (with for each answer)
                // does nothing now
            } else {
                // throw error, incorrect type assigned to text
            }
        }
    }


    public TextNode getRoot() {
        return root;
    }

    public String getRootText() {
        return root.getText();
    }

    public String getRootType() {
        return root.getTextType();
    }

    public ArrayList<TextNode> getChildren() {
        return current.getChildren();
    }
}
