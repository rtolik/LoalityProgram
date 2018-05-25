package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.DTO.DailyRentDTO;
import photopolis.loalityprogram.model.Rent;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.model.enums.RentStatus;
import photopolis.loalityprogram.repository.RentRepository;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.util.ArrayList;
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

    @Override
    public void save(String date, String timeOfStart, Double duration, Double price, String comment,
                     Integer rentStatus, Integer userId) {
        rentRepository.save(
                new Rent(dataParser(date), timeParser(timeOfStart),
                         doubleTimeToStringParser(timeToDoubleParser(timeOfStart)+ duration),
                         price, comment, RentStatus.values()[rentStatus],
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
    public void update(Integer id, String date, String timeOfStart, String timeOfEnd, Double price, String comment,
                       Integer rentStatus, Integer userId) {
        save(
                findOne(id).setDate(dataParser(date)).setTimeOfStart(timeParser(timeOfStart))
                           .setTimeOfEnd(timeParser(timeOfEnd)).setPrice(price).setComment(comment)
                           .setRentStatus(RentStatus.values()[rentStatus])
                           .setUser(userService.findOne(userId))
        );
    }

    @Override
    public void submitRent(Integer id) {
        save(
                findOne(id).setRentStatus(RentStatus.PAID)
        );
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
    public List<Rent> findAllBonusPaid() {
        return findAll().stream().filter(rent -> rent.getRentStatus()==RentStatus.BONUSPAID).collect(toList());
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
            dtos.add(new DailyRentDTO(dailyRents.get(i),users.get(i)));
        }
        return dtos;
    }
}
