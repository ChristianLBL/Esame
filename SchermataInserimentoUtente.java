package it.unimol.esame_175083.ui;

import it.unimol.esame_175083.app.*;

import java.io.IOException;
import java.util.Scanner;

public class SchermataInserimentoUtente implements Schermata{

    private GestoreUtenti gestoreUtenti;
    public SchermataInserimentoUtente(GestoreUtenti gestoreUtenti){
        this.gestoreUtenti = gestoreUtenti;
    }

    @Override
    public void esegui() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nome utente: ");
        String nome = scanner.nextLine();

        System.out.println("Cognome utente: ");
        String cognome = scanner.nextLine();

        Utente utente = this.gestoreUtenti.aggiungiUtente(nome, cognome);
        System.out.println("L'utente è stato inserito correttamente!\n Ecco i dettagli: " + utente);
    }
}

