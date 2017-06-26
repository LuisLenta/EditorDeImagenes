package com.example.usuario.editordeimagenes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by USUARIO on 25/06/2017.
 */

public class BotonGuardar extends Boton {

    public BotonGuardar(ImageButton id, MainActivity mainActivity)
    {
        super(id,mainActivity);
    }


    @Override
    public void clickeado()
    {

        AlertDialog.Builder guardarDialog = new AlertDialog.Builder(mainActivity);
        guardarDialog.setTitle("Guardar ediciones");
        guardarDialog.setMessage("Desea guardar en la Galeria?");
        guardarDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                mainActivity.getLienzoView().setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(
                        mainActivity.getContentResolver(), mainActivity.getLienzoView().getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");

                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(mainActivity.getApplicationContext(),
                            "La imagen se ha guardado con exito!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(mainActivity.getApplicationContext(),
                            "Opps! La imagen no se guardo!", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }

                mainActivity.getLienzoView().destroyDrawingCache();
            }
        });
        guardarDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        guardarDialog.show();

    }
}
