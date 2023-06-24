package observer;

public interface Sujeto {
    void agregar(Observer observador);
    void eliminar(Observer observador);
    void notificar();
}