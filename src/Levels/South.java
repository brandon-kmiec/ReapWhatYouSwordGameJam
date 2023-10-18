package Levels;

import Input.Mouse;
import logic.Control;

import java.awt.*;

public class South {
    private final Control ctrl;
    private boolean levelActive;
    private String hoverText;
    private final int x, y;

    public South(Control ctrl) {
        this.ctrl = ctrl;

        x = 710;
        y = 290;

        levelActive = false;
    }


    public void setLevelActive(boolean levelActive) {
        this.levelActive = levelActive;
    }

    public boolean isLevelActive() {
        return levelActive;
    }


    public void runLevel() {
        Point p = Mouse.getMouseCoords();
        ctrl.addSpriteToFrontBuffer(x, y, "EmptyPlace");
    }
}
