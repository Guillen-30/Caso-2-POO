package src.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Colmena.Colmena;

import javax.swing.*;

public class VentanaSeleccionReporte extends JFrame {
        // Color amariColor = new Color(237,236,179);//#edecb3
    private JComboBox<String> seleccionColmenaComboBox; //!!Cambiar a objeto colmena, string es para data dummy

    public VentanaSeleccionReporte() {
        GridLayout mainGridLayout = new GridLayout(2,2);

        setTitle("Reporte de colmenas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocation(200,200);
        setResizable(false);
        setLayout(mainGridLayout);

        // Declaracion de titulo
        JLabel seleccionColmenaLabel = new JLabel("Seleccione la colmena:", JLabel.CENTER);
        seleccionColmenaLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Ajustar la fuente y el tamaño del título
        seleccionColmenaLabel.setOpaque(false);

        // Declaración de botones

        JButton verReporteButton = new JButton("Ver Reporte");

        // Declaración de combobox
        seleccionColmenaComboBox = new JComboBox<>(new String[]{"Colmena 1", "Colmena 2", "Colmena 3"}); // TODO: Hacer que reciba las colmenas que hayan sido ingresadas

        // Funciones de botones

        verReporteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new VentanaReporte(); 
                dispose();
            }
        });

        // Colocar elementos en ventana

        add(seleccionColmenaLabel);
        add(seleccionColmenaComboBox);
        add(new JPanel());
        add(verReporteButton);

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaSeleccionReporte();
            }
        });
    }
}
