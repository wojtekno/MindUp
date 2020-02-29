package com.nowak.wjw.mind_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @OnClick(R.id.colorGameEasy)
    public void startEasyLevel(){
        startLevel(1);
    }

    @OnClick(R.id.colorGameMedium)
    public void startMediumLevel(){
        startLevel(2);
    }
    @OnClick(R.id.colorGameHard)
    public void startHardLevel(){
        startLevel(3);
    }

    private void startLevel(int level){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ColorGameFragment colorGameFragment = new ColorGameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("level",level);
        colorGameFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.parentContainer,colorGameFragment)
                .addToBackStack(null)
                .commit();
    }
}
