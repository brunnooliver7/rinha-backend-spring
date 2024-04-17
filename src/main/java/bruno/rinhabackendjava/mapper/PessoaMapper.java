package bruno.rinhabackendjava.mapper;

import bruno.rinhabackendjava.dto.PessoaDTO;
import bruno.rinhabackendjava.entity.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper
public interface PessoaMapper {

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "stack", source = "stack", qualifiedByName = "listToString")
    Pessoa fromDTO(PessoaDTO dto);

    @Mapping(target = "stack", source = "stack", qualifiedByName = "stringToList")
    PessoaDTO toDTO(Pessoa entity);

    List<PessoaDTO> toDTOs(List<Pessoa> entities);

    @Named("stringToList")
    default List<String> stringToList(String stack) {
        return Arrays.asList(stack.split(","));
    }

    @Named("listToString")
    default String listToString(List<String> stack) {
        return String.join(",", stack);
    }

}
