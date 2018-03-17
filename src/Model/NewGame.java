package Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class NewGame{

    String name;
    int sprite;
    int playerClass;

    public NewGame (String name, int sprite, int playerClass){
        this.name = name;
        this.sprite = sprite;
        this.playerClass = playerClass;
        newGame();
    }



    public void newGame(){


        File saveFolder = new File(System.getProperty("user.dir") + "/SavedGames");

        //if the SavedGames folder doesn't exist make one
        if(!saveFolder.exists()){
            saveFolder.mkdir();
        }

        Path path = Paths.get(System.getProperty("user.dir") + "/SavedGames/PlayerName");

        File sourceMapFolder = new File(System.getProperty("user.dir") + "/DefaultGameFiles");
        File destinationMapFolder = new File(path.toString());

        //copys contents of default folder to player map folder

        copyFolder(sourceMapFolder, destinationMapFolder);

        //writes the players name to the last line of the file
        try {
            Files.write(Paths.get(System.getProperty("user.dir") + "/SavedGames/PlayerName" + "/Player/Player.txt"), String.valueOf("Class: " + playerClass).getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(System.getProperty("user.dir") + "/SavedGames/PlayerName" + "/Player/Player.txt"), System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(System.getProperty("user.dir") + "/SavedGames/PlayerName" + "/Player/Player.txt"), String.valueOf("Sprite: " + sprite).getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(System.getProperty("user.dir") + "/SavedGames/PlayerName" + "/Player/Player.txt"), System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(System.getProperty("user.dir") + "/SavedGames/PlayerName" + "/Player/Player.txt"), String.valueOf("Name: " + name).getBytes(), StandardOpenOption.APPEND);

            } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private static void copyFolder(File sourceFolder, File destinationFolder) {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then copy the file directly to new location
        if (sourceFolder.isDirectory()) {
            //Verify if destinationFolder is already present; If not then create it
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
            }

            //Get all files from source directory
            String files[] = sourceFolder.list();

            //Iterate over all files and copy them to destinationFolder one by one
            for (String file : files) {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                //Recursive function call
                copyFolder(srcFile, destFile);
            }
        } else {
            //Copy the file content from one place to another
            try {
                Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
