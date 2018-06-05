package photopolis.loalityprogram.service;


import photopolis.loalityprogram.DTO.DailyRentDTO;
import photopolis.loalityprogram.DTO.RentUserDTO;
import photopolis.loalityprogram.model.Rent;


import java.util.List;

/**
 * Created by Anatoliy on 23.05.2018.
 */
public interface RentService {

    void save(String date, String timeOfStart, Double duration, Double price, Double bonusPrice, String comment,
                    Integer rentStatus, Integer userId);
    void save(Rent rent);

    void createNewRent(Integer userId, String date, String timeOfStart, Double duration, String comment);

    void delete(Integer id);

    void update(Integer id, String date, String timeOfStart, String timeOfEnd, Double price,Double bonusPrice,
                String comment, Integer rentStatus, Integer userId);

    RentUserDTO update(Integer id, String date, String timeOfStart, Double duration,String comment);

    RentUserDTO submitRent(Integer id,Double price,Double bonusPrice);

    void submitRentLeave(Integer id);

    void updateDate(Integer id, String date);

    void updateTimeOfStart(Integer id, String time);

    void updateTimeOfEnd(Integer id, String time);

    Rent findOne(Integer id);

    List<Rent> findAll();

    List<Rent> findAllByDate(String date);

    List<RentUserDTO> findAllByUserId(Integer id);

    List<Rent> findAllAwait();

    List<Rent> findAllPaid();

    List<Rent> findAllLeaved();

    List<Rent> findAllActing();

    List<DailyRentDTO> findAllForDay(String date);

    RentUserDTO findOneDTO(Integer id);

    List<Rent> findAllInDateInterval(String startDate, String endDate);

}
