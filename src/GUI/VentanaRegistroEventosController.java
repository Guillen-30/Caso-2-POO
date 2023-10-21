package src.GUI;
import java.time.LocalDateTime;
import javax.swing.*;
import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Colmena.enEvento;
import src.Finca.Finca;
import src.Finca.Sector;

public class VentanaRegistroEventosController {
    private VentanaRegistroEventos win;
    private Finca finca;

    public VentanaRegistroEventosController(VentanaRegistroEventos win) {

        this.win = win;
        this.finca = finca;
    }
    public void registrar(Colmena associatedColmena,LocalDateTime dateTimeParsed, Sector associatedSector, enEvento tipoEvento, String eventoMielField){
        if(eventoMielField==null){
            JOptionPane.showMessageDialog(win,"Error al ingresar datos","Aviso",JOptionPane.ERROR_MESSAGE);
        }else{
            if(tipoEvento.equals(enEvento.MIEL)){
                String mielAgregar = eventoMielField;
                Integer mielInt = Integer.parseInt(mielAgregar);
                associatedColmena.addToMielProducida(mielInt);
                Evento eventoAgregar = new Evento("Agregar "+eventoMielField+"mL de miel",dateTimeParsed);
                associatedColmena.addEvento(eventoAgregar);
                win.dispose();
            }else{
                Evento eventoAgregar = new Evento(eventoMielField,dateTimeParsed);
                associatedColmena.addEvento(eventoAgregar);
                win.dispose();
            }
        }
    }
}