package photopolis.loalityprogram.service;


import photopolis.loalityprogram.model.Rent;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.model.enums.RentStatus;

import java.util.List;

/**
 * Created by Anatoliy on 23.05.2018.
 */
public interface RentService {

    void save(String date, String timeOfStart, String timeOfEnd, Double price, String comment,
                     RentStatus rentStatus, User user);

    void submitRent(Integer id);

    List<Rent> getAllByDate(String date);

    List<Rent> getAllByUserId(Integer id);
}
