import java.io.BufferedReader;
import java.io.FileReader;

public class archivos {
    public String leerTxt(String direccion) {
        //direccion del archivo
        String texto ="";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null ){
            temp = temp + bfRead;
            }

            texto = temp;
        } catch (Exception e) {System.err.println("No se encontro el archivo");

        }
        return texto;
    }
}
