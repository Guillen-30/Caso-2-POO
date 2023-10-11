package src.Colmena;
import java.io.Serializable;
import java.util.ArrayList;

public class Colmena implements Serializable{
    private int id;
    private enEstados estado;
    private ArrayList<Evento> eventos;
    private int mielProducida=0;

    public Colmena(int id, enEstados estado, int mielProducida) {
        this.id = id;
        this.estado = estado;
        this.eventos = new ArrayList<Evento>();
        this.mielProducida+=mielProducida;
        }

    public void addEvento(Evento evento){
        eventos.add(evento);
    }

    public int getID(){
        return id;
    }

    public enEstados getEstado(){
        return estado;
    }

    public ArrayList<Evento> getEventos(){
        return eventos;
    }

    public int getMielProducida(){
        return mielProducida;
    }

    public void addToMielProducida(int mL){
        mielProducida += mL;
    }

    @Override
    public String toString(){
        return "\t\t" +id + ") Colmena: " + estado + "\n" + eventos;
    }
}
