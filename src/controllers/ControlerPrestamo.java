package controllers;

import model.Prestamo;

import java.util.List;

public class ControlerPrestamo {
    private List<Prestamo> listaPrestamos;

    public ControlerPrestamo(){}

    public void crearPrestamo(Prestamo prestamo){
        listaPrestamos.add(prestamo);
    }
    public void borrarPrestamo(Long id){
        Prestamo prestamo = devolverPrestamo(id);
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

    public Prestamo devolverPrestamo(Long id){
        for(Prestamo i: listaPrestamos){
            if(i.getId() == id)
                return i;

        }
        return null;
    }
}