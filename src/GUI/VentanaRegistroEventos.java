package src.GUI;
import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Colmena.enEvento;
import src.Finca.Finca;
import src.Finca.Sector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class VentanaRegistroEventos extends JFrame {
        private Sector associatedSector = new Sector(-1, -1);
        private Colmena associatedColmena = new Colmena(-1, null, -1);


    public VentanaRegistroEventos(Finca finca) {
        setTitle("Registro de Eventos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear componentes de la ventana
        JLabel idSectorLabel = new JLabel("ID del Sector:");
        JLabel idColmenaLabel = new JLabel("ID de la Colmena:");
        JLabel tipoEventoLabel = new JLabel("Tipo de evento:");
        JLabel eventoMielLabel = new JLabel("Evento:");
        JLabel cantidadMielLabel = new JLabel("Cantidad de miel mL");
        JLabel fechaLabel = new JLabel("HH:MM DD-MM-YYYY:");
        

        
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
        
        JTextField eventoMielField = new JTextField();

        
        
        // JTextField diaField = new JTextField(2);
        TimePickerSettings timeSettings;
        timeSettings = new TimePickerSettings();
        timeSettings.setDisplaySpinnerButtons(true);
        timeSettings.setAllowEmptyTimes(false);
        timeSettings.setInitialTimeToNow();
        timeSettings.use24HourClockFormat();
        TimePicker timePicker = new TimePicker(timeSettings);


                // Create a date picker: No empty dates. (aka null)

        DatePickerSettings dateSettings;
        final LocalDate today = LocalDate.now();
        int pickerNumber = 0;
        dateSettings = new DatePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
        dateSettings.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
        DatePicker datePicker = new DatePicker(dateSettings);

        sectorComboBox.addActionListener(e -> {
            ComboBoxItem selected = (ComboBoxItem) sectorComboBox.getSelectedItem();
            associatedSector = (Sector) selected.getAssociatedObject();
            System.out.println("----------------------------------"+associatedSector);
            
            });

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enEvento tipoEvento = (enEvento) eventoComboBox.getSelectedItem();
                
                System.out.println("ID de la Colmena: " + associatedColmena.getID());
                System.out.println("Evento: " + eventoMielField + "\nEn la fecha: "+datePicker.getText()+" "+timePicker.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTimeParsed = LocalDateTime.parse(datePicker.getText()+" "+timePicker.getText(), formatter); 
                String eventoMielFieldText = eventoMielField.getText();
                VentanaRegistroEventosController controller = new VentanaRegistroEventosController(VentanaRegistroEventos.this);
                controller.registrar(associatedColmena, dateTimeParsed, associatedSector, tipoEvento, eventoMielFieldText);
                dispose();
                    
                }
            }
        );
        eventoComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Enum selectedEvent = (Enum) e.getItem();
                    if (selectedEvent.equals(enEvento.MIEL)) {
                        eventoMielLabel.setText("Cantidad de Miel en mL:");
                        eventoMielField.setText("");
                        eventoMielField.addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                char c = e.getKeyChar();
                                if (!Character.isDigit(c)) {
                                    e.consume(); // Ignorar caracteres que no son números
                                }
                            }
                        
                            @Override
                            public void keyPressed(KeyEvent e) {
                                // No es necesario implementar este método
                            }
                        
                            @Override
                            public void keyReleased(KeyEvent e) {
                                // No es necesario implementar este método
                            }
                        });
                    } else {
                        eventoMielLabel.setText("Evento:");
                        eventoMielField.addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                // No es necesario implementar este método
                            }
                        
                            @Override
                            public void keyPressed(KeyEvent e) {
                                // No es necesario implementar este método
                            }
                        
                            @Override
                            public void keyReleased(KeyEvent e) {
                                // No es necesario implementar este método
                            }
                        });
                    }
                }
            }
        });

        sectorComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Sector selectedEvent = (Sector) ((ComboBoxItem) e.getItem()).getAssociatedObject();
                    idColmenaComboBox.removeAllItems();
                    for (Colmena colmena:selectedEvent.getColmenas()){
                        idColmenaComboBox.addItem(new ComboBoxItem<Colmena>(colmena.getID(), colmena));

                    associatedSector=selectedEvent;
                    }
                }
            }
        });

        idColmenaComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Colmena selectedEvent = (Colmena) ((ComboBoxItem) e.getItem()).getAssociatedObject();
                    associatedColmena = selectedEvent;
                }
            }
            
        });

        JTextField cantidadMielField = new JTextField(10);
        cantidadMielLabel.setVisible(false); // Hide the cantidadMielLabel by default

        //Add the labels and fields to the panel
        JPanel panelEventoMielLabels = new JPanel();
        JPanel panelEventoMielFields = new JPanel();
        GridLayout pGridLayout = new GridLayout(1,1);
        panelEventoMielLabels.setLayout(pGridLayout);
        panelEventoMielFields.setLayout(pGridLayout);
        panelEventoMielLabels.add(eventoMielLabel);
        panelEventoMielFields.add(eventoMielField);

        // Crear el panel para organizar los componentes
        JPanel panelItems = new JPanel();
        GridLayout itemsGridLayout = new GridLayout(6, 3);
        panelItems.setLayout(itemsGridLayout);
        panelItems.add(idSectorLabel);
        panelItems.add(new JPanel());
        panelItems.add(sectorComboBox);
        panelItems.add(idColmenaLabel);
        panelItems.add(new JPanel());
        panelItems.add(idColmenaComboBox);
        panelItems.add(tipoEventoLabel);
        panelItems.add(new JPanel());
        panelItems.add(eventoComboBox);
        panelItems.add(panelEventoMielLabels);
        panelItems.add(new JPanel());
        panelItems.add(panelEventoMielFields);
        panelItems.add(fechaLabel);
        panelItems.add(datePicker);
        panelItems.add(timePicker);
        
        panelItems.add(new JPanel());

        panelItems.add(registrarButton);

        // Agregar el panel a la ventana
        getContentPane().add(panelItems);

        // Mostrar la ventana
        setVisible(true);

    }

}
