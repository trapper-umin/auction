package auction.backend.dev.repositories;

import auction.backend.dev.models.Person;
import auction.backend.dev.repositories.common.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends CommonRepository<Person> {

    @Query(value = "SELECT * FROM person WHERE name=:name",nativeQuery = true)
    Optional<Person> findByName(@Param("name")String name);
}
