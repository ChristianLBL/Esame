package it.unimol.esame_175083.app;

import it.unimol.esame_175083.exceptions.IDUserException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GestoreUtenti implements Serializable {
    public static final long serialVersionUID = 1L;
    private static GestoreUtenti instance;

    public static void initialize() throws IOException {
        GestoreUtenti.instance = GestoreUtenti.load();
    }
    public static GestoreUtenti getInstance(){
        return GestoreUtenti.instance;
    }

    private static GestoreUtenti load() throws IOException{
        try(
                FileInputStream fileInputStream = new FileInputStream("Utenti.sr");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            Object o = objectInputStream.readObject();
            return (GestoreUtenti) o;
        }catch(FileNotFoundException e){
            return new GestoreUtenti();
        }catch(ClassNotFoundException ignore){
            return null;
        }
    }

    private Map<Integer, Utente> utenti;

    private int ultimoID;

    public GestoreUtenti(){
        this.utenti = new HashMap<>();
        this.ultimoID = 0;
    }

    public Utente aggiungiUtente(String nome, String cognome) throws IOException{
        this.ultimoID++;
        int id = this.ultimoID;

        try{
            Utente utente = new Utente(id, nome, cognome);
            this.utenti.put(id, utente);
            this.salvaGestoreUtenti();
            return utente;
        }catch(IOException e){
            this.ultimoID--;
            throw new IOException("Errore nel salvataggio dell'utente nel file!");
        }
    }

    private void salvaGestoreUtenti() throws IOException{
        try(
                FileOutputStream fileOutputStream = new FileOutputStream("Utenti.sr");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.writeObject(this);
        }
    }

    public Utente getUtente(int id) throws IDUserException {
        if(!this.utenti.containsKey(id)){
            throw new IDUserException();
        }
        return this.utenti.get(id);
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Integer, Utente> set : this.utenti.entrySet()){
            result += set.getKey() + ". " + set.getValue().toString() + " \n";
        }
        return result;
    }
}
