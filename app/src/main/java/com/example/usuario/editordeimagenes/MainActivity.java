package com.example.usuario.editordeimagenes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.AlertDialog;

import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imagenGaleria;

    private LienzoView lienzoView;//DrawingView drawView;

    private PaletaColores paletaColores;

    //private ImageButton    botonCargar ; //botonNuevo, botonBorrar,botonDibujar,botonGuardar,

    private Boton botonNuevo,botonBorrar, botonDibujar,botonGuardar,botonCargar;

    private float pincelPequeño, pincelMediano, pincelGrande;

    private final int SELECT_PICTURE = 300;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lienzoView = (LienzoView)findViewById(R.id.lienzo);

        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paleta_colores);
        paletaColores = new PaletaColores((ImageButton)paintLayout.getChildAt(0), getResources().getDrawable(R.drawable.paint_pressed));
        paletaColores.registarObservador(lienzoView.getPincel());


        pincelPequeño = getResources().getInteger(R.integer.tamaño_pequeño);
        pincelMediano = getResources().getInteger(R.integer.tamaño_medio);
        pincelGrande = getResources().getInteger(R.integer.tamaño_grande);


        botonDibujar=new BotonDibujar((ImageButton) findViewById(R.id.boton_dibujar),this);
        botonBorrar= new BotonBorrar((ImageButton) findViewById(R.id.boton_borrar),this);
        botonNuevo=new BotonNuevo((ImageButton) findViewById(R.id.boton_nuevo),this);
        botonGuardar=new BotonGuardar((ImageButton)findViewById(R.id.boton_guardar),this);
        botonCargar=new BotonCargar((ImageButton)findViewById(R.id.boton_cargar),this);

        //botonCargar = (ImageButton)findViewById(R.id.boton_cargar);
        //botonCargar.setOnClickListener(this);

        lienzoView.setTamañoPincel(pincelMediano);
    }

    public void pintarClicked(View view){

        if((view)!= paletaColores.getColorSeleccionado()){
            //update color

            ImageButton imgView = (ImageButton)view;
            //String color = view.getTag().toString();
            // lienzoView.setColor(color);

            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            paletaColores.setSeleccionado(getResources().getDrawable(R.drawable.paint));
            //currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            //currPaint=(ImageButton)view;
            paletaColores.setColorSeleccionado((ImageButton)view);
            paletaColores.cambiarColorPincel();
        }
    }



            @Override
            public void onClick(View view){
        //respond to clicks
        if(view.getId()==R.id.boton_dibujar){
            botonDibujar.clickeado();
        }
        else if(view.getId()==R.id.boton_borrar){
            botonBorrar.clickeado();
        }

        else if(view.getId()==R.id.boton_nuevo){
            botonNuevo.clickeado();
        }


        else if(view.getId()==R.id.boton_guardar){
            botonGuardar.clickeado();
        }
        else if (view.getId()==R.id.boton_cargar){


            /*final CharSequence[] option = {"Elegir de galeria", "Cancelar"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Elige una opción");
            builder.setItems(option, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   if(option[which] == "Elegir de galeria"){
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/.png");
                       intent.setType("image/.jpeg");
                       startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                   }else {
                        dialog.dismiss();
                    }
                }
            });

            builder.show();*/
            botonCargar.clickeado();
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Uri path = data.getData();


            this.imagenGaleria.setImageURI(path);

            BitmapDrawable drawable = (BitmapDrawable) imagenGaleria.getDrawable();

            this.lienzoView.setImagenLienzo(drawable);
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Uri selectedImage = data.getData();
            InputStream is;

            try {
                is = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bis = new BufferedInputStream(is);
                Bitmap bitmap = BitmapFactory.decodeStream(bis);
                lienzoView.setImagenLienzo(bitmap);
            } catch (FileNotFoundException e) {}
        }
    }

    public LienzoView getLienzoView()
    {
        return lienzoView;
    }

    public float setTamañoPincel(int tam)
    {
        if(tam==1)
            return pincelPequeño;
        else if(tam==2)
        return pincelMediano;
    else if(tam==3)
        return pincelGrande;
    else
        return pincelMediano;
    }
}
