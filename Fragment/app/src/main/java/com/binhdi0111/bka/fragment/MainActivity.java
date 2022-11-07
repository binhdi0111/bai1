package com.binhdi0111.bka.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentA.OnFragmentInteractionListener {
    Button btnClick,btnNext;
    private int mRadioButtonChoice = 2;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private boolean isFragmentDisplayed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClick = (Button)findViewById(R.id.button);
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

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                btnClick.setText(R.string.close);
            }
        }
    }
    public static FragmentA newInstance() {
        return new FragmentA();
    }
    public void displayFragment(){
        FragmentA fragmentA = FragmentA.newInstance(mRadioButtonChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentB fragmentB = new FragmentB();
        fragmentTransaction.add(R.id.fragment_a,fragmentA).addToBackStack(null);
        fragmentTransaction.add(R.id.Frame_layout,fragmentB).addToBackStack(null);
        fragmentTransaction.commit();
        btnClick.setText(R.string.close);
        isFragmentDisplayed = true;
    }
    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentA fragmentA = (FragmentA)fragmentManager.findFragmentById(R.id.fragment_a);
        FragmentB fragmentb = (FragmentB) fragmentManager.findFragmentById(R.id.Frame_layout);
        if (fragmentb != null && fragmentA!=null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragmentb);
            fragmentTransaction.remove(fragmentA);
            fragmentTransaction.commit();
        }

        btnClick.setText(R.string.open);
        isFragmentDisplayed = false;
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
        Toast.makeText(this, "Choice is " + Integer.toString(choice), Toast.LENGTH_SHORT).show();
        Log.d("bbbbbbbbbbbb", "onRadioButtonChoice: "+mRadioButtonChoice);
    }
}