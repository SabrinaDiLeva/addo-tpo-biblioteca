package model;

import statePrestamo.EstadoPrestamo;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private LocalDate fechaInicio;
    private int duracion;
    private LocalDate fechaDevolucion;
    private EstadoPrestamo estado;
    private Socio socio;
    private Ejemplar ejemplar;

    public Prestamo(Long id, LocalDate fechaInicio, int duracion, EstadoPrestamo estado, Socio socio, Ejemplar ejemplar) {
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

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
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

    public String getTituloEjemplar(){ return  this.ejemplar.getTitulo(); }
}
