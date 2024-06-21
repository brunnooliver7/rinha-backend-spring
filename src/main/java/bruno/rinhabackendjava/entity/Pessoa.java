package bruno.rinhabackendjava.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import bruno.rinhabackendjava.converter.StringListConverter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "rinha", name = "pessoa")
public class Pessoa {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    @NotNull
    @NotEmpty
    @Length(max = 32)
    private String apelido;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Length(max = 100)
    private String nome;

    @NotNull
    @NotEmpty
    private String nascimento;

    @Convert(converter = StringListConverter.class)
    @Column(name = "stack", columnDefinition = "text", nullable = false)
    private List<String> stack;

    public boolean invalido() {
        return (apelido == null || apelido.isBlank() || apelido.length() > 32
                || nome == null || nome.isBlank() || nome.length() > 100
                || isInvalidStack());
    }

    private Boolean isInvalidStack() {
        if (stack == null) {
            return false;
        }

        for (String s : stack) {
            if (s.length() > 32) {
                return true;
            }
        }

        return false;
    }

}
