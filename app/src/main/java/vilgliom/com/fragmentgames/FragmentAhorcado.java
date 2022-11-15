package vilgliom.com.fragmentgames;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class FragmentAhorcado extends Fragment implements IClickListener{
    private final String[] abecedario = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int intentos;
    private ParserPalabras parser;
    private ArrayList<String> palabras;
    private JuegoAhorcado partida;

    private RecyclerView rvBotones;
    private ImageView vida1, vida2, vida3, vida4, vida5, vida6;
    private ImageView imagenPrincipal;
    private TextView tvPalabra;

    public FragmentAhorcado(){
        super(R.layout.fragment_ahorcado);
        intentos = 6;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parser = new ParserPalabras(getContext());
        if(parser.parse()){
            palabras = parser.getPalabras();
        }
        partida = new JuegoAhorcado(generateWord(palabras));
        AdapterAbecedario adapterAbecedario = new AdapterAbecedario(getContext(), abecedario, this);
        this.imagenPrincipal = view.findViewById(R.id.frgAhorcado_ivImagenPrincipal);
        tvPalabra = view.findViewById(R.id.frgAhorcado_tvPalabra);
        tvPalabra.setText(partida.getPalabraMostrada());
        rvBotones = view.findViewById(R.id.frgAhorcado_rvBotones);
        vida6 = view.findViewById(R.id.frgAhorcado_ivVida6);
        vida5 = view.findViewById(R.id.frgAhorcado_ivVida5);
        vida4 = view.findViewById(R.id.frgAhorcado_ivVida4);
        vida3 = view.findViewById(R.id.frgAhorcado_ivVida3);
        vida2 = view.findViewById(R.id.frgAhorcado_ivVida2);
        vida1 = view.findViewById(R.id.frgAhorcado_ivVida1);

        rvBotones.setHasFixedSize(true);
        rvBotones.setAdapter(adapterAbecedario);
        rvBotones.setLayoutManager(new StaggeredGridLayoutManager(8, LinearLayoutManager.VERTICAL));

    }

    private String generateWord(ArrayList<String> palabras){
        System.out.println(palabras.size());
        int posicion = (int) (Math.random()*palabras.size());
        return palabras.get(posicion);
    }

    @Override
    public void onClick(View view, int pos) {
        if(partida.Comprobar(abecedario[pos])){
            view.setBackground(getResources().getDrawable(R.drawable.fondo_letrascorrecto));
            tvPalabra.setText(partida.getPalabraMostrada());
        }else{
            view.setBackground(getResources().getDrawable(R.drawable.fondo_letras_error));
            intentos--;
            if(intentos == 5){
                    vida6.setVisibility(View.INVISIBLE);
                    imagenPrincipal.setImageResource(R.drawable.hangman_5);
                }else if(intentos == 4){
                    vida5.setVisibility(View.INVISIBLE);
                    imagenPrincipal.setImageResource(R.drawable.hangman_4);
            }else if( intentos == 3) {
                    vida4.setVisibility(View.INVISIBLE);
                imagenPrincipal.setImageResource(R.drawable.hangman_3);

            }else if( intentos == 2) {
                    vida3.setVisibility(View.INVISIBLE);
                imagenPrincipal.setImageResource(R.drawable.hangman_2);

            }else if( intentos == 1) {
                    vida2.setVisibility(View.INVISIBLE);
                imagenPrincipal.setImageResource(R.drawable.hangman_1);

            }else if( intentos == 0){
                    vida1.setVisibility(View.INVISIBLE);
                imagenPrincipal.setImageResource(R.drawable.hangman_0);
                tvPalabra.setText(partida.getPalabra().toUpperCase());
                tvPalabra.setTextColor(getResources().getColor(R.color.red));

            }

        }
    }
}
