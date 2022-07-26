/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.tda.pila;

import controlador.tda.lista.ListaEnlazada;
import controlador.tda.lista.NodoLista;
import controlador.tda.grafos.exception.EstructuraDataVaciaExpetion;
import controlador.tda.lista.exception.PosicionException;
import controlador.tda.lista.exception.TopeException;

/**
 *
 * @author sebastian
 */
public class Pila <E> extends ListaEnlazada<E> {
    private Integer tope;

    public Pila(Integer tope) {
        this.tope = tope;
    }

    public Pila() {
        this.tope = 0;
    }
    
    public Boolean estaLleno() {
        if(tope == 0)
            return false;
        else if(tope == getSize().intValue())
            return true;
        else
            return false;
    }
    
    public void push(E dato) throws TopeException {
        if(!estaLleno()) {
            insertarCabecera(dato);
        } else 
            throw new TopeException("La pila esta llena");
    }
    
    public E pop(Integer pos) throws EstructuraDataVaciaExpetion, PosicionException {
        E auxdato =  null;
        if(!estaVacia()) {
            if (pos >= 0 && pos < getSize()) {
                for(int i = 0; i <= pos; i++) {
                    auxdato = eliminarDato(0);
                }
                return auxdato;
                //4   7   1   6


            } else {
                throw new PosicionException("Error al realizar la operacion POP No existe la posicion dada");
            }
        } else
            throw new EstructuraDataVaciaExpetion("La pila esta vacia");
    }

    public Integer getTope() {
        return tope;
    }   
    
    
}
