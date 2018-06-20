package photopolis.loalityprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photopolis.loalityprogram.model.Bonus;

/**
 * Created by Anatoliy on 23.05.2018.
 */
@Repository
public interface BonusRepository extends JpaRepository<Bonus,Integer>{
}
