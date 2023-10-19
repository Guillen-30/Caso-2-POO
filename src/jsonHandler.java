package src;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Colmena.enEstados;
import src.Finca.Finca;
import src.Finca.Sector;
import src.Finca.enFloracion;

public class JsonHandler {
    public Finca startFinca(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File("resources\\finca.json");
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            Map<String, Object> jsonData = objectMapper.readValue(new File("resources\\finca.json"), Map.class);

            JsonNode sectoresNode = rootNode.get("sectores");

            Finca finca = new Finca((int)jsonData.get("id"), (String)jsonData.get("nombre"), (String)jsonData.get("ubicacion"));

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
                    int mielProducida = colmenaNode.get("mielProducida").asInt();
                    Colmena colmena = new Colmena(id,estadosEnum,mielProducida);

                    

                    //Agregar todos los eventos de la colmena
                    try{
                    JsonNode eventosNode = colmenaNode.get("eventos");
                    for(JsonNode eventoNode : eventosNode){
                        try {
                            System.out.println(eventoNode);
                            String eventoName = eventoNode.get("evento").asText();
                            System.out.println(eventoNode.get("dateTime"));
                            JsonNode dateTimeArray=eventoNode.get("dateTime");
                            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            System.out.println(dateTimeArray+"-----------");
                            int year =dateTimeArray.get(0).asInt();
                            int month =dateTimeArray.get(1).asInt();
                            int day =dateTimeArray.get(2).asInt();
                            int hour =dateTimeArray.get(3).asInt();
                            int minute =dateTimeArray.get(4).asInt();
                            LocalDateTime dateTimeParsed = LocalDateTime.of(year,month,day,hour,minute);
                            Evento evento = new Evento(eventoName,dateTimeParsed);
                            System.out.println(evento);
                            colmena.addEvento(evento);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
        return finca;
        }catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
    

    public void saveFinca(Finca finca){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String jsonString = objectMapper.writeValueAsString(finca);
            File jsonFile = new File("resources/finca.json");
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