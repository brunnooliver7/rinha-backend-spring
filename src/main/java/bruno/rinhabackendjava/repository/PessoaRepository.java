package bruno.rinhabackendjava.repository;

import bruno.rinhabackendjava.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query(value = """
            SELECT p.*
            FROM pessoa p
            WHERE LOWER(p.apelido) LIKE LOWER(CONCAT('%', :termo, '%'))
               OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%'))
               OR EXISTS (
                  SELECT 1
                  FROM pessoa_stack s
                  WHERE s.pessoa_id = p.id
                    AND LOWER(s.stack) LIKE LOWER(CONCAT('%', :termo, '%'))
               )
                                        """, nativeQuery = true)
    List<Pessoa> findByTermo(@Param("termo") String termo);

}
