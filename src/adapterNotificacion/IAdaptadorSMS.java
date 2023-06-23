package adapterNotificacion;

import model.Notificacion;

public interface IAdaptadorSMS {
    void enviarSMS(Notificacion notificacion);
}
