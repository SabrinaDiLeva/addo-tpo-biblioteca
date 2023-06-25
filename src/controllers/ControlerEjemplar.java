package controllers;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlerEjemplar {
    private static ControlerEjemplar instancia;
    private List<Ejemplar> listaEjemplares = new ArrayList<Ejemplar>();

    private ControlerEjemplar(){}

    public static ControlerEjemplar getInstacia(){
        if (instancia == null)
            instancia = new ControlerEjemplar();
        return instancia;
    }
    public void crearEjemplar(String titulo, String autor, int anioPublicacion, EnumCategoriaEjemplar categoria){
        Ejemplar ejemplar = null;
        Long id = Long.valueOf(listaEjemplares.size()+1);
        if(categoria.equals(EnumCategoriaEjemplar.Libro)){
            ejemplar = new Libro(id, titulo, autor, anioPublicacion);
        }else if(categoria.equals(EnumCategoriaEjemplar.Diario)){
            ejemplar = new Diario(id, titulo, autor, anioPublicacion);
        }else if(categoria.equals(EnumCategoriaEjemplar.Revista)){
            ejemplar = new Revista(id, titulo, autor, anioPublicacion);
        }else{
            ejemplar = new RevistaEspecializada(id, titulo, autor, anioPublicacion);
        }
        listaEjemplares.add(ejemplar);
        System.out.println("Se agrego correctamente el ejemplar '"+titulo+"' con el ID '"+id+"'.");
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
    public Ejemplar buscarEjemplar(Long id){
        //System.out.println("Ejemplar con el ID '"+ id+"':");
        for(Ejemplar ejemplar: listaEjemplares){
            if(ejemplar.getId() == id){
                //System.out.println("ID: "+ ejemplar.getId() +", Titulo: "+ejemplar.getTitulo()+", Autor: "+ ejemplar.getAutor()+", AnioPublicacion: "+ejemplar.getAnioPublicacion()+".");
                return ejemplar;
            }
        }
        //System.out.println("No se encontro ningun ejemplar con el id '"+ id+"'.");
        return null;
    }

    public List<Ejemplar> buscarEjemplarXTitulo(String titulo){
        System.out.println("Ejemplares con el titulo '"+ titulo+"':");
        List<Ejemplar> ejemplaresXTitulo = new ArrayList<>();
        for (Ejemplar ejemplar: listaEjemplares){
            if(ejemplar.getTitulo().equals(titulo)){
                System.out.println("ID: "+ ejemplar.getId() +", Titulo: "+ejemplar.getTitulo()+", Autor: "+ ejemplar.getAutor()+", AnioPublicacion: "+ejemplar.getAnioPublicacion()+".");
                ejemplaresXTitulo.add(ejemplar);
            }
        }
        if(ejemplaresXTitulo.size()==0){
            System.out.println("No se encontro ningun ejemplar con el titulo '"+titulo+"'.");
        }
        return ejemplaresXTitulo;
    }
    public List<Ejemplar> buscarEjemplarXAutor(String autor){
        System.out.println("Ejemplares con el autor '"+ autor+"':");
        List<Ejemplar> ejemplaresXAutor = new ArrayList<>();
        for (Ejemplar ejemplar: listaEjemplares){
            if(ejemplar.getAutor().equals(autor)){
                System.out.println("ID: "+ ejemplar.getId() +", Titulo: "+ejemplar.getTitulo()+", Autor: "+ ejemplar.getAutor()+", AnioPublicacion: "+ejemplar.getAnioPublicacion()+".");
                ejemplaresXAutor.add(ejemplar);
            }
        }
        if(ejemplaresXAutor.size()==0){
            System.out.println("No se encontro ningun ejemplar del autor '"+autor+"'.");
        }
        return ejemplaresXAutor;
    }
    public List<Ejemplar> buscarEjemplarXAnioPublicacion(int anio){
        System.out.println("Ejemplares con el anio de publicacion '"+ anio+"':");
        List<Ejemplar> ejemplaresXAnio = new ArrayList<>();
        for (Ejemplar ejemplar: listaEjemplares){
            if(ejemplar.getAnioPublicacion()==anio){
                System.out.println("ID: "+ ejemplar.getId() +", Titulo: "+ejemplar.getTitulo()+", Autor: "+ ejemplar.getAutor()+", AnioPublicacion: "+ejemplar.getAnioPublicacion()+".");
                ejemplaresXAnio.add(ejemplar);
            }
        }
        if(ejemplaresXAnio.size()==0){
            System.out.println("No se encontro ningun ejemplar del anio '"+anio+"'.");
        }
        return ejemplaresXAnio;
    }
    public List<Ejemplar> buscarEjemplaresXCategoria(EnumCategoriaEjemplar categoria){
        System.out.println("Ejemplares en la categoria '"+categoria+"': ");
        List<Ejemplar> ejemplaresXCategoria = new ArrayList<>();
        for(Ejemplar ejemplar: listaEjemplares){
            if(ejemplar.getClass().getSimpleName().equals(categoria.name())){
                System.out.println("ID: "+ ejemplar.getId() +", Titulo: "+ejemplar.getTitulo()+", Autor: "+ ejemplar.getAutor()+", AnioPublicacion: "+ejemplar.getAnioPublicacion()+".");
                ejemplaresXCategoria.add(ejemplar);
            }
        }
        if(ejemplaresXCategoria.size()==0){
            System.out.println("No se encontro ningun ejemplar de la categoria '"+categoria+"'.");
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
    public List<Ejemplar> buscarEjemplarXTituloCategoria(String titulo, EnumCategoriaEjemplar categoria){
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

    public List<Ejemplar> buscarEjemplarxAutorCategoria (String autor, EnumCategoriaEjemplar categoria){
        List<Ejemplar> ejemplaresXAutorCategoria = new ArrayList<>();
        List<Ejemplar> ejemplaresXAutor = buscarEjemplarXAutor(autor);
        for(Ejemplar i : ejemplaresXAutor){
            if(i.getClass().equals(categoria)){
                ejemplaresXAutorCategoria.add(i);
            }
        }
        return ejemplaresXAutorCategoria;
    }
    public List<Ejemplar> buscarEjemplarxAnioCategoria (int anio, EnumCategoriaEjemplar categoria){
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
