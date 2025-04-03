package it.unimol.esame_175083.ui;

import it.unimol.esame_175083.app.*;
import it.unimol.esame_175083.exceptions.IDLibroException;
import it.unimol.esame_175083.exceptions.LibroNonInPrestitoException;

import java.io.IOException;
import java.util.Scanner;


public class SchermataRestituzioneLibro implements Schermata{

    private GestoreLibri libri;
    private GestoreUtenti utenti;

    public SchermataRestituzioneLibro(GestoreLibri libri, GestoreUtenti utenti){
        this.libri = libri;
        this.utenti = utenti;
    }

    @Override
    public void esegui(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(this.libri);
        System.out.println("Inserire l'id del libro da restituire: ");
        int idLibro = Integer.parseInt(scanner.nextLine());

        try {
            Libro libro = this.libri.getLibro(idLibro);
            libri.setNotPrestato(libro.getId());
            System.out.println("Il libro è stato restituito correttamente!");
        }catch(IDLibroException e) {
            System.err.println("ID libro inserito non valido!");
        }catch(LibroNonInPrestitoException e){
            System.err.println("Il libro con id " + idLibro + " non è in prestito!");
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio della restituzione");
        }
    }
}

