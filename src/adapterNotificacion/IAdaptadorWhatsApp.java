package adapterNotificacion;

import model.Notificacion;

public interface IAdaptadorWhatsApp {
    void enviarWhatsApp(Notificacion notificacion);
}
