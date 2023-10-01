package src.Finca;

import java.util.ArrayList;
import src.Colmena.Colmena;

public class Sector {
    private int sectorNumber;
    private int maxColmenas;
    private ArrayList<Colmena> colmenas;

    public Boolean isFull(){
        return colmenas.size() == maxColmenas;
    }

    public void addColmena(Colmena colmena){
        colmena.setSectorNumber(this.sectorNumber);
        colmenas.add(colmena);
    }
}
