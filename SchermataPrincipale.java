package it.unimol.esame_175083.ui;

import it.unimol.esame_175083.app.*;

import java.io.IOException;
import java.util.Scanner;

public class SchermataPrincipale implements Schermata {

    private static SchermataPrincipale instance;

    public static SchermataPrincipale getInstance() {
        if (SchermataPrincipale.instance == null) {
            SchermataPrincipale.instance = new SchermataPrincipale();
        }
        return SchermataPrincipale.instance;
    }

    GestoreLibri gestoreLibri;
    GestoreUtenti gestoreUtenti;

    public SchermataPrincipale() {
    }

    public void esegui() throws IOException {
        try {

            GestoreLibri.initialize();
            GestoreUtenti.initialize();

        } catch (IOException e) {

            System.err.println("Non è stato possibile leggere il file. Eliminare il file.");
            System.exit(-1);
        }

        this.gestoreLibri = GestoreLibri.getInstance();
        this.gestoreUtenti = GestoreUtenti.getInstance();

        System.out.println("Benvenuto!");

        boolean esci;

        do {
            stampaMenu();
            esci = gestisciInputUtente();
        } while (!esci);
    }

    public void stampaMenu() {
        System.out.println("1. Inserisci libro in biblioteca.");
        System.out.println("2. Inserisci utente.");
        System.out.println("3. Prestito libro.");
        System.out.println("4. Restituzione libro.");
        System.out.println("5. Visualizzi tutti i libri.");
        System.out.println("0. Esci.");
    }

    public boolean gestisciInputUtente() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                System.out.println("*** INSERIMENTO LIBRO ***");
                this.avviaInserimentoLibro();
                return false;
            case 2:
                System.out.println("*** INSERIMENTO UTENTE ***");
                this.avviaInserimentoUtente();
                return false;
            case 3:
                System.out.println("*** PRESTITO LIBRO ***");
                this.avviaPrestitoLibro();
                return false;
            case 4:
                System.out.println("*** RESTITUZIONE LIBRO ***");
                this.avviaRestituzioneLibro();
                return false;
            case 5:
                System.out.println("*** VISUALIZZA TUTTI I LIBRI ***");
                this.avviaStampaLibri();
                return false;
            case 0:
                return true;
            default:
                System.out.println("Scelta non valida!");
                return false;
        }
    }

    public void avviaInserimentoLibro() throws IOException {
        Schermata schermata = new SchermataInserimentoLibro(this.gestoreLibri);
        schermata.esegui();
    }

    public void avviaInserimentoUtente() throws IOException{
        Schermata schermata = new SchermataInserimentoUtente(this.gestoreUtenti);
        schermata.esegui();
    }

    public void avviaPrestitoLibro() throws IOException{
        Schermata schermata = new SchermataPrestitoLibro(this.gestoreLibri, this.gestoreUtenti);
        schermata.esegui();
    }

    public void avviaRestituzioneLibro() throws IOException {
        Schermata schermata = new SchermataRestituzioneLibro(this.gestoreLibri, this.gestoreUtenti);
        schermata.esegui();
    }

    public void avviaStampaLibri() throws IOException{
        Schermata schermata = new SchermataStampaLibri(this.gestoreLibri);
        schermata.esegui();
    }


}
