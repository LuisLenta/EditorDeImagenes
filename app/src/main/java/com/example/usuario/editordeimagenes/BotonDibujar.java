package com.example.usuario.editordeimagenes;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by USUARIO on 25/06/2017.
 */

public class BotonDibujar extends Boton {

    public BotonDibujar(ImageButton id, MainActivity mainActivity)
    {
        super(id,mainActivity);
    }



    public void clickeado()
    {
        final Dialog pincelDialog = new Dialog(mainActivity);
        pincelDialog.setTitle("Elija el tamaño del pincel:");
        pincelDialog.setContentView(R.layout.selector_pincel);

        ImageButton smallBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_pequeño);
        smallBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainActivity.getLienzoView().setBorrar(true);
                mainActivity.getLienzoView().setTamañoPincel(mainActivity.setTamañoPincel(1));
                pincelDialog.dismiss();
            }
        });
        ImageButton mediumBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_medio);
        mediumBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainActivity.getLienzoView().setBorrar(true);
                mainActivity.getLienzoView().setTamañoPincel(mainActivity.setTamañoPincel(2));


                pincelDialog.dismiss();
            }
        });
        ImageButton largeBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_grande);
        largeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainActivity.getLienzoView().setBorrar(true);
                mainActivity.getLienzoView().setTamañoPincel(mainActivity.setTamañoPincel(3));
                pincelDialog.dismiss();
            }
        });
        pincelDialog.show();


    }
}
