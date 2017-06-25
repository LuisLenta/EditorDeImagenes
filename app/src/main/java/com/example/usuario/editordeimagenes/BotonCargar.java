package com.example.usuario.editordeimagenes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ImageButton;

/**
 * Created by USUARIO on 25/06/2017.
 */

public class BotonCargar extends Boton {

    private final int SELECT_PICTURE = 300;

    public BotonCargar(ImageButton id, MainActivity mainActivity)
    {
        super(id,mainActivity);
    }



    @Override
    void clickeado()
    {
        final CharSequence[] option = {"Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Elige una opciÃ³n");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/.png");
                    intent.setType("image/.jpeg");
                    mainActivity.startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                }else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();

    }
}
