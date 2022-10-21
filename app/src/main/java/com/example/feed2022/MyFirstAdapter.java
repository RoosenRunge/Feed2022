package com.example.feed2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyFirstAdapter extends RecyclerView.Adapter {



    //1 - construtor para receber o ArrayList de Application1
    private ArrayList<Applications> aplications;
    public MyFirstAdapter(ArrayList<Applications> aplications) {
        this.aplications = aplications;
    }

    //3- Implementação dos métodos abstratos
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Infla a View definida no Layout e item do adapter e referência através da variável v
        //cria uma instância de ViewHolder e passando a view para a instância
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_layout, parent, false);


        return new MyFirstViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //aqui chamamos o proprio método bind do ViewHolder para informar o ViewHolder e a posição.
        ((MyFirstViewHolder) holder).bind(aplications.get(position));
    }

    @Override
    public int getItemCount() {
        return aplications.size();
    }
//2 --Criação da classe interna ViewHolder
    class MyFirstViewHolder extends RecyclerView.ViewHolder {

        //declara as variáveis correspondentes do layout do adapter
        private TextView name;
        private TextView artist;
        private TextView release;
        //construtor da classe recebe a View da classe externa Adapter
        public MyFirstViewHolder(View itemView) {
            super(itemView);
            // associação com os itens do layout do adapter
            name = itemView.findViewById(R.id.nameXML);
            artist = itemView.findViewById(R.id.textXML);
            release = itemView.findViewById(R.id.releaseXML);
        }


        //associaçõa com o contéudo de dados.
        public void bind(final Applications applications) {
            //imageView.setImageResource(aluno.getFoto());
            name.setText("app Name: "+applications.getName());
            artist.setText("Artist: "+applications.getArtist());
            release.setText("Release: "+applications.getReleaseDate());
// Complete
        }
    }
}
