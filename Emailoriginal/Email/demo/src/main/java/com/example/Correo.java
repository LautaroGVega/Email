package com.example;
import java.util.ArrayList;
import java.util.List;

public class Correo {
    private String asunto;
    private String contenido;
    private Contact remitente;
    private List<Contact> destinatarios;

    public Correo() {
        destinatarios = new ArrayList<>();
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Contact getRemitente() {
        return remitente;
    }

    public void setRemitente(Contact remitente) {
        this.remitente = remitente;
    }

    public List<Contact> getDestinatarios() {
        return destinatarios;
    }

    public void agregarDestinatario(Contact destinatario) {
        destinatarios.add(destinatario);
    }
}
