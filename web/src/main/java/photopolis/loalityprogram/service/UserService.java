package photopolis.loalityprogram.service;

import photopolis.loalityprogram.model.User;

import java.util.List;

/**
 * Created by Anatoliy on 24.05.2018.
 */
public interface UserService {

    void save(String name, String secondName, String surname, String phone, String dateOfBirth, String[] socialMedia,
              Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isActive, List<Integer> bonusId);

    void delete(Integer id);

    void update(Integer id, String name, String secondName, String surname, String phone, String dateOfBirth, String[] socialMedia,
                Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isActive, List<Integer> bonusId);

    void setUnActive(Integer id);

    void setActive(Integer id);

    User findOne(Integer id);

    List<User> findAll();

    List<User> findAllActive();

    User findByRentId(Integer rentId);

    User findByName(String name);

    User findBySurname(String surname);

    User findByPhone(String phone);
}
