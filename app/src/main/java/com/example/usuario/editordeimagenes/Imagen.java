package com.example.usuario.editordeimagenes;

import android.graphics.Bitmap;

/**
 * Created by USUARIO on 24/06/2017.
 */

public class Imagen {

    private Bitmap imagen; // canvasBitmap

    public Imagen(int w, int h){

        imagen = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagen() {
        return imagen;
    }
}
