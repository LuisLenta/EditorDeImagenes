package com.example.usuario.editordeimagenes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by USUARIO on 25/06/2017.
 */

public class BotonNuevo extends Activity implements View.OnClickListener {

    private ImageButton botonNuevo;

    //public BotonNuevo (View view, final LienzoView lienzoView){
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        botonNuevo.setOnClickListener(this);

    }
    public void onClick(View view) {

    }

    public void setBotonNuevo(ImageButton botonNuevo) {
        this.botonNuevo = botonNuevo;
    }

    }
