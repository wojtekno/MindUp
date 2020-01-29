package com.example.myapplication_java_check;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_java_check.view_model.ColorGameContainer;
import com.example.myapplication_java_check.view_model.ColorGameViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorGameActivity extends AppCompatActivity {

    @BindView(R.id.questionWordTV)
    TextView questionWord;

    @BindView(R.id.answersView)
    View answersView;

    @BindView(R.id.answersLayout)
    LinearLayout answersLayout;
    private ColorGameViewModel colorGamViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        ButterKnife.bind(this);

        ColorGameContainer colorGameContainer = ((MyApplication) getApplication()).getColorGameContainer();
        colorGamViewModel = colorGameContainer.colorGameViewModel();

        addAnswerTV(1);
        addAnswerTV(colorGamViewModel.getColor());
        colorGamViewModel.getColor();

    }

    private void addAnswerTV(int answersNumber){
        TextView answer = new TextView(this);

        answer.setText(Integer.toString(answersNumber));
        answersLayout.addView(answer);
    }
}
