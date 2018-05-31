package photopolis.loalityprogram.DTO;

import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.model.User;

import java.util.List;

/**
 * Created by Anatoliy on 31.05.2018.
 */
public class UserBonusDTO {

    private Integer id;

    private String imagePath;

    private String name;

    private String secondName;

    private String surname;

    private String phone;

    private Boolean isMember;

    private List<Bonus> bonuses;

    public UserBonusDTO(User user) {
        this.id = user.getId();
        this.imagePath = user.getImagePath();
        this.name = user.getName();
        this.secondName = user.getSecondName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.isMember = user.getMember();
        this.bonuses = user.getBonuses();
    }
}
