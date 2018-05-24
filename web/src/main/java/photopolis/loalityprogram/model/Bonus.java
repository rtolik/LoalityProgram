package photopolis.loalityprogram.model;


import photopolis.loalityprogram.model.enums.BonusType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Anatoliy on 23.05.2018.
 */
@Entity
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer value;

    private BonusType bonusType;

    private String dateOfStart;

    private String dateOfEnd;

    @JsonIgnore
    @ManyToOne
    private User user;


    public Bonus() {}

    public Bonus(Integer value, BonusType bonusType, String dateOfStart, String dateOfEnd, User user) {
        this.value = value;
        this.bonusType = bonusType;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Bonus setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public Bonus setValue(Integer value) {
        this.value = value;
        return this;
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public Bonus setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
        return this;
    }

    public String getDateOfStart() {
        return dateOfStart;
    }

    public Bonus setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
        return this;
    }

    public String getDateOfEnd() {
        return dateOfEnd;
    }

    public Bonus setDateOfEnd(String dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Bonus setUser(User user) {
        this.user = user;
        return this;
    }
}
