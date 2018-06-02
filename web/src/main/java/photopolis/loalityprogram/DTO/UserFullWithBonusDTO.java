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
public class UserFullWithBonusDTO {

    @JsonIgnore
    @Autowired
    private BonusService bonusService;

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

    public UserFullWithBonusDTO(User user) {
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
//        this.bonuses=bonusService.findAllByUserId(this.id);
        this.bonuses=user.getBonuses();
    }

    public BonusService getBonusService() {
        return bonusService;
    }

    public UserFullWithBonusDTO setBonusService(BonusService bonusService) {
        this.bonusService = bonusService;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public UserFullWithBonusDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public UserFullWithBonusDTO setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserFullWithBonusDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public UserFullWithBonusDTO setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserFullWithBonusDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserFullWithBonusDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public UserFullWithBonusDTO setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public UserFullWithBonusDTO setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
        return this;
    }

    public Integer getCardId() {
        return cardId;
    }

    public UserFullWithBonusDTO setCardId(Integer cardId) {
        this.cardId = cardId;
        return this;
    }

    public String getDateOfMember() {
        return dateOfMember;
    }

    public UserFullWithBonusDTO setDateOfMember(String dateOfMember) {
        this.dateOfMember = dateOfMember;
        return this;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public UserFullWithBonusDTO setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserFullWithBonusDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public UserFullWithBonusDTO setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserFullWithBonusDTO setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Boolean getMember() {
        return isMember;
    }

    public UserFullWithBonusDTO setMember(Boolean member) {
        isMember = member;
        return this;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public UserFullWithBonusDTO setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
        return this;
    }
}
