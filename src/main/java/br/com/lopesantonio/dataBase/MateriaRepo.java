package br.com.lopesantonio.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriaRepo extends JpaRepository<Materia, Long> {



    Optional<Materia> findByMaterias(EMaterias materias);


   boolean findByName(EMaterias enumMateria);
}
