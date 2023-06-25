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
        System.out.println("Carga de SOCIO:");
        controlerSocios.crearSocio(44749039, "Juan", "Canestrari", "juanchicanestra@gmail.com", 1140646891, EnumMedioDeComunicacion.email);

        System.out.println();
        System.out.println("-----------------------------------------");
        // CARGAR EJEMPLARES
        System.out.println();
        System.out.println("Carga de EJEMPLAR:");
        //      LIBRO
        controlerEjemplar.crearEjemplar(1L, "Pricipito", "Antoine de Saint-Exupery", 1223, EnumCategoriaEjemplar.Libro);

        //      REVISTA
        controlerEjemplar.crearEjemplar(2L, "HOLA!", "Antonio Sanchez Gomez", 2017, EnumCategoriaEjemplar.Revista);

        //      REVISTA ESPECIALIZADA
        controlerEjemplar.crearEjemplar(3L, "Todo sobre autos modernos", "Alain Prost", 1980, EnumCategoriaEjemplar.RevistaEspecializada);

        //      DIARIO
        controlerEjemplar.crearEjemplar(4L, "Clarin", "Leopoldo Alas", 2023, EnumCategoriaEjemplar.Diario);

        System.out.println();
        System.out.println("-----------------------------------------");

        // BUSCAR EJEMPLARES SEGUN:
            //TITULO
        System.out.println();
        System.out.println("Busqueda por TITULO:");
        controlerEjemplar.buscarEjemplarXTitulo("Pricipito");

            //AUTOR
        System.out.println();
        System.out.println("Busqueda por AUTOR:");
        controlerEjemplar.buscarEjemplarXAutor("Inventado");

            //ANIO PUBLICACION
        System.out.println();
        System.out.println("Busqueda por ANIO PUBLICACION:");
        controlerEjemplar.buscarEjemplarXAnioPublicacion(2023);

            //CATEGORIA
        System.out.println();
        System.out.println("Busqueda por CATEGORIA:");
        controlerEjemplar.buscarEjemplaresXCategoria(EnumCategoriaEjemplar.Libro);

        /*
            //TITULO AUTOR
        System.out.println();
        System.out.println("Busqueda por TITULO y AUTOR:");
        controlerEjemplar.buscarEjemplarXTituloAutor();

            //TITULO ANIO
        System.out.println();
        System.out.println("Busqueda por TITULO y ANIO:");

            //TITULO CATEGORIA
        System.out.println();
        System.out.println("Busqueda por TITULO y CATEGORIA:");

            //AUTOR ANIO
        System.out.println();
        System.out.println("Busqueda por AUTOR y ANIO:");

            //AUTOR CATEGORIA
        System.out.println();
        System.out.println("Busqueda por AUTOR y CATEGORIA:");

            //ANIO CATEGORIA
        System.out.println();
        System.out.println("Busqueda por ANIO y CATEGORIA:");
        */

        System.out.println();
        System.out.println("-----------------------------------------");

        // CARGA DE PRESTAMOS
        System.out.println();
        System.out.println("Carga de PRESTAMO");
        controlerPrestamo.crearPrestamo(1L, LocalDate.now(), 44749039, 1L);

        // ACTUALIZAR ESTADOS:
        System.out.println();
        System.out.println("Actualizamos estado de EnCurso a ProximoAVencer:");
        controlerPrestamo.proximoAVencer(1L);

        System.out.println();
        System.out.println("Actualizamos estado de ProximoAVencer a Vencido:");
        controlerPrestamo.vencido(1L);

        System.out.println();
        System.out.println("Actualizamos estado de Vencido a Cerrado:");
        controlerPrestamo.cerrado(1L);

        System.out.println();
        System.out.println("-----------------------------------------");
        //Cargamos otro prestamo
        System.out.println();
        System.out.println("Carga de PRESTAMO");
        controlerPrestamo.crearPrestamo(2L, LocalDate.now(), 44749039, 3L);

        // ACTUALIZAR ESTADOS:
        System.out.println();
        System.out.println("Actualizamos estado de EnCurso a ProximoAVencer:");
        controlerPrestamo.proximoAVencer(2L);

        System.out.println();
        System.out.println("Actualizamos estado de ProximoAVencer a Cerrado:");
        controlerPrestamo.cerrado(2L);

        // ACTAULIZAR PARAMETROS DE PRESTAMOS (DIAS)
        // ABRIA UN METODO EN EL CONTROLER DE PRESTAMOS QUE CAMBIE SOLO LA DURACION

        // NOTIFICAR SOBRE SITUACIONES PARTICULAES
        // YYY ESTO VA DE LA MANO DE LOS ESTADOS


        // VISUALIZAR HISTORIAL DE PRESTAMOS DE UN SOCIO

        //controlerSocios.verHistorialSocio(44749039);
        /*
        * SALTA ERROR:
        *
        * A LO MEJOR NOS COMBIENE BUSCAR EN LA LISTA DE PRESTAMOS SOBRE UN SOCIO EN ESPECIFICO
        * PARA NO TENER QUE AGREGAR LOS PRESTAMOS EN EL SOCIO
        *
        * */
    }
}
