package photopolis.loalityprogram.DTO;

import photopolis.loalityprogram.model.User;

/**
 * Created by Anatoliy on 25.05.2018.
 */
public class UserFindDTO {

    Integer id;

    String name;

    String surname;

    public UserFindDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
    }

    public Integer getId() {
        return id;
    }

    public UserFindDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserFindDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserFindDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }
}
