package auction.backend.dev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "deal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deal extends AbstractEntity{

    @Id
    @Column(name = "deal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "final_cost")
    private int finalCost;

//    @ManyToOne
//    @Column(name = "seller")
//    private Person seller;
//
//    @ManyToOne
//    @Column(name = "buyer")
//    private Person buyer;
//
//    @OneToOne
//    @Column(name = "item")
//    private Item item;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
