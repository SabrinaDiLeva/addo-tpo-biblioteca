import controllers.ControlerEjemplar;
import controllers.ControlerPrestamo;
import controllers.ControlerSocios;
import jdk.swing.interop.SwingInterOpUtils;
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
        controlerEjemplar.crearEjemplar("Pricipito", "Antoine de Saint-Exupery", 1223, EnumCategoriaEjemplar.Libro);

        //      REVISTA
        controlerEjemplar.crearEjemplar( "HOLA!", "Antonio Sanchez Gomez", 2017, EnumCategoriaEjemplar.Revista);

        //      REVISTA ESPECIALIZADA
        controlerEjemplar.crearEjemplar( "Todo sobre autos modernos", "Alain Prost", 1980, EnumCategoriaEjemplar.RevistaEspecializada);

        //      DIARIO
        controlerEjemplar.crearEjemplar("Clarin", "Leopoldo Alas", 2023, EnumCategoriaEjemplar.Diario);

        //      OTRO LIBRO
        controlerEjemplar.crearEjemplar("Don Quijote","Miguel de Cervantes",1605,EnumCategoriaEjemplar.Libro);

        //      OTRA REVISTA
        controlerEjemplar.crearEjemplar("Caras","Jorge Fontevecchia",1992,EnumCategoriaEjemplar.Revista);


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
        controlerPrestamo.crearPrestamo(LocalDate.now(), 44749039, 1L);

        // ACTUALIZAR ESTADOS:
        System.out.println();
        System.out.println("Actualizamos estado de EnCurso a ProximoAVencer:");
        controlerPrestamo.proximoAVencer(1L);

        System.out.println();
        System.out.println("Actualizamos estado de ProximoAVencer a Vencido:");
        controlerPrestamo.vencido(1L);

        System.out.println();
        System.out.println("Actualizamos estado de Vencido a Cerrado:");
        controlerPrestamo.cerradoFueraDeFecha(1L,11);

        System.out.println();
        System.out.println("-----------------------------------------");
        //Cargamos un prestamo fallido
        System.out.println();
        System.out.println("Carga de PRESTAMO");
        controlerPrestamo.crearPrestamo(LocalDate.now(), 44749039, 3L);
        System.out.println();
        System.out.println("-----------------------------------------");

        //Regularizar situacion con el bibliotecario
        System.out.println();
        System.out.println("REGULARIZACION:");
        controlerSocios.regularizarSituacion(LocalDate.now(),44749039);

        System.out.println();
        System.out.println("-----------------------------------------");

        //El socio ahora puede volver a solicitar un prestamo
        System.out.println();
        System.out.println("Nueva solicitud de PRESTAMO");
        controlerPrestamo.crearPrestamo(LocalDate.now(), 44749039, 3L);

        // ACTUALIZAR ESTADOS:
        System.out.println();
        System.out.println("Actualizamos estado de EnCurso a ProximoAVencer:");
        controlerPrestamo.proximoAVencer(2L);

        System.out.println();
        System.out.println("Actualizamos estado de ProximoAVencer a Cerrado:");
        controlerPrestamo.cerrado(2L);

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Cargamos mas prestamos para mejorar la conducta del usuario");
        controlerPrestamo.crearPrestamo(LocalDate.now(), 44749039, 2L);
        controlerPrestamo.cerrado(3L);
        controlerPrestamo.crearPrestamo(LocalDate.now(), 44749039, 6L);
        controlerPrestamo.cerrado(4L);
        controlerPrestamo.crearPrestamo(LocalDate.now(), 44749039, 4L);
        controlerPrestamo.cerrado(5L);
        controlerPrestamo.crearPrestamo(LocalDate.now(),44749039, 5L);
        controlerPrestamo.cerrado(6L);

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("El proximo prestamo deberia tener un DIA EXTRA:");
        controlerPrestamo.crearPrestamo(LocalDate.now(),44749039, 1L);
        controlerPrestamo.cerrado(7L);

        //si hacemos un prestamo mas, no deberia tener bonificacion
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Este prestamo NO deberia volver a aplicar la bonificacion:");
        controlerPrestamo.crearPrestamo(LocalDate.now(),44749039, 6L);
        controlerPrestamo.proximoAVencer(8L);
        controlerPrestamo.vencido(8L);
        controlerPrestamo.cerradoFueraDeFecha(8L,7);

        //validar que pasa si diasAtrasados>duracion
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("En este caso, el usuario tiene 7 dias de demora pero la duracion default del prestamo es de 5 dias.");
        System.out.println("Vamos a dejarle 1 dia al prestamo como minimo. Los dias atrasados que no tengan efecto aca se descontaran en el proximo prestamo.");
        controlerPrestamo.crearPrestamo(LocalDate.now(),44749039, 2L);
        controlerPrestamo.cerrado(9L);

        //contro
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Se descuentan los dias atrasados pendientes del prestamo anterior.");
        controlerPrestamo.crearPrestamo(LocalDate.now(),44749039, 5L);
        controlerPrestamo.cerrado(10L);

        // ACTAULIZAR PARAMETROS DE PRESTAMOS (DIAS)
        // ABRIA UN METODO EN EL CONTROLER DE PRESTAMOS QUE CAMBIE SOLO LA DURACION

         //controlerPrestamo.cambiarDias()

        // NOTIFICAR SOBRE SITUACIONES PARTICULAES
        // YYY ESTO VA DE LA MANO DE LOS ESTADOS


        // VISUALIZAR HISTORIAL DE PRESTAMOS DE UN SOCIO

        // controlerSocios.verHistorialSocio(44749039);
    }
}
