package adapterUbicacion;

import adapterNotificacion.WhatsAppLib;

public class Ubicacion implements IUbicacion {

    private String ubicacion;
    private UbicacionAPI api;

    public Ubicacion(){
        this.api = new UbicacionAPI();
    }
    public Ubicacion(String ubicacion) {
        this.ubicacion = ubicacion;
        this.api = new UbicacionAPI();
    }

    public void buscarUbicacion(Long id) {
        this.api.buscarUbicacion(id);
    }
}
