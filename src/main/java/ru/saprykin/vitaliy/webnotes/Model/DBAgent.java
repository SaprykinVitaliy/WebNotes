package ru.saprykin.vitaliy.webnotes.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saprykin.vitaliy.webnotes.Controller.Note;

import java.sql.*;
import java.util.ArrayList;

@Service
public class DBAgent {

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
                Note note = new Note(id,
                        notesVersions.getString("header"),
                        notesMetadata.getString("creation_time"),
                        notesMetadata.getString("last_change_time"));

                result.add(note);
            }

            notesVersions.close();
            statement2.close();
        }

        notesMetadata.close();
        statement1.close();
        return result;
    }


    public void newNote(String creation_time, String header, String text) throws SQLException {
        Statement statement = dbConnection.createStatement();

        statement.execute("INSERT INTO note_metadata (creation_time, last_change_time)\n" +
                "VALUES ('" + creation_time + "', '" + creation_time + "');");

        ResultSet idSet = statement.executeQuery("SELECT id FROM note_metadata WHERE creation_time = '" + creation_time + "';");

        int id = -1;
        if (idSet.next()) {
            id = idSet.getInt("id");
            idSet.close();
        }

        if (id == -1) {
            throw new RuntimeException("Error executing queries");
        }

        statement.execute("INSERT INTO note_versions (id, time_of_version_creation, header, text)" +
                "VALUES ('" + id + "', '" + creation_time + "', '" + header + "', '" + text + "')");

        statement.close();

    }

    public void deleteNote(int id) throws SQLException {
        Statement statement = dbConnection.createStatement();
        statement.addBatch("DELETE FROM note_versions WHERE id =" + id + ";");
        statement.addBatch("DELETE FROM note_metadata WHERE id =" + id + ";");
        statement.executeBatch();
        statement.close();
    }
}
