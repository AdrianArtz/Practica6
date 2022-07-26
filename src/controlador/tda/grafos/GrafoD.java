package controlador.tda.grafos;

import controlador.tda.grafos.exception.VerticeException;
import controlador.tda.lista.ListaEnlazada;
import controlador.tda.lista.exception.PosicionException;
import controlador.tda.pila.Pila;

/**
 *
 * @author samaelhg
 */
public class GrafoD extends Grafo {

    protected Integer numV;
    protected Integer numA;
    protected ListaEnlazada<Adyacencia> listaAdyacente[];
    protected Integer[] visitas;
    protected int ordenVisita;

    public GrafoD(Integer numV) {
        this.numV = numV;
        this.numA = 0;
        listaAdyacente = new ListaEnlazada[numV + 1];
        for (int i = 0; i <= this.numV; i++) {
            listaAdyacente[i] = new ListaEnlazada<>();
        }
    }

    @Override
    public Integer numVertices() {
        return this.numV;
    }

    @Override
    public Integer numAristas() {
        return this.numA;
    }

    @Override
    public Object[] existeArista(Integer i, Integer f) throws VerticeException {
        Object[] resultado = {Boolean.FALSE, Double.NaN};
        if (i > 0 && f > 0 && i <= numV && f <= numV) {
            ListaEnlazada<Adyacencia> lista = listaAdyacente[i];
            for (int j = 0; j < lista.getSize(); j++) {
                try {
                    Adyacencia aux = lista.obtenerDato(j);
                    if (aux.getDestino().intValue() == f.intValue()) {
                        resultado[0] = Boolean.TRUE;
                        resultado[1] = aux.getPeso();
                        break;
                    }
                } catch (PosicionException ex) {

                }
            }
        } else {
            throw new VerticeException("Algun vertice ingresado no existe");
        }
        return resultado;
    }

    @Override
    public Double pesoArista(Integer i, Integer f) throws VerticeException {
        Double peso = Double.NaN;
        Object[] existe = existeArista(i, f);
        if (((Boolean) existe[0])) {
            peso = (Double) existe[i];
        }
        return peso;
    }

    @Override
    public void insertarArista(Integer i, Integer j) throws VerticeException {
        insertarArista(i, j, Double.NaN);
    }

    @Override
    public void insertarArista(Integer i, Integer j, Double peso) throws VerticeException {
        if (i > 0 && j > 0 && i <= numV && j <= numV) {
            Object[] existe = existeArista(i, j);
            if (!((Boolean) existe[0])) {
                numA++;
                listaAdyacente[i].insertarCabecera(new Adyacencia(j, peso));
            }
        } else {
            throw new VerticeException("Algun vertice ingresado no existe");
        }

    }

    @Override
    public ListaEnlazada<Adyacencia> adyacente(Integer i) throws VerticeException {
        return listaAdyacente[i];
    }

    public Integer[] Dijkstra(Integer inicio) throws PosicionException, Exception {
        Double[][] matrix = matrizDistancia();
        Integer[] distancia = new Integer[numV];
        Integer[] padre = new Integer[numV];
        Boolean[] visto = new Boolean[numV];
        for (int i = 0; i < numV; i++) {
            distancia[i] = 1200000000;
            padre[i] = -1;
            visto[i] = false;
        }
        distancia[inicio] = 0;
        Pila<Integer> pila = new Pila();
        pila.insertar(distancia[inicio]);
        while (!pila.estaVacia()) {
            int u = pila.eliminarDato(0);
            visto[u] = true;
            for (int i = 0; i < numV; i++) {
                if (matrix[u][i] != 0) {
// aqui es donde se debe tratar de editar para que nos incluya el parametro gas que es un arreglo de strings
                    if (distancia[i] > distancia[u] + matrix[u][i]) {
                        distancia[i] = distancia[u] + matrix[u][i].intValue();
                        padre[i] = u;
                        pila.insertar(i);
                    }
                }
            }
        }
        return distancia;
    }

