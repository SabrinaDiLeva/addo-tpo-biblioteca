package statePrestamo;

import model.Prestamo;
import observer.Observer;

import java.util.List;

public class EnCurso extends EstadoPrestamo {

    private List<Observer> observers;
    public EnCurso(Prestamo prestamo){
        super(prestamo);
    }

    @Override
    public void proxAvencer() { // aca estaria asociado el observer
        EstadoPrestamo estado = new ProximoAVencer(prestamo);
        prestamo.setEstado(estado);
        notificar(this,estado);
        System.out.println("El prestamo esta por vencer");
    }

    @Override
    public void vencido() { // no se puede usar, no hay rel
        System.out.print("El Prestamo esta en curso");
    }

    @Override
    public void cerrado() {
        EstadoPrestamo estado = new Cerrado(prestamo);
        prestamo.setEstado(estado);
        notificar(this,estado);
        System.out.println("El prestamo fue devuelto");
    }

    @Override
    public void agregar(Observer observador) {
        this.observers.add(observador);
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
