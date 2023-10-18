package Main;

import Data.*;
import FileIO.EZFileWrite;
import Levels.*;
import logic.Control;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    // Fields (Static) below...
    private static TitleScreen titleScreen;
    private static North north;
    private static South south;
    private static East east;
    private static West west;


    public static void main(String[] args) {
        updateArtScript();
        Control ctrl = new Control();                // Do NOT remove!
        ctrl.gameLoop();                            // Do NOT remove!
    }

    /* This is your access to things BEFORE the game loop starts */
    public static void start(Control ctrl) {
        titleScreen = new TitleScreen(ctrl);
        titleScreen.setLevelActive(true);

        north = new North(ctrl);
        south = new South(ctrl);
        east = new East(ctrl);
        west = new West(ctrl);
    }

    /* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
    public static void update(Control ctrl) {
        ctrl.drawString(10, 30, "Credits:", Color.WHITE);
        ctrl.drawString(10, 60, "Art, Dialogue and Game Over Music: Aaron Rodriguez", Color.WHITE);
        ctrl.drawString(10, 90, "Programming: Brandon Kmiec @ CSU Sacramento", Color.WHITE);
        ctrl.drawString(10, 120, "Game Engine: Prof. Phillips @ CSU Sacramento", Color.WHITE);
        ctrl.drawString(10, 150, "Free to Use Audio: SoundsForStories @ YouTube", Color.WHITE);

        ctrl.drawString(10, 300, "Controls:", Color.WHITE);
        ctrl.drawString(10, 330, "Left mouse click to interact with the game", Color.WHITE);
        ctrl.drawString(10, 360, "Esc key on the keyboard to quit", Color.WHITE);

        if (titleScreen.isLevelActive()) {
            titleScreen.runLevel();

            if (!titleScreen.isStartGameClicked()) {
                BufferedImage gameTitle = new BufferedImage(1920, 700, BufferedImage.TYPE_INT_ARGB);
                Graphics g = gameTitle.getGraphics();
                Font font = new Font(ctrl.getFont().getFontName(), ctrl.getFont().getStyle(), 72);
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString("Reap What You Sword", gameString.getCenteredXPosition(g, font, "Reap What You Sword",
                        753, 1213, 1), 403);
                g.setColor(new Color(180, 162, 64));
                g.drawString("Reap What You Sword", gameString.getCenteredXPosition(g, font, "Reap What You Sword",
                        750, 1210, 1), 400);
                g.dispose();

                Sprite sprite = new Sprite(0, 0, gameTitle, "gameTitle");
                ctrl.addSpriteToFrontBuffer(sprite);
            }

            if (titleScreen.isStartClicked()) {
                titleScreen.setLevelActive(false);
                if (titleScreen.isNorthClicked()) {
                    titleScreen.setNorthClicked(false);
                    north.setLevelActive(true);
                }
                if (titleScreen.isSouthClicked()) {
                    titleScreen.setSouthClicked(false);
                    south.setLevelActive(true);
                }
                if (titleScreen.isEastClicked()) {
                    titleScreen.setEastClicked(false);
                    east.setLevelActive(true);
                }
                if (titleScreen.isWestClicked()) {
                    titleScreen.setWestClicked(false);
                    west.setLevelActive(true);
                }
            }
        }
        if (north.isLevelActive()) {
            north.runLevel();

            if (north.isMessageBoard()) {
                north.setLevelActive(false);
                titleScreen = new TitleScreen(ctrl);
                titleScreen.setLevelActive(true);
                titleScreen.setStartGameClicked(true);
                north = new North(ctrl);
            }
            if (north.isTitleScreen()) {
                north.setLevelActive(false);
                titleScreen = new TitleScreen(ctrl);
                titleScreen.setLevelActive(true);
                north = new North(ctrl);
            }
        }
        if (south.isLevelActive())
            south.runLevel();
        if (east.isLevelActive())
            east.runLevel();
        if (west.isLevelActive())
            west.runLevel();
    }

    // Additional Static methods below...(if needed)
    public static void updateArtScript() {
        File artDirectory = new File("Art");
        File[] artList = artDirectory.listFiles();
        EZFileWrite updateArt = new EZFileWrite("Art.txt");

        if (artList != null)
            for (File file : artList)
                updateArt.writeLine("Art/" + file.getName() + "*" + file.getName().substring(0, file.getName().indexOf('.')));
        updateArt.saveFile();
    }

}
