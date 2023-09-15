package src.wireframes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class GUI_Functions{

private MatteBorder borde = new MatteBorder(2, 2, 2, 2, Color.BLACK);
    public void Colocar_label(JLabel pLabel, JFrame pPanel, int x, int y, int h, int w, boolean pOpacity, int pTamaño) {
            pLabel.setFont(new Font("Arial", Font.BOLD, pTamaño)); // Set font and sizO
            pLabel.setForeground(Color.black); // Set text color
            Color amariColor = new Color(237,236,179);//#edecb3
            pLabel.setBackground(amariColor); // Set text color
            pLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pLabel.setOpaque(pOpacity);
            pLabel.setBounds(x, y, h ,w);
            if (pPanel != null) {pPanel.add(pLabel);}
    }

    public void ColocarBoton(JButton pBoton, JFrame pFrame, int x, int y, int h, int w, String texto, int pTamaño, ActionListener actionListener) {
        pBoton.setFont(new Font("Arial", Font.BOLD, pTamaño)); // Establecer fuente y tamaño
        pBoton.setText(texto); // Establecer el texto del botón
        pBoton.setBounds(x, y, h, w); // Establecer posición y tamaño
        pBoton.addActionListener(actionListener); // Agregar ActionListener al botón
        
        if (pFrame != null) {
            pFrame.add(pBoton); // Agregar el botón al JFrame
        }
    }
    
}