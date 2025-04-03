package it.unimol.esame_175083.test;

import it.unimol.esame_175083.app.Utente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtenteTest {

    @Test
    public void testUtenteConstruction() {
        Utente utente = new Utente(1, "John", "Doe");
        assertEquals(1, utente.getId(), "Id uguale a 1");
        assertEquals("John", utente.getNome(), "Nome uguale a John");
        assertEquals("Doe", utente.getCognome(), "Cognome uguale a Doe");
    }

}