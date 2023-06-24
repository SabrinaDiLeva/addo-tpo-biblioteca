package statePrestamo;

import model.Prestamo;
import observer.Observer;

import java.util.List;

public class ProximoAVencer extends EstadoPrestamo{
    private List<Observer> observers;


    public ProximoAVencer(Prestamo prestamo){
        super(prestamo);
    }
    @Override
    public void proxAvencer() {
        System.out.println("El prestamo esta próximo a vencer");
    }

    @Override
    public void vencido() {
        prestamo.setEstado(new Vencido(prestamo));
        System.out.println("El prestamo se venció");

        notificar();
    }

    @Override
    public void cerrado() {
        prestamo.setEstado(new Cerrado(prestamo));
        System.out.println("El prestamo fue devuelto");

        notificar();
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
        for (Observer i : observers){
            i.actualizar(this);
        }
    }
}
