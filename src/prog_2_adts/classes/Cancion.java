package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

public class Cancion implements Comparable<Integer> {
    private String titulo;
    private MyList<Artista> artista;
    private Integer posicion;
    private LocalDate fecha;
    private float tempo;
    private String pais;

    public Cancion(String titulo, MyList<Artista> artista, Integer posicion, LocalDate fecha, float tempo, String pais) {
        this.titulo = titulo;
        this.artista = new MyLinkedListImpl<>();
        this.posicion = posicion;
        this.fecha = fecha;
        this.tempo = tempo;
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public MyList getArtista() {
        return artista;
    }

    public void setArtista(MyList artista) {
        this.artista = artista;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    @Override
    public int compareTo(Integer o) {
        return 0;
    }
}
