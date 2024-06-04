package prog_2_adts.classes;

import java.util.Date;

public class Cancion {
    private String titulo;
    private String artista;
    private Integer posicion;
    private Date fecha;
    private int tempo;
    private String pais;

    public Cancion(String titulo, String artista, Integer posicion, Date fecha, int tempo) {
        this.titulo = titulo;
        this.artista = artista;
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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
