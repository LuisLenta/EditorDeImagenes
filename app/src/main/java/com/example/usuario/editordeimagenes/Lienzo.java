package com.example.usuario.editordeimagenes;

import android.graphics.Canvas;

/**
 * Created by USUARIO on 25/06/2017.
 */

public class Lienzo {

    private static final Lienzo ourInstance = new Lienzo();

    private Canvas drawCanvas;

    public static Lienzo getInstance() {
        return ourInstance;
    }

    private Lienzo() {
    }

    public void setLienzo (Canvas drawCanvas){
        this.drawCanvas = drawCanvas;
    }
    public Canvas  getLienzo(){
          return this.drawCanvas;
    }
}
