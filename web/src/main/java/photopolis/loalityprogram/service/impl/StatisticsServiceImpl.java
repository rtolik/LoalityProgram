package photopolis.loalityprogram.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import photopolis.loalityprogram.model.Rent;
import photopolis.loalityprogram.model.Statistic;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.model.enums.RentStatus;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.RentService;
import photopolis.loalityprogram.service.StatisticsService;
import photopolis.loalityprogram.service.UserService;
import photopolis.loalityprogram.service.utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.countDuration;

/**
 * Created by Anatoliy on 03.06.2018.
 */
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RentService rentService;

    @Autowired
    private BonusService bonusService;

    @Override
    public Statistic getStatistic(String startDate, String endDate) {
        Statistic statistic= new Statistic();
        List<Rent> rents=rentService.findAllInDateInterval(startDate,endDate);
        statistic.setProfit(getProfit(rents));
        statistic.setNumOfClients(getNumOfClients(rents));
        statistic.setHoursRented(getRentedHours(rents));
        statistic.setBonusPaidClients(getnumBonusPaidClients(rents));

        return statistic;
    }

    private Double getProfit(List<Rent> rents) {
        Double profit= 0.0;
        List<Rent> paidRents= rents.stream().filter(rent -> rent.getRentStatus()== RentStatus.PAID).collect(toList());
        for (Rent rent:paidRents){
            profit+=rent.getPrice();
        }
        return profit;
    }

    private Integer getNumOfClients(List<Rent> rents){
        List<User> users= new ArrayList<>();
        for (Rent rent:rents) {
            if (!users.contains(rent.getUser()))
                users.add(rent.getUser());
        }
        return users.size();
    }

    private Double getRentedHours(List<Rent> rents){
        Double hours=0.0;
        for (Rent rent:rents ) {
            hours+=countDuration(rent.getTimeOfStart(),rent.getTimeOfEnd());
        }
        return hours;
    }

    private Integer getnumBonusPaidClients (List<Rent> rents){
        List<Rent> bonusPaid=rents.stream().filter(rent -> rent.getRentStatus()==RentStatus.BONUSPAID).collect(toList());
        return getNumOfClients(bonusPaid);
    }

    private Integer getUserByType(List<Rent> rents, Boolean isMember){
        List<Rent> sorted=rents.stream().filter(rent -> rent.getUser().getMember().equals(isMember)).collect(toList());
        return null;
    }
}
