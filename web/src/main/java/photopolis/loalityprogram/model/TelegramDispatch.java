package photopolis.loalityprogram.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public TelegramDispatch() {
    }

    public TelegramDispatch(String chatId) {
        this.chatId = chatId;
        this.isActive = true;
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
}
