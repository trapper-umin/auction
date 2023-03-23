package auction.backend.dev.repositories;

import auction.backend.dev.models.Creator;
import auction.backend.dev.repositories.common.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreatorsRepository extends CommonRepository<Creator> {
    Optional<Creator> findByName(String name);
}
