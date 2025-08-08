package br.com.lopesantonio.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepo extends JpaRepository<Aluno, Long> {


    Optional<Aluno> findByNomeContainingIgnoreCase(String nome);
}
