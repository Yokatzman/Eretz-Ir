package com.example.eretz_ir;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Utilities {

    public static String print_score(List<Integer> scores){
        String score ="";
        int size = scores.size();
        for (int i=0;i<size-1;i++){
            score = score+(scores.get(i))+"-";
        }
        score=score+(scores.get(size-1));
        return score;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String Ot() {
        String[] letters = {"א","ב","ג","ד","ה","ו","ז","ח","ט","י","כ","ל","מ","נ","ס","ע","פ","צ","ק","ר","ש","ת"};
        int index = ThreadLocalRandom.current().nextInt(0,23);
        return letters[index];
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String randDefinition(){
        Definitions x = new Definitions();
        int len=x.getLen();
        int index = ThreadLocalRandom.current().nextInt(0,len);
        return x.getString(index);

    }

}

