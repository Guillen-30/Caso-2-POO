package src.Finca;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Finca implements Serializable{
    private int id;
    private String nombre;
    private String ubicacion;
    private ArrayList<Sector> sectores;
    

    public Finca(@JsonProperty("id")int id, @JsonProperty("nombre")String nombre, @JsonProperty("ubicacion")String ubicacion) {
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
