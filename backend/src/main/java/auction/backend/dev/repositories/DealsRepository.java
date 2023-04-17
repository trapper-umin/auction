package auction.backend.dev.repositories;

import auction.backend.dev.models.Deal;
import auction.backend.dev.repositories.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealsRepository extends CommonRepository<Deal> {
}
