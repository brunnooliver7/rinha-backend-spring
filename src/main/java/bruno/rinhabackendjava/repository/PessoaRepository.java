package bruno.rinhabackendjava.repository;

import bruno.rinhabackendjava.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query(value = " SELECT p.* " +
            "FROM pessoa p " +
            "WHERE LOWER(p.apelido) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%'))" +
            "OR LOWER(p.stack) LIKE LOWER(CONCAT('%', :termo, '%'))",
            nativeQuery = true)
    List<Pessoa> findByTermo(@Param("termo") String termo);

    @Lock(LockModeType.OPTIMISTIC)
    default Pessoa salvar(Pessoa pessoa) {
        return save(pessoa);
    }

    boolean existsByApelido(String apelido);
}
