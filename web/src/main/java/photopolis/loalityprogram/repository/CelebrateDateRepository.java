package photopolis.loalityprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photopolis.loalityprogram.model.CelebrateDate;

/**
 * Created by Anatoliy on 05.06.2018.
 */
@Repository
public interface CelebrateDateRepository extends JpaRepository<CelebrateDate,Integer>{
}
