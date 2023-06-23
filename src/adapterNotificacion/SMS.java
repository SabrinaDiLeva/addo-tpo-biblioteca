package adapterNotificacion;

import model.Notificacion;

public class SMS implements IMedioNotificacion{
    private IAdaptadorSMS adaptador;

    public SMS(IAdaptadorSMS adaptador) {
        this.adaptador = adaptador;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        this.adaptador.enviarSMS(notificacion);
    }
}
