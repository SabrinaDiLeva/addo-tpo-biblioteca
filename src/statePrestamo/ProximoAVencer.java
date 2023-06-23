package statePrestamo;

import model.Prestamo;

public class ProximoAVencer extends EstadoPrestamo{

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
    }

    @Override
    public void cerrado() {
        prestamo.setEstado(new Cerrado(prestamo));
        System.out.println("El prestamo fue devuelto");
    }
}
