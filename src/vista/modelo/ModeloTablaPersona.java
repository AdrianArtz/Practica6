/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.modelo;

import controlador.tda.grafos.GrafoDE;
import javax.swing.table.AbstractTableModel;
import modelo.Aereopuerto;

/**
 *
 * @author sebastian
 */
public class ModeloTablaPersona extends AbstractTableModel {

    private GrafoDE<Aereopuerto> grafo;

    public GrafoDE<Aereopuerto> getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoDE<Aereopuerto> grafo) {
        this.grafo = grafo;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return grafo.numVertices();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "Nombres";
            case 2:
                return "Ubicacion";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        try {
            Aereopuerto ae = grafo.obtenerEtiqueta(arg0 + 1);
            switch (arg1) {
                case 0:
                    return (arg0+1);
                case 1:
                    return ae.getNombre();
                case 2:
                    return (ae.getUbicacion() == null) ? "NO TIENE" : ae.getUbicacion().toString();
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error en listado");
            return null;
        }
    }

}
