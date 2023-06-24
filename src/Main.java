import controllers.ControlerEjemplar;
import controllers.ControlerPrestamo;
import controllers.ControlerSocios;
import model.*;
import statePrestamo.EstadoPrestamo;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        /*
            TODO hacer adapter de notificacion (coiar)
            TODO ver patron observer
            TODO hacer pruebas en el main

            AplicacionMensajeria aplicacionMensajeria = new AplicacionMensajeria(new WhatsApp(new AdaptadorWhatsApp()));
            aplicacionMensajeria.enviarNotificacion(new Notificacion("a@uade.edu.ar", "Hola!"));
            aplicacionMensajeria.setMedioNotificacion(new SMS(new AdaptadorTwilio()));
            aplicacionMensajeria.enviarNotificacion(new Notificacion("+5411123456789", "Hola!"));

            -- PRUEBAS --
        */
        ControlerSocios controlerSocios =ControlerSocios.getInstancia();
        ControlerEjemplar controlerEjemplar =ControlerEjemplar.getInstacia();
        ControlerPrestamo controlerPrestamo = ControlerPrestamo.getInstancia();

        controlerSocios.crearSocio(new Socio(44749039, "Juan", "Canestrari", "juanchicanestra@gmail.com", 1140646891, EnumMedioDeComunicacion.email));
        controlerEjemplar.crearEjemplar(new Libro(1L, "Pricipito", "Nose quien escribio el principito", 1223));
        controlerPrestamo.crearPrestamo(new Prestamo(10L, "", 10, , ,  ));

    }
}
