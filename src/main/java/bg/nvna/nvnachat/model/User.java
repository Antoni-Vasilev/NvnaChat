package bg.nvna.nvnachat.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;

    @Setter
    private int bestScore;

    @Column(unique = true)
    private String email;

    @Column()
    private String password;
}
