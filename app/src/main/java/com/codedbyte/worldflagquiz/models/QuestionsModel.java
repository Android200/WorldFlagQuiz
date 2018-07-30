package com.codedbyte.worldflagquiz.models;

public class QuestionsModel {
    private int flag;
    private String[] options;
    private String answers;

    public QuestionsModel(int flag, String[] options, String answers) {
        this.flag = flag;
        this.options = options;
        this.answers = answers;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
