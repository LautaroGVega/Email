import com.example.Bandeja;
import com.example.Contact;
import com.example.Correo;
import com.example.EmailManager;
import com.example.Filter;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import org.junit.Assert;
import org.junit.Test;


public class TestEmail {
    @Test
    public void verificarContact() {
        // Crear tres instancias de Contact con nombres y emails diferentes
        Contact Contact1 = new Contact("Juan Pérez", "juan@example.com");
        Contact Contact2 = new Contact("María Rodríguez", "maria@example.com");
        Contact Contact3 = new Contact("Carlos González", "carlos@example.com");

        // Mostrar la información de las tres Contacts
        System.out.println("Contact 1: " + Contact1);
        System.out.println("Contact 2: " + Contact2);
        System.out.println("Contact 3: " + Contact3);

        // Verificar que los nombres y emails sean los esperados
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
            // Crear un correo electrónico
            Correo email = new Correo();
            email.setAsunto("Prueba UCP");
            email.setContenido("Estamos en la materia Paradigmas 2");
            email.setRemitente(contacto1);
            email.agregarDestinatario(contacto2);
            email.agregarDestinatario(contacto3);
            assertEquals("Prueba UCP", email.getAsunto());
            assertEquals("Estamos en la materia Paradigmas 2", email.getContenido());
            assertEquals(contacto1, email.getRemitente());
            assertTrue(email.getDestinatarios().contains(contacto2));
            assertTrue(email.getDestinatarios().contains(contacto3));
        }
    @Test
    public void testCorreo() {
        // Crear un remitente
        Contact remitente = new Contact("Remitente", "remitente@example.com");

        // Crear un correo
        Correo correo = new Correo();
        correo.setAsunto("¡Hola!");
        correo.setContenido("Este es un correo de prueba.");
        correo.setRemitente(remitente);

        // Agregar destinatarios
        Contact destinatario1 = new Contact("Destinatario 1", "destinatario1@example.com");
        Contact destinatario2 = new Contact("Destinatario 2", "destinatario2@example.com");
        correo.agregarDestinatario(destinatario1);
        correo.agregarDestinatario(destinatario2);

        // Verificar que los datos del correo sean correctos
        assertEquals("¡Hola!", correo.getAsunto());
        assertEquals("Este es un correo de prueba.", correo.getContenido());
        assertEquals(remitente, correo.getRemitente());

        // Verificar que los destinatarios se hayan agregado correctamente
        assertEquals(2, correo.getDestinatarios().size());
        assertEquals(destinatario1, correo.getDestinatarios().get(0));
        assertEquals(destinatario2, correo.getDestinatarios().get(1));
    }
    @Test
    public void testEnviarCorreo() {
        EmailManager emailManager = new EmailManager();
        
        Contact remitente = new Contact("Remitente", "remitente@example.com");
        Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
        
        Correo correo = new Correo();
        correo.setRemitente(remitente);
        correo.agregarDestinatario(destinatario);
        correo.setAsunto("Asunto del correo");
        correo.setContenido("Contenido del correo");

        emailManager.enviarCorreo(correo);

        // Verificar que el correo esté en la bandeja de enviados del remitente
        Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
        assertEquals(1, bandejaEnviadosRemitente.getCorreosEnviados().size());

        // Verificar que el correo esté en la bandeja de recibidos del destinatario
        Bandeja bandejaRecibidosDestinatario = destinatario.getBandeja();
        assertEquals(1, bandejaRecibidosDestinatario.getCorreosRecibidos().size());
    }
    @Test
    public void testEnviarCorreoConFiltroDentroPorFacultadEnAsunto() {
    EmailManager emailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("Asunto del correo para Facultad A"); // Asunto que hace referencia a la facultad
    
    emailManager.enviarCorreo(correo);

    // Verificar que el correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
    
    // Usar Stream y filter para filtrar por asunto que contiene "Facultad A"
    List<Correo> correosEnviadosConAsuntoFacultad = bandejaEnviadosRemitente.getCorreosEnviados().stream()
            .filter(correoEnviado -> correoEnviado.getAsunto().contains("Facultad A"))
            .collect(Collectors.toList());
    
    assertEquals(1, correosEnviadosConAsuntoFacultad.size()); // Debería haber un correo con asunto que contiene "Facultad A"

    // Verificar que el correo no esté en la bandeja de enviados del destinatario
    Bandeja bandejaEnviadosDestinatario = destinatario.getBandeja();
    assertEquals(0, bandejaEnviadosDestinatario.getCorreosEnviados().size());
}
@Test
public void testEnviarCorreoConFiltroPorFacultadEnAsunto() {
    EmailManager emailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("Asunto del correo para Facultad A"); // Asunto que hace referencia a la facultad
    
    emailManager.enviarCorreo(correo);

    // Verificar que el correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
    
    // Filtrar los correos enviados por asunto "Facultad"
    List<Correo> correosFiltrados = bandejaEnviadosRemitente.filtrarCorreosEnviadosPorAsuntoFacultad();
    
// Obtener el número de correos encontrados
    int numeroDeCorreosEncontrados = correosFiltrados.size();
    
// Assert para confirmar cuántos correos hacen referencia a la facultad
    assertEquals(1, numeroDeCorreosEncontrados);
}
@Test
public void testEnviarCorreoConFiltroPorTrabajoEnAsunto() {
    EmailManager emailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("Asunto del correo para Trabajo"); // Asunto que hace referencia a la facultad
    
    emailManager.enviarCorreo(correo);

    // Verificar que el correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
    
    // Filtrar los correos enviados por asunto "Facultad"
    List<Correo> correosFiltrados = bandejaEnviadosRemitente.filtrarCorreosEnviadosPorAsuntoTrabajo();
    
// Obtener el número de correos encontrados
    int numeroDeCorreosEncontrados = correosFiltrados.size();
    
// Assert para confirmar cuántos correos hacen referencia a la facultad
    assertEquals(1, numeroDeCorreosEncontrados);
}
@Test
public void testEnviarCorreoConFiltroPorFutbolEnAsunto() {
    EmailManager emailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("Asunto del correo para Futbol"); // Asunto que hace referencia a la facultad
    
    emailManager.enviarCorreo(correo);

    // Verificar que el correo esté en la bandeja de enviados del remitente
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();
    
    // Filtrar los correos enviados por asunto "Facultad"
    List<Correo> correosFiltrados = bandejaEnviadosRemitente.filtrarCorreosEnviadosPorAsuntoFutbol();
    
// Obtener el número de correos encontrados
    int numeroDeCorreosEncontrados = correosFiltrados.size();
    
// Assert para confirmar cuántos correos hacen referencia a la facultad
    assertEquals(1, numeroDeCorreosEncontrados);
}

@Test
public void testEnviarCorreoConFiltroPorAsuntos() {
    EmailManager emailManager = new EmailManager();
    
    // Crear un remitente y destinatario con facultades diferentes
    Contact remitente = new Contact("Remitente", "remitente@example.com");
    Contact destinatario = new Contact("Destinatario", "destinatario@example.com");
    
    Correo correo = new Correo();
    correo.setRemitente(remitente);
    correo.agregarDestinatario(destinatario);
    correo.setAsunto("messi"); // Asunto que hace referencia a la facultad
    
    emailManager.enviarCorreo(correo);

    // Verificar que el correo esté en la bandeja de enviados del remitente
    Bandeja bandejaRecibidosRemitente = destinatario.getBandeja();
    Bandeja bandejaEnviadosRemitente = remitente.getBandeja();

    List<Correo> correosFiltrados = bandejaRecibidosRemitente.filtrarCorreosRecibidosPorAsuntoPalabra(correo, "messi");
    List<Correo> correosFiltrados1 = bandejaEnviadosRemitente.filtrarCorreosEnviadosPorAsuntoPalabra(correo, "messi");

    // Obtener el número de correos encontrados
    int numeroDeCorreosEncontrados = correosFiltrados.size();
    int numeroDeCorreosEncontrados1 = correosFiltrados1.size();
    
    // Assert para confirmar cuántos correos hacen referencia a la facultad
    assertEquals(1, numeroDeCorreosEncontrados);
    assertEquals(1, numeroDeCorreosEncontrados1);
}






    
    }

  
