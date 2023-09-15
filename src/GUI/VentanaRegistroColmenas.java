package src.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroColmenas extends JFrame {
    private JTextField ubicacionField;
    private JTextField cantidadColmenasField;
    private JTextField floracionField;
    private JComboBox<String> estadoComboBox;

    public VentanaRegistroColmenas() {
        setTitle("Registro de Colmenas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear componentes de la ventana
        JLabel ubicacionLabel = new JLabel("Ubicación:");
        JLabel cantidadColmenasLabel = new JLabel("Cantidad de Colmenas:");
        JLabel floracionLabel = new JLabel("Floración disponible:");
        JLabel estadoLabel = new JLabel("Estado de la colmena:");

        ubicacionField = new JTextField(20);
        cantidadColmenasField = new JTextField(10);
        floracionField = new JTextField(20);
        estadoComboBox = new JComboBox<>(new String[]{"Saludable", "Enferma", "Otro"});

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ubicacion = ubicacionField.getText();
                String cantidadColmenas = cantidadColmenasField.getText();
                String floracion = floracionField.getText();
                String estado = estadoComboBox.getSelectedItem().toString();
                
                if (ubicacion.isEmpty() || cantidadColmenas.isEmpty() || floracion.isEmpty()){
                    JOptionPane.showMessageDialog(VentanaRegistroColmenas.this,"Error al ingresar datos","Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    System.out.println("Ubicación: " + ubicacion);
                    System.out.println("Cantidad de Colmenas: " + cantidadColmenas); //TODO: Sustituir con agregar datos
                    System.out.println("Floración disponible: " + floracion);
                    System.out.println("Estado de la colmena: " + estado);
                }
            }
        });

        // Crear el panel para organizar los componentes
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(ubicacionLabel);
        panel.add(ubicacionField);
        panel.add(cantidadColmenasLabel);
        panel.add(cantidadColmenasField);
        panel.add(floracionLabel);
        panel.add(floracionField);
        panel.add(estadoLabel);
        panel.add(estadoComboBox);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(registrarButton);

        // Agregar el panel a la ventana
        getContentPane().add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaRegistroColmenas();
            }
        });
    }
}
