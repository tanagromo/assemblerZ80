import java.util.Scanner; 
import java.io.File; 
 
public class Traductor{

    public static void main(String a []){
        Scanner sc=new Scanner (System.in);

        System.out.println("Ingrese el nombre del archivo: ");
        String fileName=sc.nextLine();
        String[] filesplit=fileName.split("\\.");
        File f=new File(fileName);
        CalculadoraCL calc=new CalculadoraCL(); 

        if(f.exists() && filesplit[1].contains("asm")){
            CreadorTablas creadorTablas = new CreadorTablas();        
            creadorTablas.creandoTablas(fileName, "TS");
            creadorTablas.creandoTablas(fileName, "CL");
            creadorTablas.creandoTablas(fileName, "COD");

            System.out.println("hola:"+ fileName);
            PrimeraPasada p1 = new PrimeraPasada();        
            p1.pasadaUno(fileName);

            SegundaPasada p2 = new SegundaPasada();
             p2.Second(filesplit[0],fileName);

        }
        else
            System.out.println("\nEl archivo no existe o la extension no es valida");



    }

}