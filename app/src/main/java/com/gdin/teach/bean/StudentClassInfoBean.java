package com.gdin.teach.bean;

/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class StudentClassInfoBean {
    private String imageUrl;
    private String className;
    private String classTeacher;
    private String classTime;
    private String classPosition;
    private int yuYueNum;
    private int yinDaoNum;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassPosition() {
        return classPosition;
    }

    public void setClassPosition(String classPosition) {
        this.classPosition = classPosition;
    }

    public int getYuYueNum() {
        return yuYueNum;
    }

    public void setYuYueNum(int yuYueNum) {
        this.yuYueNum = yuYueNum;
    }

    public int getYinDaoNum() {
        return yinDaoNum;
    }

    public void setYinDaoNum(int yinDaoNum) {
        this.yinDaoNum = yinDaoNum;
    }
}
