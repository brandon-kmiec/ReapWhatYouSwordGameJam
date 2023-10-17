package Levels;

import Input.Mouse;
import logic.Control;

import java.awt.*;

public class West {
    private final Control ctrl;
    private boolean levelActive;
    private String hoverText;
    private final int x, y;

    public West(Control ctrl) {
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
        ctrl.addSpriteToFrontBuffer(x, y, "CreepyBuilding");
        ctrl.addSpriteToFrontBuffer(x, y, "CreepySeller");
        ctrl.addSpriteToFrontBuffer(x, y, "TextBox");
    }
}
