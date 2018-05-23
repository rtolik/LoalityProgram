package photopolis.loalityprogram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import photopolis.loalityprogram.model.enums.RentStatus;

import javax.persistence.*;

/**
 * Created by Anatoliy on 23.05.2018.
 */
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;

    private String timeOfStart;

    private String timeOfEnd;

    private Double price;

    private String comment;

    private RentStatus rentStatus;

    @JsonIgnore
    @ManyToOne
    User user;

    public Rent() {}

    public Rent(String date, String timeOfStart, String timeOfEnd, Double price, String comment,
                       RentStatus rentStatus, User user) {
        this.date = date;
        this.timeOfStart = timeOfStart;
        this.timeOfEnd = timeOfEnd;
        this.price = price;
        this.comment = comment;
        this.rentStatus=rentStatus;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Rent setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Rent setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTimeOfStart() {
        return timeOfStart;
    }

    public Rent setTimeOfStart(String timeOfStart) {
        this.timeOfStart = timeOfStart;
        return this;
    }

    public String getTimeOfEnd() {
        return timeOfEnd;
    }

    public Rent setTimeOfEnd(String timeOfEnd) {
        this.timeOfEnd = timeOfEnd;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Rent setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Rent setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Rent setUser(User user) {
        this.user = user;
        return this;
    }

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public Rent setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
        return this;
    }
}
