package src.Serializador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import src.Finca.Finca;

public class serializador {
    public void serialize(Finca finca) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("finca.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(finca);
        out.close();
        fileOut.close();}

    public Finca deserialize(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Finca finca = (Finca) in.readObject();
        in.close();
        fileIn.close();
        return finca;
    }
}
