package com.example.myapplication_java_check;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_java_check.view_model.AlphabetGameViewModel;
import com.example.myapplication_java_check.view_model.AppContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    AlphabetGameViewModel alphabetGameViewModel;

    @BindView(R.id.letterTextView)
    TextView letterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        AppContainer appContainer = ((MyApplication) getApplication()).getAppContainer();
        alphabetGameViewModel = appContainer.alphabetGameViewModel();
    }

    @OnClick(R.id.letterTextView)
    public void goToAlphabetGame() {
        startActivity(new Intent(this, AlphabetGameActivity.class));
    }

}
