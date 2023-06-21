package model;

import java.util.ArrayList;
import java.util.List;

public class Conducta {
    private Long id;
    private int prestamosAtradados;
    private int prestamosPuntuales;

    private List<Suspension> suspensiones;


    public Conducta(Long id, int prestamosAtradados, int prestamosPuntuales) {
        this.id = id;
        this.prestamosAtradados = prestamosAtradados;
        this.prestamosPuntuales = prestamosPuntuales;
        this.suspensiones=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrestamosAtradados() {
        return prestamosAtradados;
    }

    public void setPrestamosAtradados(int prestamosAtradados) {
        this.prestamosAtradados = prestamosAtradados;
    }

    public int getPrestamosPuntuales() {
        return prestamosPuntuales;
    }

    public void setPrestamosPuntuales(int prestamosPuntuales) {
        this.prestamosPuntuales = prestamosPuntuales;
    }

    public List<Suspension> getSuspensiones() {
        return suspensiones;
    }

    public void setSuspensiones(List<Suspension> suspensiones) {
        this.suspensiones = suspensiones;
    }
}
