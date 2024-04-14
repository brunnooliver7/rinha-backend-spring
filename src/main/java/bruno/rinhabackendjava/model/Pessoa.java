package bruno.rinhabackendjava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "rinha", name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String apelido;

    @Column(nullable = false)
    private String nome;

    private LocalDate nascimento;

    @ElementCollection
    private List<String> stack;

}
