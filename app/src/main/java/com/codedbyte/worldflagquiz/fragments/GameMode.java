package com.codedbyte.worldflagquiz.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codedbyte.worldflagquiz.R;
import com.codedbyte.worldflagquiz.activities.MainActivity;


public class GameMode extends Fragment implements View.OnClickListener{

    Button quiz, learning;
    public GameMode() {
        // Required empty public constructor
    }


    public static GameMode newInstance() {
        GameMode fragment = new GameMode();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_mode, container, false);
        quiz = (Button)v.findViewById(R.id.quiz);
        learning = (Button)v.findViewById(R.id.learning);

        quiz.setOnClickListener(this);
        learning.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity)getActivity();
        switch (view.getId()){
            case R.id.quiz:
                mainActivity.loadQuiz();
                break;
            case R.id.learning:
                mainActivity.loadLearning();
                break;

        }
    }
}
