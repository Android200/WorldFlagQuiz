package com.codedbyte.worldflagquiz.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codedbyte.worldflagquiz.Questions;
import com.codedbyte.worldflagquiz.R;
import com.codedbyte.worldflagquiz.activities.MainActivity;
import com.codedbyte.worldflagquiz.models.QuestionsModel;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class QuizFragment extends Fragment implements View.OnClickListener {
    TextView score, timer, numberOfQues;
    Button opt1, opt2, opt3, opt4;
    CardView go;
    ImageView flag, wink;
    ProgressBar progressBar;
    int queLength, index;
    int scoreCount, numberOfQuesCounter = 1;
    CountDownTimer countDownTimer;
    int progressbarincrement;
    RelativeLayout quizContainer;
    Toast toast;
    ArrayList<QuestionsModel> questions = Questions.getMyQ();



    public QuizFragment() {
        // Required empty public constructor
    }
    private static final String ARG_PARAM1 = "param1";
    private String gameMode;
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gameMode = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        score = (TextView)v.findViewById(R.id.score);
        timer = (TextView)v.findViewById(R.id.timer);
        numberOfQues = (TextView)v.findViewById(R.id.numberOfQues);

        flag =(ImageView)v.findViewById(R.id.flag);
        wink =(ImageView)v.findViewById(R.id.wink);
        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        opt1 = (Button)v.findViewById(R.id.opt1);
        opt2 = (Button)v.findViewById(R.id.opt2);
        opt3 = (Button)v.findViewById(R.id.opt3);
        opt4 = (Button)v.findViewById(R.id.opt4);
        go = (CardView) v.findViewById(R.id.go);
        quizContainer = (RelativeLayout)v.findViewById(R.id.quiz_container);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);

        index = 0;
        queLength = questions.size();
        progressbarincrement = (int) Math.ceil(100/queLength);


        timerCOunter();



        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go.setVisibility(View.INVISIBLE);
                quizContainer.setVisibility(View.VISIBLE);

                Collections.shuffle(questions);
                if(gameMode.equals("quiz")){
                    countDownTimer.start();
                }else {
                    timer.setVisibility(View.INVISIBLE);
                    wink.setVisibility(View.VISIBLE);
                }
                updateUi();
            }
        });
        return v;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.opt1:
                checkAns(opt1.getText().toString());
                break;
            case R.id.opt2:
                checkAns(opt2.getText().toString());
                break;
            case R.id.opt3:
                checkAns(opt3.getText().toString());
                break;
            case R.id.opt4:
                checkAns(opt4.getText().toString());
                break;
        }
        if(index == 0){
            gameOver("end");
        }else {
            if(gameMode.equals("quiz")){
                updateUi();
            }

        }

    }

    public void updateUi(){
        opt1.setText(questions.get(index).getOptions()[0]);
        opt2.setText(questions.get(index).getOptions()[1]);
        opt3.setText(questions.get(index).getOptions()[2]);
        opt4.setText(questions.get(index).getOptions()[3]);
        flag.setImageResource(questions.get(index).getFlag());
        numberOfQues.setText(numberOfQuesCounter + "/" + queLength);
        progressBar.incrementProgressBy(progressbarincrement);
    }


    public void checkAns(String selected){
        String ans = questions.get(index).getAnswers();
        if(selected.equals(ans)){
            if(gameMode.equals("quiz")){
                if(toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(getContext(), "CORRECT", Toast.LENGTH_SHORT);
                toast.show();
            }else {
                 checkAnsDialog(ans,true);
            }
            scoreCount += 50;
        }else {
            if(gameMode.equals("quiz")){
                if(toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(getContext(), "WRONG", Toast.LENGTH_SHORT);
                toast.show();
            }else {
                checkAnsDialog(ans,false);
            }

        }
        index = (index + 1) % queLength;
        score.setText("Score : " +scoreCount);
        numberOfQuesCounter++;

    }
    public void timerCOunter(){
        countDownTimer = new CountDownTimer(20000+100, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                timer.setText("0");
                gameOver("time");
            }
        };
    }
    public void gameOver(String status){
        countDownTimer.cancel();
        int alertType = 0;
        String title = "";
        String content = "";
        switch (status){
                case "end":
                    alertType = SweetAlertDialog.SUCCESS_TYPE;
                    if(scoreCount>=400){
                        title = "ASTONISHING";
                        content = "You are a genius\n" +
                                "Your Score is : " + scoreCount +
                                "\nAttempted Que " + numberOfQuesCounter+
                                "\nCorrect Ans : " + scoreCount/50;
                    }else if(scoreCount>=250){
                        title = "GOOD";
                        content = "Well played!, but you can do better\n" +
                                "Your Score is : " + scoreCount +
                                "\nAttempted Que " + numberOfQuesCounter +
                                "\nCorrect Ans : " + scoreCount/50;
                    }else {
                        alertType = SweetAlertDialog.WARNING_TYPE;
                        title = "FAILED";
                        content = "You need to improve\n" +
                                "Your Score is : " + scoreCount +
                                "\nAttempted Que " + numberOfQuesCounter +
                                "\nCorrect Ans : " + scoreCount/50;
                    }
                    break;
                case "time":
                    alertType = SweetAlertDialog.ERROR_TYPE;
                    title = "TIME UP";

                    if(scoreCount>=400){
                        content = "You are a genius\n" +
                                "Your Score is : " + scoreCount +
                                "\nAttempted Que " + numberOfQuesCounter+
                                "\nCorrect Ans : " + scoreCount/50;
                    }else if(scoreCount>=250){
                        content = "Well played!, but you can do better\n" +
                                "Your Score is : " + scoreCount +
                                "\nAttempted Que " + numberOfQuesCounter +
                                "\nCorrect Ans : " + scoreCount/50;
                    }else {
                        content = "You need to improve\n" +
                                "Your Score is : " + scoreCount +
                                "\nAttempted Que " + numberOfQuesCounter +
                                "\nCorrect Ans : " + scoreCount/50;
                    }
                break;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), alertType)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("Play again")
                .setCancelText("Main menu")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                            playAgain();
                            sweetAlertDialog.dismiss();

                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.loadMainMenu();
                        playAgain();
                        sweetAlertDialog.dismiss();
                    }
                });
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();
    }

    public void playAgain(){
        index = 0;
        progressBar.setProgress(0);
        scoreCount = 0;
        score.setText("Score : " +scoreCount);
        numberOfQuesCounter = 1;
        quizContainer.setVisibility(View.INVISIBLE);
        go.setVisibility(View.VISIBLE);
        updateUi();
    }
    public void checkAnsDialog(String ans, Boolean rightOrWrong){
        String title = "";
        String content = "";
        int alertType  = 0;
        if(rightOrWrong){
            alertType = SweetAlertDialog.SUCCESS_TYPE;
            title = "CORRECT";
            content = "YOU ROCK";
        }else {
            alertType = SweetAlertDialog.ERROR_TYPE;
            title = "WRONG";
            content = "The right answer is " + ans;
        }

         SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), alertType)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        updateUi();
                        sDialog.dismiss();
                    }
                });
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();
    }

}
