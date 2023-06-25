package statePrestamo;

import controllers.ControlerSocios;
import model.Conducta;
import model.Prestamo;
import model.Socio;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class EnCurso extends EstadoPrestamo {

    private List<Observer> observers;
    public EnCurso(Prestamo prestamo){
        super(prestamo);
        observers= new ArrayList<>();
    }
    ControlerSocios controlerSocios = ControlerSocios.getInstancia();
    @Override
    public void proxAvencer() { // aca estaria asociado el observer
        EstadoPrestamo estado = new ProximoAVencer(prestamo);
        prestamo.setEstado(estado);
        notificar(this,estado);
        System.out.println("El prestamo esta por vencer.");
    }

    @Override
    public void vencido() { // no se puede usar, no hay rel
        System.out.print("El Prestamo esta en curso.");
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
            System.out.println("Recibira una bonificacion de " + (int)Math.floor(prestamosATiempo/5) +" dias en su proximo prestamo.");
        }
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
