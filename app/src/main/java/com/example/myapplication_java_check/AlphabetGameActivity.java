package com.example.myapplication_java_check;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_java_check.view_model.AlphabetGameViewModel;
import com.example.myapplication_java_check.view_model.AppContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlphabetGameActivity extends AppCompatActivity {

    @BindView(R.id.leftButton)
    Button leftB;
    @BindView(R.id.rightButton)
    Button rightB;
    @BindView(R.id.startButton)
    Button startB;

    @BindView(R.id.letterTextView)
    TextView letterTextView;
    @BindView(R.id.handTextView)
    TextView handTextView;
    @BindView(R.id.testTextView)
    TextView testTextView;
    @BindView(R.id.lowerDescriptionTextView)
    TextView lowerTextView;


    final long GAME_TIME = 10000;
    private final int SIMULTANEOUS_DIFFERENCE = 200;

    AlphabetGameViewModel alphabetGameViewModel;
    long leftClick = 0;
    long rightClick = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_alphabet_game);
        ButterKnife.bind(this);


        AppContainer appContainer = ((MyApplication) getApplication()).getAppContainer();
        alphabetGameViewModel = appContainer.alphabetGameViewModel();
        prepareView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    @Override
    protected void onStart() {
        super.onStart();
prepareView();
//        countDownStart();
    }

    private void prepareView() {
        leftB.setVisibility(View.VISIBLE);
        rightB.setVisibility(View.VISIBLE);
        startB.setVisibility(View.VISIBLE);
        testTextView.setText("Reading out loud\n the alphabet letter");
        lowerTextView.setText("Press as fast as you can corresponding button\n" +
                "Left for \"L\"\n" +
                "Right for \"R\"\n" +
                "Both at the same time for \"B\"");



        letterTextView.setText(Character.toString(alphabetGameViewModel.getFirstLetter()));
        handTextView.setText("B");
        leftB.setClickable(false);
        rightB.setClickable(false);

    }

    public void startGame() {
        leftB.setClickable(true);
        rightB.setClickable(true);
        leftB.setVisibility(View.VISIBLE);
        rightB.setVisibility(View.VISIBLE);
        startB.setVisibility(View.GONE);
        letterTextView.setText(Character.toString(alphabetGameViewModel.getFirstLetter()));
        handTextView.setText((Character.toString(alphabetGameViewModel.generateHand())));
        lowerTextView.setText("");


        new CountDownTimer(GAME_TIME, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 4000) {
                    testTextView.setText(String.valueOf((int) millisUntilFinished / 1000));
                }
            }

            @Override
            public void onFinish() {
                testTextView.setText("Finish");
                stopGame();
            }
        }.start();

    }


    public void stopGame() {
        leftB.setClickable(false);
        rightB.setClickable(false);
        testTextView.setText("Game\nfinished");
        startB.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.startButton)
    public void pressStartAgain() {
        prepareView();
        countDownStart();
    }

    private void countDownStart() {
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                testTextView.setText(String.valueOf((int) millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {
                testTextView.setText("START");
                startGame();

            }
        }.start();
    }

    private void buttonPressed(boolean simultan) {
        if (!simultan) {
            letterTextView.setText(Character.toString(alphabetGameViewModel.getNextLetter()));
            handTextView.setText(Character.toString(alphabetGameViewModel.generateHand()));
            ;
        }
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

}
