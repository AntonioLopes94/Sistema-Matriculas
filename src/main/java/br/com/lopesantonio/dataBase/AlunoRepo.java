package br.com.lopesantonio.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AlunoRepo extends JpaRepository<Aluno, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Aluno WHERE nome = %:nome%")
    void deleteByName(@Param("nome") String nome);

    Optional<Aluno> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);
}
