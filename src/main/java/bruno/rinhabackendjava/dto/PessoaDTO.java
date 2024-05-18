package bruno.rinhabackendjava.dto;

import bruno.rinhabackendjava.validator.ValidDate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PessoaDTO {

    private UUID id;

    @NotNull
    @NotEmpty
    @Max(32)
    private String apelido;

    @NotNull
    @NotEmpty
    @Max(100)
    private String nome;

    @NotNull
    @ValidDate
    private String nascimento;

    @NotNull
    private List<String> stack;

}
