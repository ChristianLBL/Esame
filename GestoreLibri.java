package it.unimol.esame_175083.app;

import it.unimol.esame_175083.exceptions.IDLibroException;
import it.unimol.esame_175083.exceptions.IDUserException;
import it.unimol.esame_175083.exceptions.LibroGiaPrestatoException;
import it.unimol.esame_175083.exceptions.LibroNonInPrestitoException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GestoreLibri implements Serializable {

    public static final long serialVersionUID = 1L;
    private static GestoreLibri instance;
    private Map<Integer, Libro> libri;

    public GestoreLibri(){
        this.libri = new HashMap<>();
    }

    public static void initialize() throws IOException{
        GestoreLibri.instance = GestoreLibri.load();
    }
    private static GestoreLibri load() throws IOException{
        try(
        FileInputStream fileInputStream = new FileInputStream("Libri.sr");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            Object o = objectInputStream.readObject();
            return (GestoreLibri) o;
        } catch (FileNotFoundException e) {
            return new GestoreLibri();
        } catch (ClassNotFoundException ignore) {
            return null;
        }
    }

    /**
     * Crea un libro dai dati passati dall'utente e lo aggiunge alla collezione dei libri già presenti
     * @param id, l'id del libro
     * @param titolo, il titolo del libro
     * @param autore, nome e cognome dell'autore
     * @return il libro creato con i parametri presi dall'utente
     * @throws IOException perché potrebbero verificarsi errori durante l'apertura o la scrittura sul file
     * @throws IDLibroException, perché l'id che l'utente passa al metodo, potrebbe già esistere come id di un altro libro
     */
    public Libro aggiungiLibro(int id, String titolo, String autore) throws IOException, IDLibroException {
        try {
            if (libri.containsKey(id))
                throw new IDLibroException();
            Libro libro = new Libro(id, titolo, autore);

            this.libri.put(id, libro);
            this.salvaLibri();
            return libro;
        }catch (IOException e) {
            throw new IOException("Errore nel salvataggio del libro nel file!");
        }
    }

    public static GestoreLibri getInstance(){
        return GestoreLibri.instance;
    }

    public void setPrestato(int bookId, int userId) throws LibroGiaPrestatoException, IOException, IDUserException {
        Libro l = libri.get(bookId);
        GestoreUtenti.getInstance().getUtente(userId);
        if(l.isPrestato()){
            throw new LibroGiaPrestatoException();
        }
        else {
            l.setIdUtentePrestato(userId);
            this.salvaLibri();
        }
    }

    public void setNotPrestato(int bookId) throws IOException, LibroNonInPrestitoException {
        Libro l = libri.get(bookId);
        if(!l.isPrestato()){
            throw new LibroNonInPrestitoException();
        }
        else {
            l.setNotPrestato();
            this.salvaLibri();
        }
    }



    public void salvaLibri() throws IOException{
        try(
                FileOutputStream fileOutputStream = new FileOutputStream("Libri.sr");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.writeObject(this);
        }
    }

    public void stampaLibri() throws IOException{
        System.out.println(this);
    }

    public Libro getLibro(int id) throws IDLibroException {
        if(!libri.containsKey(id)){
            throw new IDLibroException();
        }else {
            return libri.get(id);
        }
    }

    @Override
    public String toString() {

        return this.libri.entrySet().stream()
                .map( (p) -> p.getKey() + ": " + p.getValue().toString() + "\n")
                .reduce("", (result, element) -> result + element);

        /* (equivalenti)
        String result = " ";
        for (Map.Entry<Integer, Libro> set : this.libri.entrySet()){
            result += set.getKey() + ". " + set.getValue().toString() + "\n ";
        }
        return result;*/
    }

}
