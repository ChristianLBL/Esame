package it.unimol.esame_175083.ui;

import it.unimol.esame_175083.app.GestoreLibri;
import it.unimol.esame_175083.app.GestoreUtenti;
import it.unimol.esame_175083.app.Libro;
import it.unimol.esame_175083.app.Utente;
import it.unimol.esame_175083.exceptions.IDLibroException;
import it.unimol.esame_175083.exceptions.IDUserException;
import it.unimol.esame_175083.exceptions.LibroGiaPrestatoException;

import java.io.IOException;
import java.util.Scanner;



public class SchermataPrestitoLibro implements Schermata{

    private GestoreLibri libri;
    private GestoreUtenti utenti;

    public SchermataPrestitoLibro(GestoreLibri libri, GestoreUtenti utenti){
        this.libri = libri;
        this.utenti = utenti;
    }

    @Override
    public void esegui(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(this.libri);
        System.out.println("Inserire l'id del libro che si vuole prestare: ");
        int idLibro = Integer.parseInt(scanner.nextLine());


        System.out.println(this.utenti.toString());
        System.out.println("Inserire l'id dell'utente che vuole prestare il libro: ");
        int idUtente = Integer.parseInt(scanner.nextLine());

        try {
            Libro libro = this.libri.getLibro(idLibro);
            Utente utente = this.utenti.getUtente(idUtente);
            libri.setPrestato(libro.getId(), idUtente);
            System.out.println("Il libro è stato prestato correttamente all'utente: \n"+ utente);
        }catch(IDUserException e){
            System.err.println("ID utente inserito non valido");
        } catch (IDLibroException e) {
            System.err.println("ID Libro inserito non valido!");
        } catch (LibroGiaPrestatoException e) {
            System.err.println("Libro già in prestito!");
        } catch (IOException e){
            System.err.println("Errore nel salvataggio del prestito");
        }
    }
}

