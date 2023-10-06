package src.Finca;

import java.util.ArrayList;
import src.Colmena.Colmena;

public class Sector {
    private int sectorNumber;
    private int maxColmenas;
    private ArrayList<Colmena> colmenas;
    private enFloracion floracion;
    

    public Sector(int sectorNumber,int maxColmenas){
        this.sectorNumber = sectorNumber;
        this.maxColmenas = maxColmenas;
        this.colmenas = new ArrayList<Colmena>();
    }

    public Boolean isFull(){
        return colmenas.size() == maxColmenas;
    }

    public void addColmena(Colmena colmena){
        colmenas.add(colmena);
    }

    public int getSectorNumber(){
        return this.sectorNumber;
    }

    public void setFloracion(enFloracion floracion){
        this.floracion = floracion;
    }

    public enFloracion getFloracion(){
        return floracion;
    }

    public ArrayList<Colmena> getColmenas(){
        return colmenas;
    }

    @Override
    public String toString(){
        return "\tSector " + sectorNumber + " - " + floracion + "\n" + colmenas;
    }

    public int getMaxColmenas() {
        return maxColmenas;
    }
}
