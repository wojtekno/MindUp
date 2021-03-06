package com.nowak.wjw.mind_up;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nowak.wjw.mind_up.view_model.AlphabetGameViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    AlphabetGameViewModel alphabetGameViewModel;

    @BindView(R.id.alphabetGameTextView)
    TextView letterTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
//        AlphabetGameContainer alphabetGameContainer = ((MyApplication) getApplication()).getAlphabetGameContainer();
//        alphabetGameViewModel = alphabetGameContainer.alphabetGameViewModel();
//        ((MyApplication)getApplication()).getColorGameContainer().colorGame();
    }

    @OnClick(R.id.alphabetGameTextView)
    public void goToAlphabetGame() {
        startActivity(new Intent(this, AlphabetGameActivity.class));
    }


    @OnClick(R.id.colorGameTextView)
    public void goToColorGame(){startActivity(new Intent(this, ColorGameActivity.class));}
}
