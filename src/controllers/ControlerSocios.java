package controllers;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlerSocios {
    private static ControlerSocios instancia;
    private List<Socio> listaSocios =  new ArrayList<Socio>();

    private ControlerSocios(){ }

    public static ControlerSocios getInstancia() {
        if(instancia == null)
            instancia = new ControlerSocios();
        return instancia;
    }
    //CRUD SOCIOS
    public void crearSocio(int dni, String nombre, String apellido, String email, int telefono, EnumMedioDeComunicacion medioDeComunicacion){;
        Socio socio = new Socio(dni, nombre, apellido,email,telefono,medioDeComunicacion);
        listaSocios.add(socio);
        crearConducta(0,0,socio.getDni());
        System.out.println("Se agrego correctamente al socio '"+nombre+"'. Ahora hay "+listaSocios.size()+" socios en total.");
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
    public void crearConducta(int diasAtrasados, int prestamosPuntuales, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        Conducta conducta = new Conducta(diasAtrasados,prestamosPuntuales);
        socio.setConducta(conducta);
        int indice = buscarSocioIndice(dniSocio);
        listaSocios.add(indice,socio);
    }
    /*public void borrarConducta(int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        socio.setConducta(null);
    }*/
    public void actualizarConducta(Conducta conducta, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        socio.setConducta(conducta);
    }

    public Conducta buscarConducta(int dni){
        for(Socio persona: listaSocios){
            if(persona.getDni() == dni){
                return persona.getConducta();
            }
        }
        return null;
    }

    public int buscarConductaIndice(int dni){
        return buscarSocioIndice(dni);
    }

    //CRUD Suspension
    public void crearSuspenion(LocalDate fecha, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        int indice = buscarSocioIndice(dniSocio);
        Conducta conducta =socio.getConducta();
        List<Suspension> suspensionesActuales =conducta.getSuspensiones();
        suspensionesActuales.add(new Suspension(fecha));
        conducta.setSuspensiones(suspensionesActuales);
        socio.setConducta(conducta);
        listaSocios.add(indice,socio);
    }

    /*public void borrarSuspension(Suspension suspension, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        Conducta conducta =socio.getConducta();
        List<Suspension> suspensionesActuales =conducta.getSuspensiones();
        suspensionesActuales.remove(suspension);
        conducta.setSuspensiones(suspensionesActuales);
        socio.setConducta(conducta);
    }*/


    public void actualizarSuspension(Suspension suspension, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        int indiceSocio =buscarSocioIndice(dniSocio);
        Conducta conducta =socio.getConducta();
        List<Suspension> suspensionesActuales =conducta.getSuspensiones();
        int indice= suspensionesActuales.indexOf(suspension);
        suspensionesActuales.set(indice, suspension);
        conducta.setSuspensiones(suspensionesActuales);
        socio.setConducta(conducta);
        listaSocios.add(indiceSocio,socio);
    }

    public void regularizarSituacion(LocalDate fecha, int dniSocio){
        Socio socio = buscarSocio(dniSocio);
        int indiceSocio = buscarSocioIndice(dniSocio);

        //le agregamos una fecha de fin a la suspension
        List<Suspension> suspensiones = socio.getConducta().getSuspensiones();
        int indice = suspensiones.size()-1;
        Suspension suspensionActual = suspensiones.get(indice);
        suspensionActual.setFechaFin(fecha);
        suspensiones.add(indice,suspensionActual);

        socio.getConducta().setSuspensiones(suspensiones);

        //le quitamos 10 dias atrasados
        int diasAtrasados = socio.getConducta().getDiasAtradados();
        diasAtrasados=diasAtrasados-10;
        socio.getConducta().setDiasAtradados(diasAtrasados);

        listaSocios.add(indiceSocio,socio);
        System.out.println("La situacion ha sido regularizada exitosamente con el bibliotecario.");
        System.out.println("La suspension del socio "+socio.getDni()+" ha finalizado el "+fecha.toString()+".");
        System.out.println("Se han restado 10 dias atrasados de su historial. Cuenta actualmente con "+socio.getConducta().getDiasAtradados()+" dias atrasados.");
    }


    public Suspension buscarSuspension(LocalDate fechaInicio){
        for(Socio socio: listaSocios){
            List<Suspension> suspensiones = socio.getConducta().getSuspensiones();
            for(Suspension s : suspensiones){
                if(s.getFechaInicio()==fechaInicio){
                    return s;
                }
            }
        }
        return null;
    }


}
