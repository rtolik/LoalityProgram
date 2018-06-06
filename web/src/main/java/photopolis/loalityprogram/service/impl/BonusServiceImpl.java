package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.config.Constants;
import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.model.CelebrateDate;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.model.enums.BonusType;
import photopolis.loalityprogram.repository.BonusRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.dataEqualiser;
import static photopolis.loalityprogram.service.utils.Utility.dataParser;
import static photopolis.loalityprogram.service.utils.Utility.datePluser;


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
    public void save(Double value, Integer bonusType, String dateOfStart, String dateOfEnd, Integer userId) {
        bonusRepository.save(
                new Bonus(
                        value, BonusType.values()[bonusType], dataParser(dateOfStart), dataParser(dateOfEnd), userService.findOne(userId)
                )
        );
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
    public void updade(Integer id, Double value, Integer bonusType, String dateOfStart, String dateOfEnd,
                       Integer userId) {
        save(
                findOne(id).setValue(value).setBonusType(BonusType.values()[bonusType]).
                        setDateOfStart(dataParser(dateOfStart)).setDateOfEnd(dataParser(dateOfEnd))
                        .setUser(userService.findOne(id))
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
    public Bonus updateValue(Integer id, Double value) {
        Bonus bonus = findOne(id);
        save(
                bonus.setValue(value).setDateOfEnd(datePluser(dataParser(LocalDate.now().toString()),bonus.getBonusType()))
        );
        return bonus;
    }

    @Override
    public void decrementBonus(Integer id) {
        save(
                findOne(id).setValue(0.0)
        );
    }

    @Override
    public void countBonusByRentId(Integer rentId) {
        Double price = rentService.findOne(rentId).getPrice()* Constants.BONUS_MULTIPLUER;
        save(
                rentService.findOne(rentId).getUser().getBonuses()
                .stream()
                .filter(bonus1 -> bonus1.getBonusType()==BonusType.REGULAR)
                .findFirst().get().addToValue(price)
        );
    }

    @Override
    public List<Bonus> findAllByBonusesId(List<Integer> id) {
        List<Bonus> tmp= new ArrayList<>();
        id.forEach(el -> tmp.add(findOne(el)));
        return tmp;
    }

    @Override
    public List<Bonus> findAllByUserId(Integer userId) {
        return findAll().stream().filter(bonus -> bonus.getUser().getId().equals(userId)).collect(toList());
    }

    @Override
    public void update(Bonus bonus) {
        save(bonus);
    }

    @Override
    public List<Bonus> findAllParty() {
        return findAll().stream().filter(bonus -> bonus.getBonusType()==BonusType.PARTY).collect(toList());
    }

    @Override
    public List<Bonus> findAllAnniversary() {
        return findAll().stream().filter(bonus -> bonus.getBonusType()==BonusType.ANNIVERSARY).collect(toList());
    }

    @Override
    public List<Bonus> findAllBirthDay() {
        return findAll().stream().filter(bonus -> bonus.getBonusType()==BonusType.BIRTHDAY).collect(toList());
    }

    @Override
    public void setPartyBonus(List<CelebrateDate> dates) {
        for (CelebrateDate date:dates)  {
            findAllParty().forEach(
                    bonus -> updateValue(
                            bonus.getId(),bonus.addToValue(Constants.BONUS_PER_PARTY).getValue()
                    )
            );
        }
    }

    @Override
    public void setAnniversaryBonus() {
        List<User> users=userService.findAllActive().stream().filter(
                user -> user.getMember()
                && dataEqualiser(dataParser(LocalDate.now().toString()),user.getDateOfRegistration())
        ).collect(toList());
        List<Bonus> bonuses= new ArrayList<>();
        users.forEach(user -> bonuses.add(user.getBonuses().get(2)));
        bonuses.forEach(bonus -> updateValue(
                bonus.getId(),bonus.addToValue(Constants.BONUS_PER_ANNIVERSARY).getValue()
        ));
    }

    @Override
    public void setBirhDayBonus() {
        List<User> users=userService.findAllActive().stream().filter(
                user -> user.getMember()
                && dataEqualiser(dataParser(LocalDate.now().toString()),user.getDateOfBirth())
        ).collect(toList());
        List<Bonus> bonuses= new ArrayList<>();
        users.forEach(user -> bonuses.add(user.getBonuses().get(1)));
        bonuses.forEach(bonus -> updateValue(
                bonus.getId(),bonus.addToValue(Constants.BONUS_PER_BIRTHDAY).getValue()
        ));
    }
}
