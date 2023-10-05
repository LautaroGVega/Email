package com.example;

import java.util.List;
import java.util.stream.Collectors;

public class Filtros {
    public static List<Correo> filtrarCorreosUniversidad(Bandeja bandeja) {
        return bandeja.getCorreosEnviados()
                .stream()
                .filter(correo -> correo.getContenido().contains("universidad"))
                .collect(Collectors.toList());
    }
}
