package vilgliom.com.fragmentgames;

import android.content.Context;
import android.util.Log;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ParserPalabras {
    private ArrayList<String> palabras;
    private final InputStream file;
    public ParserPalabras(Context context) {
        file = context.getResources().openRawResource(R.raw.wordses);
        palabras = new ArrayList<>();
    }
    public boolean parse(){
        boolean parsed = false;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
            String palabra;
            while((palabra=bufferedReader.readLine())!= null){
                String cadenaNomralizada = Normalizer.normalize(palabra, Normalizer.Form.NFD);
                String palabra2 = cadenaNomralizada.replaceAll("[^\\p{ASCII}]", "");
                this.palabras.add(palabra2);
            }
            file.close();
            bufferedReader.close();
            parsed = true;
        }catch (Exception e){
            System.out.println("---------------->Error al parsear");
            e.printStackTrace();
        }

        return parsed;
    }

    public ArrayList<String> getPalabras(){
        return palabras;
    }
}
