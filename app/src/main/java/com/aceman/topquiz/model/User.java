package com.aceman.topquiz.model;

/**
 * Created by Lionel JOFFRAY - on 14/01/2019.
 */
public class User {
   private String mFirstName;

   public String getFirstName() {
      return mFirstName;
   }

   public void setFirstName(String firstName) {
      mFirstName = firstName;
   }

   @Override
   public String toString() {
      return "User{" +
              "mFirstName='" + mFirstName + '\'' +
              '}';
   }
}
