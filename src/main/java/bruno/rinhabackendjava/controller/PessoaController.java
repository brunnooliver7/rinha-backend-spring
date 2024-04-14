package bruno.rinhabackendjava.controller;

import bruno.rinhabackendjava.model.Pessoa;
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

    private final PessoaService pessoaService;

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(pessoaService.obterPessoaPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> obterPessoaPorTermo(@RequestParam(name = "t") String termo) {
        List<Pessoa> listaPessoa = pessoaService.obterListaPessoaPorTermo(termo);
        return new ResponseEntity<>(listaPessoa, HttpStatus.OK);
    }

    @GetMapping("/contagem-pessoas")
    public String contagem() {
        return null;
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.criarPessoa(pessoa);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/pessoas/" + pessoaSalva.getId().toString())
                .body(pessoa);
    }

}
