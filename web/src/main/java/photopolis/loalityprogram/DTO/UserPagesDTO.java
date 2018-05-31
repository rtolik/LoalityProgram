package photopolis.loalityprogram.DTO;

import photopolis.loalityprogram.model.User;

/**
 * Created by Anatoliy on 29.05.2018.
 */
public class UserPagesDTO {

    private Integer id;

    private String imagePath;

    private String name;

    private String secondName;

    private String surname;

    private String phone;

    private Boolean isMember;

    public UserPagesDTO(User user) {
        this.id = user.getId();
        this.imagePath = user.getImagePath();
        this.name = user.getName();
        this.secondName = user.getSecondName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.isMember = user.getMember();
    }

    public Integer getId() {
        return id;
    }

    public UserPagesDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getimagePath() {
        return imagePath;
    }

    public UserPagesDTO setimagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserPagesDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public UserPagesDTO setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserPagesDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserPagesDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Boolean getMember() {
        return isMember;
    }

    public UserPagesDTO setMember(Boolean member) {
        isMember = member;
        return this;
    }
}
