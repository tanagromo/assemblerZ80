import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimeraPasada{

    int cl=0;
    int lon;
    String hexCL;

    public void pasadaUno(String fileName){
        
        
        
        try {
            CalculadoraCL longitudCl = new CalculadoraCL();
            String[] fileNameSplit=fileName.split("\\.");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            BufferedReader ts = new BufferedReader(new FileReader(fileNameSplit[0]+"TS.txt"));
            ArrayList<String> etis = new ArrayList<String>(); //todas las etiquetas que se encuentren en el archivo se guardan aquí
            String line;
            String l;
            String mn;
            while(!((line = br.readLine()).equals("    END"))) {
                if(line.indexOf(":")!=-1){
                    String[] etiDef=line.split(":");
                    String eti=etiDef[0].replace(" ", "");
                    etis.add(etiDef[0]);
                    while((l=ts.readLine())!=null) {
                        String[] lSplit =l.split("\\|");
                        hexCL = Integer.toHexString(cl);
                        //System.out.println(etiDef[1]);
                        //System.out.println(hexCL);
                        Writer output = new BufferedWriter(new FileWriter(fileNameSplit[0]+"TS.txt", true));
                        Writer outCl = new BufferedWriter(new FileWriter(fileNameSplit[0]+"CL.txt",true));
                        if (hexCL.length()==1){
                            hexCL="000"+hexCL;
                        }else if(hexCL.length()==2){
                            hexCL="00"+hexCL;
                        }else if (hexCL.length()==3){
                            hexCL="0"+hexCL;
                        }  
                        output.append(eti+"|"+hexCL+"|S\r\n");
                        output.close();
                        outCl.append(etiDef[1].replace("    ", "")+"|"+hexCL.toUpperCase()+"\r\n");
                        outCl.close();
                        lon=longitudCl.calcularLongitud(etiDef[1], 1, 2);
                        if(lon!=-1){               
                            cl=cl+lon;
                            break;
                        }
                        System.out.println("Error, no es posible calcular la longitud eti");
                        break;
                        }
                }else{
                    //System.out.println(line);
                    hexCL = Integer.toHexString(cl);
                    //System.out.println(hexCL);
                    Writer outCl = new BufferedWriter(new FileWriter(fileNameSplit[0]+"CL.txt",true));
                    if (hexCL.length()==1){
                        hexCL="000"+hexCL;
                    }else if(hexCL.length()==2){
                        hexCL="00"+hexCL;
                    }else if (hexCL.length()==3){
                        hexCL="0"+hexCL;
                    }
                    outCl.append(" "+line.replace("    ", "")+"|"+hexCL.toUpperCase()+"\r\n");
                    outCl.close();
                    lon=longitudCl.calcularLongitud(line, 4, 5);
                    if(lon!=-1){               
                        cl=cl+lon;
                    }else{
                        System.out.println("Error, no es posible calcular la longitud");
                    }
                }
            }
            for (int i = 0; i < etis.size(); i++) {
                for (int j = i + 1 ; j < etis.size(); j++) {
                    if (etis.get(i).equals(etis.get(j))) {
                             System.out.println("Error: Definicion multiple de la etiqueta: "+etis.get(i));
                             ts.close();
                               File archts = new File(fileNameSplit[0]+"TS.txt");
                               File archcl = new File(fileNameSplit[0]+"CL.txt");
                               File archcod = new File(fileNameSplit[0]+"COD.txt");                          
                               archcl.delete();
                               archts.delete();
                               archcod.delete();
                               break;
                    }
               }
           }
        }catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
    }
    
         
    
}