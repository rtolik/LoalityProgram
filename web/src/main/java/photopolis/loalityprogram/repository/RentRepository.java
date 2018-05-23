package photopolis.loalityprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import photopolis.loalityprogram.model.Rent;

/**
 * Created by Anatoliy on 23.05.2018.
 */
public interface RentRepository extends JpaRepository<Rent,Integer> {
}
