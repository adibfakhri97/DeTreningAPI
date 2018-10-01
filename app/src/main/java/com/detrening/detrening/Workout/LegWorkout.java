package com.detrening.detrening.Workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.detrening.detrening.R;

import java.util.ArrayList;
import java.util.List;

public class LegWorkout extends AppCompatActivity {

    private RecyclerView recyclerLeg;
    private List<methodWorkout> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_workout);
        setTitle("Leg Workout");

        recyclerLeg = (RecyclerView) findViewById(R.id.recyclerLeg);

        int gridCol = getResources().getInteger(R.integer.grid_column_count);

        recyclerLeg.setLayoutManager(new GridLayoutManager(this, gridCol));
        recyclerLeg.setHasFixedSize(true);

        list = new ArrayList<>();

        list.add(new methodWorkout(1, "Side-to-Side Lunges", R.drawable.deepsidelunges, "Hampir sama seperti sumo squats pertama, posisi kalian berdiri dengan kaki bersiap-siap posisi kuda-kuda kemudian kalian ke arah kanan dengan kaki kanan agak menahan dan kaki kiri miring lurus ke kanan, dan setelah itu lakukan sebaliknya seperti gambar diatas"));
        list.add(new methodWorkout(2, "Lunge Step", R.drawable.leg2, "Pertama, berdiri tegak lalu kedua tangan memegang pinggang lalu, kaki satu persatu menekuk kebelakang seperti membentuk 90 derajat kebalik"));
        list.add(new methodWorkout(3, "Calf Raises", R.drawable.leg5, "Pertama, kalian berdiri tegak dan lurus kemudian kalian tekan jari kaki kalian ke atas dan turun secara perlahan seperti gambar diatas"));
        list.add(new methodWorkout(4, "Leg Extensions", R.drawable.legextensions, "Pertama, kalian harus posisi sujud kemudian tangan menahan kepala setelah itu kaki menendang ke belakang dan lakukan secara bergantian seperti gambar diatas"));
        list.add(new methodWorkout(5, "Squats", R.drawable.sumosquats, "Pertama, posisi kalian berdiri dengan kaki bersiap-siap posisi kuda-kuda kemudian turun kebawah sampai hampir nekuk seperti gambar diatas"));
//        list.add(new methodWorkout(6, "Gerakan 6", R.drawable.giphy, ""));
//        list.add(new methodWorkout(7, "Gerakan 7", R.drawable.giphy, ""));
//        list.add(new methodWorkout(8, "Gerakan 8", R.drawable.giphy, ""));
//        list.add(new methodWorkout(9, "Gerakan 9", R.drawable.giphy, ""));
//        list.add(new methodWorkout(10, "Gerakan 10", R.drawable.giphy, ""));


        adapterWorkout adapter = new adapterWorkout(this, list);
        recyclerLeg.setAdapter(adapter);
    }
}
