package com.example.usuario.editordeimagenes;

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
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);
        imagen = new Imagen(w,h);
        drawCanvas = new Canvas(imagen.getImagen());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(imagen.getImagen(), 0, 0, pincel.getPincelCanvas());
        canvas.drawPath(pincel.getDibujarCamino(), pincel.getPincel());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
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
        //set color
        invalidate();
        pincel.setPincelColor(Color.parseColor(newColor));

    }

    public void setTamañoPincel(float nuevoTamaño){
        //update size
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                nuevoTamaño, getResources().getDisplayMetrics());
        pincel.setTamañoPincel(pixelAmount);

        //brushSize=pixelAmount;
        //drawPaint.setStrokeWidth(brushSize);
    }

    public void setUltimoTamañoPincel(float ultimoTamaño){
        setUltimoTamañoPincel(ultimoTamaño);
    }
    public float getUltimoTamañoPincel(){
        return pincel.getUltimoTamañoPincel();
    }

    public void setBorrar(boolean isBorrar){
        //set erase true or false
        borrar = isBorrar;
        if(borrar) pincel.setPincelColor(Color.WHITE); //drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else pincel.setPincelColor(Color.RED);
        // drawPaint.setXfermode(null);
    }

    public void empezarNuevo(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

}
