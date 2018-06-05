package photopolis.loalityprogram.service;

import photopolis.loalityprogram.model.CelebrateDate;

import java.util.List;

/**
 * Created by Anatoliy on 05.06.2018.
 */
public interface CelebrationDateService {

    CelebrateDate save(CelebrateDate date);

    void delete(Integer id);

    List<CelebrateDate> findAll();

}
