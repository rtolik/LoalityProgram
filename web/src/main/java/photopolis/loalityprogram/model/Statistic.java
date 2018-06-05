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

    private Double percentReqularClients;

    private Double percentFriendClients;

    private Double percentPaidRents;

    private Double percentLeavedRents;

    @Override
    public String toString() {
        return "profit "+ profit+" numOfClients "+numOfClients +" housrRneted"+ hoursRented;
    }

    public Statistic() {
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

    public Double getPercentReqularClients() {
        return percentReqularClients;
    }

    public Statistic setPercentReqularClients(Double percentReqularClients) {
        this.percentReqularClients = percentReqularClients;
        return this;
    }

    public Double getPercentFriendClients() {
        return percentFriendClients;
    }

    public Statistic setPercentFriendClients(Double percentFriendClients) {
        this.percentFriendClients = percentFriendClients;
        return this;
    }

    public Double getPercentPaidRents() {
        return percentPaidRents;
    }

    public Statistic setPercentPaidRents(Double percentPaidRents) {
        this.percentPaidRents = percentPaidRents;
        return this;
    }

    public Double getPercentLeavedRents() {
        return percentLeavedRents;
    }

    public Statistic setPercentLeavedRents(Double percentLeavedRents) {
        this.percentLeavedRents = percentLeavedRents;
        return this;
    }
}
