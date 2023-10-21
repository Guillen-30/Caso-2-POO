package src.GUI;

import java.awt.*;
import java.util.Arrays;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Finca.Finca;
import src.Finca.Sector;
import src.GUI.VentanaReporteController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class VentanaReporte extends JFrame {
        // Color amariColor = new Color(237,236,179);//#edecb3
    public VentanaReporte(Finca finca) {
        GridLayout mainGridLayout = new GridLayout(1,1);

        setTitle("Reporte de colmenas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLocation(200,200);
        setResizable(false);
        setLayout(mainGridLayout);

        // Declaracion elementos de panel de datos

        JTable tabla = new JTable();
        String[] columnNames = {"Sector","ID","Finca","Ubicacion","Estado","Floracion","Evento","Fecha","Miel"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0);
        VentanaReporteController controller = new VentanaReporteController(VentanaReporte.this, finca);
        controller.generarReporte(tabla, model);

        setVisible(true);
        
    }
}
