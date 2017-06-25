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
        //save drawing
        AlertDialog.Builder guardarDialog = new AlertDialog.Builder(mainActivity);
        guardarDialog.setTitle("Guardar ediciones");
        guardarDialog.setMessage("Guardar en la Gallery?");
        guardarDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //save drawing
                mainActivity.getLienzoView().setDrawingCacheEnabled(true);  //drawView.setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(
                        mainActivity.getContentResolver(), mainActivity.getLienzoView().getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");

                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(mainActivity.getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(mainActivity.getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }

                mainActivity.getLienzoView().destroyDrawingCache(); //drawView.destroyDrawingCache();
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
