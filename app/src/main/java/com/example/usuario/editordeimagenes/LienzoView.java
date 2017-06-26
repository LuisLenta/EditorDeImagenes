package com.example.usuario.editordeimagenes;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.MotionEvent;


/**
 * Created by USUARIO on 24/06/2017.
 */

public class LienzoView extends View {

    private Pincel pincel;

    private Imagen imagen;

    private Canvas drawCanvas;



    private boolean borrar=false;

    int w;
    int h;


    public LienzoView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setupLienzo();
    }

    public void setupLienzo(){


        pincel = new Pincel();
        pincel.setTamañoPincel(getResources().getInteger(R.integer.tamaño_medio));
        pincel.setUltimoTamañoPincel(getResources().getInteger(R.integer.tamaño_medio));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        this.w=w;
        this.h=h;
        imagen = new Imagen(w,h);
        drawCanvas = new Canvas(imagen.getImagen());


    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(imagen.getImagen(), 0, 0, pincel.getPincelCanvas());
        canvas.drawPath(pincel.getDibujarCamino(), pincel.getPincel());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pincel.getDibujarCamino().moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                pincel.getDibujarCamino().lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(pincel.getDibujarCamino(), pincel.getPincel());
                pincel.getDibujarCamino().reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor(String newColor){

        invalidate();
        pincel.setPincelColor(Color.parseColor(newColor));

    }

    public void setTamañoPincel(float nuevoTamaño){

        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                nuevoTamaño, getResources().getDisplayMetrics());
        pincel.setTamañoPincel(pixelAmount);


    }
    public void setBorrar(boolean isBorrar){

        borrar = isBorrar;
        if(borrar) pincel.setPincelColor(Color.WHITE);
        else pincel.setPincelColor(Color.RED);

    }

    public void empezarNuevo(){

        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public Pincel getPincel() {
        return pincel;
    }

   public void setImagenLienzo(Bitmap nuevaImagen) {

       this.imagen=new Imagen(w,h);
       this.imagen.setImagen(nuevaImagen);
       drawCanvas.setBitmap(imagen.getImagen());


   }


}
