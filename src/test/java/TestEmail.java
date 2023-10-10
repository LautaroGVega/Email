import com.example.Bandeja;
import com.example.Contact;
import com.example.Correo;
import com.example.EmailManager;
import com.example.Filter;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import org.junit.Assert;
import org.junit.Test;


public class TestEmail {
    @Test
    public void verificarContact() {
        // Crear tres instancias de Contact con nombres y Correos diferentes
        Contact Contact1 = new Contact("Juan Pérez", "juan@example.com");
        Contact Contact2 = new Contact("María Rodríguez", "maria@example.com");
        Contact Contact3 = new Contact("Carlos González", "carlos@example.com");

        // Mostrar la información de las tres Contacts
        System.out.println("Contact 1: " + Contact1);
        System.out.println("Contact 2: " + Contact2);
        System.out.println("Contact 3: " + Contact3);

        // Verificar que los nombres y Correos sean los esperados
        assertEquals("Juan Pérez", Contact1.getNombre());
        assertEquals("juan@example.com", Contact1.getEmail());

        assertEquals("María Rodríguez", Contact2.getNombre());
        assertEquals("maria@example.com", Contact2.getEmail());

        assertEquals("Carlos González", Contact3.getNombre());
        assertEquals("carlos@example.com", Contact3.getEmail());
    }
   
    @Test
    public void verificarCorreo() {
            // Crear contactos
            Contact contacto1 = new Contact("Juan Pérez", "juan@example.com");
            Contact contacto2 = new Contact("María Rodríguez", "maria@example.com");
            Contact contacto3 = new Contact("Mariano", "mariano@example.com");
            // Crear un Correo electrónico
            Correo Correo = new Correo();
            Correo.setAsunto("Prueba UCP");
            Correo.setContenido("Estamos en la materia Paradigmas 2");
            Correo.setRemitente(contacto1);
            Correo.agregarDestinatario(contacto2);
            Correo.agregarDestinatario(contacto3);
            assertEquals("Prueba UCP", Correo.getAsunto());
            assertEquals("Estamos en la materia Paradigmas 2", Correo.getContenido());
            assertEquals(contacto1, Correo.getRemitente());
            assertTrue(Correo.getDestinatarios().contains(contacto2));
            assertTrue(Correo.getDestinatarios().contains(contacto3));
        }
    @Test
    public void testCorreo() {
        // Crear un remitente
        Contact remitente = new Contact("Remitente", "remitente@example.com");

        // Crear un Correo
        Correo Correo = new Correo();
        Correo.setAsunto("¡Hola!");
        Correo.setContenido("Este es un Correo de prueba.");
        Correo.setRemitente(remitente);

        // Agregar destinatarios
        Contact destinatario1 = new Contact("Destinatario 1", "destinatario1@example.com");
        Contact destinatario2 = new Contact("Destinatario 2", "destinatario2@example.com");
        Correo.agregarDestinatario(destinatario1);
        Correo.agregarDestinatario(destinatario2);

        // Verificar que los datos del Correo sean correctos
        assertEquals("¡Hola!", Correo.getAsunto());
        assertEquals("Este es un Correo de prueba.", Correo.getContenido());
        assertEquals(remitente, Correo.getRemitente());

        // Verificar que los destinatarios se hayan agregado correctamente
        assertEquals(2, Correo.getDestinatarios().size());
        assertEquals(destinatario1, Correo.getDestinatarios().get(0));
        assertEquals(destinatario2, Correo.getDestinatarios().get(1));
    }
    @Test
    public void testEnviarCorreo() {
        EmailManager EmailManager = new EmailManager();
        
        Contact remitente = new Contact("Remitente", "remitente@example.com");
        Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
        
        Correo Correo = new Correo();
        Correo.setRemitente(remitente);
        Correo.agregarDestinatario(destinatario);
        Correo.setAsunto("Asunto del Correo");
        Correo.setContenido("Contenido del Correo");

        EmailManager.enviarCorreo(Correo);

        // Verificar que el Correo esté en la bandeja de enviados del remitente
        Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
        assertEquals(1, bandejaEnviadosRemitente.getCorreosEnviados().size());

        // Verificar que el Correo esté en la bandeja de recibidos del destinatario
        Bandeja bandejaRecibidosDestinatario = destinatario.getBandeja();
        assertEquals(1, bandejaRecibidosDestinatario.getCorreosRecibidos().size());
    }
    @Test
    public void testEnviarCorreoConFiltroDentroPorFacultadEnAsunto() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    Correo.agregarDestinatario(destinatario);
    Correo.setAsunto("Asunto del Correo para Facultad A"); // Asunto que hace referencia a la facultad
    
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
    
