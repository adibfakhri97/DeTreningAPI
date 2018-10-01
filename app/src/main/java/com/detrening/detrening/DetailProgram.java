package com.detrening.detrening;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

import pl.droidsonroids.gif.GifImageView;

public class DetailProgram extends AppCompatActivity {
    private GifImageView gifImageView;
    private TextView judul, deskripsi, indicator, jumlah;
    private Button mButton;
    private ImageView img;

    private int max = 6;
    private int min = 0;
    private int current = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_program);

        gifImageView = (GifImageView) findViewById(R.id.gifProg);
        judul = (TextView) findViewById(R.id.judulProgram);
        deskripsi = (TextView) findViewById(R.id.deskripsiProgram);
        mButton = (Button) findViewById(R.id.skipProg);
        indicator = (TextView) findViewById(R.id.indicator);
        jumlah = (TextView) findViewById(R.id.jumlahGerakan);


        randomProgram();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current+=1;

                if (current<=max){
                    indicator.setText(current+"/"+max);
                    randomProgram();

                    if (current==max){
                        Toast.makeText(DetailProgram.this, "Congratulations, you have completed today's program", Toast.LENGTH_SHORT).show();
                        mButton.setText("Done");
                        mButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(DetailProgram.this, Program.class));
                                finish();
                            }
                        });
                    }
                } else {
                    current=max;

                }

            }
        });



    }

    public void randomProgram(){
        shuffleProg();

        gifImageView.setImageResource(arrayProg[2].getmGif());
        judul.setText(arrayProg[2].getNamaGerakan());
        deskripsi.setText(arrayProg[2].getDeskripsiGerakan());
        jumlah.setText(arrayProg[2].getJumlah());
    }

    methodProgram p1 = new methodProgram( "Leg Raised Circles", R.drawable.legraisedcirclescircles, "Dengan posisi tertidur dan kaki angkat ke atas sekitar 110 derajat kemudian ayungkan melingkar seperti gambar diatas", "Do this movement 10 times");
    methodProgram p2 = new methodProgram("Leg Raises",R.drawable.legraises,  "Pertama harus pose tidur kemudian tangan menempel ditanah, lalu ayunkan kaki secara bersamaan ke atas sampai 90 derajat dan turun secara perlahan (tahan kaki jangan sampai menempel ke tanah)", "Do this movement 10 times");
    methodProgram p3 = new methodProgram("Sit Up", R.drawable.situp,  "Pertama kalian harus dalam posisi tidur kemudian silangkan tangan kalian dan letakkan di dada (jangan sampai tangan di belakang punggung kalian) lalu angkat kaki seperti gambar diatas jika sudah angkat badan kalian dengan tenaga perut angkat sampai ke atas lalu turunkan dengan pelan-pelan jangan sampai kepala kalian nempel ke tanah.", "Do this movement 20 times");
    methodProgram p4 = new methodProgram( "Scissors",R.drawable.scissors, "Pertama kalian harus di posisi tidur, kemudian angkat kaki kalian kurang lebihnya 120 derajat selanjutnya ayunkan kaki kanan ke arah kiri dan kaki kiri ke kanan seperti gerakan diatas ","Do this movement 10 times");
    methodProgram p5 = new methodProgram( "Flutter Kicks",R.drawable.flutterkicks, "Pertama posisi tidur lalu angkat kaki dan tahan (jangan sampai turun kebawah) kemudian ayungkan ke atas secara bergantian seperti gambar diatas", "Do this movement 20 times");
    methodProgram p6 = new methodProgram( "Pull Up",R.drawable.pullups, "Pertama, kedua tangan pegang ke tiang kemudian tahan dan angkat menggunakan lengan kalian (jangan menggunakan perut) dan turun secara perlahan", "Do this movement 5 times");
    methodProgram p7 = new methodProgram( "Push Up",R.drawable.pushups, "Letakkan tangan ditanah dengan posisi terbuka selebar bahu kalian. Kaki bertumpu pada ujung jari kaki kalian, hingga tubuh berposisi lurus sempurna. Dan jangan ada posisi atau bongkong kalian menonjol ke atas. Turunkan bahu kalian sampai membentuk siku 90 derajat. Kemudian dorong bahu dan lengan hingga lurus ke depan dengan sambil mengambil nafas. ", "Do this movement 10 times");
    methodProgram p8 = new methodProgram( "Shoulder Tap",R.drawable.shouldertaps, "Posisinya hampir sama seperti pushups Letakkan tangan di tanah dengan posisi terbuka selebar bahu kalian. Kaki bertumpu pada ujung jari kaki kalian, hingga tubuh berposisi lurus sempurna. Dan angkat 1 tangan dan pegang pundak berlawanan (tangan kanan pegang pundak kiri, tangan kiri penang pundak kanan)", "Do this movement 10 times");
//    methodProgram p9 = new methodProgram( "Site Plank",R.drawable.siteplank, "Gerakan ini dilakukan dengan cara satu tangan anda menyentuh tanah dan satu tangan lurus keatas. Posisi badan anda miring dan kaki menyentuh tanah lalu tahan posisi tersebut.", "Do this for 20 seconds");
    methodProgram p10 = new methodProgram( "Thigh Taps",R.drawable.thightaps, "Posisinya hampir sama seperti shoulder taps Letakkan tangan di tanah dengan posisi terbuka selebar bahu kalian. Kaki bertumpu pada ujung jari kaki kalian, hingga tubuh berposisi lurus sempurna. Dan angkat 1 tangan dan pegang paha secara bergantian", "Do this movement 10 times");
    methodProgram p11 = new methodProgram( "Side-to-Side Lunges",R.drawable.deepsidelunges, "Hampir sama seperti sumo squats pertama, posisi kalian berdiri dengan kaki bersiap-siap posisi kuda-kuda kemudian kalian ke arah kanan dengan kaki kanan agak menahan dan kaki kiri miring lurus ke kanan, dan setelah itu lakukan sebaliknya seperti gambar diatas", "Do this movement 20 times");
    methodProgram p12 = new methodProgram( "Lunge Step",R.drawable.leg2, "Pertama, berdiri tegak lalu kedua tangan memegang pinggang lalu, kaki satu persatu menekuk kebelakang seperti membentuk 90 derajat kebalik", "Do this movement 10 times");
    methodProgram p13 = new methodProgram("Calf Raises", R.drawable.leg5, "Pertama, kalian berdiri tegak dan lurus kemudian kalian tekan jari kaki kalian ke atas dan turun secara perlahan seperti gambar diatas", "Do this movement 10 times");
    methodProgram p14 = new methodProgram("Leg Extensions", R.drawable.legextensions, "Pertama, kalian harus posisi sujud kemudian tangan menahan kepala setelah itu kaki menendang ke belakang dan lakukan secara bergantian seperti gambar diatas", "Do this movement 20 times");
    methodProgram p15 = new methodProgram("Squats", R.drawable.sumosquats, "Pertama, posisi kalian berdiri dengan kaki bersiap-siap posisi kuda-kuda kemudian turun kebawah sampai hampir nekuk seperti gambar diatas", "Do this movement 40 times");



    methodProgram [] arrayProg = new methodProgram[]{
            p1, p2, p3, p4, p5, p6, p7, p8, p10, p11, p12, p13, p14, p15
    };

    public void shuffleProg(){
        Collections.shuffle(Arrays.asList(arrayProg));

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You must complete all programs!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
