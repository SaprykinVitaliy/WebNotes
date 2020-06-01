/*
package ru.saprykin.vitaliy.webnotes.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.saprykin.vitaliy.webnotes.Model.Note;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class DBAuthAgent {
    private final Connection dbConnection;

    @Autowired
    public DBAuthAgent(PostgresDBConnector connector) {
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
            ResultSet notesVersions = statement2.executeQuery("SELECT header, text FROM note_versions WHERE id = " + id + " AND time_of_version_creation = '" + last_change_time + "';");
            if (notesVersions.next()) {
                Note note = new Note(id,
                        notesVersions.getString("header"),
                        notesVersions.getString("text"),
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

    */
/*public User findByUsername(String username) {
        //TDO

    }*//*


    public void setPassword(String login, String password) {
    }

    public User findUserByUsername(String username) {
        return null;
    }
}
*/
