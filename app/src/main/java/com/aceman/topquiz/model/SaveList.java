package com.aceman.topquiz.model;

import java.util.Comparator;

/**
 * Created by Lionel JOFFRAY - on 22/01/2019.
 */
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
class SortByScore implements Comparator<SaveList>
{
    // Used for sorting in ascending order of
    // roll number
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