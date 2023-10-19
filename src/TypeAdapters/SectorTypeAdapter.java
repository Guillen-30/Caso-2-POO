package src.TypeAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import src.Colmena.Colmena;
import src.Colmena.Evento;
import src.Finca.Sector;
import src.Finca.enFloracion;

import java.io.IOException;
import java.util.ArrayList;

public class SectorTypeAdapter extends TypeAdapter<Sector> {

    @Override
    public void write(JsonWriter out, Sector sector) throws IOException {
        out.beginObject();
        out.name("sectorNumber").value(sector.getSectorNumber());
        out.name("maxColmenas").value(sector.getMaxColmenas());
        out.name("colmenas");
        out.beginArray();
        for (Colmena colmena : sector.getColmenas()) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Colmena.class, new ColmenaTypeAdapter().nullSafe());
            Gson gson = builder.create();
            gson.toJson(colmena, Colmena.class, out); // Serialize the Colmena object
        }
        out.endArray();
        out.name("floracion").value(sector.getFloracion().name()); // Assuming enFloracion is an enum
        out.endObject();
    }

    @Override
    public Sector read(JsonReader in) throws IOException {
        int sectorNumber = 0;
        int maxColmenas = 0;
        ArrayList<Colmena> colmenas = new ArrayList<>();
        enFloracion floracion = enFloracion.MARGARITA; // Assuming enFloracion is an enum

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "sectorNumber":
                    sectorNumber = in.nextInt();
                    break;
                case "maxColmenas":
                    maxColmenas = in.nextInt();
                    break;
                case "colmenas":
                    in.beginArray();
                    while (in.hasNext()) {
                        GsonBuilder builder = new GsonBuilder();
                        builder.registerTypeAdapter(Colmena.class, new ColmenaTypeAdapter().nullSafe());
                        Gson gson = builder.create();
                        Colmena colmena = gson.fromJson(in, Colmena.class);
                        colmenas.add(colmena);
                    }
                    in.endArray();
                    break;
                case "floracion":
                    floracion = enFloracion.valueOf(in.nextString()); // Assuming enFloracion is an enum
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();

        Sector sector = new Sector(sectorNumber, maxColmenas);
        sector.getColmenas().addAll(colmenas);
        sector.setFloracion(floracion);
        return sector;
    }

}
