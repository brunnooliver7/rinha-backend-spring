package bruno.rinhabackendjava.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PessoaDTO {

    private UUID id;

    @NotNull
    private String apelido;

    @NotNull
    private String nome;

    private LocalDate nascimento;

    @NotNull
    private List<String> stack;

}
