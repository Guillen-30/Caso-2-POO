package src.GUI;

import java.awt.*;
import java.util.Arrays;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Finca.Finca;
import src.Finca.Sector;

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

        //Layout panel de datos

        

        // Declaracion elementos de panel de datos

        JTable tabla = new JTable();
        String[] columnNames = {"Sector","ID","Finca","Ubicacion","Estado","Floracion","Evento","Fecha","Miel"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0);

        //Prueba System.out.println con todos los datos a mostrar

        for(Sector sector:finca.getSectores()){
            for(Colmena colmena:sector.getColmenas()){
                System.out.println("ID de colmena: "+colmena.getID()+
                "\nFinca: "+finca.getNombre()+
                "\nUbicacion de la colmena: "+finca.getUbicacion()+
                "\nEstado de la colmena: "+colmena.getEstado()+
                "\nFloracion disponible para la colmena: "+sector.getFloracion()+
                "\nEventos: "
                );
                if(colmena.getEventos().isEmpty()){
                    model.addRow(new Object[]{sector.getSectorNumber(),colmena.getID(),finca.getNombre(),finca.getUbicacion(),colmena.getEstado(),sector.getFloracion(),"NO HAY EVENTOS",null,colmena.getMielProducida()+"mL"});
                }
                else{
                for(Evento evento:colmena.getEventos()){
                    model.addRow(new Object[]{sector.getSectorNumber(),colmena.getID(),finca.getNombre(),finca.getUbicacion(),colmena.getEstado(),sector.getFloracion(),evento.getEvento(),evento.getDateTime(),colmena.getMielProducida()+"mL"});
                }}
                // for(Evento evento:colmena.getEventos()){
                //     System.out.println(evento.getEvento());
                //     System.out.println(evento.getDateTime());
                // }

            }
        }
        tabla.setModel(model);
        // Crear TableRowSorter y asignarlo a la tabla
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        tabla.setRowSorter(sorter);
        // Ordenar la tabla por la columna "Fecha"
        sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(7, SortOrder.ASCENDING))); // 7 is the index of the "Fecha" column
        tabla.setDefaultEditor(Object.class, null);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(1);



        

        // Panel de datos

        //JPanel datosPanel = new JPanel(datosLayout);

        // Agregar elementos a panel de datos

        // datosPanel.add(idLabel);
        // datosPanel.add(idDatosLabel);
        // datosPanel.add(ubiLabel);
        // datosPanel.add(ubiDatosLabel);
        // datosPanel.add(cantidadLabel);
        // datosPanel.add(cantidadDatosLabel);
        // datosPanel.add(floracionLabel);
        // datosPanel.add(floracionDatosLabel);
        // datosPanel.add(estadoLabel);
        // datosPanel.add(estadoDatosLabel);
        // datosPanel.add(mielLabel);
        // datosPanel.add(mielDatosLabel);

        // Agregar panel de datos a ventana

        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane);
        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());
        // add(new JPanel());

        // Hacer visible la ventana
        setVisible(true);
    }
}
