package src.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Colmena.enEvento;
import src.Finca.Finca;
import src.Finca.Sector;

public class VentanaRegistroEventosController {
    private VentanaRegistroEventos ventana;

    public VentanaRegistroEventosController(VentanaRegistroEventos ventana) {

        this.ventana = ventana;
        Sector associatedSector=ventana.getAssociatedSector();
        JButton registrarButton = ventana.getRegistrarButton();
        JTextField eventoMielField = ventana.getEventoMielField();
        JLabel eventoMielLabel = ventana.getEventoMielLabel();
        Finca finca = ventana.getFinca();

        JComboBox<enEvento> eventoComboBox = new JComboBox<>();
        for (enEvento evento : enEvento.values()){
            eventoComboBox.addItem(evento);
        }
        JComboBox<ComboBoxItem> sectorComboBox = new JComboBox<>();
        for (Sector sector : finca.getSectores()){
            sectorComboBox.addItem(new ComboBoxItem<Sector>(sector.getSectorNumber(), sector));
            sectorComboBox.setSelectedItem(null);

        }

        JComboBox<ComboBoxItem> idColmenaComboBox = new JComboBox<>();
        for (Colmena colmena : associatedSector.getColmenas()){
            idColmenaComboBox.addItem(new ComboBoxItem<Colmena>(colmena.getID(), colmena));

        }
        for (Colmena colmena : associatedSector.getColmenas()){
            sectorComboBox.addItem(new ComboBoxItem<Colmena>(colmena.getID(), colmena));
            sectorComboBox.setSelectedIndex(0);
        }

    }
            // Función para validar la fecha
        boolean isValidDate(String year, String month, String day) {
            try {
                LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                return true;
            } catch (DateTimeException e) {
                return false;
            }
        }

        // Función para validar la hora
        boolean isValidTime(String hour, String minute) {
            try {
                LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                return true;
            } catch (DateTimeException e) {
                return false;
            }
        }
}