    // Usar Stream y filter para filtrar por asunto que contiene "Facultad A"
    List<Correo> CorreosEnviadosConAsuntoFacultad = bandejaEnviadosRemitente.getCorreosEnviados().stream()
            .filter(CorreoEnviado -> CorreoEnviado.getAsunto().contains("Facultad A"))
            .collect(Collectors.toList());
    
    assertEquals(1, CorreosEnviadosConAsuntoFacultad.size()); // Debería haber un Correo con asunto que contiene "Facultad A"

    // Verificar que el Correo no esté en la bandeja de enviados del destinatario
    Bandeja bandejaEnviadosDestinatario = destinatario.getBandeja();
    assertEquals(0, bandejaEnviadosDestinatario.getCorreosEnviados().size());
}
@Test
public void testFiltrarPorAsuntoFacultad() {
 
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@ucp.edu.ar");
    Contact destinatario = new Contact("Destinatario", "destinatario@gmail.com");
    
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    Correo.agregarDestinatario(destinatario);
    Correo.setAsunto("Asunto del Correo para Facultad A"); // Asunto que hace referencia a la facultad
    
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();

    // Filtrar los correos de Bandeja usando el método de Filter
    List<Correo> correosFiltrados = Filter.filtrarPorAsuntoFacultad(bandejaEnviadosRemitente.getCorreosEnviados());

    // Verificar que se filtraron los correos correctamente
    assertEquals(1, correosFiltrados.size()); // Debería haber un correo con asunto "Facultad"
}
@Test
public void testEnviarCorreoConFiltroPorTrabajoEnAsunto() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    Correo.agregarDestinatario(destinatario);
    Correo.setAsunto("Asunto del Correo para Trabajo"); // Asunto que hace referencia a la facultad
    
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
        
      // Filtrar los correos de Bandeja usando el método de Filter
    List<Correo> correosFiltrados = Filter.filtrarPorAsuntoTrabajo(bandejaEnviadosRemitente.getCorreosEnviados());

    // Verificar que se filtraron los correos correctamente
    assertEquals(1, correosFiltrados.size()); // Debería haber un correo con asunto "Facultad"
}
@Test
public void testEnviarCorreoConFiltroPorFutbolEnAsunto() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    Correo.agregarDestinatario(destinatario);
    Correo.setAsunto("Asunto del Correo para Futbol"); // Asunto que hace referencia a la facultad
    
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
        
    // Filtrar los correos de Bandeja usando el método de Filter
    List<Correo> correosFiltrados = Filter.filtrarPorAsuntoFutbol(bandejaEnviadosRemitente.getCorreosEnviados());

    // Verificar que se filtraron los correos correctamente
    assertEquals(1, correosFiltrados.size()); // Debería haber un correo con asunto "Facultad"
}

@Test
public void testEnviarCorreoConFiltroPorAsuntos() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    Correo.agregarDestinatario(destinatario);
    Correo.setAsunto("messi"); // Asunto que hace referencia a la facultad
    
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaRecibidosRemitente = destinatario.getBandeja();
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();

     // Filtrar los correos de Bandeja usando el método de Filter
    List<Correo> correosFiltrados = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosRemitente.getCorreosRecibidos(), "messi");
    List<Correo> correosFiltrados1 = Filter.filtrarPorAsuntoPalabra(bandejaEnviadosRemitente.getCorreosEnviados(), "messi");
    // Verificar que se filtraron los correos correctamente
    
    // Assert para confirmar cuántos Correos hacen referencia a la facultad
    assertEquals(1, correosFiltrados.size()); // Debería haber un correo con asunto "Facultad"
    
    assertEquals(1, correosFiltrados1.size());
}

