package com.example;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

public class Bandeja {
    private List<Correo> correosEnviados;
    private List<Correo> correosRecibidos;
   

    public Bandeja() {
        correosEnviados = new ArrayList<>();
        correosRecibidos = new ArrayList<>();
    }

    public List<Correo> getCorreosEnviados() {
        return correosEnviados;
    }

    public List<Correo> getCorreosRecibidos() {
        return correosRecibidos;
    }

    public void agregarCorreoEnviado(Correo correo) {
        correosEnviados.add(correo);
    }

    public void agregarCorreoRecibido(Correo correo) {
        correosRecibidos.add(correo);
    }
    
//asfhiuasiufhasjfsa
///aspfasfasf
//asfasfasfasf







    
    public List<Correo> filtrarCorreosEnviadosPorAsuntoFacultad() {
        return Filter.filtrarPorAsuntoFacultad(correosEnviados);
    }
    public List<Correo> filtrarCorreosEnviadosPorAsuntoFutbol() {
        return Filter.filtrarPorAsuntoFutbol(correosEnviados);
    }
    public List<Correo> filtrarCorreosEnviadosPorAsuntoTrabajo() {
        return Filter.filtrarPorAsuntoTrabajo(correosEnviados);
    }
    public List<Correo> filtrarCorreosRecibidosPorAsuntoFacultad() {
        return Filter.filtrarPorAsuntoFacultad(correosRecibidos);
    }
    public List<Correo> filtrarCorreosRecibidosPorAsuntoFutbol() {
        return Filter.filtrarPorAsuntoFutbol(correosRecibidos);
    }
    public List<Correo> filtrarCorreosRecibidosPorAsuntoTrabajo() {
        return Filter.filtrarPorAsuntoTrabajo(correosRecibidos);
    }
    public List<Correo> filtrarCorreosRecibidosPorAsuntoPalabra(Correo correo, String string) {
        return Filter.filtrarPorAsuntoPalabra(correosRecibidos, string);
    }
    public List<Correo> filtrarCorreosEnviadosPorAsuntoPalabra(Correo correo, String string) {
        return Filter.filtrarPorAsuntoPalabra(correosEnviados, string);
    }
    public List<Correo> filtrarCorreosEnviadosUCP(Correo correo) {
        return Filter.filtrarPorDireccionUCP(correosEnviados);
    }
    public List<Correo> filtrarCorreosRecibidosUCP(Correo correo) {
        return Filter.filtrarPorDireccionUCP(correosRecibidos);
    }
}
