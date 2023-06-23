package adapterNotificacion;

import model.Notificacion;

public interface IAdaptadorEmail {
    void enviarEmail(Notificacion notificacion);
}
