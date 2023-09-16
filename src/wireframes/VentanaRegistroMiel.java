package src.wireframes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroMiel extends JFrame {
    private JComboBox<String> idColmenaComboBox;
    private JTextField cantidadMielField;

    public VentanaRegistroMiel() {
        setTitle("Registro de Producción de Miel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear componentes de la ventana
        JLabel idColmenaLabel = new JLabel("ID de la Colmena:");
        JLabel cantidadMielLabel = new JLabel("Cantidad de Miel (ml):");

        idColmenaComboBox = new JComboBox<>(new String[]{"Colmena 1", "Colmena 2", "Colmena 3"}); // TODO: Hacer que reciba las colmenas que hayan sido ingresadas
        cantidadMielField = new JTextField(10);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes implementar la lógica para guardar los datos en tu sistema
                String idColmena = idColmenaComboBox.getSelectedItem().toString();
                String cantidadMiel = cantidadMielField.getText();
                
                // Puedes imprimir los datos a modo de ejemplo
                if (cantidadMiel.isEmpty()){
                    JOptionPane.showMessageDialog(VentanaRegistroMiel.this,"Error al ingresar datos","Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                System.out.println("ID de la Colmena: " + idColmena);
                System.out.println("Cantidad de Miel (ml): " + cantidadMiel); //TODO: Agregar registro de miel
                }
            }
        });

        // Crear el panel para organizar los componentes
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(idColmenaLabel);
        panel.add(idColmenaComboBox);
        panel.add(cantidadMielLabel);
        panel.add(cantidadMielField);
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
                new VentanaRegistroMiel();
            }
        });
    }
}
