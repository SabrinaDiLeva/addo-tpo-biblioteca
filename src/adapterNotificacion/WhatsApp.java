package adapterNotificacion;

import model.Notificacion;

public class WhatsApp implements IMedioNotificacion{
    private IAdaptadorWhatsApp adaptador;

    public WhatsApp(IAdaptadorWhatsApp adaptador) {
        this.adaptador = adaptador;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        this.adaptador.enviarWhatsApp(notificacion);
    }
}