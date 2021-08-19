package com.example.denemem.swipeandtabs;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepCounter extends Fragment implements SensorEventListener {

    private PieModel sliceGoal, sliceCurrent;
    private PieChart pg;

    TextView step_value;
    TextView calories;

    private int goal;
    private int steps;


    boolean running = false;

    SensorManager sensorManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_stepcounter, container, false);

        step_value = (TextView) rootView.findViewById(R.id.step_value);
        calories= (TextView) rootView.findViewById(R.id.calories);



        pg = (PieChart) rootView.findViewById(R.id.graph);
        sliceCurrent = new PieModel("",0, Color.parseColor("#99CC00"));
        pg.addPieSlice(sliceCurrent);


        sliceGoal = new PieModel("", Settings.GOAL_DEFAULT, Color.parseColor("#CC0000"));
        pg.addPieSlice(sliceGoal);

        pg.setDrawValueInPie(false);
        pg.setUsePieRotation(true);
        pg.startAnimation();


        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        return rootView;



    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;

        SharedPreferences prefs = getActivity().getSharedPreferences("bunehacı", Context.MODE_PRIVATE);
        goal = prefs.getInt("goal", Settings.GOAL_DEFAULT);

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null)   {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }   else    {
            Toast.makeText(getActivity(), "Your phone doesn't sport step counter :(", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running) {
            step_value.setText(String.valueOf(event.values[0]));
            steps = (int) event.values[0];
            calories.setText(String.valueOf(steps*1/4));
        }

        updatePie(event.values[0]);
    }

    private void updatePie(float value)    {
        sliceCurrent.setValue(value);
        if (goal- value>0) {
            //goal not reached
            if (pg.getData().size() == 1) {
                pg.addPieSlice(sliceGoal);
            }
            sliceGoal.setValue(goal - value);
        }else{
            //goal reached
            pg.clearChart();
            pg.addPieSlice(sliceCurrent);
        }
        pg.update();
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

