package vilgliom.com.fragmentgames;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

public class JuegoTresEnRayaV2 {
    private final ImageButton[][] tablero;

    public JuegoTresEnRayaV2(ImageButton[][] tablero){
        this.tablero = tablero;
    }
    public void AnyadirJugadaUsuario(String opcion, ImageButton imageButton){
        int posI = Integer.parseInt(String.valueOf(opcion.charAt(0))),
                posJ = Integer.parseInt(String.valueOf(opcion.charAt(1)));
        imageButton.setTag("1");
        tablero[posI][posJ] = imageButton;
    }
    public boolean AnyadirJugadaCPU(int i, int j, ImageButton imageButton){
        boolean sePuede = false;

        if(tablero[i][j].getTag() != "1" && tablero[i][j].getTag() != "2"){
            tablero[i][j] = imageButton;
            imageButton.setTag("2");
            sePuede = true;
        }

        return sePuede;
    }

    public int Win(){
        int jugador = 0;
        /*
        Primero Comprobamos las lineas

        Pienso cambiar la asignacion a jugador y hacer return en cada comprobacion, asi no tiene que realizar todas las comprobaciones
         */
        if(CompruebaLineas(0, 0)){
            jugador = DevuelveGanador(0, 0);
        }else if(CompruebaLineas(1, 0)){
            jugador = DevuelveGanador(1, 0);
        }else if(CompruebaLineas(2, 0)){
            jugador = DevuelveGanador(2, 0);
        }
        /*
        Despues comprobamos las columnas
        Aunque este comentado Hay un ELSE IF a continuacion
         */
        else if(CompruebaColumnas(0, 0)){
            jugador = DevuelveGanador(0, 0);
        }else if(CompruebaColumnas(0, 1)){
            jugador = DevuelveGanador(0, 1);
        }else if(CompruebaColumnas(0, 2)){
            jugador = DevuelveGanador(0, 2);
        }
        /*
        por ultimo las diagonales
        Aunque este comentado Hay un ELSE IF a continuacion

         */
        else if(CompruebaDiagonal(0, 0)){
            jugador = DevuelveGanador(0, 0);
        }else if(CompruebaDiagonalInversa(0, 2)){
            jugador = DevuelveGanador(0, 2);
        }

        return jugador;
    }

    private boolean CompruebaLineas( int i, int j){
        boolean win = false;
        if(tablero[i][j].getTag() == tablero[i][j+1].getTag() && tablero[i][j].getTag() == tablero[i][j+2].getTag() && tablero[i][j].getTag()!=null){
            win = true;
        }
        return win;
    }
    private boolean CompruebaColumnas( int i, int j){
        boolean win = false;
        if(tablero[i][j].getTag() == tablero[i+1][j].getTag() && tablero[i][j].getTag() == tablero[i+2][j].getTag()){
            win = true;
        }
        return win;
    }
    private boolean CompruebaDiagonal( int i, int j){
        boolean win = false;
        if(tablero[i][j].getTag() == tablero[i+1][j+1].getTag() && tablero[i][j].getTag() == tablero[i+2][j+2].getTag()){
            win = true;
        }
        return win;
    }
    private boolean CompruebaDiagonalInversa( int i, int j){
        boolean win = false;
        if(tablero[i][j].getTag() == tablero[i+1][j-1].getTag() && tablero[i][j].getTag() == tablero[i+2][j-2].getTag()){
            win = true;
        }
        return win;
    }

    private int DevuelveGanador(int i, int j){
        int jugador = 0;
        if("1" == tablero[i][j].getTag()){
            jugador = 1;
        }else if (tablero[i][j].getTag() == "2"){
            jugador = 2;
        }
        return jugador;
    }
}
