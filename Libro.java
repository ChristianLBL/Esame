package it.unimol.esame_175083.app;

import java.io.Serializable;

public class Libro implements Serializable{
    public static final long serialVersionID = 1L;

    private int id;
    private String titolo;
    private String autore;

    private boolean prestato;
    private Integer idUtentePrestato;

    public Libro(int id, String titolo, String autore){
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
    }

    @Override
    public String toString() {
        return "\nTitolo: " + titolo + " Autore: " + autore + " Id: " + id + " Stato prestito: " + (prestato ? ("prestato") : "libero");
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public boolean isPrestato() {
        return prestato;
    }

    public Integer getIdUtentePrestato() {
        return idUtentePrestato;
    }

    public void setIdUtentePrestato(Integer idUtentePrestato) {
        this.prestato = true;
        this.idUtentePrestato = idUtentePrestato;
    }

    public void setNotPrestato(){
        this.prestato = false;
        this.idUtentePrestato = null;
    }
}
