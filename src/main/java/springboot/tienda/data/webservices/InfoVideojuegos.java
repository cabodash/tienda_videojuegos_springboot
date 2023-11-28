package springboot.tienda.data.webservices;

import java.util.List;
import java.util.Map;

public class InfoVideojuegos {
    private List<Map<String, Object>> videojuegos;
    private int totalVideojuegos;

    public InfoVideojuegos(){}

    public InfoVideojuegos(List<Map<String, Object>> videojuegos, int totalVideojuegos) {
        this.videojuegos = videojuegos;
        this.totalVideojuegos = totalVideojuegos;
    }

    public List<Map<String, Object>> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<Map<String, Object>> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public int getTotalVideojuegos() {
        return totalVideojuegos;
    }

    public void setTotalVideojuegos(int totalVideojuegos) {
        this.totalVideojuegos = totalVideojuegos;
    }
}
