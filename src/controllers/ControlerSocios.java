package controllers;

import model.Conducta;
import model.Prestamo;
import model.Socio;

import java.util.List;

public class ControlerSocios {
    private ControlerSocios instancia;
    private List<Socio> listaSocios;

    private ControlerSocios(){ }

    public ControlerSocios getInstancia() {
        if(instancia == null)
            instancia = new ControlerSocios();
        return instancia;
    }
    //CRUD SOCIOS
    public void crearSocio(Socio socio){
        listaSocios.add(socio);
    }
    public void borrarSocio(int dni){
        Socio aux = buscarSocio(dni);
        listaSocios.remove(aux);
    }
    public void actualizarSocio(Socio socio){

        int indice = buscarSocioIndice(socio.getDni());
        if (indice >= 0){
            //listaSocios.add(indice, socio);
            listaSocios.get(indice).setNombre(socio.getNombre());
            listaSocios.get(indice).setApellido(socio.getApellido());
            listaSocios.get(indice).setEmail(socio.getEmail());
            listaSocios.get(indice).setTelefono(socio.getTelefono());
            listaSocios.get(indice).setMedioDeComunicacion(socio.getMedioDeComunicacion());
        }
    }

    private int buscarSocioIndice(int dni) {
        for(int i = 0; i < listaSocios.size(); i++ ){
            if(listaSocios.get(i).getDni() == dni){
                return i;
            }
        }
        return -1;
    }

    public Socio buscarSocio(int dni){
        for(Socio persona: listaSocios){
            if(persona.getDni() == dni){
                return persona;
            }
        }
        return null;
    }

    public List<Socio> listarSocios(){
        return listaSocios;
    }

    public void listarSociosEnConsola(){
        for(Socio i : listaSocios){
            System.out.println("Nombre: " + i.getNombre());
            System.out.println("Apellido: " + i.getApellido());
        }
    }

    public void verHistorialSocio(int dni){
        Socio aux = buscarSocio(dni);

        List<Prestamo> listaDePrestamos = aux.getPrestamos();
        System.out.println("Historial de el socio: "+ aux.getNombre());
        for (Prestamo prestamo: listaDePrestamos){
            System.out.println("Id de prestamo: " + prestamo.getId());
            System.out.println("Nombre del Ejemplar: " + prestamo.getTituloEjemplar());
            System.out.println("Fecha inicio: "+ prestamo.getFechaInicio().toString());
            System.out.println("Duracion: " + prestamo.getDuracion());
            System.out.println("Fecha devolucion: " + prestamo.getFechaDevolucion().toString());    // PORQUE ESTA LA FECHA EN INT ??
            System.out.print("Estado: "+ prestamo.getEstado());

        }
    }


    //CRUD CONDUCTA
    public void crearConducta(Conducta conducta, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        socio.setConducta(conducta);
    }
    public void borrarConducta(int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        socio.setConducta(null);
    }
    public void actualizarConducta(Conducta conducta, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        socio.setConducta(conducta);
    }


    public Conducta busarConducta(int dni){
        for(Socio persona: listaSocios){
            if(persona.getDni() == dni){
                return persona.getConducta();
            }
        }
        return null;
    }

    public Long busarConductaIndice(int dni){
        for(Socio persona: listaSocios){
            if(persona.getDni() == dni){
                return persona.getConducta().getId();
            }
        }
        return null;
    }
}
