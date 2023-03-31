package auction.backend.dev.repositories;

import auction.backend.dev.models.Item;
import auction.backend.dev.repositories.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CommonRepository<Item> {
}
