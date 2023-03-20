package auction.backend.dev.repositories;

import auction.backend.dev.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByName(String name);
}
