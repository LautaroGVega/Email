package com.example;

import java.util.List;

public class Contact {
    private String nombre;
    private String email;
    private Bandeja bandeja;

    public Contact(String nombre, String email) {
        this.nombre = nombre;
        // Validar que el email contenga el símbolo "@" antes de asignarlo
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("El email no es válido.");
        }
        this.bandeja = new Bandeja();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Bandeja getBandeja() {
        return bandeja;
    }
    
}
