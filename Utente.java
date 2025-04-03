package it.unimol.esame_175083.app;

import java.io.Serializable;

public class Utente implements Serializable {

    public static final long serialVersionUID = 1L;

    int id;
    private String nome;
    private String cognome;



    public Utente(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nCognome: " + cognome;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}


