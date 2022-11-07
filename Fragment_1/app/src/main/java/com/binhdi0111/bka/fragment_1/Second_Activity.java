package com.binhdi0111.bka.fragment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Second_Activity extends AppCompatActivity {

    Button btnClick, btnBack;

    static final String STATE_FRAGMENT = "state_of_fragment";
    private boolean isFragmentDisplayed = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        btnClick = (Button) findViewById(R.id.button2);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });
        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Second_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment_1 is displayed, change button to "close".
                btnClick.setText(R.string.close);
            }
        }
    }

    public static FragmentA newInstance() {
        return new FragmentA();
    }

    public void displayFragment(){
        FragmentA fragmentA = FragmentA.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentB fragmentB = new fragmentB();
        fragmentTransaction.add(R.id.fragment_a2,fragmentA).addToBackStack(null);
        fragmentTransaction.add(R.id.Frame_layout2,fragmentB).addToBackStack(null);
        fragmentTransaction.commit();
        btnClick.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentA fragmenta = (FragmentA) fragmentManager.findFragmentById(R.id.fragment_a2);
        fragmentB fragmentB = (fragmentB) fragmentManager.findFragmentById(R.id.Frame_layout2);
        if (fragmenta != null) {
            // Create and commit the transaction to remove the fragment_1.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragmenta);
            fragmentTransaction.remove(fragmentB);
            fragmentTransaction.commit();
        }

        btnClick.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment_1 (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }
}