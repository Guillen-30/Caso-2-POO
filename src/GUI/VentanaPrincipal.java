package src.GUI;



import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import src.Finca.Finca;

public class VentanaPrincipal extends JFrame {

    private AbstractButton botonRegistroColmenas;
    private AbstractButton botonRegistroEventos;
    private AbstractButton botonReporte;
    private AbstractButton botonGuardar;
    private VentanaPrincipalController controller;


    public VentanaPrincipal(Finca finca) {
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

        //Panel de botones Guardar y Cargar

        GridLayout GCLayout = new GridLayout(3,1);
        JPanel GuardarPanel = new JPanel();
        GuardarPanel.setLayout(GCLayout);

        // Declaracion de titulo
        JLabel titulo = new JLabel("Sistema Apícola", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); // Ajustar la fuente y el tamaño del título
        titulo.setOpaque(false);

        // Declaración de botones
        JButton botonRegistroColmenaButton = new JButton("Registrar Colmenas");
        this.botonRegistroColmenas = botonRegistroColmenaButton;
        JButton botonRegistroEventosButton = new JButton("Registrar Eventos de Colmena");
        this.botonRegistroEventos = botonRegistroEventosButton;
        JButton botonReporteButton = new JButton("Generar Reporte");
        this.botonReporte = botonReporteButton;
        JButton botonGuardarButton = new JButton("Guardar Finca");
        this.botonGuardar = botonGuardarButton;

        // Colocar botones y título en el panel

        botonesPanel.add(botonRegistroColmenaButton);
        botonesPanel.add(botonRegistroEventosButton);
        botonesPanel.add(botonReporteButton);

        // Colocar botones y vacio en el panel Guardar

        GuardarPanel.add(new JPanel());
        GuardarPanel.add(botonGuardarButton);
        GuardarPanel.add(new JPanel());

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
        add(GuardarPanel);
        add(new JPanel());

        // Hacer visible la ventana
        setVisible(true);
        VentanaPrincipalController controller = new VentanaPrincipalController(this, finca);
        this.controller = controller;
        System.out.println(this.controller);
    }

        public void addBotonRegistroColmenasListener(ActionListener listener) {
            botonRegistroColmenas.addActionListener(listener);
        }
    
        public void addBotonRegistroEventosListener(ActionListener listener) {
            botonRegistroEventos.addActionListener(listener);
        }
    
        public void addBotonReporteListener(ActionListener listener) {
            botonReporte.addActionListener(listener);
        }
    
        public void addBotonGuardarListener(ActionListener listener) {
            botonGuardar.addActionListener(listener);
        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal(null);
            }
        });
    }    
}
