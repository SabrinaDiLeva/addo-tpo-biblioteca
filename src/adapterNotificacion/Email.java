package adapterNotificacion;

import model.Notificacion;

public class Email implements IMedioNotificacion{
    private IAdaptadorEmail adaptador;
    public Email(IAdaptadorEmail adaptador) {
        this.adaptador = adaptador;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        this.adaptador.enviarEmail(notificacion);
    }
}
