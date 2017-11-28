package hyperic.thinkfast;

import java.util.Random;

/**
 * Created by Alek Ivanovski on 11/21/2016.
 */

public class GenerateNumbers {
    public int generateNum(){
        Random ran = new Random();
        int broj = ran.nextInt(20);
        return broj;
    }

    public int generateGame(){
        Random ran = new Random();
        int broj = ran.nextInt(3)+1;
        return broj;
    }

    public int generatOpe(){
        Random ran = new Random();
        int broj = ran.nextInt(3);
        return broj;
    }

    public boolean generatePre(){
        Random ran = new Random();
        int broj = ran.nextInt(10);
        if(broj < 6)
            return false;
        else
            return true;
    }
}
