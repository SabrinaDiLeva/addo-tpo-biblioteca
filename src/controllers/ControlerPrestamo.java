package controllers;

import model.*;
import statePrestamo.EnCurso;
import statePrestamo.EstadoPrestamo;
import controllers.ControlerSocios;
import controllers.ControlerEjemplar;
import statePrestamo.ProximoAVencer;
import statePrestamo.Vencido;

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
        Ejemplar ejemplar = controlerEjemplar.buscarEjemplar(idEjemplar);
        Long id = Long.valueOf(listaPrestamos.size()+1);
        int duracion = calcularDuracionPrestamo(socio,ejemplar);
        Prestamo prestamo= new Prestamo(id, fechaInicio,duracion,null,socio,ejemplar);
        prestamo.setEstado(new EnCurso(prestamo));
        listaPrestamos.add(prestamo);
        System.out.println("El prestamo '"+id+"' fue creado con exito. Se encuentra '"+prestamo.getEstado().getClass().getSimpleName()+"' y tendra una duracion de "+prestamo.getDuracion()+" dias.");
    }
    public int calcularDuracionPrestamo(Socio socio, Ejemplar ejemplar){
        int duracion=0;
        if(ejemplar.getClass().getSimpleName().equals(EnumCategoriaEjemplar.Libro.name())){
            duracion=10;
        }else{
            duracion=5;
        }
        duracion = duracion - socio.getConducta().getDiasAtradados();
        duracion = duracion + (int)Math.floor(socio.getConducta().getPrestamosPuntuales()/5);
        return duracion;
    }
    public void borrarPrestamo(Long id){
        Prestamo prestamo = buscarPrestamo(id);
        listaPrestamos.remove(prestamo);
    }
    public void actualizarPrestamo(Prestamo prestamo){
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
        prestamo.getEstado().cerrado();
        EstadoPrestamo estado = new Vencido(prestamo);
        prestamo.setEstado(estado);
    }


}
