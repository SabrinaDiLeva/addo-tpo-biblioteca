package statePrestamo;

import model.Prestamo;

public class Vencido extends EstadoPrestamo{
    public Vencido(Prestamo prestamo){
        super(prestamo);
    }

    @Override
    public void proxAvencer() {
        System.out.print("El Prestamo se venció");
    }

    @Override
    public void vencido() { // no se puede usar, no hay rel
        System.out.print("El Prestamo se venció");
    }

    @Override
    public void cerrado() {
        prestamo.setEstado(new Cerrado(prestamo));
        System.out.println("El prestamo fue devuelto");
    }
}
