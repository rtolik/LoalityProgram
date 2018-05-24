package photopolis.loalityprogram.service;

import photopolis.loalityprogram.model.Bonus;

import java.util.List;

/**
 * Created by Anatoliy on 24.05.2018.
 */
public interface BonusService {

    void save(Integer value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId);

    void delete(Integer id);

    void updade(Integer id,Integer value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId);

    Bonus findOne(Integer id);

    List<Bonus> findAll();

    void updateValue(Integer id, Integer value);

    void updateDateOfStart(Integer id, String date);

    void updateDateOfEnd(Integer id, String date);

    void decrementBonus(Integer id);

    void countBonusByRentId(Integer rentId);
}
