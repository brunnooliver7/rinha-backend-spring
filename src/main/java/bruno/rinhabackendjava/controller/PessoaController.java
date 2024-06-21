package bruno.rinhabackendjava.controller;

import bruno.rinhabackendjava.entity.Pessoa;
import bruno.rinhabackendjava.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            if (pessoa.invalido()) throw new Exception();
            pessoa.setId(UUID.randomUUID());
            service.criarPessoa(pessoa);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header(HttpHeaders.LOCATION, "/pessoas/" + pessoa.getId().toString())
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        }
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable UUID id) {
        try {
            Pessoa pessoa = service.obterPessoaPorId(id);
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> obterPessoaPorTermo(@RequestParam(name = "t") String termo) {
        List<Pessoa> listaPessoa = service.obterListaPessoaPorTermo(termo);
        return new ResponseEntity<>(listaPessoa, HttpStatus.OK);
    }

    @GetMapping("/contagem-pessoas")
    public long contagem() {
        return service.countPessoas();
    }

}
