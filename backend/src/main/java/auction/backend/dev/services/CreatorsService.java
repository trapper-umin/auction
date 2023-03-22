package auction.backend.dev.services;

import auction.backend.dev.models.Creator;
import auction.backend.dev.repositories.CreatorsRepository;
import auction.backend.dev.util.Excaption.Creator.CreatorNotFoundException;
import auction.backend.dev.util.Excaption.Creator.CreatorsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CreatorsService {

    private final CreatorsRepository creatorsRepository;

    public CreatorsService(CreatorsRepository creatorsRepository){
        this.creatorsRepository=creatorsRepository;
    }

    public List<Creator> getAllCreators(){
        List<Creator> creators=creatorsRepository.findAll();
        if(creators.size()==0)
            throw new CreatorsNotFoundException("There are no creators in the database");
        return creators;
    }

    public Creator getCreatorById(int id){
        Optional<Creator> creator=creatorsRepository.findById(id);
        if(creator.isEmpty())
            throw new CreatorNotFoundException("Creator with id "+id+" not found");
        return creator.get();
    }

    public Optional<Creator> getOptionalCreatorByName(String name){
        return creatorsRepository.findByName(name);
    }

    @Transactional
    public void createCreator(Creator creator){
        creator.setCreatedAt(LocalDateTime.now());
        creator.setUpdatedAt(LocalDateTime.now());
        creatorsRepository.save(creator);
    }

    @Transactional
    public void updateCreator(int id, Creator creator){
        Optional<Creator> creatorBeforeUpdate=creatorsRepository.findById(id);
        if(creatorBeforeUpdate.isEmpty())
            throw new CreatorNotFoundException("Creator with id "+id+" not found");

        creator.setId(id);
        creator.setCreatedAt(creatorBeforeUpdate.get().getCreatedAt());
        creator.setUpdatedAt(LocalDateTime.now());
        creatorsRepository.save(creator);
    }
}
