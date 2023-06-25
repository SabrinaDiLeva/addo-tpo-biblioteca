package model;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    private int dni;
    private String nombre;
    private String apellido;
    private String email;
    private int telefono;
    private EnumMedioDeComunicacion medioDeComunicacion;

    private Conducta conducta;

    private List<Prestamo> prestamos;

    public Socio(int dni, String nombre, String apellido, String email, int telefono, EnumMedioDeComunicacion medioDeComunicacion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.medioDeComunicacion = medioDeComunicacion;
        this.conducta=new Conducta(0,0);
        this.prestamos=new ArrayList<>();
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public EnumMedioDeComunicacion getMedioDeComunicacion() {
        return medioDeComunicacion;
    }

    public void setMedioDeComunicacion(EnumMedioDeComunicacion medioDeComunicacion) {
        this.medioDeComunicacion = medioDeComunicacion;
    }

    public Conducta getConducta() {
        return conducta;
    }

    public void setConducta(Conducta conducta) {
        this.conducta = conducta;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}
