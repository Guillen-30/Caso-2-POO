package src.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Colmena.Colmena;

import javax.swing.*;

public class VentanaReporte extends JFrame {
        // Color amariColor = new Color(237,236,179);//#edecb3
    public VentanaReporte() {
        GridLayout mainGridLayout = new GridLayout(1,1);

        setTitle("Reporte de colmenas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocation(200,200);
        setResizable(false);
        setLayout(mainGridLayout);

        //Layout panel de datos

        GridLayout datosLayout = new GridLayout(6,2);

        // Declaracion elementos de panel de datos

        JLabel idLabel = new JLabel("ID de la colmena");
        JLabel ubiLabel = new JLabel("Ubicacion de la colmena:");
        JLabel cantidadLabel = new JLabel("Cantidad de colmenas:");
        JLabel floracionLabel = new JLabel("Floracion disponible para la colmena:");
        JLabel estadoLabel = new JLabel("Estado de la colmena");
        JLabel mielLabel = new JLabel("Miel producida por la colmena");

        JLabel idDatosLabel = new JLabel("278");
        JLabel ubiDatosLabel = new JLabel("Cartago");
        JLabel cantidadDatosLabel = new JLabel("13");
        JLabel floracionDatosLabel = new JLabel("Girasoles"); //TODO: Conseguir datos de colmena ingresada
        JLabel estadoDatosLabel = new JLabel("Sana");
        JLabel mielDatosLabel = new JLabel(("71273"+"mL"));

        // Panel de datos

        JPanel datosPanel = new JPanel(datosLayout);

        // Agregar elementos a panel de datos

        datosPanel.add(idLabel);
        datosPanel.add(idDatosLabel);
        datosPanel.add(ubiLabel);
        datosPanel.add(ubiDatosLabel);
        datosPanel.add(cantidadLabel);
        datosPanel.add(cantidadDatosLabel);
        datosPanel.add(floracionLabel);
        datosPanel.add(floracionDatosLabel);
        datosPanel.add(estadoLabel);
        datosPanel.add(estadoDatosLabel);
        datosPanel.add(mielLabel);
        datosPanel.add(mielDatosLabel);

        // Agregar panel de datos a ventana

        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());
        add(datosPanel);
        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());

        // Hacer visible la ventana
        setVisible(true);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaReporte();
            }
        });
    }
}
