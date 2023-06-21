package model;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private LocalDate fechaInicio;
    private int duracion;
    private int fechaDevolucion;
    private String estado; //modificar por un enum
    private Socio socio;
    private Ejemplar ejemplar;

    public Prestamo(Long id, LocalDate fechaInicio, int duracion, String estado, Socio socio, Ejemplar ejemplar) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.estado = estado;
        this.socio = socio;
        this.ejemplar = ejemplar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(int fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
}
