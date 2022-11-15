package vilgliom.com.fragmentgames;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTresEnRaya extends Fragment {
    private ImageButton[][] tablero;
    private TextView marcadorUsuario, marcadorCPU;
    private Button playAgain;
    private JuegoTresEnRayaV2 partida;
    private int turno, pointsUsuario, pointsCPU;

    public FragmentTresEnRaya(){
        super(R.layout.fragment_tres_en_raya);
        tablero = new ImageButton[3][3];
        partida = new JuegoTresEnRayaV2(tablero);
        turno = (int) (Math.random()*2)+1;
        pointsCPU = 0;
        pointsUsuario = 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playAgain = view.findViewById(R.id.frgTres_btJuegaAgain);
        marcadorUsuario = view.findViewById(R.id.frgTres_tvMarcadorUsuario);
        marcadorCPU = view.findViewById(R.id.frgTres_tvMarcadorMaquina);
        tablero[0][0] = view.findViewById(R.id.frgTres_ib00);
        tablero[0][1] = view.findViewById(R.id.frgTres_ib01);
        tablero[0][2] = view.findViewById(R.id.frgTres_ib02);
        tablero[1][0] = view.findViewById(R.id.frgTres_ib10);
        tablero[1][1] = view.findViewById(R.id.frgTres_ib11);
        tablero[1][2] = view.findViewById(R.id.frgTres_ib12);
        tablero[2][0] = view.findViewById(R.id.frgTres_ib20);
        tablero[2][1] = view.findViewById(R.id.frgTres_ib21);
        tablero[2][2] = view.findViewById(R.id.frgTres_ib22);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundResource(R.drawable.tictactoe_0);
                if (view.getId() == R.id.frgTres_ib00){
                    partida.AnyadirJugadaUsuario("00", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib01){
                    partida.AnyadirJugadaUsuario("01", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib02){
                    partida.AnyadirJugadaUsuario("02", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib10){
                    partida.AnyadirJugadaUsuario("10", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib11){
                    partida.AnyadirJugadaUsuario("11", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib12){
                    partida.AnyadirJugadaUsuario("12", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib20){
                    partida.AnyadirJugadaUsuario("20", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib21){
                    partida.AnyadirJugadaUsuario("21", (ImageButton) view);
                }else if(view.getId() == R.id.frgTres_ib22){
                    partida.AnyadirJugadaUsuario("22", (ImageButton) view);
                }
                if(Comprobar()){
                    LimpiarTablero();
                }
                JuegaCPU();
                Comprobar();
            }
        };

        for (int i = 0; i<3; i++) {
            for(int j=0; j<3; j++){
                tablero[i][j].setOnClickListener(listener);
            }
        }
        if(turno == 2){
            JuegaCPU();
        }

    }
    public void JuegaCPU(){
        int i,j;
        do{
            i = (int) (Math.random()*3);
            j = (int) (Math.random()*3);
        }while (!partida.AnyadirJugadaCPU(i, j, tablero[i][j]));
        tablero[i][j].setBackgroundResource(R.drawable.tictactoe_x);
    }
    public boolean Comprobar(){

        if(partida.Win() == 1){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(),"HA GANADO EL USUARIO", Toast.LENGTH_LONG).show();
            pointsUsuario++;
            marcadorUsuario.setText(String.valueOf(pointsUsuario));
            return true;
        }else if(partida.Win() == 2){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(),"HA GANADO LA CPU", Toast.LENGTH_LONG).show();
            pointsCPU++;
            marcadorCPU.setText(String.valueOf(pointsCPU));
            return true;
        }
        return false;
    }
    public void LimpiarTablero(){
        for (int i = 0; i<3; i++) {
            for(int j=0; j<3; j++){
                tablero[i][j].setImageResource(R.drawable.tictactoe_void);
                tablero[i][j].setBackgroundResource(R.drawable.fondo_letras);

                tablero[i][j].setTag(null);
            }
        }
    }

}
