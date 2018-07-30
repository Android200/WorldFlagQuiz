package com.codedbyte.worldflagquiz.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codedbyte.worldflagquiz.R;
import com.codedbyte.worldflagquiz.fragments.GameMode;
import com.codedbyte.worldflagquiz.fragments.QuizFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameMode gameMode = (GameMode) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(gameMode == null){
            gameMode = GameMode.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, gameMode).commit();
        }

    }
    public  void loadQuiz(){
        QuizFragment quizFragment = QuizFragment.newInstance("quiz");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, quizFragment)
                .addToBackStack(null)
                .commit();

    }
    public  void loadLearning(){
        QuizFragment quizFragment = QuizFragment.newInstance("learning");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, quizFragment)
                .addToBackStack(null)
                .commit();
    }
    public  void loadMainMenu(){
        GameMode gameMode = GameMode.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, gameMode)
                .commit();
    }
}
