package BDFlatsHibernate;

import javax.persistence.*;

@Entity
//@Table(name="SimpleClients")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    private String region;
    private String address;
    private double flatArea;
    private int numberOfRooms;
    private double price;

    public Flat(String region, String address, double flatArea, int numberOfRooms, double price) {
        this.region = region;
        this.address = address;
        this.flatArea = flatArea;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
    }

    public Flat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getFlatArea() {
        return flatArea;
    }

    public void setFlatArea(double flatArea) {
        this.flatArea = flatArea;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", flatArea=" + flatArea +
                ", numberOfRooms=" + numberOfRooms +
                ", price=" + price +
                '}';
    }
}