@Test
public void testEnviarCorreoConFiltroPorAsuntosPlural() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    
    // Crear 6 destinatarios con facultades diferentes
    Contact destinatario1 = new Contact("Destinatario1", "destinatario1@example.com");
    Contact destinatario2 = new Contact("Destinatario2", "destinatario2@example.com");
    Contact destinatario3 = new Contact("Destinatario3", "destinatario3@example.com");
    Contact destinatario4 = new Contact("Destinatario4", "destinatario4@example.com");
    Contact destinatario5 = new Contact("Destinatario5", "destinatario5@example.com");
    Contact destinatario6 = new Contact("Destinatario6", "destinatario6@example.com");
    
    // Crear un Correo con asunto "messi"
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    
    // Agregar los 6 destinatarios al Correo
    Correo.agregarDestinatario(destinatario1);
    Correo.agregarDestinatario(destinatario2);
    Correo.agregarDestinatario(destinatario3);
    Correo.agregarDestinatario(destinatario4);
    Correo.agregarDestinatario(destinatario5);
    Correo.agregarDestinatario(destinatario6);
    
    Correo.setAsunto("messi"); // Asunto que hace referencia a la facultad
    
    // Enviar el Correo a los 6 destinatarios
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en las bandejas de enviados y recibidos de cada destinatario
    Bandeja bandejaRecibidosDestinatario1 = destinatario1.getBandeja();
    Bandeja bandejaRecibidosDestinatario2 = destinatario2.getBandeja();
    Bandeja bandejaRecibidosDestinatario3 = destinatario3.getBandeja();
    Bandeja bandejaRecibidosDestinatario4 = destinatario4.getBandeja();
    Bandeja bandejaRecibidosDestinatario5 = destinatario5.getBandeja();
    Bandeja bandejaRecibidosDestinatario6 = destinatario6.getBandeja();
    
    List<Correo> correosFiltrados1 = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosDestinatario1.getCorreosRecibidos(), "messi");
    List<Correo> correosFiltrados2 = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosDestinatario2.getCorreosRecibidos(), "messi");
    List<Correo> correosFiltrados3 = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosDestinatario3.getCorreosRecibidos(), "messi");
    List<Correo> correosFiltrados4 = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosDestinatario4.getCorreosRecibidos(), "messi");
    List<Correo> correosFiltrados5 = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosDestinatario5.getCorreosRecibidos(), "messi");
    List<Correo> correosFiltrados6 = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosDestinatario6.getCorreosRecibidos(), "messi");

    // Assert para confirmar cuántos Correos hacen referencia a la facultad
    assertEquals(1, correosFiltrados1.size());
    assertEquals(1, correosFiltrados2.size());
    assertEquals(1, correosFiltrados3.size());
    assertEquals(1, correosFiltrados4.size());
    assertEquals(1, correosFiltrados5.size());
    assertEquals(1, correosFiltrados6.size());

}
@Test
public void testEnviar100CorreosDesdeRemitenteADestinatario() {
    EmailManager emailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");

    // Enviar 100 correos del remitente al destinatario
    for (int i = 1; i <= 100; i++) {
        Correo correo = new Correo();
        correo.setRemitente(remitente);
        correo.agregarDestinatario(destinatario);
        correo.setAsunto("Asunto del Correo para Futbol");
        correo.setContenido("jugar Futbol");
        emailManager.enviarCorreo(correo);
    }

    // Verificar que el Correo esté en la bandeja de recibidos del destinatario
    Bandeja bandejaRecibidosDestinatario = destinatario.getBandeja();
        
    // Verificar que se recibieron los 100 correos correctamente
    assertEquals(101, bandejaRecibidosDestinatario.getCorreosRecibidos().size()); // Debería haber 100 correos en la bandeja de recibidos del destinatario
}

