package auction.backend.dev.models;

import auction.backend.dev.models.common.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends AbstractEntity {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should be not empty")
    @Size(min=3,max = 255,message = "Name should be between 3 and 255")
    private String name;

    @Column(name = "password")
    @NotEmpty(message = "Password should be not empty")
    @Size(min = 10,max = 50,message = "Password size should be between 10 and 50")
    private String password;

    @Column(name = "cash")
    @NotNull(message = "Cash should be not empty")
    @Min(value = 0,message = "Minimal size of sash is 0")
    private int cash;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Item> items;
}
