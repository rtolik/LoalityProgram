package photopolis.loalityprogram.model;


import javax.persistence.*;

/**
 * Created by Anatoliy on 05.06.2018.
 */
@Entity
public class CelebrateDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String date;

    private Double bonusesToAdd;

    public CelebrateDate() {
    }

    public CelebrateDate(String name, String date, Double bonusesToAdd) {
        this.name = name;
        this.date = date;
        this.bonusesToAdd = bonusesToAdd;
    }

    public Integer getId() {
        return id;
    }

    public CelebrateDate setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CelebrateDate setName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public CelebrateDate setDate(String date) {
        this.date = date;
        return this;
    }

    public Double getBonusesToAdd() {
        return bonusesToAdd;
    }

    public CelebrateDate setBonusesToAdd(Double bonusesToAdd) {
        this.bonusesToAdd = bonusesToAdd;
        return this;
    }
}

