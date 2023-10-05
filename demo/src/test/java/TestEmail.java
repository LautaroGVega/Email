import com.example.Contact;
import com.example.Correo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
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
    
            // Crear un correo electrónico
            Correo email = new Correo();
            email.setAsunto("Prueba UCP");
            email.setContenido("Estamos en la materia Paradigmas 2");
            email.setRemitente(contacto1);
            email.agregarDestinatario(contacto2);
    
            assertEquals("Prueba UCP", email.getAsunto());
            assertEquals("Estamos en la materia Paradigmas 2", email.getContenido());
            assertEquals(contacto1, email.getRemitente());
            assertTrue(email.getDestinatarios().contains(contacto2));
        }
    }

