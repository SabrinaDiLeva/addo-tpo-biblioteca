package statePrestamo;

import model.Prestamo;

public class EnCurso extends EstadoPrestamo {

    public EnCurso(Prestamo prestamo){
        super(prestamo);
    }

    @Override
    public void proxAvencer() {
        prestamo.setEstado(new ProximoAVencer(prestamo));
        System.out.println("El prestamo esta por vencer");
    }

    @Override
    public void vencido() { // no se puede usar, no hay rel
        System.out.print("El Prestamo esta en curso");
    }

    @Override
    public void cerrado() {
        prestamo.setEstado(new Cerrado(prestamo));
        System.out.println("El prestamo fue devuelto");
    }
}
