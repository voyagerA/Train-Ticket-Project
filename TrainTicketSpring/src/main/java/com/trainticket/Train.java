package com.trainticket;

public class Train {
    private Integer train_no;
    private String train_name;
    private String source1;
    private String destination;
    private double ticket_price;

    public Train(Integer train_no, String train_name, String source1, String destination, double ticket_price) {
        this.train_no = train_no;
        this.train_name = train_name;
        this.source1 = source1;
        this.destination = destination;
        this.ticket_price = ticket_price;
    }

    public Train() {

    }


    public Integer getTrain_no() {
        return train_no;
    }

    public void setTrain_no(Integer train_no) {
        this.train_no = train_no;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getSource1() {
        return source1;
    }

    public void setSource(String source) {
        this.source1 = source1;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }
    @Override
    public String toString() {
        return "Train{" +
                "train_no=" + train_no +
                ", train_name='" + train_name + '\'' +
                ", source1='" + source1 + '\'' +
                ", destination='" + destination + '\'' +
                ", ticket_price=" + ticket_price +
                '}';
    }
}
