package adapterNotificacion;

public class WhatsAppLib {
    public final void enviarMensaje(String phone, String message) {
        System.out.printf("Mandando a telefono %s el mensaje: %s\n", phone, message);
    }
}
