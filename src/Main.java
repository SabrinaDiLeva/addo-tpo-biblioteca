import controllers.ControlerEjemplar;
import controllers.ControlerPrestamo;
import controllers.ControlerSocios;
import model.*;
import statePrestamo.EnCurso;
import statePrestamo.EstadoPrestamo;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        /*
           ALCANCE:
           •Cargar nuevos socios. (HECHO)
           •Cargar ejemplares (libros, revistas especializadas, revistas, diarios). (HECHO)
           •Buscar ejemplares según el criterio solicitadoy mostrar datos completos (incluyendo ubicación).
           •Actualizar el estado de ejemplares prestados presencialmente o vía web (préstamo y devolución).
           •Actualizar parámetros de préstamos (plazo en días).
           •Notificar a socios acerca de situaciones particulares (próximo vencimiento, penalizaciónpor devolución fuera de término, premio por devoluciones).
           •Visualizarel historial de préstamos de un socio. (HECHO)

            -- PRUEBAS --
        */

        // -- CONTROLERS
        ControlerSocios controlerSocios = ControlerSocios.getInstancia();
        ControlerEjemplar controlerEjemplar = ControlerEjemplar.getInstacia();
        ControlerPrestamo controlerPrestamo = ControlerPrestamo.getInstancia();

        // CARGAR
        controlerSocios.crearSocio(new Socio(44749039, "Juan", "Canestrari", "juanchicanestra@gmail.com", 1140646891, EnumMedioDeComunicacion.email));

        // CARGAR EJEMPLARES

        //      LIBRO
        controlerEjemplar.crearEjemplar(new Libro(1L, "Pricipito", "Antoine de Saint-Exupéry", 1223));

        //      REVISTA
        controlerEjemplar.crearEjemplar(new Revista(2L, "HOLA!", "Antonio Sánchez Gómez ", 2017));

        //      REVISTA ESPECIALIZADA
        controlerEjemplar.crearEjemplar(new RevistaEspecializada(3L, "Todo sobre autos modernos", "Alain Prost", 1980));

        //      DIARIO
        controlerEjemplar.crearEjemplar(new Diario(4L, "Clarin", "Leopoldo Alas", 2023));

        // BUSCAR EJEMPLARES SEGUN:

        // CARGA DE PRESTAMOS
        controlerPrestamo.crearPrestamo(new Prestamo(10L, LocalDate.now(), 10, null,  controlerSocios.buscarSocio(44749039), controlerEjemplar.buscarEjemplar(1L)));

        // ACTUALIZAR ESTADOS:
        // ABRIA QUE BUSCAR UN METODO EN EL CONTROLER QUE ACTUALIZE


        // ACTAULIZAR PARAMETROS DE PRESTAMOS (DIAS)
        // ABRIA UN METODO EN EL CONTROLER DE PRESTAMOS QUE CAMBIE SOLO LA DURACION

        // NOTIFICAR SOBRE SITUACIONES PARTICULAES
        // YYY ESTO VA DE LA MANO DE LOS ESTADOS


        // VISUALIZAR HISTORIAL DE PRESTAMOS DE UN SOCIO

        controlerSocios.verHistorialSocio(44749039);
        /*
        * SALTA ERROR:
        *
        * A LO MEJOR NOS COMBIENE BUSCAR EN LA LISTA DE PRESTAMOS SOBRE UN SOCIO EN ESPECIFICO
        * PARA NO TENER QUE AGREGAR LOS PRESTAMOS EN EL SOCIO
        *
        * */
    }
}
