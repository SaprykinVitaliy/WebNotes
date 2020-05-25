package ru.saprykin.vitaliy.webnotes.Model;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    Connection getAppDBConnection() throws SQLException;
    void close() throws SQLException;
}
