package com.trainticket;

import java.sql.*;

public class TrainDAO {
    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/traindb";
    private final String USERNAME = "root";
    private final String PASSWORD = "Welcome@123";
    Train train;

    public Train findTrainNo(int train_no) throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "select * from train where train_no=" + train_no;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
                train = new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));

            if (train != null) {
                return train;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

          /*  if (rs.next()) {
                int train_no1 = rs.getInt("train_no");
                String train_name = rs.getString("train_name");
                String source1 = rs.getString("source1");
                String destination = rs.getString("destination");
                double ticket_price = rs.getDouble("ticket_price");
                train = new Train(train_no1, train_name, source1, destination, ticket_price);
            }
            else
            if (!rs.next()) {
                System.out.println("Train Number not Exists!");
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return train;
    }*/

