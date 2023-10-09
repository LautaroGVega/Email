package com.example;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public static List<Correo> filtrarPorAsuntoFacultad(List<Correo> correos) {
        return correos.stream()
                .filter(correo -> correo.getAsunto().toLowerCase().contains("facultad"))
                .collect(Collectors.toList());
    }
    
    public static List<Correo> filtrarPorAsuntoFutbol(List<Correo> correos) {
        return correos.stream()
                .filter(correo -> correo.getAsunto().toLowerCase().contains("futbol"))
                .collect(Collectors.toList());
    }
    
    public static List<Correo> filtrarPorAsuntoTrabajo(List<Correo> correos) {
        return correos.stream()
                .filter(correo -> correo.getAsunto().toLowerCase().contains("trabajo"))
                .collect(Collectors.toList());
    }

    public static List<Correo> filtrarPorAsuntoPalabra(List<Correo> correos, String palabraClave) {
        return correos.stream()
                .filter(correo -> correo.getAsunto().toLowerCase().contains(palabraClave.toLowerCase()))
                .collect(Collectors.toList());
    }
     public static List<Correo> filtrarPorAsuntoPalabraEnContenido(List<Correo> correos, String palabraClave) {
        return correos.stream()
                .filter(correo -> correo.getContenido().toLowerCase().contains(palabraClave.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public static List<Correo> filtrarPorDireccionUCP(List<Correo> correos) {
        return correos.stream()
                .filter(correo -> correo.getRemitente().getEmail().toLowerCase().contains("@ucp.edu.ar"))
                .collect(Collectors.toList());
    }
    
    public static List<Correo> filtrarPorDireccionYAsunto(List<Correo> correos) {
        return correos.stream()
                .filter(correo -> correo.getRemitente().getEmail().toLowerCase().contains("@gmail.com")
                        && correo.getAsunto().toLowerCase().contains("estudiar"))
                .collect(Collectors.toList());
    }
    public static List<Correo> filtrarPorDireccionYAsuntoBuscar(List<Correo> correos, String direccion, String palabraClave) {
        return correos.stream()
                .filter(correo -> correo.getRemitente().getEmail().toLowerCase().contains(direccion.toLowerCase())
                        && correo.getAsunto().toLowerCase().contains(palabraClave.toLowerCase()))
                .collect(Collectors.toList());
    }
}
