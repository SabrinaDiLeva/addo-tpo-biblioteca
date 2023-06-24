package observer;

import statePrestamo.EstadoPrestamo;

public interface Sujeto {
    void agregar(Observer observador);
    void eliminar(Observer observador);
    void notificar(EstadoPrestamo estadoActual, EstadoPrestamo estadoProximo);
}