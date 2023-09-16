package src.colmena;

public class Colmena {
    private int id;
    private String ubicacion;
    private int cantidadColmenas;
    private String floracion;
    private String estado;
    private int miel;

    // Constructor
    public Colmena(int id, String ubicacion, int cantidadColmenas, String floracion, String estado, int miel) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.cantidadColmenas = cantidadColmenas;
        this.floracion = floracion;
        this.estado = estado;
        this.miel = miel;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCantidadColmenas() {
        return cantidadColmenas;
    }

    public void setCantidadColmenas(int cantidadColmenas) {
        this.cantidadColmenas = cantidadColmenas;
    }

    public String getFloracion() {
        return floracion;
    }

    public void setFloracion(String floracion) {
        this.floracion = floracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getmiel() {
        return miel;
    }

    public void setmiel(int miel) {
        this.miel = miel;
    }
}
