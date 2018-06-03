package photopolis.loalityprogram.model;



/**
 * Created by Anatoliy on 02.06.2018.
 */
public class Statistic {

     private Double profit;

    private Integer numOfClients;

    private Double hoursRented;

    private Integer bonusPaidClients;

    private Integer numOfFriendClients;

    private Double hoursPerClient;

    private Integer newClients;

    private Integer numOfRents;

    private Integer numOfPaidRents;

    private Integer numOfLeavedRents;

    private Integer numOfRegularClients;

    public Statistic() {
    }

    public Statistic(Double profit, Integer numOfClients, Double hoursRented, Integer bonusPaidClients,
                     Integer numOfFriendClients, Double hoursPerClient, Integer newClients, Integer numOfRents,
                     Integer numOfPaidRents, Integer numOfLeavedRents, Integer numOfRegularClients) {
        this.profit = profit;
        this.numOfClients = numOfClients;
        this.hoursRented = hoursRented;
        this.bonusPaidClients = bonusPaidClients;
        this.numOfFriendClients = numOfFriendClients;
        this.hoursPerClient = hoursPerClient;
        this.newClients = newClients;
        this.numOfRents = numOfRents;
        this.numOfPaidRents = numOfPaidRents;
        this.numOfLeavedRents = numOfLeavedRents;
        this.numOfRegularClients = numOfRegularClients;
    }

    public Double getProfit() {
        return profit;
    }

    public Statistic setProfit(Double profit) {
        this.profit = profit;
        return this;
    }

    public Integer getNumOfClients() {
        return numOfClients;
    }

    public Statistic setNumOfClients(Integer numOfClients) {
        this.numOfClients = numOfClients;
        return this;
    }

    public Double getHoursRented() {
        return hoursRented;
    }

    public Statistic setHoursRented(Double hoursRented) {
        this.hoursRented = hoursRented;
        return this;
    }

    public Integer getBonusPaidClients() {
        return bonusPaidClients;
    }

    public Statistic setBonusPaidClients(Integer bonusPaidClients) {
        this.bonusPaidClients = bonusPaidClients;
        return this;
    }

    public Integer getNumOfFriendClients() {
        return numOfFriendClients;
    }

    public Statistic setNumOfFriendClients(Integer numOfFriendClients) {
        this.numOfFriendClients = numOfFriendClients;
        return this;
    }

    public Double getHoursPerClient() {
        return hoursPerClient;
    }

    public Statistic setHoursPerClient(Double hoursPerClient) {
        this.hoursPerClient = hoursPerClient;
        return this;
    }

    public Integer getNewClients() {
        return newClients;
    }

    public Statistic setNewClients(Integer newClients) {
        this.newClients = newClients;
        return this;
    }

    public Integer getNumOfRents() {
        return numOfRents;
    }

    public Statistic setNumOfRents(Integer numOfRents) {
        this.numOfRents = numOfRents;
        return this;
    }

    public Integer getNumOfPaidRents() {
        return numOfPaidRents;
    }

    public Statistic setNumOfPaidRents(Integer numOfPaidRents) {
        this.numOfPaidRents = numOfPaidRents;
        return this;
    }

    public Integer getNumOfLeavedRents() {
        return numOfLeavedRents;
    }

    public Statistic setNumOfLeavedRents(Integer numOfLeavedRents) {
        this.numOfLeavedRents = numOfLeavedRents;
        return this;
    }

    public Integer getNumOfRegularClients() {
        return numOfRegularClients;
    }

    public Statistic setNumOfRegularClients(Integer numOfRegularClients) {
        this.numOfRegularClients = numOfRegularClients;
        return this;
    }
}
