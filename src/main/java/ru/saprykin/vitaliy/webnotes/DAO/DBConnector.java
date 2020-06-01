package ru.saprykin.vitaliy.webnotes.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    Connection getAppDBConnection();
    void close() throws SQLException;
}
