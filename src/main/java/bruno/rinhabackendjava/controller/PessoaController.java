package bruno.rinhabackendjava.controller;

import bruno.rinhabackendjava.dto.PessoaDTO;
import bruno.rinhabackendjava.entity.Pessoa;
import bruno.rinhabackendjava.mapper.PessoaMapper;
import bruno.rinhabackendjava.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;
    private final PessoaMapper mapper;

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<PessoaDTO> obterPessoaPorId(@PathVariable UUID id) {
        try {
            Pessoa pessoa = service.obterPessoaPorId(id);
            return new ResponseEntity<>(mapper.toDTO(pessoa), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PessoaDTO>> obterPessoaPorTermo(@RequestParam(name = "t") String termo) {
        List<Pessoa> listaPessoa = service.obterListaPessoaPorTermo(termo);
        return new ResponseEntity<>(mapper.toDTOs(listaPessoa), HttpStatus.OK);
    }

    @GetMapping("/contagem-pessoas")
    public long contagem() {
        return service.countPessoas();
    }

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody @Valid PessoaDTO dto) {
        if (Boolean.TRUE.equals(service.apelidoExiste(dto.getApelido()))) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(null);
        }

        Pessoa pessoa = mapper.fromDTO(dto);
        Pessoa pessoaSalva = service.criarPessoa(pessoa);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/pessoas/" + pessoaSalva.getId().toString())
                .body(mapper.toDTO(pessoaSalva));
    }

}
