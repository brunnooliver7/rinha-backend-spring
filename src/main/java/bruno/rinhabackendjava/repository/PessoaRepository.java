package bruno.rinhabackendjava.repository;

import bruno.rinhabackendjava.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query(value = "SELECT p.* " +
            "FROM rinha.pessoa p " +
            "WHERE termo ILIKE '%' || :termo || '%' " +
            "LIMIT 50",
            nativeQuery = true)
    List<Pessoa> findByTermo(@Param("termo") String termo);

}
