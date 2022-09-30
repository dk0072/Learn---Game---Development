package com.nogravity.learnit;

import androidx.annotation.NonNull;

public class Chat {

    String UserName;
    String Message;
    String TimeStamp;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String Date;


    String profileUri;


    public Chat() {
    }
    public Chat(String profileUri) {
        this.profileUri = profileUri;
    }
    @NonNull
    @Override
    public String toString() {
        return "Chat{" +
                "UserName='" + UserName + '\'' +
                ", Message='" + Message + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                '}';
    }




    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
