package com.example.myapplication_java_check;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication_java_check.model.ColorEnum;
import com.example.myapplication_java_check.model.ColorObj;
import com.example.myapplication_java_check.view_model.ColorGameContainer;
import com.example.myapplication_java_check.view_model.ColorGameViewModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ColorGameFragment extends Fragment {

    private final int ANSWER_MARGIN = 16;
    private final int ANSWER_TIME_DELAY = 300;

    @BindView(R.id.questionWordTV)
    TextView questionWord;
    @BindView(R.id.answersLayoutParent)
    LinearLayout answersLayoutParent;

    @BindView(R.id.helpingLayout)
    LinearLayout helpingLayout;
    @BindView(R.id.separateWords)
    LinearLayout separateWords;
    @BindView(R.id.heightValue)
    TextView screenHeightValueTV;
    @BindView(R.id.widthValue)
    TextView screenWidthValueTV;
    @BindView(R.id.layoutWidthValue)
    TextView layoutWidthValueTV;

    LinearLayout currentAnswerLinearLayout;

    Unbinder unbinder;
    private ColorGameViewModel colorGamViewModel;// = new ViewModelProvider(requireActivity()).get(ColorGameViewModel.class);
    private DisplayMetrics metrics;

    HashMap<ColorEnum, Integer> colorMap = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_color_game, container, false);
        unbinder = ButterKnife.bind(this, view);

        ColorGameContainer colorGameContainer = ((MyApplication) getActivity().getApplication()).getColorGameContainer();
        colorGamViewModel = colorGameContainer.colorGameViewModel();

        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        initializeColorMap();
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

    private void colorTheScreen(ColorObj[] colors) {
        helpingLayout.removeAllViews();
        separateWords.removeAllViews();
        prepareQuestionTV(colors[0]);
        //todo destroy all of the views howto?
        answersLayoutParent.removeAllViewsInLayout();
        currentAnswerLinearLayout = null;
        for (int i = 1; i < colors.length; i++) {
            TextView answer = prepareAnswerTV(colors[i]);
            placeAnswerTVOnTheScreen(answer);
        }
    }

    private TextView prepareAnswerTV(ColorObj colorObj) {
        TextView answer = new TextView(getActivity());
        answer.setText(colorObj.getmName().toString());
        answer.setTextColor(colorMap.get(colorObj.getmColor()));
        answer.setTextAppearance(getActivity().getApplicationContext(), R.style.ColorGameFont);
        answer.setTextSize(32);
        answer.setMaxLines(1);

        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linParams.setMargins(ANSWER_MARGIN, ANSWER_MARGIN, ANSWER_MARGIN, ANSWER_MARGIN);
        answer.setLayoutParams(linParams);

        answer.setOnClickListener(v -> {
            setAnswersClickable(false);
            displayGraphicEvaluation(colorGamViewModel.evaluateAnswer(colorObj.getmName().ordinal()), v);
            answer.postDelayed(() -> colorTheScreen(colorGamViewModel.getNextBoard()), ANSWER_TIME_DELAY);
        });

        return answer;
    }

    private void setAnswersClickable(boolean clickable) {
        for (int i = 0; i < answersLayoutParent.getChildCount(); i++) {
            ViewGroup viewGroup = (ViewGroup) answersLayoutParent.getChildAt(i);
            for (int j = 0; j < viewGroup.getChildCount(); j++) {
                View view = viewGroup.getChildAt(j);
                view.setClickable(clickable);
            }
        }
    }

    private void displayGraphicEvaluation(boolean isCorrect, View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        int index = parent.indexOfChild(view);
        parent.removeView(view);
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relParams.setMargins(ANSWER_MARGIN, ANSWER_MARGIN, ANSWER_MARGIN, ANSWER_MARGIN);

        view.setLayoutParams(relParams);

        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(relParams);

        if (isCorrect) {
            imageView.setImageResource(R.drawable.ic_correct);
        } else {
            imageView.setImageResource(R.drawable.ic_close);
        }

        relativeLayout.addView(view);
        relativeLayout.addView(imageView);
        parent.addView(relativeLayout, index);
    }

    private void placeAnswerTVOnTheScreen(View answer) {
        answer.measure(0, 0);
        int answerWidth = answer.getMeasuredWidth();

        if (currentAnswerLinearLayout == null) {
            currentAnswerLinearLayout = new LinearLayout(getActivity());
            currentAnswerLinearLayout.setGravity(Gravity.CENTER);
            answersLayoutParent.addView(currentAnswerLinearLayout);
        }

        if (doesTextViewFitsTheScreen(answerWidth)) {
            currentAnswerLinearLayout.addView(answer);
        } else {
            currentAnswerLinearLayout = new LinearLayout(getActivity());
            currentAnswerLinearLayout.setGravity(Gravity.CENTER);
            currentAnswerLinearLayout.addView(answer);
            answersLayoutParent.addView(currentAnswerLinearLayout);
        }

    }

    private void prepareQuestionTV(ColorObj colorObj) {
        questionWord.setText(colorObj.getmName().toString());
        questionWord.setTextColor(colorMap.get(colorObj.getmColor()));
    }

    private boolean doesTextViewFitsTheScreen(int textViewWidth) {
        currentAnswerLinearLayout.measure(0, 0);
        return currentAnswerLinearLayout.getMeasuredWidth() + textViewWidth + 2 * ANSWER_MARGIN < metrics.widthPixels;
    }

    private void addHelpingTextView(int viewsWidth) {
        TextView helptext1 = new TextView(getActivity());
        TextView helptext2 = new TextView(getActivity());
        currentAnswerLinearLayout.measure(0, 0);
        helptext1.setText(String.format("|__%d__|", currentAnswerLinearLayout.getMeasuredWidth()));
        helptext2.setText(String.format("|__%d__|", viewsWidth));
        helpingLayout.addView(helptext1);
        separateWords.addView(helptext2);
        updateLinearLayoutWidth();
    }

    private void updateLinearLayoutWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        currentAnswerLinearLayout.measure(0, 0);
        layoutWidthValueTV.setText(String.format("LayoutWidth: %d", currentAnswerLinearLayout.getMeasuredWidth()));
        screenHeightValueTV.setText(String.format("screenHeight: %d", height));
        screenWidthValueTV.setText(String.format("screenWidth: %d", width));
    }
}
