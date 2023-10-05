package src.Finca;

import java.io.Serializable;
import java.util.ArrayList;

public class Finca implements Serializable{
    private int id;
    private String nombre;
    private String ubicacion;
    private ArrayList<Sector> sectores;
    

    public Finca(int id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.sectores = new ArrayList<Sector>();
    }

    public void addSector(Sector sector){
        sectores.add(sector);
    }

    public ArrayList<Sector> getSectores(){
        return sectores;
    }

    public String getUbicacion(){
        return ubicacion;
    }

    public int getID(){
        return id;
    }

	public String getNombre() {
        return nombre;
	}

    @Override
    public String toString(){
        return id+ ") Finca: " + nombre + " - " + ubicacion+"\n"+sectores;
    }
}
