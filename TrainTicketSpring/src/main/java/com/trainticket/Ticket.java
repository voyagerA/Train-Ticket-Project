package com.trainticket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.TreeMap;

public class Ticket {
    public static int counter=100;
    private String pnr;
    private Date travel_date;
    private Train train;
    private TreeMap<Passenger,Integer> passengerTreeMap=new TreeMap<>();

    public Ticket(Date travel_date,Train train) {
       Date  currentDate = new Date();
        boolean flag1=travel_date.before(currentDate);
        try {
            if(flag1) {
                System.out.println("Invalid Date!! Please enter valid Date");
                System.exit(0);
            }
        }catch(DateTimeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        this.travel_date=travel_date;
        this.train=train;
    }

    public Ticket() {

    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Date getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(Date travel_date) {
        this.travel_date = travel_date;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TreeMap<Passenger, Integer> getPassengerTreeMap() {
        return passengerTreeMap;
    }

    public void setPassengerTreeMap(TreeMap<Passenger, Integer> passengerTreeMap) {
        this.passengerTreeMap = passengerTreeMap;
    }

    public String generatePNR()  {
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        String strDate=format.format(travel_date);
        String[]split=strDate.split("/");
        String finalDate=split[0]+split[1]+split[2];
        pnr=String.valueOf(train.getSource1().charAt(0))+String.valueOf(train.getDestination().charAt(0))+"_"+finalDate+"_"+Integer.toString(counter);
        return pnr;
    }

    public double calcPassengerFare(Passenger passenger) {
        double fare = train.getTicket_price();

        if (passenger.getAge() <= 12){
            return fare = fare * 0.5;}
        else if (passenger.getAge() >= 60){
            return fare = fare * 0.6;}
        else if (passenger.getGender().equals('F')){
            return fare = fare - (fare*25/100);}
        else {
            return fare;}
    }

    public void addPassenger(String name,char gender,int age){
        Passenger passenger=new Passenger(name,gender,age);
        double fare=calcPassengerFare(passenger);
        this.passengerTreeMap.put(passenger,(int)fare);
    }

    public double calculateTotalTicketPrice(){
        double sum=0.0;
        for(Passenger passenger:passengerTreeMap.keySet()){
            sum=sum+passengerTreeMap.get(passenger);
        }
        return sum;
    }

    public StringBuilder generateTicket() throws IOException {
        Passenger passenger=new Passenger();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("PNR:"+generatePNR());
        stringBuilder.append("\nTrain No:"+train.getTrain_no());
        stringBuilder.append("\nTrain Name:"+train.getTrain_name());
        stringBuilder.append("\nFrom:"+train.getSource1());
        stringBuilder.append("\nTo:"+train.getDestination());
        stringBuilder.append("\nTravel Date:"+getTravel_date());
        stringBuilder.append("\n");
        stringBuilder.append("Passengers:"+"\n");
        stringBuilder.append("\tName \t\t Age \t Gender \t Fare \n");
        for (Passenger passenger1: passengerTreeMap.keySet()) {
            stringBuilder.append( "\t" + passenger1.getName() + "\t\t" + passenger1.getAge() + "\t\t" + passenger1.getGender() + "\t\t" + calcPassengerFare(passenger1)+"\n");
        }
        stringBuilder.append("\nTotal Price:"+calculateTotalTicketPrice());
        return stringBuilder;
    }

    public void writeTicket() throws IOException {
        String str=generateTicket().toString();
        String filename=generatePNR()+".txt";
        FileOutputStream fos=new FileOutputStream(filename);
        PrintWriter pr=new PrintWriter(fos);
        pr.write(str);
        pr.flush();
        pr.close();
        System.out.println("\nTicket Booked with PNR :  "+generatePNR());

    }
}
