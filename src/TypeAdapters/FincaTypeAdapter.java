package src.TypeAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import src.Colmena.Colmena;
import src.Finca.Finca;
import src.Finca.Sector;

import java.io.IOException;
import java.util.ArrayList;

public class FincaTypeAdapter extends TypeAdapter<Finca> {

    @Override
    public void write(JsonWriter out, Finca finca) throws IOException {
        out.beginObject();
        out.name("id").value(finca.getID());
        out.name("nombre").value(finca.getNombre());
        out.name("ubicacion").value(finca.getUbicacion());
        out.name("sectores");
        out.beginArray();
        for (Sector sector : finca.getSectores()) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Sector.class, new SectorTypeAdapter().nullSafe());
            Gson gson = builder.create();
            gson.toJson(sector, Sector.class, out); // Serialize the Sector object
        }
        out.endArray();
        out.endObject();
    }

    @Override
    public Finca read(JsonReader in) throws IOException {
        int id = 0;
        String nombre = null;
        String ubicacion = null;
        ArrayList<Sector> sectores = new ArrayList<>();

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "id":
                    id = in.nextInt();
                    break;
                case "nombre":
                    nombre = in.nextString();
                    break;
                case "ubicacion":
                    ubicacion = in.nextString();
                    break;
                case "sectores":
                    in.beginArray();
                    while (in.hasNext()) {
                        GsonBuilder builder = new GsonBuilder();
                        builder.registerTypeAdapter(Sector.class, new SectorTypeAdapter().nullSafe());
                        Gson gson = builder.create();
                        Sector sector = gson.fromJson(in, Sector.class);
                        sectores.add(sector);
                    }
                    in.endArray();
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();

        Finca finca = new Finca(id, nombre, ubicacion);
        finca.getSectores().addAll(sectores);
        return finca;
    }
}
