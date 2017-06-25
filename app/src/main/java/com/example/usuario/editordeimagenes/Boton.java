package com.example.usuario.editordeimagenes;

import android.widget.ImageButton;

/**
 * Created by USUARIO on 25/06/2017.
 */

public abstract class Boton {

        MainActivity mainActivity;
        ImageButton imgButton;

        public Boton(ImageButton id, MainActivity mainActivity)
        {
            this.imgButton=id;
            this.mainActivity=mainActivity;
            setOCL();
        }

        //public void set
        private void setOCL()
        {
            imgButton.setOnClickListener(mainActivity);
        }

        abstract void clickeado();
    }

