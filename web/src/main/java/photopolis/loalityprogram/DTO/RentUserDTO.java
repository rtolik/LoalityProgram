package photopolis.loalityprogram.DTO;


import photopolis.loalityprogram.model.Rent;

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

    private UserFullWithBonusDTO user;

    public RentUserDTO(Rent rent) {
        this.id = rent.getId();
        this.date = rent.getDate();
        this.timeOfStart = rent.getTimeOfStart();
        this.duration = countDuration(rent.getTimeOfStart(),rent.getTimeOfEnd());
        this.price = rent.getPrice();
        this.bonusPrice = rent.getBonusPrice();
        this.comment = rent.getComment();
        this.rentStatus = rent.getRentStatus().toString();
        this.user = new UserFullWithBonusDTO(rent.getUser());
    }
}
