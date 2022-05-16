package com.jpmc.theater.vo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public void setAudienceCount(int audienceCount) {
        this.audienceCount = audienceCount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", showing=" + showing +
                ", audienceCount=" + audienceCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return audienceCount == that.audienceCount && Objects.equals(customer, that.customer) && Objects.equals(showing, that.showing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, showing, audienceCount);
    }
}
