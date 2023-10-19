package src;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Colmena.enEstados;
import src.Finca.Finca;
import src.Finca.Sector;
import src.Finca.enFloracion;
import src.TypeAdapters.EventoTypeAdapter;
import src.TypeAdapters.FincaTypeAdapter;

public class jsonHandler {
    public Finca startFinca() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Finca.class, new FincaTypeAdapter().nullSafe());
        Gson gson = builder.create();
    
        try {
            String json = new String(Files.readAllBytes(Paths.get("resources/finca.json")));
            Finca finca = gson.fromJson(json, Finca.class);
            System.out.println(finca);
            return finca;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle the error as needed
        }
    }
    

    public void saveFinca(Finca finca){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(finca);
            File jsonFile = new File("finca.json");
            objectMapper.writeValue(jsonFile, finca);
            System.out.println(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // GsonBuilder builder = new GsonBuilder();
        // builder.registerTypeAdapter(Evento.class, new EventoTypeAdapter().nullSafe());
        // builder.setPrettyPrinting();
        // Gson gson = builder.create();
        // String json = gson.toJson(finca);

        // try {
        //     objectMapper.writeValue(new File("resources\\finca.json"), json);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }   
}