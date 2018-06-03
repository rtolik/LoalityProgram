package photopolis.loalityprogram.DTO;


import photopolis.loalityprogram.model.Rent;
import photopolis.loalityprogram.model.User;

import static photopolis.loalityprogram.service.utils.Utility.countDuration;

/**
 * Created by Anatoliy on 31.05.2018.
 */
public class RentUserDTO {

    private Integer id;

    private  String date;

    private String timeOfStart;

    private Double duration;

    private Double price;

    private Double bonusPrice;

    private String comment;

    private String rentStatus;

    private User user;

    public RentUserDTO(Rent rent) {
        this.id = rent.getId();
        this.date = rent.getDate();
        this.timeOfStart = rent.getTimeOfStart();
        this.duration = countDuration(rent.getTimeOfStart(),rent.getTimeOfEnd());
        this.price = rent.getPrice();
        this.bonusPrice = rent.getBonusPrice();
        this.comment = rent.getComment();
        this.rentStatus = rent.getRentStatus().toString();
        this.user = rent.getUser();
    }

    public Integer getId() {
        return id;
    }

    public RentUserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public RentUserDTO setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTimeOfStart() {
        return timeOfStart;
    }

    public RentUserDTO setTimeOfStart(String timeOfStart) {
        this.timeOfStart = timeOfStart;
        return this;
    }

    public Double getDuration() {
        return duration;
    }

    public RentUserDTO setDuration(Double duration) {
        this.duration = duration;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public RentUserDTO setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getBonusPrice() {
        return bonusPrice;
    }

    public RentUserDTO setBonusPrice(Double bonusPrice) {
        this.bonusPrice = bonusPrice;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public RentUserDTO setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public RentUserDTO setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RentUserDTO setUser(User user) {
        this.user = user;
        return this;
    }
}
