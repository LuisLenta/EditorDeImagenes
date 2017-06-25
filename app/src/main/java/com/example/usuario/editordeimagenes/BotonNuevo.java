package com.example.usuario.editordeimagenes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ImageButton;

/**
 * Created by USUARIO on 25/06/2017.
 */

public class BotonNuevo extends Boton{


    public BotonNuevo(ImageButton id, MainActivity mainActivity)
    {
        super(id, mainActivity);
    }



    @Override
    public void clickeado()
    {
        AlertDialog.Builder nuevoDialog = new AlertDialog.Builder(mainActivity);
        nuevoDialog.setTitle("Nueva edición");
        nuevoDialog.setMessage("Empezar nueva edición (esta seguro)?");
        nuevoDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                mainActivity.getLienzoView().empezarNuevo(); //drawView.startNew();
                dialog.dismiss();
            }
        });
        nuevoDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        nuevoDialog.show();
    }
}
