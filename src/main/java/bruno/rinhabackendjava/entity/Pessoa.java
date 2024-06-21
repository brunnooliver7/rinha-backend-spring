package bruno.rinhabackendjava.entity;

import bruno.rinhabackendjava.converter.StringListConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

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
//        var apelidoValido = apelido != null && !apelido.isEmpty() && apelido.length() < 32;
//        var nomeValido = nome != null && !nome.isEmpty() && nome.length() < 100;
//        var nascimentoValido = nascimento != null && isValidDate(nascimento);
//        var stackValida = stack != null && stack.stream().allMatch(value -> value != null && value.length() < 32);
//        return apelidoValido && nomeValido && nascimentoValido && stackValida;
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

    private boolean isValidDate(String dateAsString) {
        try {
            DateTimeFormatter.ofPattern("yyyy[-MM[-dd]]").parseBest(dateAsString, LocalDate::from, YearMonth::from, Year::from);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
