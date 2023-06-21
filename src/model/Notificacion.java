package model;

import java.time.LocalDate;

public class Notificacion {
    private Long id;
    private String mensajePredefinido;
    private LocalDate fecha;
    private String motivo; //modificar a enum

    public Notificacion(Long id, String mensajePredefinido, LocalDate fecha, String motivo) {
        this.id = id;
        this.mensajePredefinido = mensajePredefinido;
        this.fecha= fecha;
        this.motivo = motivo;
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
}
