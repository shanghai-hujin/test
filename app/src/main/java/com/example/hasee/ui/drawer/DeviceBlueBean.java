package com.example.hasee.ui.drawer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeviceBlueBean implements Serializable {

    public String getBlueName() {
        return blueName;
    }

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }

    public String getBlueAdress() {
        return blueAdress;
    }

    public void setBlueAdress(String blueAdress) {
        this.blueAdress = blueAdress;
    }

    public String getFindTime() {
        return findTime;
    }

    public void setFindTime(String findTime) {
        this.findTime = findTime;
    }

    @SerializedName("blueName")
    private String blueName;

    @SerializedName("blueAdress")
    private String blueAdress;

    @SerializedName("findTime")
    private String findTime;

}
