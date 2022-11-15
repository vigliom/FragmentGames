package vilgliom.com.fragmentgames;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class JuegoAhorcado{
    private String palabra;
    private String palabraMostrada;

    public JuegoAhorcado(String palabra){
        this.palabra = palabra;
        this.palabraMostrada="";
            for(int i = 0; i<palabra.length(); i++) {
                palabraMostrada += "_";
            }
    }
    public String getPalabra() {
        return palabra;
    }
    public String getPalabraMostrada() {
        return palabraMostrada;
    }

    public boolean Comprobar(String letra){
        boolean res = false;
        for(int i = 0; i<palabra.length(); i++){
            if(palabra.charAt(i) == letra.toLowerCase().charAt(0)){
                StringBuilder cadenaModi = new StringBuilder(palabraMostrada);//Creamos un builder a partir del String original
                cadenaModi.setCharAt(i,letra.charAt(0));//Realizamos cambios
                palabraMostrada = cadenaModi.toString();//Recuperamos nuevo String con los cambios.
                res = true;
            }
        }
        return res;
    }


}
