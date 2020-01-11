package com.example.myapplication_java_check;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication_java_check.view_model.AlphabetGameViewModel;
import com.example.myapplication_java_check.view_model.AppContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final int SIMULTANEOUS_DIFFERENCE = 200;

    AlphabetGameViewModel alphabetGameViewModel;

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
    final long GAME_TIME = 60000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AppContainer appContainer = ((MyApplication) getApplication()).getAppContainer();
        alphabetGameViewModel = appContainer.alphabetGameViewModel();
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
            testTextView.setText("");
            return false;
        }
    }

    public void startGame(View view) {

        letterTextView.setText(Character.toString(alphabetGameViewModel.nextLetter()));
        handTextView.setText((Character.toString(alphabetGameViewModel.generateHand())));
        startBut.setVisibility(View.GONE);
        leftB.setVisibility(View.VISIBLE);
        rightB.setVisibility(View.VISIBLE);
        new CountDownTimer(GAME_TIME, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 4000){
                    testTextView.setText(String.valueOf((int)millisUntilFinished/1000));
                }
            }

            @Override
            public void onFinish() {
                testTextView.setText("Finish");
            }
        }.start();

    }

    private void buttonPressed(boolean simultan) {
        if (!simultan) {
            letterTextView.setText(Character.toString(alphabetGameViewModel.nextLetter()));
            handTextView.setText(Character.toString(alphabetGameViewModel.generateHand()));
            ;
        }
    }
}
