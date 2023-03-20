package auction.backend.dev.repositories;

import auction.backend.dev.models.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreatorsRepository extends JpaRepository<Creator, Integer> {
    Optional<Creator> findByName(String name);
}
