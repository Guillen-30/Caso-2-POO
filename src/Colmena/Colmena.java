package src.Colmena;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Colmena {
    private int id;
    //private int cantidadPanales;
    private enEstados estado;
    private double cantidadMiel;
    private LocalDateTime fechaIngreso;
    private int sectorNumber;

    public Colmena(int id, int cantidadPanales, enEstados estado, double cantidadMiel) {
        this.id = id;
        //this.cantidadPanales = cantidadPanales;
        this.estado = estado;
        this.cantidadMiel = cantidadMiel;
        this.fechaIngreso = LocalDateTime.now();
        }

    public double calcularMiel() {
        LocalDateTime now = LocalDateTime.now();
        double horasDesdeIngreso = (double) fechaIngreso.until(now, ChronoUnit.HOURS);
        return cantidadMiel * horasDesdeIngreso;
    }

    public void setSectorNumber(int sectorNumber) {
        this.sectorNumber = sectorNumber;
    }

}
