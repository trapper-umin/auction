package auction.backend.dev.services;

import auction.backend.dev.models.Creator;
import auction.backend.dev.repositories.CreatorsRepository;
import auction.backend.dev.services.common.ICommonService;
import auction.backend.dev.util.Excaption.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CreatorsDBService implements ICommonService<Creator> {

    private final CreatorsRepository creatorsRepository;

    public CreatorsDBService(CreatorsRepository creatorsRepository) {
        this.creatorsRepository = creatorsRepository;
    }

    public List<Creator> getAll(){
        List<Creator> creators=creatorsRepository.findAll();
        if(creators.size()==0)
            throw new NotFoundException("There are no creators in the database");
        return creators;
    }

    public Creator get(int id){
        Optional<Creator> creator=creatorsRepository.findById(id);
        if(creator.isEmpty())
            throw new NotFoundException("Creator with id "+id+" not found");
        return creator.get();
    }

    public Optional<Creator> getOptionalCreatorByName(String name){
        return creatorsRepository.findByName(name);
    }

    @Transactional
    public void create(Creator creator){
        creator.setCreatedAt(LocalDateTime.now());
        creator.setUpdatedAt(LocalDateTime.now());
        creatorsRepository.save(creator);
    }

    @Transactional
    public void update(int id, Creator creator){
        Optional<Creator> creatorBeforeUpdate=creatorsRepository.findById(id);
        if(creatorBeforeUpdate.isEmpty())
            throw new NotFoundException("Creator with id "+id+" not found");
        creator.setId(id);
        creator.setCreatedAt(creatorBeforeUpdate.get().getCreatedAt());
        creator.setUpdatedAt(LocalDateTime.now());
        creatorsRepository.save(creator);
    }

    @Transactional
    public void delete(int id){
        if(creatorsRepository.findById(id).isEmpty())
            throw new NotFoundException("Creator with id "+id+" not found");
        creatorsRepository.deleteById(id);
    }
}
