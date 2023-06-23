package statePrestamo;

import model.Prestamo;

public abstract class EstadoPrestamo {
    protected Prestamo prestamo;

    public EstadoPrestamo(Prestamo prestamos){
        this.prestamo = prestamos;
    }

    public abstract void proxAvencer();
    public abstract void vencido();
    public abstract void cerrado();
    

}
