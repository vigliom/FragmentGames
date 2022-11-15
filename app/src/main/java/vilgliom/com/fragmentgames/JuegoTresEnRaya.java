package vilgliom.com.fragmentgames;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

public class JuegoTresEnRaya {
    private final ImageButton[][] tablero;

    public JuegoTresEnRaya(ImageButton[][] tablero){
        this.tablero = tablero;
    }

    public void AnyadirJugadaUsuario(String opcion, ImageButton imageButton){
        int posI = Integer.parseInt(String.valueOf(opcion.charAt(0))),
            posJ = Integer.parseInt(String.valueOf(opcion.charAt(1)));
        imageButton.setTag("Usuario");
        tablero[posI][posJ] = imageButton;
    }

    /**
     * Devuelve 0 Si no ha ganado nadie
     *          1 Si ha ganado el usuario
     *          2 Si ha ganado la maquina
     *
     * @return Devuelve 0 Si no ha ganado nadie
     *                  1 Si ha ganado el usuario
     *                  2 Si ha ganado la maquina
     */
    public int Win(Context context){
        int jugador = 0;
        Drawable drawable = context.getDrawable(R.drawable.tictactoe_void);
        /*
        Primero Comprobamos las lineas

        Pienso cambiar la asignacion a jugador y hacer return en cada comprobacion, asi no tiene que realizar todas las comprobaciones
         */
        if(CompruebaLineas(0, 0)){
            jugador = DevuelveGanador(context, 0, 0);
        }else if(CompruebaLineas(1, 0)){
            jugador = DevuelveGanador(context, 1, 0);
        }else if(CompruebaLineas(2, 0)){
            jugador = DevuelveGanador(context, 2, 0);
        }
        /*
        Despues comprobamos las columnas
        Aunque este comentado Hay un ELSE IF a continuacion
         */
        else if(CompruebaColumnas(0, 0)){
            jugador = DevuelveGanador(context, 0, 0);
        }else if(CompruebaColumnas(1, 0)){
            jugador = DevuelveGanador(context, 1, 0);
        }else if(CompruebaColumnas(2, 0)){
            jugador = DevuelveGanador(context, 2, 0);
        }
        /*
        por ultimo las diagonales
        Aunque este comentado Hay un ELSE IF a continuacion

         */
        else if(CompruebaDiagonal(0, 0)){
            jugador = DevuelveGanador(context, 0, 0);
        }else if(CompruebaDiagonalInversa(0, 2)){
            jugador = DevuelveGanador(context, 1, 0);
        }

        return jugador;
    }

    private int DevuelveGanador(Context context, int i, int j){
        int jugador = 0;
        Drawable imgX = context.getDrawable(R.drawable.tictactoe_x);
        Drawable imgO=  context.getDrawable(R.drawable.tictactoe_0);

        if(tablero[i][j].getDrawable() == imgO){
            jugador = 1;
        }else if (tablero[i][j].getDrawable() == imgX){
            jugador = 2;
        }
        return jugador;
    }

    private boolean CompruebaLineas( int i, int j){
        boolean win = false;
        if(tablero[i][j].getDrawable() == tablero[i][j++].getDrawable() && tablero[i][j].getDrawable() == tablero[i][j++].getDrawable()){
            win = true;
        }
    return win;
    }

    private boolean CompruebaColumnas( int i, int j){
        boolean win = false;
        if(tablero[i][j].getDrawable() == tablero[i++][j].getDrawable() && tablero[i][j].getDrawable() == tablero[i++][j].getDrawable()){
            win = true;
        }
        return win;
    }
    private boolean CompruebaDiagonal( int i, int j){
        boolean win = false;
        if(tablero[i][j].getDrawable() == tablero[i++][j++].getDrawable() && tablero[i][j].getDrawable() == tablero[i++][j++].getDrawable()){
            win = true;
        }
        return win;
    }
    private boolean CompruebaDiagonalInversa( int i, int j){
        boolean win = false;
        if(tablero[i][j].getDrawable() == tablero[i++][j--].getDrawable() && tablero[i][j].getDrawable() == tablero[i++][j--].getDrawable()){
            win = true;
        }
        return win;
    }
}
