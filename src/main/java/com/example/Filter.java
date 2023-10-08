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
}
