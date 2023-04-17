import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class test {

    public static void main(String [] args)
    {

        boolean boolNOTIFICATION = true;

        saveBool("bool.txt", boolNOTIFICATION);
        loadBool("bool.txt", boolNOTIFICATION);       

        
        
    }

    public static void saveBool(String nfile, boolean boolNOTIFICATION)
    {
        try{
            PrintWriter savWriter = new PrintWriter(new File(nfile));

            savWriter.println(boolNOTIFICATION);

            savWriter.close();
        }
        catch(IOException o){}
    }

    public static void loadBool(String nfile, boolean boolNOTIFICATION)
        {
            Scanner nscan = null;

            try{
                nscan = new Scanner(new File(nfile));

                
            //boolNOTIFICATION = Boolean.parseBoolean();
                

            System.out.println("this is it " + Boolean.parseBoolean(nscan.nextLine()));
            //System.out.println("this is it " + nscan.nextLine());

            nscan.close();
           

            }
            catch(FileNotFoundException e){}
            
            
            
        }
    


    
    
}

