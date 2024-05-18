package bruno.rinhabackendjava.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "rinha", name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String apelido;

    @Column(nullable = false)
    private String nome;

    private String nascimento;

    private String stack;

    @Version
    @Column(nullable = false)
    private Integer version;

}
