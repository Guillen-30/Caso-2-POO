package src.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.Colmena.Colmena;
import src.Colmena.enEstados;
import src.Finca.Finca;
import src.Finca.Sector;
import java.util.Hashtable;

public class VentanaRegistroColmenasController {
    private JTextField idField;
    private JComboBox<enEstados> estadoComboBox;
    private Sector associatedObject;

    public VentanaRegistroColmenasController(VentanaRegistroColmenas ventana, JTextField idField, JComboBox<enEstados> estadoComboBox, Sector associatedObject) {
        this.idField = idField;
        this.estadoComboBox = estadoComboBox;
        this.associatedObject = associatedObject;

        idField.addKeyListener(new KeyListener() {
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

        JButton registrarButton = ventana.getRegistrarButton();
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(ventana, "Error al ingresar datos", "Aviso", JOptionPane.ERROR_MESSAGE);
                } else {
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
                        JOptionPane.showMessageDialog(ventana, "ID ya en uso", "Aviso", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        if (associatedObject.getColmenas().size() < associatedObject.getMaxColmenas()) {
                            associatedObject.addColmena(new Colmena(id, enEstados.valueOf(estado), 0));
                            ventana.dispose();
                        } else {
                            JOptionPane.showMessageDialog(ventana, "No se pueden agregar más colmenas a este sector", "Aviso", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    System.out.println("ID: " + idField.getText());
                    System.out.println("Estado de la colmena: " + estado);
                    System.out.println("Sector en que se ubica: " + ventana.getSectorComboBox().getSelectedItem());
                }
            }
        });
    }
}
