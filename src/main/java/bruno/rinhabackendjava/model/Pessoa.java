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
@Table(schema = "rinha", name = "pessoa", indexes = {
        @Index(columnList = "id", name = "idx_id"),
        @Index(columnList = "apelido", name = "idx_apelido"),
        @Index(columnList = "nome", name = "idx_nome"),
        @Index(columnList = "stack", name = "idx_stack")})
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String apelido;

    @Column(nullable = false)
    private String nome;

    private LocalDate nascimento;

    private String stack;

    @Version
    @Column(nullable = false)
    private Integer version;

}
