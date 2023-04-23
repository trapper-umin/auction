package auction.backend.dev.services;

import auction.backend.dev.dto.DealDTO;
import auction.backend.dev.models.Deal;
import auction.backend.dev.models.Item;
import auction.backend.dev.models.Person;
import auction.backend.dev.repositories.DealsRepository;
import auction.backend.dev.services.Item.ItemsDBService;
import auction.backend.dev.services.Person.PeopleDBService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DealDBService {

    private final DealsRepository dealsRepository;
    private final PeopleDBService peopleDBService;
    private final ItemsDBService itemsDBService;


    public DealDBService(DealsRepository dealsRepository,
                         PeopleDBService peopleDBService,
                         ItemsDBService itemsDBService){
        this.dealsRepository = dealsRepository;
        this.peopleDBService=peopleDBService;
        this.itemsDBService = itemsDBService;
    }

    @Transactional
    public List<Person> create(DealDTO dealDTO,
                               int sellerId,
                               int buyerId,
                               int itemId){
        Person seller=peopleDBService.get(sellerId);
        Person buyer=peopleDBService.get(buyerId);
        Item item=itemsDBService.get(itemId);

        int cost=dealDTO.getFinalCost();
        //  1)Проверяем есть ли такой продавец
        //  2)Проверяем есть ли такой покупатель
        //  3)Проверяем есть ли такой товар
        //
        //  4)Списать деньги у покупателя
        //  5)Добавить деньги продавцу
        //  6)Удалить товар у продавца
        //  7)Добавить товар покупателю

        buyer.setCash(buyer.getCash()-cost);
        buyer.setUpdatedAt(LocalDateTime.now());

        seller.setCash(seller.getCash()+cost);
        seller.setUpdatedAt(LocalDateTime.now());

        peopleDBService.update(buyerId,buyer);
        peopleDBService.update(sellerId,seller);

        item.setOwner(buyer);
        item.setUpdatedAt(LocalDateTime.now());

        itemsDBService.update(itemId,item);

        Deal deal=new Deal();
        deal.setFinalCost(cost);
        deal.setSeller(sellerId);
        deal.setBuyer(buyerId);
        deal.setItem(item);
        deal.setCreatedAt(LocalDateTime.now());

        dealsRepository.save(deal);

        List<Person>people=new ArrayList<>(2);
        //TODO поправить баг в рспонсе
        people.add(seller);
        people.add(buyer);

        return people;
    }
}
