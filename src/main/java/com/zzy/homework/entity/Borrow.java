package com.zzy.homework.entity;

import java.util.Date;

public class Borrow {
    private Date pre_time;
    private String stu_id;
    private String book_id;
    private int status;
    private int unique_id;
    private Date nxt_time;
    private String now_time;
    private String string_version_per_time;
    private int past_days;
    private int allowed_days;
    private int remain_days;
    private int percentage;
    private String stu_name;
    private String book_name;
    private String stu_sex;
    private int more_time;

    public int getMore_time() {
        return more_time;
    }

    public void setMore_time(int more_time) {
        this.more_time = more_time;
    }

    public String getString_version_per_time() {
        return string_version_per_time;
    }

    public void setString_version_per_time(String string_version_per_time) {
        this.string_version_per_time = string_version_per_time;
    }



    public int getRemain_days() {
        return remain_days;
    }

    public void setRemain_days(int remain_days) {
        this.remain_days = remain_days;
    }



    public int getPast_days() {
        return past_days;
    }

    public void setPast_days(int past_days) {
        this.past_days = past_days;
    }



    public String getNow_time() {
        return now_time;
    }

    public void setNow_time(String now_time) {
        this.now_time = now_time;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Date getPre_time() {
        return pre_time;
    }

    public void setPre_time(Date pre_time) {
        this.pre_time = pre_time;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public Date getNxt_time() {
        return nxt_time;
    }

    public void setNxt_time(Date nxt_time) {
        this.nxt_time = nxt_time;
    }

    public int getAllowed_days() {
        return allowed_days;
    }

    public void setAllowed_days(int allowed_days) {
        this.allowed_days = allowed_days;
    }
}
