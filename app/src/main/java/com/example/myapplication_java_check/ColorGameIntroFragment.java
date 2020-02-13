package com.example.myapplication_java_check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ColorGameIntroFragment extends Fragment {

    @BindView(R.id.startColorGameBt)
    Button startButton;
    @BindView(R.id.instructionsTV)
    TextView instructionTV;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_game_intro, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.startColorGameBt)
    public void startGame() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ColorGameFragment colorGameFragment = new ColorGameFragment();
        fragmentTransaction.add(R.id.parentContainer, colorGameFragment);
//        fragmentTransaction.replace(R.id.parentContiner,colorGameFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
