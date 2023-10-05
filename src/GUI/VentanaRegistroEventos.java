package src.GUI;
import javax.swing.*;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Finca.Finca;
import src.Finca.Sector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VentanaRegistroEventos extends JFrame {
    private JTextField eventoField;

    public VentanaRegistroEventos(Finca finca) {
        setTitle("Registro de Eventos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear componentes de la ventana
        JLabel idColmenaLabel = new JLabel("ID de la Colmena:");
        JLabel eventoLabel = new JLabel("Evento:");
        JLabel fechaLabel = new JLabel("HH:MM DD-MM-YYYY:");
        JComboBox<Integer> idColmenaComboBox = new JComboBox<>();
        for (Sector sector : finca.getSectores()){
            for (Colmena colmena : sector.getColmenas()){
                idColmenaComboBox.addItem(colmena.getID());
            }
        }
        eventoField = new JTextField();

        
        
        JTextField diaField = new JTextField(2);
        JTextField mesField = new JTextField(2);
        JTextField yearField = new JTextField(4);

        JTextField horaField = new JTextField(2);
        JTextField minutoField = new JTextField(2);

        //Key Listener para que solo se ingresen numeros en la fecha y hora

        diaField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); 
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        mesField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); 
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        yearField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); 
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        horaField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); 
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        minutoField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); 
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes implementar la lógica para guardar los datos en tu sistema
                Integer idColmena = (Integer) idColmenaComboBox.getSelectedItem();
                String evento = eventoField.getText();
                String dia = diaField.getText();
                String mes = mesField.getText();
                String year = yearField.getText();
                String hora = horaField.getText();
                String minuto = minutoField.getText();


                
                //imprimir los datos a modo de ejemplo
                if (evento.isEmpty()||dia.isEmpty()||mes.isEmpty()||year.isEmpty()||hora.isEmpty()||minuto.isEmpty()){
                    JOptionPane.showMessageDialog(VentanaRegistroEventos.this,"Error al ingresar datos","Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if (!isValidDate(year, mes, dia) || !isValidTime(hora, minuto)) {
                        JOptionPane.showMessageDialog(VentanaRegistroEventos.this, "Fecha o hora inválida", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                System.out.println("ID de la Colmena: " + idColmena);
                System.out.println("Evento: " + evento + "\nEn la fecha: "+year+"-"+mes+"-"+dia+" "+hora+":"+minuto);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTimeParsed = LocalDateTime.parse(year+"-"+mes+"-"+dia+" "+hora+":"+minuto, formatter); 
                Evento eventoAgregar = new Evento(evento,dateTimeParsed);
                for (Sector sector : finca.getSectores()){
                    for (Colmena colmena : sector.getColmenas()){
                        if(colmena.getID()==idColmena){
                            colmena.addEvento(eventoAgregar);
                        };
                    }
                }
                }
            }
        });

        // Crear el panel para organizar los componentes
        JPanel panelItems = new JPanel();
        GridLayout itemsGridLayout = new GridLayout(5, 3);
        panelItems.setLayout(itemsGridLayout);
        panelItems.add(idColmenaLabel);
        panelItems.add(new JPanel());
        panelItems.add(idColmenaComboBox);
        panelItems.add(eventoLabel);
        panelItems.add(new JPanel());
        panelItems.add(eventoField);
        panelItems.add(fechaLabel);
        panelItems.add(horaField);
        panelItems.add(minutoField);
        panelItems.add(diaField);
        panelItems.add(mesField);
        panelItems.add(yearField);
        
        panelItems.add(new JPanel());

        panelItems.add(registrarButton);





        // Agregar el panel a la ventana
        getContentPane().add(panelItems);

        // Mostrar la ventana
        setVisible(true);

    }
    
        // Función para validar la fecha
    private boolean isValidDate(String year, String month, String day) {
        try {
            LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    // Función para validar la hora
    private boolean isValidTime(String hour, String minute) {
        try {
            LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

}
