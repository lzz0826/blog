package com.sai.blog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Guess {

    private String answer;
    private int A;
    private int B;
    private int time;
    private int disit;


    public Guess() {
        disit = 4;
        setArrigbte();
    }

    public Guess(int p) {
        disit = p;
        setArrigbte();

    }

    public void setArrigbte() {
        String all_nunbers = "0123456789";
        String answer_deme = "";
        List<String> answerList = new ArrayList<>(Arrays.asList(all_nunbers.split("")));
        Collections.shuffle(answerList);
        for (String s : answerList) {
            answer_deme += s;
        }
        answer = answer_deme.substring(0, disit);
        do {
            answer = answer_deme.substring(0, disit);
        } while (Character.toString(answer.charAt(0)).equals("a"));
        A = 0;
        B = 0;
        time = 0;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAB() {
        return Integer.toString(A) +"A" + Integer.toString(B)+"B";

    }

    public int getTime() {
        return time;
    }

    public void addTime() {
        time += 1;

    }

    public void ABcounter(String guess) {

        A = 0;
        B = 0;

        for (int i = 0; i < disit; i++) {
            String j = Character.toString(answer.charAt(i));
            if (guess.contains(j)) {
                if (answer.indexOf(j) == guess.indexOf(j)) {
                    A += 1;
                } else {
                    B += 1;
                }
            }
        }

    }

    public boolean findNumber(String guess) {
        int count = 0;

        for (int i = 0; i < disit; i++) {
            for (int j = 0; j < 4; j++) {
                if (Character.toString(guess.charAt(i)).equals(Character.toString(guess.charAt(j)))) {
                    count += 1;
                }
            }
            if (count > 1) {
                return true;
            }
            count = 0;
        }
        return false;
    }

    public boolean tast(String guess){
        addTime();
        ABcounter(guess);
        if( A == disit) {
            return true;
        }else{
            return false;
        }
    }
}
