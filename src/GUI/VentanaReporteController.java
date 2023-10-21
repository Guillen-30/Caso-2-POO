package src.GUI;

import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Finca.Finca;
import src.Finca.Sector;

public class VentanaReporteController {
    private VentanaReporte view;
    private Finca finca;
    VentanaReporteController(VentanaReporte view, Finca finca){
        this.view = view;
        this.finca = finca;
    }
    public void generarReporte(JTable tabla, DefaultTableModel model){
        try {
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

            }
        }
        tabla.setModel(model);
        // Crear TableRowSorter y asignarlo a la tabla
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        tabla.setRowSorter(sorter);
        // Ordenar la tabla por la columna "Fecha"
        sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(7, SortOrder.ASCENDING))); 
        tabla.setDefaultEditor(Object.class, null);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(1);

        JScrollPane scrollPane = new JScrollPane(tabla);
        view.add(scrollPane);

        // Hacer visible la ventana
        } catch (Exception e) {
            System.out.println("No hay colmenas registradas");
            JOptionPane.showMessageDialog(null, "No hay colmenas registradas", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }
}
