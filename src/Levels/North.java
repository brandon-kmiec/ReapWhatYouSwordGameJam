package Levels;

import Data.AText;
import Data.Click;
import Data.RECT;
import Input.Mouse;
import Sound.Sound;
import logic.Control;

import java.awt.*;
import java.util.ArrayList;

public class North {
    private final Control ctrl;
    private boolean levelActive;
    private boolean userResponseActive;
    private boolean deathScreen;
    private boolean messageBoard;
    private boolean titleScreen;
    private String hoverText;
    private final int x, y;
    private RECT textBoxRect;
    private RECT responseA, responseB;
    private RECT gameOver;
    private int counter;
    private final ArrayList<AText> aTextList;
    private ArrayList<String> wrap;
    private AText aText;
    private Sound gameOverSound;

    public North(Control ctrl) {
        this.ctrl = ctrl;

        x = 710;
        y = 290;

        hoverText = "";
        counter = 0;

        aText = new AText("", 20, ctrl);
        aTextList = new ArrayList<>();
        wrap = new ArrayList<>();

        String temp = "Ah, thanks for meeting me back here. Trust me, what I offer is " +
                "much better than anything the marketplace offers.";
        wrap = aText.wrapText(temp);

        textBoxRect = new RECT(722, 647, 1198, 772, "textBox");
        responseA = new RECT(721, 305, 1200, 433, "responseA");
        responseB = new RECT(721, 453, 1200, 580, "responseB");
        gameOver = new RECT(x, y, x + 500, y + 500, "gameOver");

        levelActive = false;
        userResponseActive = false;
        deathScreen = false;
        messageBoard = false;
        titleScreen = false;

        gameOverSound = new Sound("Game_Over_Jingle");
    }


    public void setLevelActive(boolean levelActive) {
        this.levelActive = levelActive;
    }

    public boolean isLevelActive() {
        return levelActive;
    }

    public boolean isMessageBoard() {
        return messageBoard;
    }

    public boolean isTitleScreen() {
        return titleScreen;
    }


