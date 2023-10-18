package Levels;

import Data.Click;
import Data.RECT;
import Input.Mouse;
import Sound.Sound;
import logic.Control;

import java.awt.*;

public class TitleScreen {
    private final Control ctrl;
    private boolean levelActive;
    private boolean startClicked;
    private boolean startGameClicked;
    private boolean northClicked;
    private boolean southClicked;
    private boolean eastClicked;
    private boolean westClicked;
    private final RECT startRect;
    private final RECT northRect, southRect, eastRect, westRect;
    private String hoverText;
    private final int x, y;
    private static Sound backgroundAudio;

    public TitleScreen(Control ctrl) {
        this.ctrl = ctrl;

        levelActive = false;
        startGameClicked = false;

        x = 710;
        y = 290;

        hoverText = "";

        startRect = new RECT(x, y, 1210, 790, "start");

        northRect = new RECT(823, 633, 931, 665, "north");
        southRect = new RECT(967, 633, 1080, 665, "south");
        eastRect = new RECT(823, 689, 921, 722, "east");
        westRect = new RECT(965, 689, 1067, 722, "west");

        backgroundAudio = new Sound("Market_Ambience");
    }


    public void setLevelActive(boolean levelActive) {
        this.levelActive = levelActive;
    }

    public boolean isLevelActive() {
        return levelActive;
    }

    public boolean isStartClicked() {
        return startClicked;
    }

    public boolean isStartGameClicked() {return startGameClicked;}

    public boolean isNorthClicked() {
        return northClicked;
    }

    public boolean isSouthClicked() {
        return southClicked;
    }

    public boolean isEastClicked() {
        return eastClicked;
    }

    public boolean isWestClicked() {
        return westClicked;
    }

    public void setStartGameClicked(boolean startGameClicked) {this.startGameClicked = startGameClicked;}

    public void setNorthClicked(boolean northClicked) {
        this.northClicked = northClicked;
    }

    public void setSouthClicked(boolean southClicked) {
        this.southClicked = southClicked;
    }

    public void setEastClicked(boolean eastClicked) {
        this.eastClicked = eastClicked;
    }

    public void setWestClicked(boolean westClicked) {
        this.westClicked = westClicked;
    }


    public void runLevel() {
        Point p = Mouse.getMouseCoords();

        if (!startGameClicked) {
            ctrl.addSpriteToFrontBuffer(x, y, "TitleScreen");
            if (startRect.isCollision(p.x, p.y))
                hoverText = "Click Anywhere to Start";
            else
                hoverText = "";

            ctrl.drawString(p.x, (p.y - 2), hoverText, Color.BLACK);
            ctrl.drawString((p.x - 2), (p.y - 2) - 2, hoverText, Color.YELLOW);

            if (Control.getMouseInput() != null)
                if (startRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                    startGameClicked = true;
                    backgroundAudio.setLoop();
                    hoverText = "";
                }
        } else
            displayMessageBoard();
    }

    private void displayMessageBoard() {
        Point p = Mouse.getMouseCoords();
        ctrl.addSpriteToFrontBuffer(x, y, "FantasyMessageBoard");

        if (northRect.isCollision(p.x, p.y))
            hoverText = "Click to Travel North";
        else if (southRect.isCollision(p.x, p.y))
            hoverText = "Click to Travel South";
        else if (eastRect.isCollision(p.x, p.y))
            hoverText = "Click to Travel East";
        else if (westRect.isCollision(p.x, p.y))
            hoverText = "Click to Travel West";
        else
            hoverText = "";

        ctrl.drawString(p.x, (p.y - 2), hoverText, Color.BLACK);
        ctrl.drawString((p.x - 2), (p.y - 2) - 2, hoverText, Color.YELLOW);

        if (Control.getMouseInput() != null) {
            if (northRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                northClicked = true;
                southClicked = false;
                eastClicked = false;
                westClicked = false;
                startClicked = true;
                backgroundAudio.pauseWAV();
            }
            if (southRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                northClicked = false;
                southClicked = true;
                eastClicked = false;
                westClicked = false;
                startClicked = true;
                backgroundAudio.pauseWAV();
            }
            if (eastRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                northClicked = false;
                southClicked = false;
                eastClicked = true;
                westClicked = false;
                startClicked = true;
                backgroundAudio.pauseWAV();
            }
            if (westRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                northClicked = false;
                southClicked = false;
                eastClicked = false;
                westClicked = true;
                startClicked = true;
                backgroundAudio.pauseWAV();
            }
        }
    }
}
