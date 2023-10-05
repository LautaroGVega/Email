package com.example;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {
    private List<Correo> bandejaDeEnviados;

    public EmailManager() {
        bandejaDeEnviados = new ArrayList<>();
    }

    public void enviarCorreo(Correo correo) {
        // Agregar el correo a la bandeja de enviados del remitente
        Contact remitente = correo.getRemitente();
        remitente.getBandeja().agregarCorreoEnviado(correo);

        // Agregar el correo a la bandeja de recibidos de los destinatarios
        List<Contact> destinatarios = correo.getDestinatarios();
        for (Contact destinatario : destinatarios) {
            destinatario.getBandeja().agregarCorreoRecibido(correo);
        }

        // Agregar el correo a la bandeja de enviados del EmailManager
        bandejaDeEnviados.add(correo);
    }
}
