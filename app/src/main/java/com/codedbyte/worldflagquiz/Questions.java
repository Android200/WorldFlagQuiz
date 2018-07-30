package com.codedbyte.worldflagquiz;

import com.codedbyte.worldflagquiz.models.QuestionsModel;

import java.util.ArrayList;

public class Questions {



    public static ArrayList<QuestionsModel> getMyQ() {
        ArrayList<QuestionsModel> questionsArray = new ArrayList<>();
        questionsArray.add(new QuestionsModel(R.drawable.argentina,
                new String[]{"greece","spain", "argentina", "serbia"},
                "argentina"));
        questionsArray.add(new QuestionsModel(R.drawable.belgium,
                new String[]{"costa rica", "belgium", "egypt", "iran"},
                "belgium"));
        questionsArray.add(new QuestionsModel( R.drawable.colombia,
                new String[]{"columbia", "senegal", "panama", "iceland"},
                "columbia"));
        questionsArray.add(new QuestionsModel(R.drawable.england,
                new String[]{"usa", "south korea", "peru", "england"},
                "england"));
        questionsArray.add(new QuestionsModel(R.drawable.france,
                new String[]{"mexico", "brazil", "france", "iran"},
                "france"));
        questionsArray.add(new QuestionsModel(R.drawable.nigeria,
                new String[]{"tunisia", "ghana", "nigeria", "iraq"},
                "nigeria"));
        questionsArray.add(new QuestionsModel(R.drawable.poland,
                new String[]{"algeria", "poland", "portugal", "switzerland"},
                "poland"));
        questionsArray.add(new QuestionsModel(R.drawable.morocco,
                new String[]{"morocco", "sweden", "denmark", "uruguay"},
                "morocco"));
        questionsArray.add(new QuestionsModel(R.drawable.saudiarabia,
                new String[]{"palestine", "qatar", "saudi arabia", "australia"},
                "saudi arabia"));
        questionsArray.add(new QuestionsModel(R.drawable.russia,
                new String[]{"uzbekistan", "russia", "turkmenistan", "togo"},
                "russia"));
        return questionsArray;

}

}
