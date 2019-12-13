package com.zzy.homework.entity;

public class Book {
    private String book_id;
    private String book_writer;
    private int book_presum;
    private int book_nowsum;
    private String book_boss;
    private String book_name;
    private int percentage;

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_writer() {
        return book_writer;
    }

    public void setBook_writer(String book_writer) {
        this.book_writer = book_writer;
    }

    public int getBook_presum() {
        return book_presum;
    }

    public void setBook_presum(int book_presum) {
        this.book_presum = book_presum;
    }

    public int getBook_nowsum() {
        return book_nowsum;
    }

    public void setBook_nowsum(int book_nowsum) {
        this.book_nowsum = book_nowsum;
    }

    public String getBook_boss() {
        return book_boss;
    }

    public void setBook_boss(String book_boss) {
        this.book_boss = book_boss;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