    /*public ListaEnlazada<Integer> busquedaAnchura(Integer vertice) throws VerticeException, PosicionException, EstructuraDataVaciaExpetion {
        ListaEnlazada<Integer> recorridos = new ListaEnlazada<>();
        vistas[vertice - 1] = false;
        Cola<Integer> cola = new Cola(0);
        recorridos.insertar(vertice);
        cola.insertar(vertice);
        Integer inicial = vertice;
        while (!cola.estaVacia()) {
            Integer aux = cola.dequeue(0);
            ListaEnlazada<Adyacencia> adycencias = adyacente(inicial);
            for (int i = 0; i < adycencias.getSize(); i++) {
                Adyacencia ad = adycencias.obtenerDato(i);
                if (ad.getDestino() == 1 && !vistas[i]) {
                    cola.insertar(i);
                    recorridos.insertar(i);
                    vistas[i] = true;
                }
            }
        }
        System.out.println(recorridos.toString());
        return recorridos;
    }*/
 /*public ListaEnlazada<Integer> recorridoProfunidad(Integer vertice) {

        ListaEnlazada<Integer> recorridos = new ListaEnlazada();
        vistasP[vertice] = false;

        ListaEnlazada<Integer> cola = new ListaEnlazada();
        recorridos.insertar(vertice);
        cola.insertar(vertice);
        Integer inicial = vertice;
        while (!cola.estaVacia()) {
            int j = cola.eliminarDato(0);
            ListaEnlazada<Adyacencia> adycencias = adyacente(inicial);
            for (int i = 0; i < adycencias.getSize(); i++) {
                Adyacencia ad = adycencias.obtenerDato(i);
                if (ad.getDestino() == 1 && !vistasP[i]) {
                    cola.insertar(i);

                    recorridos.insertarCabecera(recorridoProfunidad(i));
                    vistasP[i] = true;
                }
            }
        }
        return recorridos;
    }*/

    public Double[][] matrizDistancia() throws Exception {
        //Matriz de Adyacencias
        Double[][] mDistancias = new Double[numV + 1][numV + 1];
        for (int i = 1; i <= numV; i++) {
            ListaEnlazada<Adyacencia> lista = listaAdyacente[i];
            for (int j = 1; j <= numV; j++) {
                if (i == j) {
                    mDistancias[i][j] = 0.0;
                } else if (lista.getSize() > 0) {
                    for (int k = 0; k < lista.getSize(); k++) {
                        try {
                            Adyacencia aux = lista.obtenerDato(k);
                            mDistancias[i][aux.getDestino()] = aux.getPeso();
                        } catch (PosicionException ex) {
                        }
                    }
                }
                if (mDistancias[i][j] == null) {
                    mDistancias[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        return mDistancias;
    }

    public Integer[] recorridoAnchura(Integer nodoInicial) throws PosicionException {
        Integer[] visitados = new Integer[numV];
        visitados[0] = nodoInicial;
        Integer cont = 1;
        int i = 0;
        boolean band = false;
        while (cont < numV) {
            i++;
            for (int j = 0; j < listaAdyacente[nodoInicial].getSize(); j++) {
                for (int k = 0; k < visitados.length; k++) {
                    if (visitados[k] == listaAdyacente[nodoInicial].obtenerDato(j).getDestino()) {
                        band = true;
                        break;
                    } else {
                        band = false;
                    }
                }
                if (!band) {
                    visitados[cont] = listaAdyacente[nodoInicial].obtenerDato(j).getDestino();
                    cont++;
                }
            }

            if (nodoInicial == numV) {
                nodoInicial = 0;
            } else if (cont == numV) {
                break;
            }
            nodoInicial++;
            if (i > numV) {
                break;
            }
        }
        return visitados;
    }

    public Integer[] recorridoProfundidad(Integer origen) throws Exception {
        Integer resultado[] = new Integer[numVertices() + 1];
        visitas = new Integer[numVertices() + 1];
        ordenVisita = 0;

        for (int i = 0; i <= numVertices(); i++) {
            visitas[i] = 0;
        }

        for (int i = 1; i <= numVertices(); i++) {
            if (visitas[i] == 0) {
                toArrayDFS(i, resultado);
            }
        }
        Integer[] vertice = resultado;
        for (int j = 0; j < resultado.length; j++) {
            for (int k = 0; k < vertice.length; k++) {
                if (k != j) {
                    if (resultado[k] == vertice[j]) {
                        for (int i = k; i < vertice.length - 1; i++) {
                            resultado[i] = vertice[i + 1];

                        }
                    }
                }
            }
        }

        return resultado;
    }

    public void toArrayDFS(Integer origen, Integer resultado[]) throws Exception {
        resultado[ordenVisita] = origen;
        visitas[origen] = ordenVisita++;
        ListaEnlazada<Adyacencia> lista = listaAdyacente[origen];
        for (int i = 0; i < lista.getSize(); i++) {
            Adyacencia ad;
            if (lista.comprobarDato(i)) {
                break;
            } else {
                ad = lista.obtenerDato(i);
            }

            lista.setCabecera(lista.getCabecera().getSiguiente());
            if (visitas[ad.getDestino()] == 0) {
                toArrayDFS(ad.getDestino(), resultado);
            }
        }
    }

    

}
