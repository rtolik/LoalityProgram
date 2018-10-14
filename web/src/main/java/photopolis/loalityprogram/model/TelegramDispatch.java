package photopolis.loalityprogram.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import photopolis.loalityprogram.model.enums.Role;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

/**
 * Created by Anatoliy on 19.06.2018.
 */
@Entity
public class TelegramDispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chatId;

    private Boolean isActive;

    private String password;

    @Enumerated
    private Role role;

    @JsonIgnore
    @ManyToOne
    private User user;

    public TelegramDispatch() {
    }

    public TelegramDispatch(String chatId) {
        this.chatId = chatId;
        this.isActive = true;
        this.role= Role.USER;
    }

    public TelegramDispatch(String chatId, User user,String password) {
        this.chatId = chatId;
        this.isActive = true;
        this.role = Role.USER;
        this.user = user;
        this.password=password;
    }

    public Integer getId() {
        return id;
    }

    public TelegramDispatch setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public TelegramDispatch setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public TelegramDispatch setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public User getUser() {
        return user;
    }

    public TelegramDispatch setUser(User user) {
        this.user = user;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public TelegramDispatch setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public TelegramDispatch setPassword(String password) {
        this.password = password;
        return this;
    }
}
