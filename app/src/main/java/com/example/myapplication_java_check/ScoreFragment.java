package com.example.myapplication_java_check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScoreFragment extends Fragment {


    @BindView(R.id.textView1)
    private TextView message;

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.trial_layput, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        int score = getArguments().getInt("SCORE");
        message.setText(String.format("You scored: %d", score));

        return rootView;
    }


    @Override
    public void onDetach() {
        getFragmentManager().popBackStack();
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();

    }
}
