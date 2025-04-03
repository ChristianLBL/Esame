package it.unimol.esame_175083.test;
import it.unimol.esame_175083.app.GestoreLibri;
import it.unimol.esame_175083.app.Libro;
import it.unimol.esame_175083.exceptions.IDLibroException;
import it.unimol.esame_175083.exceptions.IDUserException;
import it.unimol.esame_175083.exceptions.LibroGiaPrestatoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GestoreLibriTest {
    private GestoreLibri gestoreLibri;

    @BeforeEach
    public void setUp() throws IOException {
        GestoreLibri.initialize();
        gestoreLibri = GestoreLibri.getInstance();
    }

    @Test
    public void testAggiungiLibro() throws IOException, IDLibroException {
        Libro libro = gestoreLibri.aggiungiLibro(1, "The Catcher in the Rye", "J.D. Salinger");
        assertEquals(1, libro.getId(), "ID del libro appena inserito uguale a 1");
        assertEquals("The Catcher in the Rye", libro.getTitolo(), "Titolo del libro uguale a 'The Catcher in the Rye'");
        assertEquals("J.D. Salinger", libro.getAutore(), "Autore del libro uguale a 'J.D. Salinger'");
        assertFalse(libro.isPrestato(), "Libro creato ma mai prestato");
        assertNull(libro.getIdUtentePrestato(), "Id utente che ha preso in prestito il libro sarà null appena creato il libro");
    }

    @Test
    public void testAggiungiLibroWithExistingID() throws IOException, IDLibroException {
        gestoreLibri.aggiungiLibro(1, "The Catcher in the Rye", "J.D. Salinger");
        assertThrows(IDLibroException.class, () -> gestoreLibri.aggiungiLibro(1, "The Great Gatsby", "F. Scott Fitzgerald"),
                "Id libro già esistente");
    }

    @Test
    public void testSetPrestato() throws LibroGiaPrestatoException, IOException, IDLibroException, IDUserException {
        gestoreLibri.aggiungiLibro(1, "The Catcher in the Rye", "J.D. Salinger");
        gestoreLibri.setPrestato(1, 1);
        assertTrue(gestoreLibri.getLibro(1).isPrestato(), "Libro con id 1 prestato");
        assertEquals(1, gestoreLibri.getLibro(1).getIdUtentePrestato(),
                "Libro con id 1 prestato all'utente con id 1");

        assertThrows(LibroGiaPrestatoException.class, () -> gestoreLibri.setPrestato(1, 1),
                "Eccezioni Libro già in prestito");
    }
}
