package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import photopolis.loalityprogram.DTO.PageFinderDTO;
import photopolis.loalityprogram.DTO.UserFIndClientDTO;
import photopolis.loalityprogram.DTO.UserFindDTO;
import photopolis.loalityprogram.DTO.UserPagesDTO;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.repository.UserRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public void createUser(MultipartFile img, String name, String secondName, String surname, String phone,
                           String dateOfBirth, String socialMedia, Integer cardId, String lastVisit,
                           Integer numberOfVisits, Boolean isMember, String dateOfMember) {
        String uuid = UUID.randomUUID().toString();
        User user = new User();
        user.setName(name).setSecondName(secondName).setSurname(surname).setPhone(phone)
                .setDateOfBirth(dataParser(dateOfBirth)).setSocialMedia(socialMedia).setCardId(cardId)
                .setLastVisit(lastVisit).setNumberOfVisits(numberOfVisits).setMember(isMember)
                .setIamgePath("/res/file/"+uuid+"/"+img.getOriginalFilename()).setDateOfMember(dataParser(dateOfMember));
        String path = System.getProperty("catalina.home")+"/resources/photopolis.loalityprogram/file/"+uuid+"/"
                + img.getOriginalFilename();
        System.out.println(path);
        File file = new File(path);

        try{
            file.getParentFile().mkdirs();
            img.transferTo(file);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("File Error!!!!");
        }
    }

    @Override
    public void save(String imagePath, String name, String secondName, String surname, String phone, String dateOfBirth,
                     String socialMedia, Integer cardId, String lastVisit, Integer numberOfVisits, Boolean isMember,
                     String dateofMember) {
        userRepository.save(
                new User(
                        imagePath, name, secondName, surname, phone,dataParser(dateOfBirth), socialMedia, cardId,
                        lastVisit, numberOfVisits,true,isMember, dataParser(dateofMember)
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
    public void update(Integer id, String imagePath, String name, String secondName, String surname, String phone,
                       String dateOfBirth, String socialMedia, Integer cardId, String lastVisit,
                       Integer numberOfVisits, Boolean isActive,Boolean isMember, List<Integer> bonusId, String dateOfMember) {
        User tmp= findOne(id);
        tmp.setName(name).setSecondName(secondName).setSurname(surname).setPhone(phone).setDateOfBirth(dateOfBirth)
                .setSocialMedia(socialMedia).setCardId(cardId).setLastVisit(lastVisit).setNumberOfVisits(numberOfVisits)
                .setActive(isActive).setBonuses(bonusService.findAllByBonusesId(bonusId)).setMember(isMember)
                .setIamgePath(imagePath).setDateOfMember(dataParser(dateOfMember));
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

    @Override
    public List<User> filterByName(String name) {
        return findAll().stream().filter(user -> user.getName().contains(name) && user.getActive()).collect(toList());
    }

    @Override
    public List<User> filterByMod(List<User> users,String mod) {
        if(mod.equals("all"))
            return users;
        if(mod.equals("regular"))
            return users.stream().filter(user -> !user.getMember()).collect(toList());
        else {
        return users.stream().filter(user -> user.getMember()).collect(toList());
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
                return o1.getDateOfMember().compareTo(o2.getDateOfMember());
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
        dto = new PageFinderDTO(dtos,pagesCount);
        return dto;
    }
}
