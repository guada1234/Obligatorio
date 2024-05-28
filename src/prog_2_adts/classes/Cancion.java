package prog_2_adts.classes;

import java.util.Date;

public class Cancion {
    private String titulo;
    private String artista;
    private String posicion;
    private Date fecha;
    private int tempo;

    public Cancion(String titulo, String artista, String posicion, Date fecha, int tempo) {
        this.titulo = titulo;
        this.artista = artista;
        this.posicion = posicion;
        this.fecha = fecha;
        this.tempo = tempo;
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
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
