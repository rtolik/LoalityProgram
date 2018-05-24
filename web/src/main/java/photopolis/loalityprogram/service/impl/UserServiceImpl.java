package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.repository.UserRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Anatoliy on 24.05.2018.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private RentService rentService;

    @Override
    public void save(String name, String secondName, String surname, String phone, String dateOfBirth,
                     String[] socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits) {
        userRepository.save(
                new User(name, secondName, surname, phone, dateOfBirth, socialMedia, cardId, lastVisit, numberOfVisits,
                        true));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public void update(Integer id, String name, String secondName, String surname, String phone, String dateOfBirth,
                       String[] socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits,
                       Boolean isActive, List<Integer> bonusId) {
        User tmp= findOne(id);
        tmp.setName(name).setSecondName(secondName).setSurname(surname).setPhone(phone).setDateOfBirth(dateOfBirth)
                .setSocialMedia(socialMedia).setCardId(cardId).setLastVisit(lastVisit).setNumberOfVisits(numberOfVisits)
                .setActive(isActive).setBonuses(bonusService.findAllByBonusesId(bonusId));
        save(tmp);
    }

    @Override
    public void setUnActive(Integer id) {
        save(findOne(id).setActive(false));
    }

    @Override
    public void setActive(Integer id) {
        save(findOne(id).setActive(true));
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllActive() {
        return findAll().stream().filter(user -> user.getActive()==true).collect(toList());
    }

    @Override
    public User findByRentId(Integer rentId) {
        return rentService.findOne(rentId).getUser();
    }

    @Override
    public List<User> findByName(String name) {
        return findAll().stream().filter(user -> user.getName().contains(name)).collect(toList());
    }

    @Override
    public List<User> findBySurname(String surname) {
        return findAll().stream().filter(user -> user.getSurname().contains(surname)).collect(toList());
    }

    @Override
    public List<User> findByPhone(String phone) {
        return findAll().stream().filter(user -> user.getPhone().contains(phone)).collect(toList());
    }
}
