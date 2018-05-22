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
    //活动时间
    private String activityTime;
    //活动日期
    private String activityDate;
    //活动内容
    private String activityThings;
    //参与人数
    private String activityPeople;
    //类型
    private String activityType;
    //程度
    private int activityLevel;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }
}
