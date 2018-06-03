package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.DTO.DailyRentDTO;
import photopolis.loalityprogram.DTO.RentUserDTO;
import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.model.Rent;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.model.enums.RentStatus;
import photopolis.loalityprogram.repository.RentRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.*;

/**
 * Created by Anatoliy on 24.05.2018.
 */
@Service
public class RentServiceImpl implements RentService{

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BonusService bonusService;

    @Override
    public void save(String date, String timeOfStart, Double duration, Double price, Double bonusPrice, String comment,
                     Integer rentStatus, Integer userId) {
        rentRepository.save(
                new Rent(dataParser(date), timeOfStart,
                         doubleTimeToStringParser(timeToDoubleParser(timeOfStart)+ duration),
                         price,bonusPrice, comment, RentStatus.values()[rentStatus],
                         userService.findOne(userId))
        );
    }

    @Override
    public void save(Rent rent) {
        rentRepository.save(rent);
    }

    @Override
    public void delete(Integer id) {
        rentRepository.delete(id);
    }

    @Override
    public void update(Integer id, String date, String timeOfStart, String timeOfEnd, Double price, Double bonusPrice,
                       String comment, Integer rentStatus, Integer userId) {
        save(
                findOne(id).setDate(dataParser(date)).setTimeOfStart(timeParser(timeOfStart))
                           .setTimeOfEnd(timeParser(timeOfEnd)).setPrice(price).setComment(comment)
                           .setRentStatus(RentStatus.values()[rentStatus]).setBonusPrice(bonusPrice)
                           .setUser(userService.findOne(userId))
        );
    }

    @Override
    public RentUserDTO submitRent(Integer id,Double price,Double bonusPrice) {
        if(bonusPrice==0.0) {
            save(
                    findOne(id).setRentStatus(RentStatus.PAID).setPrice(price).setBonusPrice(bonusPrice)
            );
        }
        else {
            save(
                    findOne(id).setRentStatus(RentStatus.BONUSPAID).setPrice(price).setBonusPrice(bonusPrice)
            );
            if (bonusPrice != 0.0) {
                Double bonusPriceLeft = bonusPrice;
                List<Bonus> bonuses = findOne(id).getUser().getBonuses();
                for (int i = 3; i <= 0; i--) {
                    if (bonuses.get(i).getValue() < bonusPriceLeft) {
                        bonusPriceLeft -= bonuses.get(3).getValue();
                        bonusService.updateValue(bonuses.get(i).getId(), 0.0);
                        System.out.println("---------------------------------------------------------------------------");
                    } else {
                        bonusService.updateValue(bonuses.get(i).getId(), bonuses.get(i).getValue() - bonusPriceLeft);
                        System.out.println("===========================================================================");
                        break;
                    }
                }
            }
        }
        return new RentUserDTO(findOne(id));
    }

    @Override
    public void submitRentLeave(Integer id) {
        save(
                findOne(id).setRentStatus(RentStatus.LEAVED)
        );
    }

    @Override
    public void updateDate(Integer id, String date) {
        save(
                findOne(id).setDate(dataParser(date))
        );
    }

    @Override
    public void updateTimeOfStart(Integer id, String time) {
        save(
                findOne(id).setTimeOfStart(timeParser(time))
        );
    }

    @Override
    public void updateTimeOfEnd(Integer id, String time) {
        save(
                findOne(id).setTimeOfEnd(timeParser(time))
        );
    }

    @Override
    public Rent findOne(Integer id) {
        return rentRepository.findOne(id);
    }

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public List<Rent> findAllByDate(String date) {
        return findAll().stream().filter(rent -> rent.getDate().equals(dataParser(date))).collect(toList());
    }

    @Override
    public List<Rent> findAllByUserId(Integer id) {
        return findAll().stream().filter(rent -> rent.getUser().getId()==id).collect(toList());
    }

    @Override
    public List<Rent> findAllAwait() {
        return findAll().stream().filter(rent -> rent.getRentStatus()==RentStatus.AWAIT).collect(toList());
    }

    @Override
    public List<Rent> findAllPaid() {
        return findAll().stream().filter(rent -> rent.getRentStatus()==RentStatus.PAID).collect(toList());
    }

    @Override
    public List<Rent> findAllLeaved() {
        return findAll().stream().filter(rent -> rent.getRentStatus()==RentStatus.LEAVED).collect(toList());
    }

    @Override
    public List<Rent> findAllActing() {
        return findAll().stream().filter(rent -> rent.getRentStatus()!=RentStatus.LEAVED).collect(toList());
    }

    @Override
    public List<DailyRentDTO> findAllForDay(String date) {
        List<Rent> dailyRents= findAllByDate(date);
        List<User> users = new ArrayList<>();
        dailyRents.forEach(rent ->
            users.add(userService.findByRentId(rent.getId()))
        );
        List<DailyRentDTO> dtos= new ArrayList<>();
        for(int i = 0; i < dailyRents.size(); i++){
            dtos.add(new DailyRentDTO(dailyRents.get(i), users.get(i)));
        }
        return dtos;
    }

    @Override
    public void createNewRent(Integer userId, String date, String timeOfStart, Double duration, String comment) {
        save(dataParser(date),timeOfStart,duration,0.0,0.0,comment,0,userId);
    }

    @Override
    public RentUserDTO update(Integer id, String date, String timeOfStart, Double duration, String comment) {
        rentRepository.
        save(findOne(id).setDate(date).setTimeOfStart(timeOfStart).setTimeOfEnd(
                doubleTimeToStringParser(timeToDoubleParser(timeOfStart)+ duration)).setComment(comment)
        );
        return new RentUserDTO(findOne(id));
    }

    @Override
    public RentUserDTO findOneDTO(Integer id) {
        return new RentUserDTO(findOne(id));
    }

    @Override
    public List<Rent> findAllInDateInterval(String startDate, String endDate) {
        return findAll().stream().filter(
                rent -> dataComparer(rent.getDate(),startDate)&&!dataComparer(rent.getDate(),endDate)
        ).collect(toList());
    }
}
