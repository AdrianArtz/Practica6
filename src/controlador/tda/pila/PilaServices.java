/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.tda.pila;

import controlador.tda.grafos.exception.EstructuraDataVaciaExpetion;
import controlador.tda.lista.exception.PosicionException;
import controlador.tda.lista.exception.TopeException;

/**
 *
 * @author sebastian
 */
public class PilaServices <E> {
    private Pila<E> pila;

    public PilaServices(Integer tope) {
        if(tope == 0)
            pila = new Pila<>();
        else
            pila = new Pila<>(tope);
    }
    public boolean push(E dato) {
        try {
            pila.push(dato);
            return true;
        } catch (TopeException e) {
            System.out.println("Error "+e);
        }
        return false;
    }
    
    public E pop(Integer pos) {
        try {
            return pila.pop(pos);
            
        } catch (EstructuraDataVaciaExpetion | PosicionException e) {
            System.out.println("Error "+e);
        }
        return null;
    }
    
    public Integer getSize() {
        return pila.getSize();
    }
    
    public Integer getTope() {
        return pila.getTope();
    }
    
}
