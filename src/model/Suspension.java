package model;

import java.time.LocalDate;

public class Suspension {
    //private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Suspension(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.fechaFin=null;
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
