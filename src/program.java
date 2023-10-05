package src;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Colmena.enEstados;
import src.Finca.Finca;
import src.Finca.Sector;
import src.Finca.enFloracion;
import src.GUI.*;
import src.Serializador.serializador;

public class program {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Finca finca;
        finca = new Finca(0, null, null);  
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("resources\\finca.json");
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            Map<String, Object> jsonData = objectMapper.readValue(new File("resources\\finca.json"), Map.class);

            JsonNode sectoresNode = rootNode.get("sectores");

            finca = new Finca((int)jsonData.get("id"), (String)jsonData.get("nombre"), (String)jsonData.get("ubicacion"));

            //Agregar todos los sectores del json

            try{
            for(JsonNode sectorNode : sectoresNode){
                int sectorNumber = sectorNode.get("sectorNumber").asInt();
                int maxColmenas = sectorNode.get("maxColmenas").asInt();
                String floracion = sectorNode.get("floracion").asText();
                enFloracion floracionEnum = enFloracion.valueOf(floracion);
                Sector sector = new Sector(sectorNumber,maxColmenas);
                sector.setFloracion(floracionEnum);
                
                //Agregar todas las colmenas del sector
                try{
                JsonNode colmenasNode = sectorNode.get("colmenas");
                for(JsonNode colmenaNode : colmenasNode){
                    int id = colmenaNode.get("id").asInt();
                    String estado = colmenaNode.get("estado").asText();
                    enEstados estadosEnum = enEstados.valueOf(estado);
                    Colmena colmena = new Colmena(id,estadosEnum);

                    

                    //Agregar todos los eventos de la colmena
                    try{
                    JsonNode eventosNode = colmenaNode.get("eventos");
                    for(JsonNode eventoNode : eventosNode){
                        System.out.println(eventoNode);
                        String eventoName = eventoNode.get("eventoName").asText();
                        String dateTime = eventoNode.get("dateTime").asText();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateTimeParsed = LocalDateTime.parse(dateTime, formatter);
                        Evento evento = new Evento(eventoName,dateTimeParsed);
                        colmena.addEvento(evento);
                    
                    }   
                    sector.addColmena(colmena);
                    }catch(Exception e){
                        e.printStackTrace();
                    }   
                }
                }catch(Exception e){
                    e.printStackTrace();
                }
            finca.addSector(sector);
            }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace(); 
        }
        
        //serializador s = new serializador();
        // try {
        //     finca = s.deserialize("finca.dat");
        // } catch (Exception e) {
        //     System.err.println("Error deserializing Finca object: " + e.getMessage());
        //     finca = new Finca(0, null, null);   
        // }
        System.out.println(finca);
        new VentanaPrincipal(finca);
    }
}


//TODO: Revisar guardar en json