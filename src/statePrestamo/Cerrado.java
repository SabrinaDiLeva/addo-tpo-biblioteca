package statePrestamo;

import model.Prestamo;

public class Cerrado extends EstadoPrestamo{
    public Cerrado(Prestamo prestamo){
        super(prestamo);
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
}
