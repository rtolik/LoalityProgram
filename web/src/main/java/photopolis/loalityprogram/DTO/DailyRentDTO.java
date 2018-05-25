package photopolis.loalityprogram.DTO;


import photopolis.loalityprogram.model.Rent;
import photopolis.loalityprogram.model.User;

import static photopolis.loalityprogram.service.utils.Utility.timeToDoubleParser;

/**
 * Created by Anatoliy on 25.05.2018.
 */
public class DailyRentDTO {

    Integer id;

    String timeOfStart;

    String timeOfEnd;

    String rentStatus;

    Double duration;

    UserDailyDTO user;

    public DailyRentDTO(Rent rent, User user) {
        this.id =rent.getId();
        this.timeOfStart =rent.getTimeOfStart();
        this.timeOfEnd =rent.getTimeOfEnd();
        this.rentStatus=rent.getRentStatus().toString();
        this.user = new UserDailyDTO(user);
        this.duration=timeToDoubleParser(timeOfEnd)-timeToDoubleParser(timeOfStart);
    }

    public Integer getId() {
        return id;
    }

    public DailyRentDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTimeOfStart() {
        return timeOfStart;
    }

    public DailyRentDTO setTimeOfStart(String timeOfStart) {
        this.timeOfStart = timeOfStart;
        return this;
    }

    public String getTimeOfEnd() {
        return timeOfEnd;
    }

    public DailyRentDTO setTimeOfEnd(String timeOfEnd) {
        this.timeOfEnd = timeOfEnd;
        return this;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public DailyRentDTO setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
        return this;
    }

    public UserDailyDTO getUser() {
        return user;
    }

    public DailyRentDTO setUser(UserDailyDTO user) {
        this.user = user;
        return this;
    }
}
