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
    

}
