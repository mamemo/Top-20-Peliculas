package tc2.mamendez.top20movies2017;

import android.graphics.Bitmap;

/**
 * Created by Mauro on 3/23/2018.
 */

public class Pelicula {
    private static int id = 0;
    private int numero;
    private Bitmap portada;
    private String titulo;
    private String estrellas;
    private String metascore;

    public Pelicula(Bitmap portada, String titulo, String estrellas, String metascore) {
        numero = id+1;
        this.portada = portada;
        this.titulo = titulo;
        this.estrellas = estrellas;
        this.metascore = metascore;
        id++;
    }

    public Bitmap getPortada() {
        return portada;
    }

    public void setPortada(Bitmap portada) {
        this.portada = portada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(String estrellas) {
        this.estrellas = estrellas;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return numero + ". " + titulo + "\n" +
                "Estrellas: " + estrellas + '\n' +
                "Metascore: " + metascore;
    }
}
