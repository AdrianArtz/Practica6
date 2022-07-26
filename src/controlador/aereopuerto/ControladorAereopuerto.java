package controlador.aereopuerto;

// @author samaelhg
import controlador.tda.grafos.GrafoDE;
import modelo.Aereopuerto;
import modelo.Ubicacion;

public class ControladorAereopuerto {

    private GrafoDE<Aereopuerto> grafoAereopuerto;
    private Aereopuerto aereopuerto;

    public ControladorAereopuerto(Integer numVertices) {
        grafoAereopuerto = new GrafoDE<>(numVertices, Aereopuerto.class);
        for (int i = 1; i <= numVertices; i++) {
            Aereopuerto ae = new Aereopuerto();
            ae.setNombre("Aereopuerto: " + i);
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(0.0);
            ubicacion.setLongitud(0.0);
            ae.setUbicacion(ubicacion);
            grafoAereopuerto.etiquetarVertice(i, ae);
        }
    }

    public GrafoDE<Aereopuerto> getGrafoAereopuerto() {
        return grafoAereopuerto;
    }

    public void setGend(GrafoDE<Aereopuerto> getGrafoAereopuerto) {
        this.grafoAereopuerto = getGrafoAereopuerto;
    }

    public Aereopuerto getAereopuerto() {
        if (aereopuerto == null) {
            aereopuerto = new Aereopuerto();
        }
        return aereopuerto;
    }

    public void setPersona(Aereopuerto aereopuerto) {
        this.aereopuerto = aereopuerto;
    }

    public Double calcularDistancia(Aereopuerto a1, Aereopuerto a2) {
        // raiz cuadrara ((x1 - x2)elevado al cuadradro + (y1 - y2)elevado al cuadradro)
        Double dis = 0.0;
        Double x = a1.getUbicacion().getLongitud() - a2.getUbicacion().getLongitud();
        Double y = a1.getUbicacion().getLatitud() - a2.getUbicacion().getLatitud();
        dis = Math.sqrt((x * x) + (y * y));
        return dis;
    }
    
    
}
