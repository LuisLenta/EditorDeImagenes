package com.example.usuario.editordeimagenes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imagenGaleria;

    private LienzoView lienzoView;//DrawingView drawView;

    private PaletaColores paletaColores;

    private ImageButton currPaint, botonDibujar,  botonGuardar, botonCargar ; //botonNuevo, botonBorrar,

    private Boton botonNuevo,botonBorrar;

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

        //currPaint = (ImageButton)paintLayout.getChildAt(0);
        //currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        pincelPequeño = getResources().getInteger(R.integer.tamaño_pequeño);
        pincelMediano = getResources().getInteger(R.integer.tamaño_medio);
        pincelGrande = getResources().getInteger(R.integer.tamaño_grande);

        botonDibujar = (ImageButton)findViewById(R.id.boton_dibujar);
        botonDibujar.setOnClickListener(this);


        botonBorrar= new BotonBorrar((ImageButton) findViewById(R.id.boton_borrar),this);
        //botonBorrar = (ImageButton)findViewById(R.id.boton_borrar);  // eraseBtn = (ImageButton)findViewById(R.id.erase_btn);
        //botonBorrar.setOnClickListener(this);                        //eraseBtn.setOnClickListener(this);


         botonNuevo=new BotonNuevo((ImageButton) findViewById(R.id.boton_nuevo),this);
        //botonNuevo = (ImageButton)findViewById(R.id.boton_nuevo);
        //botonNuevo.setOnClickListener(this);

        botonGuardar = (ImageButton)findViewById(R.id.boton_guardar); //saveBtn = (ImageButton)findViewById(R.id.save_btn);
        botonGuardar.setOnClickListener(this); //saveBtn.setOnClickListener(this);

        botonCargar = (ImageButton)findViewById(R.id.boton_cargar);
        botonCargar.setOnClickListener(this);

        lienzoView.setTamañoPincel(pincelMediano);
    }

    public void pintarClicked(View view){
        //use chosen color
       // if(view!=currPaint){
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
            //draw button clicked
            final Dialog pincelDialog = new Dialog(this);
            pincelDialog.setTitle("Tamaño de pincel:");
            pincelDialog.setContentView(R.layout.selector_pincel);

            ImageButton smallBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_pequeño);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    lienzoView.setTamañoPincel(pincelPequeño);    //drawView.setBrushSize(smallBrush);
                   // lienzoView.setUltimoTamañoPincel(pincelPequeño);  //drawView.setLastBrushSize(smallBrush);
                    lienzoView.setBorrar(false); //drawView.setErase(false);
                    pincelDialog.dismiss();
                }
            });

            ImageButton mediumBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_medio);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    lienzoView.setTamañoPincel(pincelMediano); //drawView.setBrushSize(mediumBrush);
                   // lienzoView.setUltimoTamañoPincel(pincelMediano); //drawView.setLastBrushSize(mediumBrush);
                    lienzoView.setBorrar(false); //drawView.setErase(false);
                    pincelDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_grande);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    lienzoView.setTamañoPincel(pincelGrande); //drawView.setBrushSize(largeBrush);
                    //lienzoView.setUltimoTamañoPincel(pincelGrande); //drawView.setLastBrushSize(largeBrush);
                    lienzoView.setBorrar(false); //drawView.setErase(false);
                    pincelDialog.dismiss();
                }
            });
            pincelDialog.show();
        }

        else if(view.getId()==R.id.boton_borrar){
            //switch to erase - choose size
           /* final Dialog pincelDialog = new Dialog(this);
            pincelDialog.setTitle("Tamaño borrar:");
            pincelDialog.setContentView(R.layout.selector_pincel);

            ImageButton smallBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_pequeño);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    lienzoView.setBorrar(true);  //drawView.setErase(true);
                    lienzoView.setTamañoPincel(pincelPequeño); //drawView.setBrushSize(smallBrush);
                    pincelDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_medio);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    lienzoView.setBorrar(true); //drawView.setErase(true);
                    lienzoView.setTamañoPincel(pincelMediano); //drawView.setBrushSize(mediumBrush);
                    pincelDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton)pincelDialog.findViewById(R.id.pincel_grande);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    lienzoView.setBorrar(true); //drawView.setErase(true);
                    lienzoView.setTamañoPincel(pincelGrande); //drawView.setBrushSize(largeBrush);
                    pincelDialog.dismiss();
                }
            });
            pincelDialog.show();*/
            botonBorrar.clickeado();
        }

        else if(view.getId()==R.id.boton_nuevo){
            //new button
          /*  AlertDialog.Builder nuevoDialog = new AlertDialog.Builder(this);
            nuevoDialog.setTitle("Nueva edición");
            nuevoDialog.setMessage("Empezar nueva edición (esta seguro)?");
            nuevoDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    lienzoView.empezarNuevo(); //drawView.startNew();
                    dialog.dismiss();
                }
            });
            nuevoDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            nuevoDialog.show();*/
            botonNuevo.clickeado();
        }


        else if(view.getId()==R.id.boton_guardar){
            //save drawing
            AlertDialog.Builder guardarDialog = new AlertDialog.Builder(this);
            guardarDialog.setTitle("Guardar ediciones");
            guardarDialog.setMessage("Guardar en la Gallery?");
            guardarDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //save drawing
                    lienzoView.setDrawingCacheEnabled(true);  //drawView.setDrawingCacheEnabled(true);
                    String imgSaved = MediaStore.Images.Media.insertImage(
                            getContentResolver(), lienzoView.getDrawingCache(),
                            UUID.randomUUID().toString()+".png", "drawing");

                    if(imgSaved!=null){
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                        savedToast.show();
                    }
                    else{
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }

                    lienzoView.destroyDrawingCache(); //drawView.destroyDrawingCache();
                }
            });
            guardarDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            guardarDialog.show();
        }
        else if (view.getId()==R.id.boton_cargar){


            final CharSequence[] option = {"Elegir de galeria", "Cancelar"};
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

            builder.show();
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
