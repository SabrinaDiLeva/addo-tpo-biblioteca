package statePrestamo;

import model.Prestamo;
import observer.Sujeto;

public abstract class EstadoPrestamo implements Sujeto {
    protected Prestamo prestamo;

    public EstadoPrestamo(Prestamo prestamos){
        this.prestamo = prestamos;
    }

    public abstract void proxAvencer();
    public abstract void vencido();
    public abstract void cerrado(int dias);
    

}
