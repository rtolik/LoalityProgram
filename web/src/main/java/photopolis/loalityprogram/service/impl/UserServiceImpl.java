package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.repository.UserRepository;
import photopolis.loalityprogram.service.UserService;

import java.util.List;

/**
 * Created by Anatoliy on 24.05.2018.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(String name, String secondName, String surname, String phone, String dateOfBirth, String[] socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isActive, List<Integer> bonusId) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id, String name, String secondName, String surname, String phone, String dateOfBirth, String[] socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isActive, List<Integer> bonusId) {

    }

    @Override
    public void setUnActive(Integer id) {

    }

    @Override
    public void setActive(Integer id) {

    }

    @Override
    public User findOne(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllActive() {
        return null;
    }

    @Override
    public User findByRentId(Integer rentId) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public User findBySurname(String surname) {
        return null;
    }

    @Override
    public User findByPhone(String phone) {
        return null;
    }
}
