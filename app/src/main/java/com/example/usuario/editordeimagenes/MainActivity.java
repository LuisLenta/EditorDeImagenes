package com.example.usuario.editordeimagenes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LienzoView lienzoView;//DrawingView drawView;

    private ImageButton currPaint, botonDibujar, botonBorrar, botonNuevo, botonGuardar ;

    private float pincelPequeño, pincelMediano, pincelGrande;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lienzoView = (LienzoView)findViewById(R.id.lienzo);

        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paleta_colores);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        pincelPequeño = getResources().getInteger(R.integer.tamaño_pequeño);
        pincelMediano = getResources().getInteger(R.integer.tamaño_medio);
        pincelGrande = getResources().getInteger(R.integer.tamaño_grande);

        botonDibujar = (ImageButton)findViewById(R.id.boton_dibujar);
        botonDibujar.setOnClickListener(this);

        botonBorrar = (ImageButton)findViewById(R.id.boton_borrar);  // eraseBtn = (ImageButton)findViewById(R.id.erase_btn);
        botonBorrar.setOnClickListener(this);                        //eraseBtn.setOnClickListener(this);

        botonNuevo = (ImageButton)findViewById(R.id.boton_nuevo);
        botonNuevo.setOnClickListener(this);

        botonGuardar = (ImageButton)findViewById(R.id.boton_guardar); //saveBtn = (ImageButton)findViewById(R.id.save_btn);
        botonGuardar.setOnClickListener(this); //saveBtn.setOnClickListener(this);


        lienzoView.setTamañoPincel(pincelMediano);
    }

    public void pintarClicked(View view){
        //use chosen color
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            lienzoView.setColor(color);

            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;
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
            final Dialog pincelDialog = new Dialog(this);
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
            pincelDialog.show();
        }

        else if(view.getId()==R.id.boton_nuevo){
            //new button
            AlertDialog.Builder nuevoDialog = new AlertDialog.Builder(this);
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
            nuevoDialog.show();
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
    }
}
