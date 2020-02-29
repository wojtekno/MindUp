package com.nowak.wjw.mind_up;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nowak.wjw.mind_up.view_model.AlphabetGameContainer;
import com.nowak.wjw.mind_up.view_model.AlphabetGameViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlphabetGameActivity extends AppCompatActivity {

    private static final long ALPHABET_GAME_TIME_MILLISEC = 10000;
    @BindView(R.id.leftButton)
    Button leftB;
    @BindView(R.id.rightButton)
    Button rightB;
    @BindView(R.id.startButton)
    Button startB;
    @BindView(R.id.infoButton)
    Button infoB;

    @BindView(R.id.alphabetGameTextView)
    TextView letterTextView;
    @BindView(R.id.handTextView)
    TextView handTextView;
    @BindView(R.id.upperDescriptionTextView)
    TextView upperDescriptionTextView;
    @BindView(R.id.lowerDescriptionTextView)
    TextView lowerDescriptionTextView;
    @BindView(R.id.communicatorTextView)
    TextView communicatorTextView;


    final long GAME_TIME_MILISEC = 10100;
    private final int SIMULTANEOUS_DIFFERENCE = 200;

    //    CountDownTimer currentCountDownTimer;
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


        AlphabetGameContainer alphabetGameContainer = ((MyApplication) getApplication()).getAlphabetGameContainer();
        alphabetGameViewModel = alphabetGameContainer.alphabetGameViewModel();
        prepareView();
        setInfoVisible(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void prepareView() {
        leftB.setVisibility(View.VISIBLE);
        rightB.setVisibility(View.VISIBLE);
        startB.setVisibility(View.VISIBLE);
        leftB.setClickable(false);
        rightB.setClickable(false);

        upperDescriptionTextView.setText(getResources().getString(R.string.alphabet_game_instruction_p1));
        lowerDescriptionTextView.setVisibility(View.VISIBLE);
        lowerDescriptionTextView.setText(getResources().getString(R.string.alphabet_game_instruction_p2));

        letterTextView.setText(getResources().getString(R.string.alphabet_game_intro_letter));
        handTextView.setText(getResources().getString(R.string.alphabet_game_intro_hand));

    }

    private void setInfoVisible(boolean state) {
        if (state) {
            infoB.setVisibility(View.GONE);
            upperDescriptionTextView.setVisibility(View.VISIBLE);
            lowerDescriptionTextView.setVisibility(View.VISIBLE);
        } else {
            upperDescriptionTextView.setVisibility(View.GONE);
            lowerDescriptionTextView.setVisibility(View.GONE);
        }
    }

    public void startGame() {
        leftB.setClickable(true);
        rightB.setClickable(true);
        alphabetGameViewModel.resetLetter();
        letterTextView.setText(Character.toString(alphabetGameViewModel.getNextLetter()));
        handTextView.setText((Character.toString(alphabetGameViewModel.generateHand())));


//        currentCountDownTimer =
        new CountDownTimer(GAME_TIME_MILISEC, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 10000) {
                    communicatorTextView.setText(String.valueOf((int) millisUntilFinished / 1000));
                }
            }

            @Override
            public void onFinish() {
                stopGame();
            }
        }.start();

    }


    public void stopGame() {
        leftB.setClickable(false);
        rightB.setClickable(false);
        communicatorTextView.setText(getResources().getString(R.string.alphabet_game_game_finished));
        startB.setVisibility(View.VISIBLE);
        infoB.setVisibility(View.VISIBLE);
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
    public void pressStart() {
        startB.setVisibility(View.GONE);
        infoB.setVisibility(View.GONE);
        setInfoVisible(false);
        countDownStart();
    }

    private void countDownStart() {
//        currentCountDownTimer =
        new CountDownTimer(3100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                communicatorTextView.setText(String.valueOf((int) millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                communicatorTextView.setText(getResources().getString(R.string.start_counter_start));
                startGame();
            }
        }.start();
    }

    @OnClick(R.id.infoButton)
    public void pressInfoB() {
        setInfoVisible(true);
        infoB.setVisibility(View.GONE);
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
            communicatorTextView.setText("BOTH");
            return true;
        } else {
            communicatorTextView.setText("");
            return false;
        }
    }

}
