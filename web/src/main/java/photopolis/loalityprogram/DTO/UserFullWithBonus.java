package photopolis.loalityprogram.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.service.BonusService;

import java.util.List;

/**
 * Created by Anatoliy on 31.05.2018.
 */
public class UserFullWithBonus {

//    @JsonIgnore
//    @Autowired
//    private BonusService bonusService;

    private Integer id;

    private String imagePath;

    private String name;

    private String secondName;

    private String surname;

    private String phone;

    private String dateOfBirth;

    private String socialMedia;

    private Integer cardId;

    private String dateOfMember;

    private String lastVisit;

    private String email;

    private String dateOfRegistration;

    private Boolean isActive;

    private Boolean isMember;

    private List<Bonus> bonuses;

    public UserFullWithBonus(User user) {
        this.id =  user.getId();
        this.imagePath = user.getImagePath();
        this.name = user.getName();
        this.secondName = user.getSecondName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.dateOfBirth = user.getDateOfBirth();
        this.socialMedia = user.getSocialMedia();
        this.cardId = user.getCardId();
        this.dateOfMember = user.getDateOfMember();
        this.lastVisit = user.getLastVisit();
        this.email = user.getEmail();
        this.dateOfRegistration = user.getDateOfRegistration();
        this.isActive = user.getActive();
        this.isMember = user.getMember();
//        this.bonuses=bonusService.findAllByUserId(user.getId());
        this.bonuses=user.getBonuses();
    }
}
