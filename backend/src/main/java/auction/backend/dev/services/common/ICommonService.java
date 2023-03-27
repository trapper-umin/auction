package auction.backend.dev.services.common;

import auction.backend.dev.models.AbstractEntity;

import java.util.List;

public interface ICommonService<E extends AbstractEntity> {

    List<E> getAll();
    E get(int id);
    void create(E entity);
    void update(int id, E entity);
    void delete(int id);
}
