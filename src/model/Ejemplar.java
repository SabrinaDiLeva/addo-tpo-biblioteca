package model;

import java.time.LocalDate;

public abstract class Ejemplar {
    private Long id;
    private String titulo;
    private String autor;
    private int anioPublicacion;

    private EnumCategoriaEjemplar categoria;


    private boolean disponible;
    public Ejemplar(){

    }

    public Ejemplar(Long id, String titulo, String autor, int anioPublicacion, EnumCategoriaEjemplar categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        this.categoria=categoria;
    }

    public Ejemplar(Long id, String titulo, String autor, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }


    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public EnumCategoriaEjemplar getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoriaEjemplar categoria) {
        this.categoria = categoria;
    }

}