    public void runLevel() {
        Point p = Mouse.getMouseCoords();

        if (!deathScreen) {
            ctrl.addSpriteToFrontBuffer(x, y, "SusGuyAlleyway");
            ctrl.addSpriteToFrontBuffer(x, y, "SusGuy");
            ctrl.addSpriteToFrontBuffer(x, y, "TextBox");
            ctrl.drawString(765, 652, "Roland", Color.BLACK);

            if (!userResponseActive) {
                if (textBoxRect.isCollision(p.x, p.y))
                    hoverText = "Click to continue";
                else
                    hoverText = "";
            } else {
                if (responseA.isCollision(p.x, p.y) || responseB.isCollision(p.x, p.y))
                    hoverText = "Click to Select";
                else
                    hoverText = "";
            }

            ctrl.drawHudString(p.x, (p.y - 2), hoverText, Color.BLACK);
            ctrl.drawHudString((p.x - 2), (p.y - 2) - 2, hoverText, Color.YELLOW);

            String temp = "";

            drawAnimatedText();


            if (counter == 0) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        aTextList.clear();
                        temp = "I may not look like it, but I’m quite the expert when it comes to " +
                                "rare and powerful swords. I got exactly what you need, my dear warrior " +
                                "friend.";
                        wrap = aText.wrapText(temp);
                    }
            } else if (counter == 1) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        userResponseActive = true;
                    }
            } else if (counter == 2) {
                if (userResponseActive) {
                    ctrl.addSpriteToFrontBuffer(x, y, "ResponseTextBox");
                    ctrl.drawString(870, 380, "Uh...are you sure?", Color.WHITE);
                    ctrl.drawString(820, 520, "Sorry, I changed my mind.", Color.WHITE);

                    if (Control.getMouseInput() != null) {
                        if (responseA.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 4;
                            aTextList.clear();
                            temp = "Anyways, I’m offering a one-of-a-kind sword. Don’t worry " +
                                    "about the price, we can negotiate that later. This sword will make the " +
                                    "demon lord shiver in fear from its mere presence alone.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                        if (responseB.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 3;
                            aTextList.clear();
                            temp = "Hey now, we haven’t gone into the details yet. It’s too early to " +
                                    "change your mind.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                    }
                }
            } else if (counter == 3) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter = 2;
                        userResponseActive = true;
                    }
            } else if (counter == 4) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        userResponseActive = true;
                    }
            } else if (counter == 5) {
                if (userResponseActive) {
                    ctrl.addSpriteToFrontBuffer(x, y, "ResponseTextBox");
                    ctrl.drawString(870, 380, "That’s just what I need!", Color.WHITE);
                    ctrl.drawString(820, 520, "Cool, but…where is it?", Color.WHITE);

                    if (Control.getMouseInput() != null) {
                        if (responseA.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 7;
                            aTextList.clear();
                            temp = "Terrific! As I said, don’t worry about the price. I do require that " +
                                    "you give me an item up front, one that’s…say…worth more than " +
                                    "$2,500?";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                        if (responseB.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 6;
                            aTextList.clear();
                            temp = "I don’t have it with me right now. Even in an alley, a sword this " +
                                    "powerful isn’t something you’d want to be flashing around y’know.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                    }
                }
            } else if (counter == 6) {
                if (Control.getMouseInput() != null) {
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter = 8;
                        aTextList.clear();
                        temp = "Anyways, I do require that you give me an item up front, one " +
                                "that’s…say…worth more than $2,500?";
                        wrap = aText.wrapText(temp);
                        userResponseActive = true;
                    }
                }
            } else if (counter == 7) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        userResponseActive = true;
                    }
            } else if (counter == 8) {
                if (userResponseActive) {
                    ctrl.addSpriteToFrontBuffer(x, y, "ResponseTextBox");
                    ctrl.drawString(870, 380, "Understood." /* "Understood. (open inventory and give an item worth more " +
                        "than $2,500 gold)" */, Color.WHITE);
                    ctrl.drawString(820, 520, "Sorry, I can’t do that.", Color.WHITE);

                    if (Control.getMouseInput() != null) {
                        if (responseA.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 9;
                            aTextList.clear();
                            temp = "Thank you. Say, I noticed that you have some interesting items " +
                                    "in your possession. I think they’d look great in my pockets.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                        if (responseB.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 13;
                            aTextList.clear();
                            temp = "Can’t? Or won’t? What’s more important: your items, " +
                                    "or a sword that can slay the demon lord?";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                    }
                }
            } else if (counter == 9) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        userResponseActive = true;
                    }
            } else if (counter == 10) {
                if (userResponseActive) {
                    ctrl.addSpriteToFrontBuffer(x, y, "ResponseTextBox");
                    ctrl.drawString(870, 380, "What are you talking about?", Color.WHITE);
                    ctrl.drawString(820, 520, "I think I should leave.", Color.WHITE);

                    if (Control.getMouseInput() != null) {
                        if (responseA.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 11;
                            aTextList.clear();
                            temp = "They look nice, that’s all. Oh hey, what’s that over there? GET ‘EM!!!";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                        if (responseB.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 12;
                            aTextList.clear();
                            temp = "Hey now, what’s the rush? I only said you have some interesting " +
                                    "items. I'm not gonna hurt ya.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                    }
                }
            } else if (counter == 11) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        deathScreen = true;
                        gameOverSound.playWAV();
                    }
            } else if (counter == 12) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter = 11;
                        aTextList.clear();
                        temp = "Okay, that was a lie. NOW!";
                        wrap = aText.wrapText(temp);
                    }
            } else if (counter == 13) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter = 14;
                        aTextList.clear();
                        temp = "We all have to make sacrifices, pal. It’s part of life.";
                        wrap = aText.wrapText(temp);
                    }
            } else if (counter == 14) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        userResponseActive = true;
                    }
            } else if (counter == 15) {
                if (userResponseActive) {
                    ctrl.addSpriteToFrontBuffer(x, y, "ResponseTextBox");
                    ctrl.drawString(870, 380, "Good point…", Color.WHITE);
                    ctrl.drawString(820, 520, "I have already sacrificed enough.", Color.WHITE);

                    if (Control.getMouseInput() != null) {
                        if (responseA.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 16;
                            aTextList.clear();
                            temp = "Thanks for understanding. I know it’s not easy to sacrifice.\n" +
                                    "Believe me, I’ve lost a lot…but not quite as much as you.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                        if (responseB.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 17;
                            aTextList.clear();
                            temp = "What the hell have you sacrificed? Your sword broke, heaven " +
                                    "forbid! I lost my home and my family to the demon lord you warriors " +
                                    "swore to eradicate! I’m barely making ends meet as it is!";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                    }
                }
            } else if (counter == 16) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter = 11;
                        aTextList.clear();
                        temp = "GET ‘EM!";
                        wrap = aText.wrapText(temp);
                    }
            } else if (counter == 17) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter = 18;
                        aTextList.clear();
                        temp = "I’ve been trying to find the right warrior to sell this sword to so " +
                                "they can put an end to that wretched being! Do you have any idea how " +
                                "long I’ve been doing this??";
                        wrap = aText.wrapText(temp);
                    }
            } else if (counter == 18) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        userResponseActive = true;
                    }
            } else if (counter == 19) {
                if (userResponseActive) {
                    ctrl.addSpriteToFrontBuffer(x, y, "ResponseTextBox");
                    ctrl.drawString(870, 380, "I…apologize.", Color.WHITE);
                    ctrl.drawString(820, 520, "You think I’m a fool?", Color.WHITE);

                    if (Control.getMouseInput() != null) {
                        if (responseA.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 9;
                            aTextList.clear();
                            temp = "Thank you. Say, I noticed that you have some interesting items " +
                                    "in your possession. I think they’d look great in my pockets.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                        if (responseB.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 20;
                            aTextList.clear();
                            temp = "Ugh…I hate sharp people like you. Get ‘em, boys.";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                    }
                }
            } else if (counter == 20) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        counter++;
                        userResponseActive = true;
                    }
            } else if (counter == 21) {
                if (userResponseActive) {
                    ctrl.addSpriteToFrontBuffer(x, y, "ResponseTextBox");
                    ctrl.drawString(870, 380, "Fight.", Color.WHITE);
                    ctrl.drawString(820, 520, "Run away.", Color.WHITE);

                    if (Control.getMouseInput() != null) {
                        if (responseA.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 11;
                            aTextList.clear();
                            temp = "Ha! You can’t fight without a sword! Who’s the fool now?";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                        if (responseB.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                            counter = 22;
                            aTextList.clear();
                            temp = "Don’t let him escape! Get back here!";
                            wrap = aText.wrapText(temp);
                            userResponseActive = false;
                        }
                    }
                }
            } else if (counter == 22) {
                if (Control.getMouseInput() != null)
                    if (textBoxRect.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
                        messageBoard = true;
                    }
            }
        } else {
            ctrl.addSpriteToFrontBuffer(x, y, "GameOverScreen");

            if (gameOver.isCollision(p.x, p.y))
                hoverText = "Click to continue";
            else
                hoverText = "";
            ctrl.drawHudString(p.x, (p.y - 2), hoverText, Color.BLACK);
            ctrl.drawHudString((p.x - 2), (p.y - 2) - 2, hoverText, Color.YELLOW);

            if (Control.getMouseInput() != null)
                if (gameOver.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON))
                    titleScreen = true;
        }
    }

    private void drawAnimatedText() {
        for (int i = 0; i < wrap.size(); i++) {
            aTextList.add(new AText(wrap.get(i), 20, ctrl));
            String test = aTextList.get(i).getCurrentStr();
            ctrl.drawString(744, 679 + (i * 20), test, Color.WHITE);
        }
    }
}
