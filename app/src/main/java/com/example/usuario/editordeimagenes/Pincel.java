package com.example.usuario.editordeimagenes;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;


/**
 * Created by USUARIO on 24/06/2017.
 */

public class Pincel implements  Observador {

    private Path dibujarCamino;

    private Paint pincel, pincelCanvas;
    private int pincelColor = Color.WHITE;

    private float tamañoPincel =20,ultimoTamañoPincel;

    public Pincel(){



        dibujarCamino = new Path();
        pincel = new Paint();
        pincel.setColor(pincelColor);
        pincel.setAntiAlias(true);
        pincel.setStrokeWidth(tamañoPincel);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeJoin(Paint.Join.ROUND);
        pincel.setStrokeCap(Paint.Cap.ROUND);

        pincelCanvas = new Paint(Paint.DITHER_FLAG);

    }

    public void update(String nuevoColor){
                this.pincel.setColor(Color.parseColor(nuevoColor));
    }

    public void setDibujarCamino(Path dibujarCamino) {
        this.dibujarCamino = dibujarCamino;
    }

    public void setPincel(Paint pincel) {
        this.pincel = pincel;
    }

    public void setPincelCanvas(Paint pincelCanvas) {
        this.pincelCanvas = pincelCanvas;
    }

    public void setPincelColor(int pincelColor) {
        this.pincel.setColor(pincelColor);
    }

    public void setTamañoPincel(float tamañoPincel) {
        this.tamañoPincel=tamañoPincel;
        this.pincel.setStrokeWidth(tamañoPincel);
    }

    public void setUltimoTamañoPincel(float ultimoTamañoPincel) {
        this.ultimoTamañoPincel = ultimoTamañoPincel;
    }

    public float getUltimoTamañoPincel() {
        return ultimoTamañoPincel;
    }

    public Paint getPincel() {
        return pincel;
    }

    public Paint getPincelCanvas() {
        return pincelCanvas;
    }

    public Path getDibujarCamino() {
        return dibujarCamino;
    }

    public int getPincelColor() {
        return pincelColor;
    }
}
