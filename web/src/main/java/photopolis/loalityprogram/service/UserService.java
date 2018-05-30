package photopolis.loalityprogram.service;

import org.springframework.web.multipart.MultipartFile;
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
                     String dateOfBirth, String socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits,
                     Boolean isMember, String dateOfmember);

    void save(String imagePath, String name, String secondName, String surname, String phone, String dateOfBirth,
              String socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isMember,
              String dateOfMember);

    void save(User user);

    void delete(Integer id);

    void update(Integer id, String imagePath, String name, String secondName, String surname, String phone,
                String dateOfBirth, String socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits,
                Boolean isActive,Boolean isMember, List<Integer> bonusId, String dateOfMember);

    void setUnActive(Integer id);

    void setActive(Integer id);

    User findOne(Integer id);

    List<User> findAll();

    List<User> findAllActive();

    User findByRentId(Integer rentId);

    List<User> findByName(String name);

    List<User> findBySurname(String surname);

    List<User> findByPhone(String phone);

    User findByCardId(Integer cardId);

    List<UserFindDTO> findAllShort();

    List<UserFIndClientDTO> findAllClieants();

    List<User> filterByName(String name);

    List<User> filterByMod(List<User> users, String mod);

    List<User> sortByCriterion(List<User> users, String criterion);

    Integer findPagesCount(List<User> users, Integer elOnPage);

    List<User> findElementsOnPage(List<User> users, Integer pageNum, Integer elOnPage);

    PageFinderDTO pageParserFilter(String name, Integer pagenum, Integer elOnPage,String userMod, String criterion);
}
