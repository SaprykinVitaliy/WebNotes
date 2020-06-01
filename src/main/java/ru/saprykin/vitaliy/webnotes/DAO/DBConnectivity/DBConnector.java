package ru.saprykin.vitaliy.webnotes.DAO.DBConnectivity;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    Connection getAppDBConnection();
    void close() throws SQLException;
}
