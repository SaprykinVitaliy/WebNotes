package ru.saprykin.vitaliy.webnotes.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saprykin.vitaliy.webnotes.Note;

import java.sql.*;
import java.util.ArrayList;

@Service
public class DBAgent {
    //private PostgresDBConnector connector;

    private final Connection dbConnection;

    @Autowired
    public DBAgent(PostgresDBConnector connector) {
        dbConnection = connector.getAppDBConnection();
    }

    public ArrayList<Note> getNotes() throws SQLException {
        Statement statement1 = dbConnection.createStatement();

        ResultSet notesMetadata = statement1.executeQuery("SELECT id, creation_time, last_change_time FROM note_metadata;");

        ArrayList<Note> result = new ArrayList<>();

        while (notesMetadata.next()) {
            int id = notesMetadata.getInt("id");
            Timestamp last_change_time = notesMetadata.getTimestamp("last_change_time");

            Statement statement2 = dbConnection.createStatement();
            ResultSet notesVersions = statement2.executeQuery("SELECT header FROM note_versions WHERE id = " + id + " AND time_of_version_creation = '" + last_change_time + "';");
            if (notesVersions.next()) {
                Note note = new Note(notesVersions.getString("header"),
                        notesMetadata.getTimestamp("creation_time"),
                        notesMetadata.getTimestamp("last_change_time"));

                result.add(note);
            }

            notesVersions.close();
            statement2.close();
        }

        notesMetadata.close();
        statement1.close();
        return result;
    }
}
