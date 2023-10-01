package src.Finca;

import java.util.ArrayList;

public class Finca{
    private int id;
    private String nombre;
    private String ubicacion;
    private ArrayList<Sector> sectores;
    private enFloracion floracion;
    

    public Finca(int id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public String getUbicacion(){
        return ubicacion;
    }

    public void setFloracion(enFloracion floracion){
        this.floracion = floracion;
    }
}
