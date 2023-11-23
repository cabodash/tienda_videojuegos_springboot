package springboot.tienda.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import springboot.tienda.model.embedded.PlataformasVideojuegosId;

@Entity
@Table(name = "plataformas_videojuegos")
public class PlataformasVideojuegos implements Serializable{

    @EmbeddedId
    private PlataformasVideojuegosId id;

    @ManyToOne
    @JoinColumn(name = "videojuego_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Videojuego videojuego;

    @ManyToOne
    @JoinColumn(name = "plataforma_id", referencedColumnName = "id", insertable = false, updatable = false) // Added referencedColumnName
    private Plataforma plataforma;

    PlataformasVideojuegos(){}

    public PlataformasVideojuegos(Videojuego videojuego, Plataforma plataforma) {
        this.videojuego = videojuego;
        this.plataforma = plataforma;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }


    
}
