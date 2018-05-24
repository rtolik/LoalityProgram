package photopolis.loalityprogram.service;


import photopolis.loalityprogram.model.Rent;


import java.util.List;

/**
 * Created by Anatoliy on 23.05.2018.
 */
public interface RentService {

    void save(String date, String timeOfStart, String timeOfEnd, Double price, String comment,
                    Integer rentStatus, Integer userId);

    void delete(Integer id);

    void update(Integer id, String timeOfStart, String timeOfEnd, Double price, String comment,
                Integer rentStatus, Integer userId);

    void submitRent(Integer id);

    void submitRentLeave(Integer Id);

    void updateDate(Integer id, String date);

    void updateTimeOfStart(Integer id, String time);

    void updateTimeOfEnd(Integer id, String time);

    Rent findOne(Integer id);

    List<Rent> findAll();

    List<Rent> findAllByDate(String date);

    List<Rent> findAllByUserId(Integer id);

    List<Rent> findAllAwait();

    List<Rent> findAllPaid();

    List<Rent> findAllBonusPaid();

    List<Rent> findAllLeaved();

    List<Rent> findAllActing();
}
