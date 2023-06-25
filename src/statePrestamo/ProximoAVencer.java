package statePrestamo;

import model.Prestamo;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ProximoAVencer extends EstadoPrestamo{
    private List<Observer> observers;


    public ProximoAVencer(Prestamo prestamo){
        super(prestamo);
        observers= new ArrayList<>();
    }

    @Override
    public void proxAvencer() {
        System.out.println("El prestamo esta próximo a vencer.");
    }

    @Override
    public void vencido() {
        EstadoPrestamo estado = new Vencido(prestamo);
        prestamo.setEstado(estado);
        notificar(this,estado);
        System.out.println("El prestamo se vencio.");

    }

    @Override
    public void cerrado(int dias) {
        EstadoPrestamo estado = new Cerrado(prestamo);
        prestamo.setEstado(estado);

        notificar(this,estado);
        System.out.println("El prestamo fue devuelto a tiempo.");

        //impacto positivo en la conducta del socio
        int prestamosATiempo= prestamo.getSocio().getConducta().getPrestamosPuntuales();
        prestamo.getSocio().getConducta().setPrestamosPuntuales(prestamosATiempo+1);
        prestamosATiempo=prestamosATiempo+1;

        if(prestamosATiempo>=5){
            System.out.println("El socio "+ prestamo.getSocio().getDni()+" ha devuelto "+prestamosATiempo + " prestamos a tiempo.");
            System.out.println("Recibira una bonificacion de " +(int)Math.floor(prestamosATiempo/5)+" dias en su proximo prestamo.");
        }

    }

    @Override
    public void agregar(Observer observador) {
        observers.add(observador);
        System.out.println("Suscribiendo observador");
    }

    @Override
    public void eliminar(Observer observador) {
        this.observers.remove(observador);
        System.out.println("Desuscribiendo observador");
    }

    @Override
    public void notificar(EstadoPrestamo estadoActual, EstadoPrestamo estadoProximo) {
        for (int i = 0; i < this.observers.size(); i++) {
            this.observers.get(i).actualizar(estadoActual, estadoProximo);
        }
    }
}
