package controllers;

import model.Ejemplar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlerEjemplar {
    private ControlerEjemplar instancia;
    private List<Ejemplar> listaEjemplares;

    private ControlerEjemplar(){}

    public ControlerEjemplar getInstacia(){
        if (instancia == null)
            instancia = new ControlerEjemplar();
        return this.instancia;
    }
    public void cargarEjemplares(Ejemplar ejemplar){
        listaEjemplares.add(ejemplar);
    }
    public void actualizarEjemplar(Ejemplar ejemplar){
        int indice = buscarEjemplarIndice(ejemplar.getTitulo());
        if (indice >= 0){
            listaEjemplares.get(indice).setAutor(ejemplar.getAutor());
            listaEjemplares.get(indice).setDisponible(ejemplar.getDisponible());
            listaEjemplares.get(indice).setAnioPublicacion(ejemplar.getAnioPublicacion());
        }
    }
    private int buscarEjemplarIndice(String titulo) {
        for(int i = 0; i < listaEjemplares.size(); i++ ){
            if(listaEjemplares.get(i).getTitulo().equals(titulo)){
                return i;
            }
        }
        return -1;
    }

    public List<Ejemplar> buscarEjemplarXTitulo(String titulo){
        List<Ejemplar> ejemplaresXTitulo = new ArrayList<>();
        for (Ejemplar i: listaEjemplares){
            if(i.getTitulo().equals(titulo))
                ejemplaresXTitulo.add(i);
        }
        return ejemplaresXTitulo;
    }
    public List<Ejemplar> buscarEjemplarXAutor(String autor){
        List<Ejemplar> ejemplaresXAutor = new ArrayList<>();
        for (Ejemplar i: listaEjemplares){
            if(i.getAutor().equals(autor))
                ejemplaresXAutor.add(i);
        }
        return ejemplaresXAutor;
    }
    public List<Ejemplar> buscarEjemplarXAnioPublicacion(int anio){
        List<Ejemplar> ejemplaresXAnio = new ArrayList<>();
        for (Ejemplar i: listaEjemplares){
            if(i.getAnioPublicacion()==anio)
                ejemplaresXAnio.add(i);
        }
        return ejemplaresXAnio;
    }
    public List<Ejemplar> buscarEjemplaresXCategoria(String categoria){
        List<Ejemplar> ejemplaresXCategoria = new ArrayList<>();
        for(Ejemplar i : listaEjemplares){
            if(i.getClass().equals(categoria)){
                ejemplaresXCategoria.add(i);
            }
        }
        return ejemplaresXCategoria;
    }
    private void datosEjemplar(Ejemplar ejemplar){
        System.out.println(ejemplar.getId());
        System.out.println(ejemplar.getTitulo());
        System.out.println(ejemplar.getAutor());
        System.out.println(ejemplar.getAnioPublicacion());
    }

    public List<Ejemplar> buscarEjemplarXTituloAutor(String titulo, String autor){
        List<Ejemplar> ejemplaresXTituloAutor = new ArrayList<>();
        List<Ejemplar> ejemplaresXAutor = buscarEjemplarXAutor(autor);
        for(Ejemplar i : ejemplaresXAutor){
            if(i.getTitulo().equals(titulo)){
                ejemplaresXTituloAutor.add(i);
            }
        }
        return ejemplaresXTituloAutor;
    }
    public List<Ejemplar> buscarEjemplarXTituloAnio(String titulo, int anio){
        List<Ejemplar> ejemplaresXTituloAnio = new ArrayList<>();
        List<Ejemplar> ejemplaresXAnio = buscarEjemplarXAnioPublicacion(anio);
        for(Ejemplar i : ejemplaresXAnio){
            if(i.getTitulo().equals(titulo)){
                ejemplaresXTituloAnio.add(i);
            }
        }
        return ejemplaresXTituloAnio;
    }
    public List<Ejemplar> buscarEjemplarXTituloCategoria(String titulo, String categoria){
        List <Ejemplar> ejemplarXTituloCategoria = new ArrayList<>();
        List<Ejemplar> ejemplaresXCategoria = buscarEjemplaresXCategoria(categoria);
        for(Ejemplar i : ejemplaresXCategoria){
            if(i.getTitulo().equals(titulo)){
                ejemplarXTituloCategoria.add(i);
            }
        }
        return ejemplarXTituloCategoria;
    }

    public List<Ejemplar> buscarEjemplarxAutorAnio (String autor, int anio){
        List<Ejemplar> ejemplaresXAutorAnio = new ArrayList<>();
        List<Ejemplar> ejemplaresXAutor = buscarEjemplarXAutor(autor);
        for(Ejemplar i : ejemplaresXAutor){
            if(i.getAnioPublicacion()==anio){
                ejemplaresXAutorAnio.add(i);
            }
        }
        return ejemplaresXAutorAnio;
    }

    public List<Ejemplar> buscarEjemplarxAutorCategoria (String autor, String categoria){
        List<Ejemplar> ejemplaresXAutorCategoria = new ArrayList<>();
        List<Ejemplar> ejemplaresXAutor = buscarEjemplarXAutor(autor);
        for(Ejemplar i : ejemplaresXAutor){
            if(i.getClass().equals(categoria)){
                ejemplaresXAutorCategoria.add(i);
            }
        }
        return ejemplaresXAutorCategoria;
    }
    public List<Ejemplar> buscarEjemplarxAnioCategoria (int anio, String categoria){
        List<Ejemplar> ejemplaresXAnioCategoria = new ArrayList<>();
        List<Ejemplar> ejemplaresXAnio = buscarEjemplarXAnioPublicacion(anio);
        for(Ejemplar i : ejemplaresXAnio){
            if(i.getClass().equals(categoria)){
                ejemplaresXAnioCategoria.add(i);
            }
        }
        return ejemplaresXAnioCategoria;
    }
    //TITULO AUTOR ANIO
    //TITULO AUTOR  CATEGORIA
    //TITULO ANIO CATEGORIA
    //AUTOR ANIO CATEGORIA

    //TITULO AUTOR ANIO CATEGORIA

}
