package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diest.jogopalavrabren.DataPk.RecyclerViewClick;
import com.diest.jogopalavrabren.DataPk.level_id;
import com.diest.jogopalavrabren.R;

import java.util.List;

public class hangman_adapter extends RecyclerView.Adapter<hangman_adapter.MyViewHolder> {
    private final List<level_id> listLevel;
    private static RecyclerViewClick recyclerViewClick;

    public hangman_adapter(List<level_id> list, RecyclerViewClick recyclerViewClick){       //constructor class
        this.listLevel = list;
        hangman_adapter.recyclerViewClick = recyclerViewClick;
    }       //Constructor

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_h, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        level_id level = listLevel.get(position);

        holder.nameLevel.setText(level.get_nameLevel());
        holder.numberLevel.setText(level.get_numberLevel());     //holder.ano.setText(filme.getAno());
    }

    @Override
    public int getItemCount() {
        return listLevel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameLevel;
        TextView numberLevel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLevel = itemView.findViewById(R.id.nameLevelHMLeId);
            numberLevel = itemView.findViewById(R.id.numberLevelHMLeId);        //genero = itemView.findViewById(R.id.textGenero);
            itemView.setOnClickListener(view -> recyclerViewClick.onItemClick(getAdapterPosition()));
        }
    }
}
