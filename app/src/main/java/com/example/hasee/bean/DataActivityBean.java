package com.example.hasee.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/22 10:53
 */

public class DataActivityBean extends DataSupport implements Serializable {
    //活动名字
    private String activityName;

    //活动日期开始
    private String activityDateStart;

    //活动日期结束
    private String activityDateEnd;

    //活动内容
    private String activityThings;

    //参与人数
    private String activityPeople;

    //类型
    private int activityType;

    //程度
    private int activityLevel;

    public String getActivityDateEnd() {
        return activityDateEnd;
    }

    public void setActivityDateEnd(String activityDateEnd) {
        this.activityDateEnd = activityDateEnd;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }


    public String getActivityDateStart() {
        return activityDateStart;
    }

    public void setActivityDateStart(String activityDate) {
        this.activityDateStart = activityDate;
    }

    public String getActivityThings() {
        return activityThings;
    }

    public void setActivityThings(String activityThings) {
        this.activityThings = activityThings;
    }

    public String getActivityPeople() {
        return activityPeople;
    }

    public void setActivityPeople(String activityPeople) {
        this.activityPeople = activityPeople;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }
}
