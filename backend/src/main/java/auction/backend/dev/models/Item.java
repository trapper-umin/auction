package auction.backend.dev.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends AbstractEntity{

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 4,max = 255,message = "Name size should be between 4 and 255")
    @Column(name="name")
    private String name;

    @NotEmpty(message = "Description should be not empty")
    @Size(min = 4,max = 255,message = "Description size should be between 4 and 255")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Cost should be not null")
    @Min(value = 0,message = "Minimal size of cost is 0")
    @Column(name = "cost")
    private int cost;

    @NotNull(message = "Step should be not null")
    @Min(value = 5, message = "Minimal step is 5")
    @Column(name = "step")
    private int step;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
