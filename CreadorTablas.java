import java.io.PrintWriter; 
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class CreadorTablas{

    public static void creandoTablas(String fileName, String tabla){
        try {
            String[] fileNameSplit=fileName.split("\\.");
			PrintWriter pw = new PrintWriter(new OutputStreamWriter ( new FileOutputStream(fileNameSplit[0]+tabla+".txt")));
			pw.println(fileNameSplit[0]+tabla);
			pw.close();
		}catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
    }

}