package bruno.rinhabackendjava.service;

import bruno.rinhabackendjava.entity.Pessoa;
import bruno.rinhabackendjava.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa obterPessoaPorId(UUID id) {
        return pessoaRepository.findById(id).orElseThrow();
    }

    public Boolean apelidoExiste(String apelido) {
        return pessoaRepository.existsByApelido(apelido);
    }

    public List<Pessoa> obterListaPessoaPorTermo(String termo) {
        return pessoaRepository.findByTermo(termo);
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.salvar(pessoa);
    }

    public long countPessoas() {
        return pessoaRepository.count();
    }
}
