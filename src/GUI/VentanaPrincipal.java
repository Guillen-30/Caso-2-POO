package src.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
        // Color amariColor = new Color(237,236,179);//#edecb3

    public VentanaPrincipal() {
        GridLayout mainGridLayout = new GridLayout(4,3);

        setTitle("Sistema Apícola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocation(200,200);
        setResizable(false);
        setLayout(mainGridLayout);
        
        //Panel de botones

        GridLayout buttonsLayout = new GridLayout(3,1);
        GridLayout tituloLayout = new GridLayout(3,1);
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(buttonsLayout);
        JPanel tituloImagen = new JPanel();
        tituloImagen.setLayout(tituloLayout);

        // Declaracion de titulo
        JLabel titulo = new JLabel("Sistema Apícola", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); // Ajustar la fuente y el tamaño del título
        titulo.setOpaque(false);

        // Declaración de botones
        JButton botonRegistroColmenaButton = new JButton("Registrar Colmenas");
        JButton botonRegistroMielButton = new JButton("Registrar Producción de Miel");
        JButton botonReporteButton = new JButton("Generar Reporte");

        // Funciones de botones

        botonRegistroColmenaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new VentanaRegistroColmenas(); 
            }
        });

        botonRegistroMielButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new VentanaRegistroMiel(); 
            }
        });


        botonReporteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new VentanaSeleccionReporte(); 
            }
        });

        // Colocar botones y título en el BorderLayout

        botonesPanel.add(botonRegistroColmenaButton);
        botonesPanel.add(botonRegistroMielButton);
        botonesPanel.add(botonReporteButton);

        // Espacio entre botones

        buttonsLayout.setHgap(50);
        buttonsLayout.setVgap(25);
        mainGridLayout.setHgap(50);

        // Cargar la imagen y cambiar su tamaño
        ImageIcon originalIcon = new ImageIcon("resources\\159-1598156_bee-free-png-transparent-bee-barry-benson-bee.png");
        Image image = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);

        // Crear un JLabel para el título con la imagen redimensionada
        JLabel icono = new JLabel("", JLabel.CENTER);
        icono.setIcon(resizedIcon);

        // Colocar la imagen y el titulo en el panel
        tituloImagen.add(new JPanel());
        tituloImagen.add(titulo);

        // Colocar paneles en ventana

        add(new JPanel());
        add(icono);
        add(new JPanel());
        add(new JPanel());
        add(tituloImagen);
        add(new JPanel());
        add(new JPanel());
        add(botonesPanel);
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}
