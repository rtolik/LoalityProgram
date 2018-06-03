package photopolis.loalityprogram.service;

import photopolis.loalityprogram.model.Statistic;

/**
 * Created by Anatoliy on 03.06.2018.
 */
public interface StatisticsService {

    Statistic getStatistic(String startDate, String  endDate);
}
