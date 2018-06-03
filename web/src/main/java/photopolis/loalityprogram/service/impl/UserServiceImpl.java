package photopolis.loalityprogram.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import photopolis.loalityprogram.DTO.*;
import photopolis.loalityprogram.config.Constants;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.model.enums.BonusType;
import photopolis.loalityprogram.repository.UserRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.dataComparer;
import static photopolis.loalityprogram.service.utils.Utility.dataParser;
import static photopolis.loalityprogram.service.utils.Utility.datePluser;

/**
 * Created by Anatoliy on 24.05.2018.
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private RentService rentService;

    private final String photoPath= "/res/file";

    @Override
    public void createUser(MultipartFile img, String name, String secondName, String surname, String phone,
                           String dateOfBirth, String socialMedia, Integer cardId, String lastVisit,
                           Boolean isMember, String dateOfMember, String email, String dateOfRegistration) {
        String uuid = UUID.randomUUID().toString();
        User user = new User();
        user.setName(name).setSecondName(secondName).setSurname(surname).setPhone(phone)
                .setDateOfBirth(dateOfBirth == null?null:dataParser(dateOfBirth)).setSocialMedia(socialMedia)
                .setCardId(cardId).setLastVisit(lastVisit).setMember(isMember).setActive(true)
                .setImagePath(saveFile(img)
                )
                .setDateOfMember(dateOfMember==null?null:dataParser(dateOfMember))
                .setEmail(email).setDateOfRegistration(dataParser(dateOfRegistration));
        save(user);
        if(user.getMember()) {
            bonusService.save(50.0, 0, dataParser(dateOfRegistration),
                    datePluser(dataParser(dateOfRegistration), BonusType.REGULAR)
                    , user.getId());
            bonusService.save(0.0,1,null,null,user.getId());
            bonusService.save(0.0,2,null,null,user.getId());
            bonusService.save(0.0,3,null,null,user.getId());
        }
    }

    @Override
    public void save(String imagePath, String name, String secondName, String surname, String phone, String dateOfBirth,
                     String socialMedia, Integer cardId, String lastVisit, Boolean isMember,
                     Boolean isActive, String dateOfMember, String email,String dateOfRegistration) {
        userRepository.save(
                new User(imagePath,name,secondName,surname,phone,dateOfBirth,socialMedia,cardId,dateOfMember,lastVisit,
                        email,dateOfRegistration,isActive,isMember
                )
        );
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
    public User update(Integer id, String imagePath, String name, String secondName, String surname, String phone,
                       String dateOfBirth, String socialMedia, Integer cardId, String lastVisit,
                       Boolean isActive,Boolean isMember, List<Integer> bonusId,
                       String dateOfMember, String dateOfRegistration) {
        User tmp= findOne(id);
        tmp.setName(name).setSecondName(secondName).setSurname(surname).setPhone(phone).setDateOfBirth(dateOfBirth)
                .setSocialMedia(socialMedia).setCardId(cardId).setLastVisit(lastVisit)
                .setActive(isActive).setBonuses(bonusService.findAllByBonusesId(bonusId)).setMember(isMember)
                .setImagePath(imagePath).setDateOfMember(dataParser(dateOfMember))
                .setDateOfRegistration(dateOfRegistration);
        save(tmp);
        return findOne(tmp.getId());
    }

    @Override
    public User update(User user) {
        save(user.setDateOfMember(dataParser(user.getDateOfMember())).setMember(!user.getCardId().equals(null)));
        return findOne(user.getId());
    }

    @Override
    public User updateWithImg(User user, MultipartFile file) {
        return update(user.setImagePath(saveFile(file)).setDateOfMember(dataParser(user.getDateOfMember()))
                .setMember(!user.getCardId().equals(null)));
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

    @Override
    public List<User> filterByName(String name) {
        return findAll().stream().filter(user -> user.getName().contains(name) && user.getActive()).collect(toList());
    }

    @Override
    public List<User> filterByMod(List<User> users,String mod) {
        if(mod.equals("all"))
            return users;
        if(mod.equals("regular"))
            return users.stream().filter(user -> !user.getMember() && user.getActive()).collect(toList());
        else {
        return users.stream().filter(user -> user.getMember() && user.getActive()).collect(toList());
        }
    }

    @Override
    public List<User> sortByCriterion(List<User> users, String criterion) {
        Comparator<User> comparator= new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                String o1COmp = o1.getName() + o1.getSurname() + o1.getSecondName();
                String o2Comp= o2.getName()+ o2.getSurname() + o2.getSecondName();
                return o1COmp.compareTo(o2Comp);
            }
        };

        Comparator<User> dateComparator= new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getDateOfRegistration().compareTo(o2.getDateOfRegistration());
            }
        };
        if(criterion.equals("alp")) {
            Collections.sort(users, comparator);
            return users;
        }
        if (criterion.equals("rev")) {
            Collections.sort(users, comparator);
            Collections.reverse(users);
            return users;
        }
        Collections.sort(users, dateComparator);
        return users;
    }

    @Override
    public Integer findPagesCount(List<User> users, Integer elOnPage) {
        return (int) Math.ceil(users.size()/elOnPage)+1;
    }

    @Override
    public List<User> findElementsOnPage(List<User> users, Integer pageNum, Integer elOnPage) {
        Integer startIndex=pageNum*elOnPage;
        Integer endIndex=startIndex+elOnPage;
        if(endIndex<users.size())
            return users.subList(startIndex,endIndex);
        return users.subList(startIndex,users.size());
    }

    @Override
    public PageFinderDTO pageParserFilter(String name, Integer pagenum, Integer elOnPage, String userMod,
                                                String criterion) {
        PageFinderDTO dto;
        List<UserPagesDTO> dtos= new ArrayList<>();
        List<User> users;
        if(!name.equals("empty"))
            users=filterByName(name);
        else
            users=findAllActive();
        users= filterByMod(users,userMod);
        users= sortByCriterion(users,criterion);
        Integer pagesCount= findPagesCount(users,elOnPage);
        users =  findElementsOnPage(users,pagenum,elOnPage);
        users.forEach(user -> dtos.add(new UserPagesDTO(user)));
        dto = new PageFinderDTO(dtos.isEmpty()?new ArrayList<>():dtos,pagesCount);
        return dto;
    }

    public String getFileTeg(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String saveFile(MultipartFile multipartFile) {
        String folder="";
        try {
            String tag = getFileTeg(multipartFile.getOriginalFilename());
            String uuid = UUID.randomUUID().toString();

            folder = String.format("%s/%s.%s", photoPath, uuid, tag);
            File file = new File(System.getProperty("catalina.home") + folder);
            file.getParentFile().mkdirs();//!correct
//            LOGGER.info(System.getProperty("catalina.home") + folder);
            if (!file.exists()) {
                multipartFile.transferTo(file);
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folder;
    }

    @Override
    public Boolean login(String login, String password) {
        if (login.equals(Constants.LOGIN)) {
            if (password.equals(Constants.PASSWORD))
                return true;
        }
        return false;
    }

    @Override
    public List<User> findNewUsersInDateInterval(String startDate, String endDate) {
        return findAll().stream()
                .filter(
                        user -> dataComparer(user.getDateOfRegistration(),startDate)
                                &&!dataComparer(user.getDateOfRegistration(),endDate)
                ).collect(toList());
    }
}
