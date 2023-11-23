package springboot.tienda.model.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PlataformasVideojuegosId implements Serializable {

    private int videojuegoId;
    private int plataformaId;

    public PlataformasVideojuegosId() {}

    public PlataformasVideojuegosId(int videojuegoId, int plataformaId) {
        this.videojuegoId = videojuegoId;
        this.plataformaId = plataformaId;
    }

    public int getVideojuegoId() {
        return videojuegoId;
    }

    public void setVideojuegoId(int videojuegoId) {
        this.videojuegoId = videojuegoId;
    }

    public int getPlataformaId() {
        return plataformaId;
    }

    public void setPlataformaId(int plataformaId) {
        this.plataformaId = plataformaId;
    }

}
