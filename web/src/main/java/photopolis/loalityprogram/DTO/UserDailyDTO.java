package photopolis.loalityprogram.DTO;

import photopolis.loalityprogram.model.User;

/**
 * Created by Anatoliy on 25.05.2018.
 */
public class UserDailyDTO {

    Integer id;

    String name;

    String surname;

    String phone;

    Boolean isMember;

    public UserDailyDTO(User user) {
        this.id =user.getId();
        this.name =user.getName();
        this.surname =user.getSurname();
        this.phone =user.getPhone();
        this.isMember =user.getMember();
    }

    public Integer getId() {
        return id;
    }

    public UserDailyDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDailyDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserDailyDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDailyDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Boolean getIsMember() {
        return isMember;
    }

    public UserDailyDTO setIsMember(Boolean isMember) {
        this.isMember = isMember;
        return this;
    }
}
