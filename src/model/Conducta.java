package model;

import java.util.ArrayList;
import java.util.List;

public class Conducta {
    //private Long id;
    private int diasAtradados;
    private int prestamosPuntuales;

    private List<Suspension> suspensiones;


    public Conducta(int diasAtradados, int prestamosPuntuales) {
        this.diasAtradados = diasAtradados;
        this.prestamosPuntuales = prestamosPuntuales;
        this.suspensiones=new ArrayList<>();
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public int getDiasAtradados() {
        return diasAtradados;
    }

    public void setDiasAtradados(int diasAtradados) {
        this.diasAtradados = diasAtradados;
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
