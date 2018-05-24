package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.model.enums.BonusType;
import photopolis.loalityprogram.repository.BonusRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Anatoliy on 24.05.2018.
 */
@Service
public class BonusServiceImpl implements BonusService{

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RentService rentService;

    @Override
    public void save(Integer value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId) {
        bonusRepository.save(new Bonus(value, bonusType, dateOfStart, dateOfEnd, userService.findOne(userId)));
    }

    @Override
    public void save(Bonus bonus) {
        bonusRepository.save(bonus);
    }

    @Override
    public void delete(Integer id) {
        bonusRepository.delete(id);
    }

    @Override
    public void updade(Integer id, Integer value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId) {
        save(
                findOne(id).setValue(value).setBonusType(BonusType.values()[bonusType]).setDateOfStart(dateOfStart)
                           .setDateOfEnd(dateOfEnd).setUser(userService.findOne(id))
        );
    }

    @Override
    public Bonus findOne(Integer id) {
        return bonusRepository.findOne(id);
    }

    @Override
    public List<Bonus> findAll() {
        return bonusRepository.findAll();
    }

    @Override
    public void updateValue(Integer id, Integer value) {
        save(
                findOne(id).setValue(value)
        );
    }

    @Override
    public void updateDateOfStart(Integer id, String date) {
        save(
                findOne(id).setDateOfStart(date)
        );
    }

    @Override
    public void updateDateOfEnd(Integer id, String date) {
        save(
                findOne(id).setDateOfEnd(date)
        );
    }

    @Override
    public void decrementBonus(Integer id) {
        save(
                findOne(id).setValue(0)
        );
    }

    @Override
    public void countBonusByRentId(Integer rentId) {
        Double price = rentService.findOne(rentId).getPrice();
        save(
                rentService.findOne(rentId).getUser().getBonuses()
                .stream()
                .filter(bonus1 -> bonus1.getBonusType()==BonusType.REGULAR)
                .findFirst().get()//TODO доробти метод в модельці addToValue
        );
    }

    @Override
    public List<Bonus> findAllByBonusesId(List<Integer> id) {
        return null;
    }
}
