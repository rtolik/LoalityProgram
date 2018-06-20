package photopolis.loalityprogram.service;

import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.model.CelebrateDate;

import java.util.List;

/**
 * Created by Anatoliy on 24.05.2018.
 */
public interface BonusService {

    void save(Double value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId);

    void save(Bonus bonus);

    void delete(Integer id);

    void update(Bonus bonus);

    void updade(Integer id,Double value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId);

    Bonus findOne(Integer id);

    List<Bonus> findAll();

    List<Bonus> findAllByBonusesId(List<Integer> id);

    Bonus updateValue(Integer id, Double value);

    void decrementBonus(Integer id);

    void countBonusByRentId(Integer rentId);

    List<Bonus> findAllByUserId(Integer userId);

    List<Bonus> findAllParty();

    List<Bonus> findAllAnniversary();

    List<Bonus> findAllBirthDay();

    void setPartyBonus(List<CelebrateDate> dates);

    String setAnniversaryBonus();

    String setBirhDayBonus();
}
