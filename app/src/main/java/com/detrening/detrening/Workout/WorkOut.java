package com.detrening.detrening.Workout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.detrening.detrening.Program;
import com.detrening.detrening.R;

public class WorkOut extends AppCompatActivity {
    Button btnProg, btnArm, btnAbs, btnLeg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);
        setTitle("Workout");


        btnProg = (Button) findViewById(R.id.btnProg);
        btnProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this, Program.class);
                startActivity(intent);
            }
        });

        btnArm = (Button) findViewById(R.id.btnArm);
        btnArm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this, ArmWorkout.class);
                startActivity(intent);
            }
        });

        btnAbs = (Button) findViewById(R.id.btnAbs);
        btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this, AbsWorkout.class);
                startActivity(intent);
            }
        });

        btnLeg = (Button) findViewById(R.id.btnLeg);
        btnLeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this, LegWorkout.class);
                startActivity(intent);
            }
        });


    }
}
