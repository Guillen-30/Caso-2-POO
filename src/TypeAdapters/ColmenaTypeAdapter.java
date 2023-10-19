package src.TypeAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Colmena.enEstados;

import java.io.IOException;
import java.util.ArrayList;

public class ColmenaTypeAdapter extends TypeAdapter<Colmena> {

    @Override
    public void write(JsonWriter out, Colmena colmena) throws IOException {
        out.beginObject();
        out.name("id").value(colmena.getID());
        out.name("estado").value(colmena.getEstado().name());
        out.name("eventos");
        out.beginArray();
        for (Evento evento : colmena.getEventos()) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Evento.class, new EventoTypeAdapter().nullSafe());
            Gson gson = builder.create();
            gson.toJson(evento, Evento.class, out); // Serialize the Evento object
        }
        out.endArray();
        out.name("mielProducida").value(colmena.getMielProducida());
        out.endObject();
    }

    @Override
    public Colmena read(JsonReader in) throws IOException {
        int id = 0;
        enEstados estado = enEstados.SANA;
        ArrayList<Evento> eventos = new ArrayList<>();
        int mielProducida = 0;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "id":
                    id = in.nextInt();
                    break;
                case "estado":
                    estado = enEstados.valueOf(in.nextString());
                    break;
                case "eventos":
                    in.beginArray();
                    while (in.hasNext()) {
                        GsonBuilder builder = new GsonBuilder();
                        builder.registerTypeAdapter(Evento.class, new EventoTypeAdapter().nullSafe());
                        Gson gson = builder.create();
                        Evento evento = gson.fromJson(in, Evento.class);
                        eventos.add(evento);
                    }
                    in.endArray();
                    break;
                case "mielProducida":
                    mielProducida = in.nextInt();
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();

        Colmena colmena = new Colmena(id, estado, mielProducida);
        colmena.getEventos().addAll(eventos);
        return colmena;
    }
}
