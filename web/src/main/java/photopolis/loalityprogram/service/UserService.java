package photopolis.loalityprogram.service;

import org.springframework.web.multipart.MultipartFile;
import photopolis.loalityprogram.DTO.LoginDTO;
import photopolis.loalityprogram.DTO.PageFinderDTO;
import photopolis.loalityprogram.DTO.UserFIndClientDTO;
import photopolis.loalityprogram.DTO.UserFindDTO;
import photopolis.loalityprogram.model.User;

import java.util.List;

/**
 * Created by Anatoliy on 24.05.2018.
 */
public interface UserService {

    void createUser (MultipartFile img, String name, String secondName, String surname, String phone,
                     String dateOfBirth, String socialMedia, Integer cardId, String lastVisit,
                     Boolean isMember, String dateOfmember, String email, String dateOfRegistration);

    void save(String imagePath, String name, String secondName, String surname, String phone, String dateOfBirth,
              String socialMedia, Integer cardId, String lastVisit, Boolean isMember, Boolean isActive,
              String dateOfMember, String email, String dateOfRegistration);

    void save(User user);

    void delete(Integer id);

    User update(Integer id, String imagePath, String name, String secondName, String surname, String phone,
                String dateOfBirth, String socialMedia, Integer cardId, String lastVisit,
                Boolean isActive,Boolean isMember, List<Integer> bonusId, String dateOfMember,
                String dateOfRegistration);

    User update(User user);

    User updateWithImg(User user, MultipartFile file);

    User setUnActive(Integer id);

    User setActive(Integer id);

    User findOne(Integer id);

    List<User> findAll();

    List<User> findAllActive();

    User findByRentId(Integer rentId);

    List<User> findByName(String name);

    List<User> findBySurname(String surname);

    List<User> findByPhone(String phone);

    User findByCardId(Integer cardId);

    List<UserFindDTO> findAllShort();

    List<UserFIndClientDTO> findAllClients();

    PageFinderDTO pageParserFilter(String name, String phonenumber, Integer pagenum, Integer elOnPage,String userMod, String criterion);

    LoginDTO login(String login, String password);

    List<User> findNewUsersInDateInterval(String startDate,String endDate);
}
