package com.detrening.detrening.Workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.detrening.detrening.R;

import java.util.ArrayList;
import java.util.List;

public class ArmWorkout extends AppCompatActivity {
    private RecyclerView recyclerArm;
    private List<methodWorkout> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arm_workout);
        setTitle("Arm Workout");

        recyclerArm = (RecyclerView) findViewById(R.id.recyclerArm);



        int gridCol = getResources().getInteger(R.integer.grid_column_count);

        recyclerArm.setLayoutManager(new GridLayoutManager(this, gridCol));
        recyclerArm.setHasFixedSize(true);

        list = new ArrayList<>();

        list.add(new methodWorkout(1, "Pull Up", R.drawable.pullups, "Pertama, kedua tangan pegang ke tiang kemudian tahan dan angkat menggunakan lengan kalian (jangan menggunakan perut) dan turun secara perlahan"));
        list.add(new methodWorkout(2, "Push Up", R.drawable.pushups, "Letakkan tangan ditanah dengan posisi terbuka selebar bahu kalian. Kaki bertumpu pada ujung jari kaki kalian, hingga tubuh berposisi lurus sempurna. Dan jangan ada posisi atau bongkong kalian menonjol ke atas. Turunkan bahu kalian sampai membentuk siku 90 derajat. Kemudian dorong bahu dan lengan hingga lurus ke depan dengan sambil mengambil nafas. "));
        list.add(new methodWorkout(3, "Shoulder Tap", R.drawable.shouldertaps, "Posisinya hampir sama seperti pushups Letakkan tangan di tanah dengan posisi terbuka selebar bahu kalian. Kaki bertumpu pada ujung jari kaki kalian, hingga tubuh berposisi lurus sempurna. Dan angkat 1 tangan dan pegang pundak berlawanan (tangan kanan pegang pundak kiri, tangan kiri penang pundak kanan)"));
  //      list.add(new methodWorkout(4, "Site Plank", R.drawable.siteplank, "Gerakan ini dilakukan dengan cara satu tangan anda menyentuh tanah dan satu tangan lurus keatas. Posisi badan anda miring dan kaki menyentuh tanah lalu tahan posisi tersebut."));
        list.add(new methodWorkout(5, "Thigh Taps", R.drawable.thightaps, "Posisinya hampir sama seperti shoulder taps Letakkan tangan di tanah dengan posisi terbuka selebar bahu kalian. Kaki bertumpu pada ujung jari kaki kalian, hingga tubuh berposisi lurus sempurna. Dan angkat 1 tangan dan pegang paha secara bergantian"));
//        list.add(new methodWorkout(6, "Gerakan 6", R.drawable.giphy, ""));
//        list.add(new methodWorkout(7, "Gerakan 7", R.drawable.giphy, ""));
//        list.add(new methodWorkout(8, "Gerakan 8", R.drawable.giphy, ""));
//        list.add(new methodWorkout(9, "Gerakan 9", R.drawable.giphy, ""));
//        list.add(new methodWorkout(10, "Gerakan 10", R.drawable.giphy, ""));


        adapterWorkout adapter = new adapterWorkout(this, list);
        recyclerArm.setAdapter(adapter);
    }
}
