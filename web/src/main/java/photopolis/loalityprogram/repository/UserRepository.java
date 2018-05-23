package photopolis.loalityprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import photopolis.loalityprogram.model.User;

/**
 * Created by Anatoliy on 23.05.2018.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
