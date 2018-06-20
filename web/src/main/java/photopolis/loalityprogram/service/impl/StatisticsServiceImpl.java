package photopolis.loalityprogram.service.impl;

import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.countDuration;

/**
 * Created by Anatoliy on 03.06.2018.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RentService rentService;


    @Override
    public Statistic getStatistic(String startDate, String endDate) {
        List<Rent> rents = rentService.findAllInDateInterval(startDate, endDate);
        Statistic statistic = new Statistic();
        statistic.setProfit(getProfit(rents))
                .setNumOfClients(getNumOfClients(rents))
                .setHoursRented(getRentedHours(rents))
                .setBonusPaidClients(getnumBonusPaidClients(rents))
                .setNumOfRegularClients(getUserByType(rents, false))
                .setNumOfFriendClients(getUserByType(rents, true))
                .setNewClients(userService.findNewUsersInDateInterval(startDate, endDate).size())
                .setNumOfPaidRents(getNumOfRentsByStatus(rents, RentStatus.PAID, RentStatus.BONUSPAID))
                .setNumOfLeavedRents(getNumOfRentsByStatus(rents, RentStatus.LEAVED))
                .setNumOfRents(statistic.getNumOfPaidRents()+statistic.getNumOfLeavedRents());
        Double hpc = statistic.getHoursRented() / statistic.getNumOfClients();
        statistic.setHoursPerClient(Math.round(hpc*100)/100.0);
        statistic.setPercentPaidRents(getPercent(statistic.getNumOfRents(),statistic.getNumOfPaidRents()))
                .setPercentLeavedRents(100-statistic.getPercentPaidRents())
                .setPercentRegularClients(getPercent(statistic.getNumOfClients(),statistic.getNumOfRegularClients()))
                .setPercentFriendClients(100-statistic.getPercentRegularClients());
        return statistic;
    }

    private Double getPercent(Integer left, Integer right){
        Double dLeft = left+0.0;
        Double dRight =right+0.0;
        Double percent = dRight/dLeft*100;
        return Math.round(percent*100)/100.0;
    }

    private Double getProfit(List<Rent> rents) {
        Double profit= 0.0;
        List<Rent> paidRents= rents.stream()
                .filter(
                        rent -> rent.getRentStatus()== RentStatus.PAID ||rent.getRentStatus()== RentStatus.BONUSPAID
                ).collect(toList());
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
        rents = rents.stream().filter(
                rent -> rent.getRentStatus()==RentStatus.PAID||rent.getRentStatus()==RentStatus.BONUSPAID
        ).collect(toList());
        for (Rent rent:rents) {
            hours+= countDuration(rent.getTimeOfStart(),rent.getTimeOfEnd());
        }
        return hours;
    }

    private Integer getnumBonusPaidClients (List<Rent> rents){
        List<Rent> bonusPaid=rents.stream().filter(rent -> rent.getRentStatus()==RentStatus.BONUSPAID).collect(toList());
        return getNumOfClients(bonusPaid);
    }

    private Integer getUserByType(List<Rent> rents, Boolean isMember){
        List<Rent> sorted=rents.stream().filter(rent -> rent.getUser().getMember() == isMember).collect(toList());
        return getNumOfClients(sorted);
    }

    private Integer getNumOfRentsByStatus(List<Rent> rents, RentStatus rentStatus){
        return rents.stream().filter(rent -> rent.getRentStatus() == rentStatus).collect(toList()).size();
    }

    private Integer getNumOfRentsByStatus(List<Rent> rents, RentStatus rentStatus, RentStatus secondStatus){
        return rents.stream()
                .filter(
                        rent -> rent.getRentStatus() == rentStatus||rent.getRentStatus() ==secondStatus
                ).collect(toList()).size();
    }

}
