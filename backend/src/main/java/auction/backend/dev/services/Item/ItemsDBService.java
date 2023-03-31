package auction.backend.dev.services.Item;

import auction.backend.dev.models.Item;
import auction.backend.dev.repositories.ItemsRepository;
import auction.backend.dev.services.common.ICommonService;
import auction.backend.dev.util.Excaption.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ItemsDBService implements ICommonService<Item> {

    private final ItemsRepository itemsRepository;

    public ItemsDBService(ItemsRepository itemsRepository){
        this.itemsRepository=itemsRepository;
    }

    @Override
    public List<Item> getAll() {
        List<Item>itemsFromDB=itemsRepository.findAll();
        if(itemsFromDB.size()==0)
            throw new NotFoundException("There are no items in the database");
        return itemsFromDB;
    }

    @Override
    public Item get(int id) {
        Optional<Item>itemFromDB=itemsRepository.findById(id);
        if(itemFromDB.isEmpty())
            throw new NotFoundException("Item with ID "+id+" not found");
        return itemFromDB.get();
    }

    @Override
    @Transactional
    public void create(Item entity) {
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        itemsRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(int id, Item entity) {
        Optional<Item>itemFromDB=itemsRepository.findById(id);
        if(itemFromDB.isEmpty())
            throw new NotFoundException("Item with ID "+id+" not found");
        entity.setId(id);
        entity.setCreatedAt(itemFromDB.get().getCreatedAt());
        entity.setUpdatedAt(LocalDateTime.now());
        itemsRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        if(itemsRepository.findById(id).isEmpty())
            throw new NotFoundException("Item with ID "+id+" not found");
        itemsRepository.deleteById(id);
    }
}
