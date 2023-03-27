package auction.backend.dev.repositories;

import auction.backend.dev.models.Creator;
import auction.backend.dev.repositories.common.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreatorsRepository extends CommonRepository<Creator> {
    @Query(value = "SELECT * FROM creator WHERE name=:name",
            nativeQuery=true)
    Optional<Creator> findByName(@Param("name") String name);
}
