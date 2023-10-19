package src.GUI;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

import src.JsonHandler;
import src.Finca.Finca;

public class VentanaPrincipal extends JFrame {
        // Color amariColor = new Color(237,236,179);//#edecb3

    private Object botonGuardarButton;


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

        GridLayout GCLayout = new GridLayout(3,2);
        JPanel CargarPanel = new JPanel();
        CargarPanel.setLayout(GCLayout);

        JPanel GuardarPanel = new JPanel();
        GuardarPanel.setLayout(GCLayout);

        // Declaracion de titulo
        JLabel titulo = new JLabel("Sistema Apícola", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); // Ajustar la fuente y el tamaño del título
        titulo.setOpaque(false);

        // Declaración de botones
        JButton botonRegistroColmenaButton = new JButton("Registrar Colmenas");
        JButton botonRegistroEventosButton = new JButton("Registrar Eventos de Colmena");
        JButton botonReporteButton = new JButton("Generar Reporte");
        JButton botonCargarButton = new JButton("Cargar Finca");
        JButton botonGuardarButton = new JButton("Guardar Finca");

        // Funciones de botones

        botonRegistroColmenaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new VentanaRegistroColmenas(finca); 
            }
        });

        botonRegistroEventosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new VentanaRegistroEventos(finca); 
            }
        });


        botonReporteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new VentanaReporte(finca); 
            }
        });


        botonGuardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println(finca);
                // serializador s = new serializador();
                // try {
                //     s.serialize(finca);
                // } catch (IOException ex) {
                //     ex.printStackTrace();
                // }
            };
        });

        // Colocar botones y título en el panel

        botonesPanel.add(botonRegistroColmenaButton);
        botonesPanel.add(botonRegistroEventosButton);
        botonesPanel.add(botonReporteButton);

        // Colocar botones y vacio en el panel Cargar

        CargarPanel.add(new JPanel());
        CargarPanel.add(new JPanel());
        //CargarPanel.add(new JPanel());
        
        CargarPanel.add(new JPanel());
        CargarPanel.add(botonCargarButton);
        CargarPanel.add(new JPanel());

        CargarPanel.add(new JPanel());
        //CargarPanel.add(new JPanel());
        //CargarPanel.add(new JPanel());

        // Colocar botones y vacio en el panel Guardar

        GuardarPanel.add(new JPanel());
        GuardarPanel.add(new JPanel());
        //GuardarPanel.add(new JPanel());
        
        //GuardarPanel.add(new JPanel());
        GuardarPanel.add(botonGuardarButton);
        GuardarPanel.add(new JPanel());

        GuardarPanel.add(new JPanel());
        GuardarPanel.add(new JPanel());
        //GuardarPanel.add(new JPanel());

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

        botonGuardarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JsonHandler jsonHandler = new JsonHandler();
            jsonHandler.saveFinca(finca);
        }
    });

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

        add(CargarPanel);
        add(new JPanel());
        add(GuardarPanel);

        // Hacer visible la ventana
        setVisible(true);}

    public static String chooseFile() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        return selectedFile.getAbsolutePath();
    } else {
        return null;
    }
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal(null);
            }
        });
    }


    public Object getBotonGuardarButton() {
        return botonGuardarButton;
    }
}
