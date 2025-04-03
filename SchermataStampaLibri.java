package it.unimol.esame_175083.ui;

import it.unimol.esame_175083.app.GestoreLibri;

import java.io.IOException;


public class SchermataStampaLibri implements Schermata{

    private GestoreLibri libri;

    public SchermataStampaLibri(GestoreLibri libri){
        this.libri = libri;
    }

    public void esegui(){
        try {
            libri.stampaLibri();
        } catch (IOException e) {
            System.err.println("Errore nella lettura dal file per la stampa dei libri!");
        }
    }
}