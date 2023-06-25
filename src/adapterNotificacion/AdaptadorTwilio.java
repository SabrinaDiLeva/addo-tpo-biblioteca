package adapterNotificacion;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import model.Notificacion;

import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;

public class AdaptadorTwilio implements IAdaptadorWhatsApp, IAdaptadorSMS {
    public AdaptadorTwilio() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void enviarSMS(Notificacion notificacion) {
        Message
                .creator(
                        new PhoneNumber(Integer.toString(notificacion.getPrestamo().getSocio().getTelefono())),
                        new PhoneNumber("+15017250604"),
                        notificacion.getMensajePredefinido()
                )
                .create();
    }

    public void enviarWhatsApp(Notificacion notificacion) {
        Message.creator(
                        new com.twilio.type.PhoneNumber(String.format("whatsapp:%s", Integer.toString(notificacion.getPrestamo().getSocio().getTelefono()))),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        notificacion.getMensajePredefinido())
                .create();
    }
}
