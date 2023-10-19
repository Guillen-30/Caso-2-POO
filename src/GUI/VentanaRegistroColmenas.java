package src.GUI;
import javax.swing.*;

import src.Colmena.Colmena;
import src.Colmena.enEstados;
import src.Finca.Finca;
import src.Finca.Sector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

public class VentanaRegistroColmenas extends JFrame {
    private JTextField idField;
    private JComboBox<enEstados> estadoComboBox;
    private Sector associatedObject;

    public VentanaRegistroColmenas(Finca finca) {
        setTitle("Registro de Colmenas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false);

        // Crear componentes de la ventana
        JLabel idLabel = new JLabel("ID:");
        JLabel sectorLabel = new JLabel("Sector en que se ubica:");
        JLabel estadoLabel = new JLabel("Estado de la colmena:");

        idField = new JTextField(20);
        JComboBox<ComboBoxItem> sectorComboBox = new JComboBox<>();
        for (Sector sector : finca.getSectores()){
            sectorComboBox.addItem(new ComboBoxItem(sector.getSectorNumber(), sector));
            associatedObject=sector;
            sectorComboBox.setSelectedItem(null);
        }
        estadoComboBox = new JComboBox<enEstados>(enEstados.values());

        sectorComboBox.addActionListener(e -> {
            ComboBoxItem selected = (ComboBoxItem) sectorComboBox.getSelectedItem();
            associatedObject = (Sector) selected.getAssociatedObject();
            System.out.println("----------------------------------"+associatedObject);
            
            });

            idField.addKeyListener((KeyListener) new KeyListener() {
                
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume(); // Ignorar caracteres que no son números
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
              
                if (idField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(VentanaRegistroColmenas.this,"Error al ingresar datos","Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String estado = estadoComboBox.getSelectedItem().toString();
                   

                    // Create a Hashtable to store the Colmena objects
                    Hashtable<Integer, Colmena> colmenas = new Hashtable<Integer, Colmena>();

                    // Add Colmena objects to the Hashtable
                    for (Colmena colmena : associatedObject.getColmenas()) {
                        colmenas.put(colmena.getID(), colmena);
                    }

                    // Search for a Colmena object by ID
                    Integer id = Integer.parseInt(idField.getText());
                    Colmena colmena = colmenas.get(id);
                    if (colmena != null) {
                        JOptionPane.showMessageDialog(VentanaRegistroColmenas.this, "ID ya en uso", "Aviso", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        if (associatedObject.getColmenas().size() < associatedObject.getMaxColmenas()) {
                            associatedObject.addColmena(new Colmena(id, enEstados.valueOf(estado), 0));
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(VentanaRegistroColmenas.this, "No se pueden agregar más colmenas a este sector", "Aviso", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    
                
                System.out.println("ID: " + idField.getText());
                System.out.println("Estado de la colmena: " + estado);
                System.out.println("Sector en que se ubica: " + sectorComboBox.getSelectedItem());
                }
            }
        });

        // Crear el panel para organizar los componentes
        GridLayout layoutPanel = new GridLayout(5, 2);
        layoutPanel.setHgap(10);
        JPanel panel = new JPanel(layoutPanel);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(sectorLabel);
        panel.add(sectorComboBox);
        panel.add(estadoLabel);
        panel.add(estadoComboBox);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(registrarButton);

        // Agregar el panel a la ventana
        getContentPane().add(panel);

        // Mostrar la ventana
        setVisible(true);
    }
}
