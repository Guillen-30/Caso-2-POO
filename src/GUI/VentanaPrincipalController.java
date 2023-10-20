package src.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.JsonHandler;
import src.Finca.Finca;

public class VentanaPrincipalController {
    private Finca model;

    public VentanaPrincipalController(VentanaPrincipal view, Finca model) {
        this.model = model;

        view.addBotonRegistroColmenasListener(new BotonRegistroColmenasListener());
        view.addBotonRegistroEventosListener(new BotonRegistroEventosListener());
        view.addBotonReporteListener(new BotonReporteListener());
        view.addBotonGuardarListener(new BotonGuardarListener());
    }

    private class BotonRegistroColmenasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new VentanaRegistroColmenas(model);
        }
    }

    private class BotonRegistroEventosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new VentanaRegistroEventos(model);
        }
    }

    private class BotonReporteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new VentanaReporte(model);
        }
    }

    private class BotonGuardarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JsonHandler jsonHandler = new JsonHandler();
            jsonHandler.saveFinca(model);
        }
    }
}