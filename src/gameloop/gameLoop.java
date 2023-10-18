package gameloop;

import Data.gameString;
import Graphics.Graphic;
import Graphics.Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class gameLoop {
    Graphic _gph;
    List<gameString> _gs, _hudText;
    Sprites _sps, _hud, _overlays;
    BufferedImage _fade;
    float _alpha;
    boolean _isFade;

    public gameLoop(Graphic gph, List<gameString> gs, Sprites sps, List<gameString> hudText, Sprites hud, Sprites overlays) {
        if (gph == null) throw new NullPointerException();
        _gph = gph;
        _gs = gs;
        _sps = sps;
		_hudText = hudText;
		_hud = hud;
        _overlays = overlays;
        _isFade = false;
        _alpha = 0.0f;
    }

    public void setFade(boolean isFadeOn) {
        _isFade = isFadeOn;
    }

    public boolean hasFadeStarted() {
        return _isFade;
    }

    public void setAlphaFade(float a) {
        _alpha = a;
        if (_alpha > 1.0f)        // Bounds checking!
            _alpha = 1.0f;
        if (_alpha < 0.0f)
            _alpha = 0.0f;
    }

    public void run() {
        /* This will handle the game loop */
        do {
            do {
                Graphics g = null;
                try {
                    g = _gph.getGraph();
                    g.clearRect(0, 0, _gph.getWidth(), _gph.getHeight());
                    g.setColor(Color.black);
                    g.fillRect(0, 0, _gph.getWidth(), _gph.getHeight());
                    Renderer.render(g, _gs, _sps, _hudText, _hud, _overlays, _alpha, _isFade);
                } finally {
                    if (g != null) {
                        g.dispose();
                    }
                }

            } while (_gph.getBufferStrategy().contentsRestored());
            _gph.getBufferStrategy().show();
        } while (_gph.getBufferStrategy().contentsLost());
    }
}