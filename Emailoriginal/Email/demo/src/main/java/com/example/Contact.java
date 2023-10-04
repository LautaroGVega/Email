package com.example;

public class Contact {
    // Atributos
    private String nombre;
    private String email;

    // Constructor
    public Contact(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Métodos getter y setter para el nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para el email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString para representar la información de la persona como una cadena
    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", email=" + email + "]";
    }


}
