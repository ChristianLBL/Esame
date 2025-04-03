package it.unimol.esame_175083.ui;

import it.unimol.esame_175083.app.*;
import it.unimol.esame_175083.exceptions.IDLibroException;

import java.io.IOException;
import java.util.Scanner;

public class SchermataInserimentoLibro implements Schermata{

    private GestoreLibri libri;

    public SchermataInserimentoLibro(GestoreLibri libri){
        this.libri = libri;
    }

    public void esegui() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID Libro: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Titolo Libro: ");
        String titolo = scanner.nextLine();

        System.out.println("Autore Libro: ");
        String autore = scanner.nextLine();

        try {
                Libro libro = this.libri.aggiungiLibro(id, titolo, autore);
                System.out.println("Libro inserito correttamente! \n Ecco i dettagli: " + libro);

        } catch (IDLibroException e){
            System.err.println("Id libro già esistente!");
        }
    }
}