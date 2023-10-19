package src.TypeAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import src.Colmena.Evento;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventoTypeAdapter extends TypeAdapter<Evento> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void write(JsonWriter out, Evento evento) throws IOException {
        out.beginObject();
        out.name("evento").value(evento.getEvento());
        out.name("dateTime").value(evento.getDateTime().toString());
        out.endObject();
    }

    @Override
    public Evento read(JsonReader in) throws IOException {
        String evento = null;
        LocalDateTime dateTime = null;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if ("evento".equals(name)) {
                evento = in.nextString();
            } else if ("dateTime".equals(name)) {
                String dateTimeStr = in.nextString();
                dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            } else {
                in.skipValue();
            }
        }
        in.endObject();

        return new Evento(evento, dateTime);
    }
}
