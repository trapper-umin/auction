package auction.backend.dev.repositories;

import auction.backend.dev.models.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Integer> {

}
