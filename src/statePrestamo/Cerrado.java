package statePrestamo;

import model.Prestamo;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Cerrado extends EstadoPrestamo{
    private List<Observer> observers;
    public Cerrado(Prestamo prestamo){
        super(prestamo);
        observers= new ArrayList<>();
    }
    @Override
    public void proxAvencer() {
        System.out.print("El Prestamo ya fue devuelto");
    }

    @Override
    public void vencido() {
        System.out.println("El Prestamo ya fue devuelto");
    }

    @Override
    public void cerrado() {
        System.out.println("El Prestamo ya fue devuelto");
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
