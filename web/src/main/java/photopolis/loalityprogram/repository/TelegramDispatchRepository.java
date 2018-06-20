package photopolis.loalityprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photopolis.loalityprogram.model.TelegramDispatch;

/**
 * Created by Anatoliy on 20.06.2018.
 */
@Repository
public interface TelegramDispatchRepository extends JpaRepository<TelegramDispatch, Integer> {
}
