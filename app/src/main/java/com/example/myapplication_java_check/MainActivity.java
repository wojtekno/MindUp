package com.example.myapplication_java_check;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final int SIMULTANEOUS_DIFFERENCE = 200;
    @BindView(R.id.startButton)
    Button startBut;
    @BindView(R.id.leftB)
    Button leftB;
    @BindView(R.id.rightB)
    Button rightB;
    @BindView(R.id.letterView)
    TextView letterV;
    @BindView(R.id.handView)
    TextView handV;
    @BindView(R.id.textViewtest)
    TextView testText;
    char[] alphabet;
    int currentLetter;
    long leftClick = 0;
    long rightClick = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    }

    @OnClick(R.id.leftB)
    public void pressLeft() {
        leftClick = System.currentTimeMillis();
        buttonPressed(evaluateSimultaneous("left"));
    }

    @OnClick(R.id.rightB)
    public void pressRight() {
        rightClick = System.currentTimeMillis();
        buttonPressed(evaluateSimultaneous("right"));
    }

    private boolean evaluateSimultaneous(String button) {
        if (Math.abs(rightClick - leftClick) < SIMULTANEOUS_DIFFERENCE) {
            testText.setText("BOTH");
            return true;
        } else {
            testText.setText("");
            return false;
        }
    }

    public void startGame(View view) {

        letterV.setText(Character.toString(alphabet[0]));
        generateHand();
        startBut.setVisibility(View.GONE);
        leftB.setVisibility(View.VISIBLE);
        rightB.setVisibility(View.VISIBLE);

    }

    public void nextLetter() {
        if (currentLetter == alphabet.length - 1) {
            currentLetter = 0;
        } else {
            currentLetter++;
        }
        letterV.setText(Character.toString(alphabet[currentLetter]));
    }

    private void buttonPressed(boolean simultan) {
        if (!simultan) {
            nextLetter();
            generateHand();
        }
    }

    private void generateHand() {
        int number = new Random().nextInt(3);
        char hand;
        switch (number) {
            case 0:
                hand = 'L';
                break;
            case 1:
                hand = 'R';
                break;
            default:
                hand = 'B';
        }
        handV.setText(Character.toString(hand));
    }
}
