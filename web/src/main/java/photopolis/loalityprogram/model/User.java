package photopolis.loalityprogram.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anatoliy on 23.05.2018.
 */
@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String iamgePath;

    private String name;

    private String secondName;

    private String surname;

    private String phone;

    private String dateOfBirth;

    private String socialMedia;

    private Integer cardId;

    private String dateOfMember;

    private String lastVisit;

    private Integer numberOfVisits;

    private Boolean isActive;

    private Boolean isMember;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Bonus> bonuses;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Rent> rents;

    public User() {}

    public User(String imagePath, String name, String secondName, String surname, String phone, String dateOfBirth,
                String socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isActive,
                Boolean isMember, String dateOfMember) {
        this.iamgePath=imagePath;
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.socialMedia = socialMedia;
        this.cardId = cardId;
        this.lastVisit = lastVisit;
        this.numberOfVisits = numberOfVisits;
        this.isActive = isActive;
        this.isMember=isMember;
        this.dateOfMember=dateOfMember;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public User setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public User setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public User setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
        return this;
    }

    public Integer getCardId() {
        return cardId;
    }

    public User setCardId(Integer cardId) {
        this.cardId = cardId;
        return this;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public User setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }

    public Integer getNumberOfVisits() {
        return numberOfVisits;
    }

    public User setNumberOfVisits(Integer numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public User setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public User setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
        return this;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public User setRents(List<Rent> rents) {
        this.rents = rents;
        return this;
    }

    public Boolean getMember() {
        return isMember;
    }

    public User setMember(Boolean member) {
        isMember = member;
        return this;
    }

    public String getIamgePath() {
        return iamgePath;
    }

    public User setIamgePath(String iamgePath) {
        this.iamgePath = iamgePath;
        return this;
    }

    public String getDateOfMember() {
        return dateOfMember;
    }

    public User setDateOfMember(String dateOfMember) {
        this.dateOfMember = dateOfMember;
        return this;
    }
}
