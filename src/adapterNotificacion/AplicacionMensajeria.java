package adapterNotificacion;

import model.Notificacion;

public class AplicacionMensajeria {
    private IMedioNotificacion medioNotificacion;

    public AplicacionMensajeria(IMedioNotificacion medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }

    public void setMedioNotificacion(IMedioNotificacion medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.medioNotificacion.enviarNotificacion(notificacion);
    }
}