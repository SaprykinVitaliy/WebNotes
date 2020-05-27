package ru.saprykin.vitaliy.webnotes.Controller;

public class Note {
    private int id;
    private String header;
    private String text;
    private String creation_time;
    private String change_time;

    public Note() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Note(int id, String header, String text, String creation_time, String change_time) {
        this.id = id;
        this.text = text;
        this.header = header;
        this.creation_time = creation_time;
        this.change_time = change_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public String getChange_time() {
        return change_time;
    }

    public void setChange_time(String change_time) {
        this.change_time = change_time;
    }
}
