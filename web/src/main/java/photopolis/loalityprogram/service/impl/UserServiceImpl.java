package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.DTO.UserFIndClientDTO;
import photopolis.loalityprogram.DTO.UserFindDTO;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.repository.UserRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.dataParser;

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
    public void save(String imagePath, String name, String secondName, String surname, String phone, String dateOfBirth,
                     String[] socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isMember) {
        userRepository.save(
                new User(imagePath, name, secondName, surname, phone,dataParser(dateOfBirth), socialMedia, cardId, lastVisit, numberOfVisits,
                        true,isMember));
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
    public void update(Integer id, String imagePath, String name, String secondName, String surname, String phone, String dateOfBirth,
                       String[] socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits,
                       Boolean isActive,Boolean isMember, List<Integer> bonusId) {
        User tmp= findOne(id);
        tmp.setName(name).setSecondName(secondName).setSurname(surname).setPhone(phone).setDateOfBirth(dateOfBirth)
                .setSocialMedia(socialMedia).setCardId(cardId).setLastVisit(lastVisit).setNumberOfVisits(numberOfVisits)
                .setActive(isActive).setBonuses(bonusService.findAllByBonusesId(bonusId)).setMember(isMember)
                .setIamgePath(imagePath);
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
        return findAll().stream().filter(user -> user.getActive().equals(true)).collect(toList());
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

    @Override
    public User findByCardId(Integer cardId) {
        return findAll().stream().filter(user -> user.getCardId().equals(cardId)).findFirst().get();
    }

    @Override
    public List<UserFindDTO> findAllShort() {
        List<UserFindDTO> dtos = new ArrayList<>();
        List<User> users = findAllActive();
        users.forEach(user -> dtos.add(new UserFindDTO(user)));
        return dtos;
    }

    @Override
    public List<UserFIndClientDTO> findAllClieants() {
        List<UserFIndClientDTO> dtos = new ArrayList<>();
        List<User> users = findAllActive();
        users.forEach(user -> dtos.add(new UserFIndClientDTO(user)));
        return dtos;
    }
}
