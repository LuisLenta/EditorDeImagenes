package com.example.usuario.editordeimagenes;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by USUARIO on 24/06/2017.
 */

public class PaletaColores  implements Subject {

    private ImageButton colorSeleccionado;
    private ArrayList<Observador> observadores;

    public PaletaColores(ImageButton colorSeleccionado,Drawable drawable){

        this.colorSeleccionado = colorSeleccionado;
        colorSeleccionado.setImageDrawable(drawable);
        observadores = new ArrayList<Observador>();
    }

    public void setColorSeleccionado(ImageButton colorSeleccionado) {
        this.colorSeleccionado = colorSeleccionado;
    }

    public ImageButton getColorSeleccionado() {
        return colorSeleccionado;
    }
    public void setSeleccionado(Drawable drawable){
        colorSeleccionado.setImageDrawable(drawable);

    }
    public void registarObservador(Observador observador){
            observadores.add(observador);
    }

    public void borrarObservador(Observador observador){
            observadores.remove(observador);
    }

    public void notificarObservadores(){
            for (int i=0; i<observadores.size(); i++){
                Observador observador = observadores.get(i);
                observador.update(colorSeleccionado.getTag().toString());
            }
    }
    public void cambiarColorPincel(){
        this.notificarObservadores();
    }



}
