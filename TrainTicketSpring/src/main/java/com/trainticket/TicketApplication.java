package com.trainticket;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class TicketApplication {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter train No:");
        int train_no = sc.nextInt();

        TrainDAO trainDAO = new TrainDAO();
        Train train = null;
        try {
            train = trainDAO.findTrainNo(train_no);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        sc.nextLine();
        System.out.println("Enter Travel Date:");
        String[] arr =sc.nextLine().split("/");
        Date travel_date=new Date();


        Ticket ticket = new Ticket(travel_date,train);

        System.out.println("Enter the number of Passengers:");
        int passenger_no = sc.nextInt();
        for (int i = 0; i < passenger_no; i++) {
            System.out.println("Enter Passenger" + (i + 1) + " Name:");
            String name = sc.next();
            System.out.println("Enter Passenger" + (i + 1) + " Gender (F/M):");
            char gender = sc.next().charAt(0);
            System.out.println("Enter Passenger" + (i + 1) + " Age:");
            int age = sc.nextInt();

            ticket.addPassenger(name, gender, age);
        }

        try {
            ticket.writeTicket();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("\n Thank you ");
    }
}
