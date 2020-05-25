package ru.saprykin.vitaliy.webnotes.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class PostgresDBConnector implements DBConnector{
    final private static String DBMSDriver = "org.postgresql.Driver";

    @Value("${dbConnection.URL}")
    private String connectionString;

    private static Connection appDBConnection;

    @PostConstruct
    public void init() {
        try {
            Class.forName(DBMSDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            appDBConnection = DriverManager.getConnection(connectionString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getAppDBConnection() throws SQLException {
        return appDBConnection;
    }

    public void close() throws SQLException {
        appDBConnection.close();
    }
}
