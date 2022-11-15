package vilgliom.com.fragmentgames;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterAbecedario extends RecyclerView.Adapter<AdapterAbecedario.AbecedarioViewHolder> {
    private final String[] abecedario;
    private final Context context;
    private final IClickListener listener;

    public AdapterAbecedario(Context context, String[] abecedario, IClickListener listener){
        this.abecedario = abecedario;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AbecedarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_letra, parent, false);
        return new AbecedarioViewHolder(itemView, context, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AbecedarioViewHolder holder, int position) {
        holder.bindAbecedario(abecedario[position]);
    }

    @Override
    public int getItemCount() {
        return abecedario.length;
    }

    public static class AbecedarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public static TextView letra;
        public final Context context;
        public final IClickListener listener;

        public AbecedarioViewHolder(View itemView, Context context, IClickListener listener){
            super(itemView);
            this.context = context;
            this.letra = itemView.findViewById(R.id.item_tvLetra);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }


        public void bindAbecedario(String letra){
            this.letra.setText(letra);
        }

        @Override
        public void onClick(View view) {
            if(listener!=null){
                listener.onClick(view, getAdapterPosition());
            }
        }
    }
}
