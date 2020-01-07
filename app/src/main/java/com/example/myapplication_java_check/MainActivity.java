package com.example.myapplication_java_check;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication_java_check.model.AlphabetGame;
import com.example.myapplication_java_check.view_model.AlphabetGameViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final int SIMULTANEOUS_DIFFERENCE = 200;

    private AlphabetGame alphabetGame = new AlphabetGame();
    private AlphabetGameViewModel alphabetGameViewModel;

    @BindView(R.id.startButton)
    Button startBut;
    @BindView(R.id.leftButton)
    Button leftB;
    @BindView(R.id.rightButton)
    Button rightB;
    @BindView(R.id.letterTextView)
    TextView letterTextView;
    @BindView(R.id.handTextView)
    TextView handTextView;
    @BindView(R.id.testTextView)
    TextView testTextView;
    long leftClick = 0;
    long rightClick = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        alphabetGameViewModel = new AlphabetGameViewModel(alphabetGame);

    }

    @OnClick(R.id.leftButton)
    public void pressLeft() {
        leftClick = System.currentTimeMillis();
        buttonPressed(evaluateSimultaneous());
    }

    @OnClick(R.id.rightButton)
    public void pressRight() {
        rightClick = System.currentTimeMillis();
        buttonPressed(evaluateSimultaneous());
    }

    private boolean evaluateSimultaneous() {
        if (Math.abs(rightClick - leftClick) < SIMULTANEOUS_DIFFERENCE) {
            testTextView.setText("BOTH");
            return true;
        } else {
            return false;
        }
    }

    public void startGame(View view) {

        letterTextView.setText(Character.toString(alphabetGameViewModel.nextLetter()));
        handTextView.setText((Character.toString(alphabetGameViewModel.generateHand())));
        startBut.setVisibility(View.GONE);
        leftB.setVisibility(View.VISIBLE);
        rightB.setVisibility(View.VISIBLE);

    }

    private void buttonPressed(boolean simultan) {
        if (!simultan) {
            letterTextView.setText(Character.toString(alphabetGameViewModel.nextLetter()));
            handTextView.setText(Character.toString(alphabetGameViewModel.generateHand()));
            ;
        }
    }
}
