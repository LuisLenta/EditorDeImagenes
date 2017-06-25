package com.example.usuario.editordeimagenes;

import java.util.Observer;

/**
 * Created by USUARIO on 24/06/2017.
 */

public interface Subject {

    public void registarObservador(Observador observador);
    public void borrarObservador(Observador observador);
    public void notificarObservadores();

}
