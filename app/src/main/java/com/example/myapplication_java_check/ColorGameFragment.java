package com.example.myapplication_java_check;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication_java_check.model.ColorEnum;
import com.example.myapplication_java_check.model.ColorGame;
import com.example.myapplication_java_check.model.ColorObj;
import com.example.myapplication_java_check.view_model.ColorGameContainer;
import com.example.myapplication_java_check.view_model.ColorGameViewModel;

import java.util.HashMap;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ColorGameFragment extends Fragment {

    @BindView(R.id.questionWordTV)
    TextView questionWord;
    @BindView(R.id.answersLayoutParent)
    LinearLayout answersLayoutParent;
    @BindView(R.id.answersLayout)
    LinearLayout answersLayout;
    @BindView(R.id.helpingLayout)
    LinearLayout helpingLayout;
    @BindView(R.id.heightValue)
    TextView screenHeightValueTV;
    @BindView(R.id.widthValue)
    TextView screenWidthValueTV;
    @BindView(R.id.layoutWidthValue)
    TextView layoutWidthValueTV;

    Unbinder unbinder;
    private ColorGameViewModel colorGamViewModel;
    private int currentAnswerLayoutWidth;
    private DisplayMetrics metrics;

    HashMap<ColorEnum, Integer> colorMap = new HashMap<>();

    public ColorGameFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_color_game, container, false);
        unbinder = ButterKnife.bind(this, view);

        ColorGameContainer colorGameContainer = ((MyApplication) getActivity().getApplication()).getColorGameContainer();
        colorGamViewModel = colorGameContainer.colorGameViewModel();

        initializeColorMap();

        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        addHelpingTextView(width);
        ColorObj[] colors = colorGamViewModel.getCurrentBoard();
        colorTheScreen(colors);


        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initializeColorMap() {
        for (int i = 0; i < ColorEnum.values().length; i++) {
            colorMap.put(ColorEnum.values()[i], getActivity().getApplicationContext().getResources().getIntArray(R.array.wordsColors)[i]);
        }
    }

    private void addAnswerTV(ColorObj colorObj) {
        TextView answer = new TextView(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);

        answer.setText(colorObj.getmName().toString());
        answer.setLayoutParams(params);
        answer.setTextAppearance(getActivity().getApplicationContext(), R.style.ColorGameFont);
        answer.setTextSize(32);
        answer.setMaxLines(1);

        answer.measure(0, 0);
        answer.setTextColor(colorMap.get(colorObj.getmColor()));
        int answerWidth = answer.getMeasuredWidth();
        if (textViewFitsTheScreen(answerWidth)) {
            currentAnswerLayoutWidth += answerWidth + 32;
            answersLayout.addView(answer);
        } else {
            LinearLayout newAnswerLayout = new LinearLayout(getActivity());
            newAnswerLayout.setGravity(Gravity.CENTER);
            newAnswerLayout.addView(answer);
            answersLayoutParent.addView(newAnswerLayout);
        }

        addHelpingTextView(answerWidth);
    }

    private boolean textViewFitsTheScreen(int screenWidth) {
        return currentAnswerLayoutWidth + screenWidth + 32 < metrics.widthPixels;
    }

    private void addHelpingTextView(int viewsWidth) {


        TextView helptext1 = new TextView(getActivity());
        TextView helptext2 = new TextView(getActivity());

        answersLayout.measure(0, 0);

        helptext1.setText(String.format("|__%d__|", viewsWidth));

        helpingLayout.addView(helptext1);
//        helpingLayout.addView(helptext2);
        udpateLinearLayoutWidth();
    }

    private void udpateLinearLayoutWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;


        answersLayout.measure(0, 0);


        layoutWidthValueTV.setText(String.format("LayoutWidth: %d", answersLayout.getMeasuredWidth()));
        screenHeightValueTV.setText(String.format("screenHeight: %d", height));
        screenWidthValueTV.setText(String.format("screenWidth: %d", width));

    }

    private void colorTheScreen(ColorObj[] colors) {
        questionWord.setText(colors[0].getmName().toString());
        for (int i = 1; i < colors.length; i++) {
            addAnswerTV(colors[i]);
        }
    }
}
