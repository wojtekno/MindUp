package com.example.myapplication_java_check;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication_java_check.model_view.AlphabetGameModelView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final int SIMULTANEOUS_DIFFERENCE = 200;

    private AlphabetGameModelView alphabetGameModelView;

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
    long leftClick = 0;
    long rightClick = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        alphabetGameModelView = new AlphabetGameModelView();

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

        letterV.setText(Character.toString(alphabetGameModelView.nextLetter()));
        handV.setText((Character.toString(alphabetGameModelView.generateHand())));
        startBut.setVisibility(View.GONE);
        leftB.setVisibility(View.VISIBLE);
        rightB.setVisibility(View.VISIBLE);

    }

    private void buttonPressed(boolean simultan) {
        if (!simultan) {
            letterV.setText(Character.toString(alphabetGameModelView.nextLetter()));
            handV.setText(Character.toString(alphabetGameModelView.generateHand()));
            ;
        }
    }
}
