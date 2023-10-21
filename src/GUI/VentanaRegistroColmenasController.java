package src.GUI;

import java.util.Hashtable;

import javax.swing.JOptionPane;

import src.Colmena.Colmena;
import src.Colmena.enEstados;
import src.Finca.Finca;
import src.Finca.Sector;

public class VentanaRegistroColmenasController {
    private VentanaRegistroColmenas view;
    private Finca finca;

    public VentanaRegistroColmenasController(VentanaRegistroColmenas view, Finca finca,String estado, int id, Sector sector,Hashtable<Integer, Colmena> colmenas) {
        this.view = view;
        this.finca = finca;
    }

    public void addColmenaController(String estado, int id, Sector sector,Hashtable<Integer, Colmena> colmenas){
        Colmena colmena = colmenas.get(id);
        if (colmena != null) {
            JOptionPane.showMessageDialog(view, "ID ya en uso", "Aviso", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            if (sector.getColmenas().size() >= sector.getMaxColmenas()) {
                JOptionPane.showMessageDialog(view, "No se pueden agregar más colmenas a este sector", "Aviso", JOptionPane.ERROR_MESSAGE);
            }else {
                colmena = new Colmena(id, enEstados.valueOf(estado), 0);
                for (Sector i:finca.getSectores()){
                    if (i.getSectorNumber()==sector.getSectorNumber()){
                        i.addColmena(colmena);
                        JOptionPane.showMessageDialog(view, "Colmena registrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                };
            }
        }
    }
}