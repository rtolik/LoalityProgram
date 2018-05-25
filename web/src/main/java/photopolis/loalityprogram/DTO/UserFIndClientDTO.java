package photopolis.loalityprogram.DTO;

import photopolis.loalityprogram.model.User;

/**
 * Created by Anatoliy on 25.05.2018.
 */
public class UserFIndClientDTO {

    Integer id;

    String imagePath;

    String name;

    String surname;

    String phone;

    Boolean isMember;

    public UserFIndClientDTO(User user) {
        this.id = user.getId();
        this.imagePath = user.getIamgePath();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.isMember = user.getMember();
    }

    public Integer getId() {
        return id;
    }

    public UserFIndClientDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public UserFIndClientDTO setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserFIndClientDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserFIndClientDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserFIndClientDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Boolean getMember() {
        return isMember;
    }

    public UserFIndClientDTO setMember(Boolean member) {
        isMember = member;
        return this;
    }
}
