package src.Colmena;

import java.time.LocalDateTime;

public class Evento {
    private String evento;
    private LocalDateTime dateTime;

    public Evento(String evento, LocalDateTime dateTime) {
        this.evento = evento;
        this.dateTime = dateTime;
    }

    public String getEvento() {
        return evento;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    @Override
    public String toString(){
        return "\t\t\t"+evento + " - " + dateTime + "\n";
    }
}

