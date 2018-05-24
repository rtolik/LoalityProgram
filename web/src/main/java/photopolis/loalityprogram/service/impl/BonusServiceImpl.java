package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.repository.BonusRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.UserService;

import java.util.List;

/**
 * Created by Anatoliy on 24.05.2018.
 */
@Service
public class BonusServiceImpl implements BonusService{

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private UserService userService;

    @Override
    public void save(Integer value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId) {
        bonusRepository.save(new Bonus(value, bonusType, dateOfStart, dateOfEnd, userService.findOne(userId)));
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void updade(Integer id, Integer value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId) {

    }

    @Override
    public Bonus findOne(Integer id) {
        return null;
    }

    @Override
    public List<Bonus> findAll() {
        return null;
    }

    @Override
    public void updateValue(Integer id, Integer value) {

    }

    @Override
    public void updateDateOfStart(Integer id, String date) {

    }

    @Override
    public void updateDateOfEnd(Integer id, String date) {

    }

    @Override
    public void decrementBonus(Integer id) {

    }

    @Override
    public void countBonusByRentId(Integer rentId) {

    }
}
