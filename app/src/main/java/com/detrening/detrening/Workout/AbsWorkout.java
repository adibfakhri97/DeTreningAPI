package com.detrening.detrening.Workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.detrening.detrening.R;

import java.util.ArrayList;
import java.util.List;

public class AbsWorkout extends AppCompatActivity {

    private RecyclerView recyclerAbs;
    private List<methodWorkout> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_workout);
        setTitle("Abs Workout");

        recyclerAbs = (RecyclerView) findViewById(R.id.recyclerAbs);

        int gridCol = getResources().getInteger(R.integer.grid_column_count);

        recyclerAbs.setLayoutManager(new GridLayoutManager(this, gridCol));
        recyclerAbs.setHasFixedSize(true);

        list = new ArrayList<>();

        list.add(new methodWorkout(1, "Flutter Kicks", R.drawable.flutterkicks, "Pertama, posisi tidur lalu angkat kaki dan tahan (jangan sampai turun kebawah) kemudian ayungkan ke atas secara bergantian seperti gambar diatas"));
        list.add(new methodWorkout(2, "Leg Raised Circle", R.drawable.legraisedcirclescircles, "Dengan posisi tertidur dan kaki angkat ke atas sekitar 110 derajat kemudian ayungkan melingkar seperti gambar diatas"));
        list.add(new methodWorkout(3, "Leg Raised", R.drawable.legraises, "Pertama, harus pose tidur kemudian tangan menempel ditanah, lalu ayunkan kaki secara bersamaan ke atas sampai 90 derajat dan turun secara perlahan (tahan kaki jangan sampai menempel ke tanah)"));
        list.add(new methodWorkout(4, "Scissors", R.drawable.scissors, "Pertama, kalian harus di posisi tidur, kemudian angkat kaki kalian kurang lebihnya 120 derajat selanjutnya ayunkan kaki kanan ke arah kiri dan kaki kiri ke kanan seperti gerakan diatas "));
        list.add(new methodWorkout(5, "Sit Up", R.drawable.situp, "Pertama, kalian harus dalam posisi tidur kemudian silangkan tangan kalian dan letakkan di dada (jangan sampai tangan di belakang punggung kalian) lalu angkat kaki seperti gambar diatas jika sudah angkat badan kalian dengan tenaga perut angkat sampai ke atas lalu turunkan dengan pelan-pelan jangan sampai kepala kalian nempel ke tanah."));
//        list.add(new methodWorkout(6, "Gerakan 6", R.drawable.giphy, ""));
//        list.add(new methodWorkout(7, "Gerakan 7", R.drawable.giphy, ""));
//        list.add(new methodWorkout(8, "Gerakan 8", R.drawable.giphy, ""));
//        list.add(new methodWorkout(9, "Gerakan 9", R.drawable.giphy, ""));
//        list.add(new methodWorkout(10, "Gerakan 10", R.drawable.giphy, ""));


        adapterWorkout adapter = new adapterWorkout(this, list);
        recyclerAbs.setAdapter(adapter);
    }
}
