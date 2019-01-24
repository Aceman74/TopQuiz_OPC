package com.aceman.topquiz.model;

import java.util.Comparator;

/**
 * Created by Lionel JOFFRAY - on 22/01/2019.
 */
    // Creation d'un joueur pour save dans une list dans SharedPreferences
public class SaveList {
protected String mName;
protected int mScore;

public SaveList(String Name, int Score){
this.mName = Name;
this.mScore = Score;
}
String getName(){
return mName;
}

int getScore(){
    return mScore;
}

}

    //  Methode comparative par score ou par nom

class SortByScore implements Comparator<SaveList>
{
    public int compare(SaveList a, SaveList b)
    {
        return b.mScore - a.mScore;
    }
}

 class SortByName implements Comparator<SaveList> {
    @Override
    public int compare(SaveList p1, SaveList p2) {
        return p1.mName.compareTo(p2.mName);
    }
}