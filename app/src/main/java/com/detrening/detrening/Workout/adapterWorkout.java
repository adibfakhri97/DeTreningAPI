package com.detrening.detrening.Workout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.detrening.detrening.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by adibf on 20/04/2018.
 */

public class adapterWorkout extends RecyclerView.Adapter<adapterWorkout.ListViewHolder> {

 //   static List<methodWorkout> listWork;
    List<methodWorkout> listWork;
    Context context;

    GifImageView gambar;



    public adapterWorkout(Context context, List<methodWorkout> listWork){
        this.context = context;
        this.listWork = listWork;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(context);
        View view = inf.inflate(R.layout.list_workout, null);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        final methodWorkout current = listWork.get(position); //membuat variable current dengan class methodAir
        holder.namaWork.setText(current.getNama());
        holder.gerakan.setImageDrawable(context.getResources().getDrawable(current.getGambar()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailWorkout.class); //Mengambil data air dengan method yang ada pada class methodAir
                intent.putExtra("nama", current.getNama());
                intent.putExtra("gambar", current.getGambar());
                intent.putExtra("deskripsi", current.getDeskripsi());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWork.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView namaWork;
        GifImageView gerakan;
        CardView cardView;

        public ListViewHolder(View itemView){
            super(itemView);
            namaWork = (TextView) itemView.findViewById(R.id.judulWorkout);
            gerakan = (GifImageView) itemView.findViewById(R.id.gifWorkout);
            cardView = (CardView) itemView.findViewById(R.id.relll);

        }

    }
}
