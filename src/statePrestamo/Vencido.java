package statePrestamo;

import model.Prestamo;
import observer.Observer;

import java.util.List;

public class Vencido extends EstadoPrestamo{
    private List<Observer> observers;
    public Vencido(Prestamo prestamo){
        super(prestamo);
    }

    @Override
    public void proxAvencer() {
        System.out.print("El Prestamo se venció");
    }

    @Override
    public void vencido() {
        System.out.print("El Prestamo se venció");
        notificar();
    }

    @Override
    public void cerrado() {
        prestamo.setEstado(new Cerrado(prestamo));
        System.out.println("El prestamo fue devuelto");
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
    public void notificar() {

    }
}
