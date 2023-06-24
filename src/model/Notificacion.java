package model;

import adapterNotificacion.*;
import observer.Observer;
import observer.Sujeto;
import org.apache.http.cookie.SM;
import statePrestamo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Notificacion implements Observer {
    private Long id;
    private String mensajePredefinido;
    private LocalDate fecha;
    private String motivo; //modificar a enum
    private Prestamo prestamo;


    public Notificacion(Long id,LocalDate fecha, Prestamo prestamo) {
        this.id = id;
        this.fecha= fecha;
        this.prestamo=prestamo;
    }

    public Notificacion(Long id, String mensajePredefinido, LocalDate fecha, String motivo, Prestamo prestamo) {
        this.id = id;
        this.mensajePredefinido = mensajePredefinido;
        this.fecha = fecha;
        this.motivo = motivo;
        this.prestamo = prestamo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensajePredefinido() {
        return mensajePredefinido;
    }

    public void setMensajePredefinido(String mensajePredefinido) {
        this.mensajePredefinido = mensajePredefinido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha= fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public void actualizar(Sujeto observableActual, Sujeto observableProximo) {
        EstadoPrestamo estadoActual = (EstadoPrestamo) observableProximo;
        EstadoPrestamo estadoProximo = (EstadoPrestamo) observableProximo;
        if(estadoActual instanceof EnCurso && estadoProximo instanceof ProximoAVencer){
            crearNotificacionProximoVencimiento();
        }else if(estadoActual instanceof ProximoAVencer && estadoProximo instanceof Vencido){
            crearNotificacionVencido();
        } else if (estadoActual instanceof EnCurso && estadoProximo instanceof Cerrado) {
            crearNotificacionCerradoCorrectamente();
        } else if (estadoActual instanceof  ProximoAVencer && estadoProximo instanceof Cerrado) {
            crearNotificacionCerradoCorrectamente();
        }

        System.out.println("Notificacion");
    }

    private void crearNotificacionCerradoCorrectamente() {
        Notificacion notificacion = new Notificacion(this.id, "El préstamo fue devuelto en tiempo y forma y recibirá días de bonificación en su próximo préstamo", this.fecha,"Cerrado correctamente",this.prestamo);
        enviarAMensajería(notificacion);
    }


    private void crearNotificacionVencido() {
        Notificacion notificacion = new Notificacion(this.id, "El préstamo se venció",this.fecha, "Vencimiento",this.prestamo);
        enviarAMensajería(notificacion);

    }

    public void crearNotificacionProximoVencimiento(){
        Notificacion notificacion = new Notificacion(this.id, "El préstamo está próximo a vencerse, tiene 2 días hábiles para devolverlo",this.fecha, "Vencimiento Próximo",this.prestamo);
        enviarAMensajería(notificacion);
    }

    public void enviarAMensajería(Notificacion notificacion){
        EnumMedioDeComunicacion medioPreferencia=prestamo.getSocio().getMedioDeComunicacion();
        if(medioPreferencia.equals(EnumMedioDeComunicacion.whatsapp)){
            AplicacionMensajeria aplicacionMensajeria = new AplicacionMensajeria(new WhatsApp(new AdaptadorWhatsApp()));
            aplicacionMensajeria.enviarNotificacion(notificacion);
        }else if(medioPreferencia.equals(EnumMedioDeComunicacion.email)){
            AplicacionMensajeria aplicacionMensajeria = new AplicacionMensajeria(new Email(new AdaptadorAngus()));
            aplicacionMensajeria.enviarNotificacion(notificacion);
        } else if (medioPreferencia.equals(EnumMedioDeComunicacion.sms)){
            AplicacionMensajeria aplicacionMensajeria = new AplicacionMensajeria(new SMS(new AdaptadorTwilio()));
            aplicacionMensajeria.enviarNotificacion(notificacion);
        }
    }

}
