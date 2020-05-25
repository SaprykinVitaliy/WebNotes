package ru.saprykin.vitaliy.webnotes;

import java.sql.Timestamp;

public class Note {
    private String header;
    private Timestamp creation_time;
    private Timestamp last_change_time;

    public Note() {

    }

    public Note(String header, Timestamp creation_time, Timestamp last_change_time) {
        this.header = header;
        this.creation_time = creation_time;
        this.last_change_time = last_change_time;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getLast_change_time() {
        return last_change_time;
    }

    public void setLast_change_time(Timestamp last_change_time) {
        this.last_change_time = last_change_time;
    }
}
