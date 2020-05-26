package ru.saprykin.vitaliy.webnotes.Controller;

public class Note {
    private int id;
    private String header;
    private String creation_time;
    private String last_change_time;

    public Note() {

    }

    public Note(int id, String header, String creation_time, String last_change_time) {
        this.id = id;
        this.header = header;
        this.creation_time = creation_time;
        this.last_change_time = last_change_time;
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

    public String getLast_change_time() {
        return last_change_time;
    }

    public void setLast_change_time(String last_change_time) {
        this.last_change_time = last_change_time;
    }
}
