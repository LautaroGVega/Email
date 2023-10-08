package com.example;

public class Contact {
    private String nombre;
    private String email;
    private Bandeja bandeja;

    public Contact(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
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
