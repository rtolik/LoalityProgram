package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.model.CelebrateDate;
import photopolis.loalityprogram.repository.CelebrateDateRepository;
import photopolis.loalityprogram.service.CelebrationDateService;

import java.util.List;

/**
 * Created by Anatoliy on 05.06.2018.
 */
@Service
public class CelebrationDateServiceImpl implements CelebrationDateService{

    @Autowired
    private CelebrateDateRepository celebrateDateRepository;

    @Override
    public CelebrateDate save(CelebrateDate date) {
        return celebrateDateRepository.save(date);
    }

    @Override
    public void delete(Integer id) {
        celebrateDateRepository.delete(id);
    }

    @Override
    public List<CelebrateDate> findAll() {
        return celebrateDateRepository.findAll();
    }
}
