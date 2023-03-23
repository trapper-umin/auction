package auction.backend.dev.services.common;

import auction.backend.dev.models.AbstractEntity;
import auction.backend.dev.repositories.common.CommonRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCommonService<E extends AbstractEntity, R extends CommonRepository<E>> implements ICommonService<E>{

    private final R repository;

    public AbstractCommonService(R repository){
        this.repository=repository;
    }


}
