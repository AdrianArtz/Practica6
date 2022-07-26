package modelo;

// @author samaelhg
public class Aereopuerto {

    private String nombre;
    private Ubicacion ubicacion;

    public Aereopuerto(){
        
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Aereopuerto: "+nombre+
              "\nLatitud:    "+ubicacion.getLatitud()+
              "\nLongitud:   "+ubicacion.getLongitud();
    }
    
    
}