@Test
public void testEnviarCorreoConFiltroPorAsuntosFor() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    
    // Crear 100 destinatarios con facultades diferentes
    List<Contact> destinatarios = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
        destinatarios.add(new Contact("Destinatario" + i, "destinatario" + i + "@example.com"));
    }
    
    // Crear un Correo con asunto "messi"
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    
    // Agregar los 100 destinatarios al Correo
    destinatarios.forEach(Correo::agregarDestinatario);
    
    Correo.setAsunto("messi"); // Asunto que hace referencia a la facultad
    
    // Enviar el Correo a los 100 destinatarios
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en las bandejas de recibidos de cada destinatario
    for (Contact destinatario : destinatarios) {
        Bandeja bandejaRecibidosDestinatario = destinatario.getBandeja();
        List<Correo> correosFiltrados1 = Filter.filtrarPorAsuntoPalabra(bandejaRecibidosDestinatario.getCorreosRecibidos(), "messi");
        
        // Assert para confirmar cuántos Correos hacen referencia a la facultad para cada destinatario
        assertEquals(1, correosFiltrados1.size());
    }
}
@Test
public void testEnviarCorreoConFiltroPorContenidoFor() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    
    // Crear 100 destinatarios con facultades diferentes
    List<Contact> destinatarios = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
        destinatarios.add(new Contact("Destinatario" + i, "destinatario" + i + "@example.com"));
    }
    
    // Crear un Correo con asunto "messi"
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    
    // Agregar los 100 destinatarios al Correo
    destinatarios.forEach(Correo::agregarDestinatario);
    
    Correo.setAsunto("messi"); // Asunto que hace referencia a la facultad
    Correo.setContenido("messi");    
    
    // Enviar el Correo a los 100 destinatarios
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en las bandejas de recibidos de cada destinatario
    for (Contact destinatario : destinatarios) {
        Bandeja bandejaRecibidosDestinatario = destinatario.getBandeja();
        List<Correo> correosFiltrados1 = Filter.filtrarPorAsuntoPalabraEnContenido(bandejaRecibidosDestinatario.getCorreosRecibidos(), "messi");
        
        // Assert para confirmar cuántos Correos hacen referencia a la facultad para cada destinatario
        assertEquals(1, correosFiltrados1.size());
    }
}
@Test
public void testEnviarCorreoConFiltroPorUCPFor() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente
    Contact remitente = new Contact("Remitente", "remitente@ucp.edu.ar");
    
    // Crear 100 destinatarios con facultades diferentes
    List<Contact> destinatarios = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
        destinatarios.add(new Contact("Destinatario" + i, "destinatario" + i + "@example.com"));
    }
    
    // Crear un Correo con asunto "messi"
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    
    // Agregar los 100 destinatarios al Correo
    destinatarios.forEach(Correo::agregarDestinatario);
    
    Correo.setAsunto("messi"); // Asunto que hace referencia a la facultad
    Correo.setContenido("messi");    
    
    // Enviar el Correo a los 100 destinatarios
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en las bandejas de recibidos de cada destinatario
    for (Contact destinatario : destinatarios) {
        Bandeja bandejaRecibidosDestinatario = destinatario.getBandeja();
        List<Correo> correosFiltrados1 = Filter.filtrarPorDireccionUCP(bandejaRecibidosDestinatario.getCorreosRecibidos());
        
        // Assert para confirmar cuántos Correos hacen referencia a la facultad para cada destinatario
        assertEquals(1, correosFiltrados1.size());
    }
}
@Test
public void testEnviarCorreoConUCP() {
    EmailManager EmailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@ucp.edu.ar");
    Contact destinatario = new Contact("Destinatario", "destinatario@gmail.com");
    
    Correo Correo = new Correo();
    Correo.setRemitente(remitente);
    Correo.agregarDestinatario(destinatario);
    Correo.setAsunto("Asunto del Correo para Facultad A"); // Asunto que hace referencia a la facultad
    
    EmailManager.enviarCorreo(Correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
    Bandeja bandejaEnviadosDestinatario = destinatario.getBandeja();
    
    // Filtrar los Correos enviados por asunto "Facultad"
    List<Correo> correosFiltrados = Filter.filtrarPorDireccionUCP(bandejaEnviadosDestinatario.getCorreosRecibidos());
    List<Correo> correosFiltrados1 = Filter.filtrarPorDireccionUCP(bandejaEnviadosRemitente.getCorreosEnviados());

    // Assert para confirmar cuántos Correos hacen referencia a la facultad
    assertEquals(1, correosFiltrados.size());
    assertEquals(1, correosFiltrados1.size());
   
}
@Test
public void testEnviarCorreoFilterEnContenido() {
    EmailManager emailManager = new EmailManager();

    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@gmail.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@gmail.com");

    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("Asunto del Correo para Facultad y estudiar"); // Asunto que hace referencia a la facultad
    correo.setContenido("hola me gustaria estudiar en la Facultad");
    
    emailManager.enviarCorreo(correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();

    // Filtrar los Correos enviados por dirección de remitente y asunto "Facultad"
    List<Correo> correosFiltrados = Filter.filtrarPorAsuntoPalabraEnContenido(bandejaEnviadosRemitente.getCorreosEnviados(), "estudiar");

    // Assert para confirmar que el Correo cumple ambos criterios
    assertEquals(1, correosFiltrados.size()); // Debería haber un correo filtrado
}

@Test
public void testEnviarCorreoFilterDoble() {
    EmailManager emailManager = new EmailManager();

    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@gmail.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@gmail.com");

    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("Asunto del Correo para Facultad y estudiar"); // Asunto que hace referencia a la facultad
    correo.setContenido("hola me gustaria estudiar en la Facultad");
    
    emailManager.enviarCorreo(correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();

    // Filtrar los Correos enviados por dirección de remitente y asunto "Facultad"
    List<Correo> correosFiltrados = Filter.filtrarPorDireccionYAsunto(bandejaEnviadosRemitente.getCorreosEnviados());

    // Assert para confirmar que el Correo cumple ambos criterios
    assertEquals(1, correosFiltrados.size()); // Debería haber un correo filtrado
    assertEquals("Asunto del Correo para Facultad y estudiar", correosFiltrados.get(0).getAsunto());
}
@Test
public void testEnviarCorreoFilterDobleConBuscar() {
    EmailManager emailManager = new EmailManager();

    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@gmail.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@gmail.com");

    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("Asunto del Correo para Facultad y estudiar"); // Asunto que hace referencia a la facultad

    emailManager.enviarCorreo(correo);

    // Verificar que el Correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();

    // Filtrar los Correos enviados por dirección de remitente y asunto "Facultad"
    List<Correo> correosFiltrados = Filter.filtrarPorDireccionYAsuntoBuscar(bandejaEnviadosRemitente.getCorreosEnviados(), "@gmail.com", "estudiar");

    // Assert para confirmar que el Correo cumple ambos criterios
    assertEquals(1, correosFiltrados.size()); // Debería haber un correo filtrado
}



 }

  
