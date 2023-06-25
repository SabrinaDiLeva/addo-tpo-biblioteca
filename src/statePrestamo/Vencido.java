package statePrestamo;

import controllers.ControlerSocios;
import model.Prestamo;
import model.Suspension;
import observer.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vencido extends EstadoPrestamo{
    private List<Observer> observers;
    public Vencido(Prestamo prestamo){
        super(prestamo);
        observers= new ArrayList<>();
    }
    ControlerSocios controlerSocios = ControlerSocios.getInstancia();

    @Override
    public void proxAvencer() {
        System.out.print("El Prestamo se vencio.");
    }

    @Override
    public void vencido() {
        System.out.print("El Prestamo se vencio.");
    }

    @Override
    public void cerrado(int dias) {
        EstadoPrestamo estado = new Cerrado(prestamo);
        prestamo.setEstado(estado);
        notificar(this,estado);
        System.out.println("El prestamo fue devuelto fuera de fecha.");

        //impacto negativo en la conducta del socio
        int diasDeDemora= prestamo.getSocio().getConducta().getDiasAtradados();
        prestamo.getSocio().getConducta().setDiasAtradados(diasDeDemora+dias);
        diasDeDemora=diasDeDemora+dias;

        if(diasDeDemora>10){
            System.out.println("El socio "+ prestamo.getSocio().getDni()+" ha acumulado "+diasDeDemora + " dias de demora.");
            System.out.println("Sera suspendido hasta regularizar su suspension con el bibliotecario.");

            List<Suspension> suspensiones =prestamo.getSocio().getConducta().getSuspensiones();
            controlerSocios.crearSuspenion(LocalDate.now(), prestamo.getSocio().getDni());
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
