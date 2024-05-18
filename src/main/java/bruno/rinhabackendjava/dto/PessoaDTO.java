package bruno.rinhabackendjava.dto;

import bruno.rinhabackendjava.validator.ValidDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 32)
    private String apelido;

    @NotNull
    @NotEmpty
    @Length(max = 100)
    private String nome;

    @NotNull
    @NotEmpty
    @ValidDate
    private String nascimento;

    @NotNull
    private List<String> stack;

}
