package adapterNotificacion;

import model.Notificacion;

public interface IMedioNotificacion {
    void enviarNotificacion(Notificacion notificacion);
}
