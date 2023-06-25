package controllers;

import model.*;
import statePrestamo.*;
import controllers.ControlerSocios;
import controllers.ControlerEjemplar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlerPrestamo {
    private static ControlerPrestamo instancia;
    private List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();

    ControlerSocios controlerSocios = ControlerSocios.getInstancia();
    ControlerEjemplar controlerEjemplar = ControlerEjemplar.getInstacia();


    private ControlerPrestamo(){}

    public static ControlerPrestamo getInstancia(){
        if(instancia == null)
            instancia = new ControlerPrestamo();
        return instancia;
    }

    public void crearPrestamo(LocalDate fechaInicio, int dniSocio, Long idEjemplar){
        Socio socio= controlerSocios.buscarSocio(dniSocio);

        //controlar que no este suspendido
        if(puedeSolicitarPrestamo(socio)){
            Ejemplar ejemplar = controlerEjemplar.buscarEjemplar(idEjemplar);
            Long id = Long.valueOf(listaPrestamos.size()+1);
            int duracion = calcularDuracionPrestamo(socio,ejemplar);
            Prestamo prestamo= new Prestamo(id, fechaInicio,duracion,null,socio,ejemplar);
            calcularFechaFin(prestamo);
            prestamo.setEstado(new EnCurso(prestamo));
            listaPrestamos.add(prestamo);

            //lo agregamos a la lista de prestamos del socio
            socio.getPrestamos().add(prestamo);
            List<Socio> socios = controlerSocios.listarSocios();
            controlerSocios.actualizarSocio(socio);

            System.out.println("El prestamo '"+id+"' fue creado con exito. Se encuentra '"+prestamo.getEstado().getClass().getSimpleName()+"' y tendra una duracion de "+prestamo.getDuracion()+" dias.");
        }else{
            System.out.println("El socio "+socio.getDni()+" no puede solicitar un prestamo porque esta suspendido actualmente, debe regularizar su situacion con el bibliotecario.");
        }
    }
    public void calcularFechaFin(Prestamo prestamo){
        LocalDate fechaInicio= prestamo.getFechaInicio();
        LocalDate fechaFinal = fechaInicio.plusDays(prestamo.getDuracion());
        prestamo.setFechaDevolucion(fechaFinal);
        actualizarPrestamo(prestamo);
    }
    public boolean puedeSolicitarPrestamo(Socio socio){
        List<Suspension> suspensiones = socio.getConducta().getSuspensiones();
        int indice=-1;
        if(suspensiones.size()==0){
            return true;
        }else{
            indice= suspensiones.size()-1;
        }
        if(suspensiones.get(indice).getFechaFin()!=null){
            return true;
        }else{
            return false;
        }
    }
    public int calcularDuracionPrestamo(Socio socio, Ejemplar ejemplar){
        int duracion=0;
        if(ejemplar.getClass().getSimpleName().equals(EnumCategoriaEjemplar.Libro.name())){
            duracion=10;
        }else{
            duracion=5;
        }
        int diasAtrasados=socio.getConducta().getDiasAtradados();
        Conducta conducta = socio.getConducta();
        if(diasAtrasados> duracion){
            int nuevaDuracion= (diasAtrasados-duracion)+1;
            conducta.setDiasAtradados(nuevaDuracion);
            socio.setConducta(conducta);
            controlerSocios.actualizarSocio(socio);
            System.out.println(controlerSocios.buscarSocio(socio.getDni()).getConducta().getDiasAtradados());
            duracion=1;

        }else{
            duracion = duracion - diasAtrasados;
            conducta.setDiasAtradados(0);
            socio.setConducta(conducta);
            controlerSocios.actualizarSocio(socio);
        }

        if(socio.getConducta().getPrestamosPuntuales()>=5){
            duracion = duracion + (int)Math.floor(socio.getConducta().getPrestamosPuntuales()/5);
            conducta.setPrestamosPuntuales(0);
            socio.setConducta(conducta);
            controlerSocios.actualizarSocio(socio);
        }

        return duracion;
    }
    public void borrarPrestamo(Long id){
        Prestamo prestamo = buscarPrestamo(id);
        listaPrestamos.remove(prestamo);
    }
    public void actualizarPrestamo(Prestamo prestamo){
        //revisar
        int indice = buscarPrestamoIndice(prestamo.getId());
        if(indice >= 0){
            listaPrestamos.get(indice).setSocio(prestamo.getSocio());
            listaPrestamos.get(indice).setFechaInicio(prestamo.getFechaInicio());
            listaPrestamos.get(indice).setDuracion(prestamo.getDuracion());
            listaPrestamos.get(indice).setFechaDevolucion(prestamo.getFechaDevolucion());
            listaPrestamos.get(indice).setEstado(prestamo.getEstado());
            listaPrestamos.get(indice).setEjemplar(prestamo.getEjemplar());
        }
    }
    private int buscarPrestamoIndice(Long id) {
        for(int i = 0; i < listaPrestamos.size(); i++ ){
            if(listaPrestamos.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public Prestamo buscarPrestamo(Long id){
        for(Prestamo i: listaPrestamos){
            if(i.getId() == id)
                return i;

        }
        return null;
    }

    public void proximoAVencer(Long id){
        Prestamo prestamo = buscarPrestamo(id);
        prestamo.getEstado().proxAvencer();
        EstadoPrestamo estado = new ProximoAVencer(prestamo);
        prestamo.setEstado(estado);
    }

    public void vencido(Long id){
        Prestamo prestamo = buscarPrestamo(id);
        prestamo.getEstado().vencido();
        EstadoPrestamo estado = new Vencido(prestamo);
        prestamo.setEstado(estado);
    }

    public void cerrado(Long id){
        Prestamo prestamo = buscarPrestamo(id);
        prestamo.getEstado().cerrado(0);
        EstadoPrestamo estado = new Cerrado(prestamo);
        prestamo.setEstado(estado);
    }

    public void cerradoFueraDeFecha(Long id, int dias) {
        Prestamo prestamo = buscarPrestamo(id);
        prestamo.getEstado().cerrado(dias);
        EstadoPrestamo estado = new Cerrado(prestamo);
        prestamo.setEstado(estado);
    }

    public void cambiarDias(Long id, int dias){
        Prestamo prestamo = buscarPrestamo(id);
        if (prestamo.getEstado() instanceof EnCurso || prestamo.getEstado() instanceof  ProximoAVencer) {
            prestamo.setDuracion(dias);
            actualizarPrestamo(prestamo);
            calcularFechaFin(prestamo);
            actualizarPrestamo(prestamo);
            System.out.println("Se cambio la cantidad de dias del prestamo '"+id+"' a " + dias+" dias.");
        }
        else{
            System.out.println("No se puede cambiar la cantidad de dias porque el prestamo ya esta cerrado/vencido");
        }
    }
}
