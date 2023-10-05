package src.GUI;
import javax.swing.*;

import src.Colmena.Colmena;
import src.Colmena.enEstados;
import src.Finca.Finca;
import src.Finca.Sector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroColmenas extends JFrame {
    private JTextField idField;
    private JComboBox<enEstados> estadoComboBox;

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
        JComboBox<Integer> sectorComboBox = new JComboBox<Integer>();
        for (Sector sector : finca.getSectores()){
            sectorComboBox.addItem(sector.getSectorNumber());
        }
        estadoComboBox = new JComboBox<enEstados>(enEstados.values());


        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idField.getText());
                String estado = estadoComboBox.getSelectedItem().toString();
                
                if (idField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(VentanaRegistroColmenas.this,"Error al ingresar datos","Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    for(Sector sector: finca.getSectores()){
                        for(Colmena colmena:sector.getColmenas()){
                            if(Integer.toString(colmena.getID()).equals(idField.getText())){
                                JOptionPane.showMessageDialog(VentanaRegistroColmenas.this,"ID ya en uso","Aviso",JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        if((int)sectorComboBox.getSelectedItem()==sector.getSectorNumber()){
                            sector.addColmena(new Colmena(id,enEstados.valueOf(estado)));

                        }
                    }
                    System.out.println("ID: " + idField.getText());
                    System.out.println("Estado de la colmena: " + estado);
                    System.out.println("Sector en que se ubica: " + sectorComboBox.getSelectedItem());
                    //TODO: Revisar que no hayan mas de maxColmenas en el sector
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
