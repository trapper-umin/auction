package auction.backend.dev.models;

import auction.backend.dev.models.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Entity
@Table(name = "deal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deal extends AbstractEntity {

    @Id
    @Column(name = "deal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "final_cost")
    private int finalCost;

    //одна сделка может иметь одного продавца
    //один продавец может иметь много сделок
    @Column(name = "seller_id")
    private int seller;

    //одина сделка может иметь одного покупателя
    //один покупатель может иметь много сделок
    @Column(name = "buyer_id")
    private int buyer;

    //одина сделка может содержать один предмет
    //один предмет может содержать множество сделок
    @ManyToOne()
    @JoinColumn(name = "item_id",referencedColumnName = "item_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Item item;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
