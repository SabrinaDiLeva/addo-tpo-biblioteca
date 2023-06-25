package statePrestamo;

import model.Prestamo;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Vencido extends EstadoPrestamo{
    private List<Observer> observers;
    public Vencido(Prestamo prestamo){
        super(prestamo);
        observers= new ArrayList<>();
    }

    @Override
    public void proxAvencer() {
        System.out.print("El Prestamo se venció");
    }

    @Override
    public void vencido() {
        System.out.print("El Prestamo se venció");
    }

    @Override
    public void cerrado() {
        EstadoPrestamo estado = new Cerrado(prestamo);
        prestamo.setEstado(estado);
        notificar(this,estado);
        System.out.println("El prestamo fue devuelto fuera de fecha");
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
