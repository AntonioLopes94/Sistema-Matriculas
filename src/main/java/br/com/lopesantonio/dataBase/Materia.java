package br.com.lopesantonio.dataBase;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Materia")
public class Materia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EMaterias materias;

    @ManyToMany(mappedBy = "materias",fetch = FetchType.EAGER)
    List<Aluno> alunos = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public EMaterias getMaterias() {
        return materias;
    }
    public void setMaterias(EMaterias materias) {
        this.materias = materias;
    }
    public Materia(String nomeMateria) {
        this.materias = EMaterias.valueOf(nomeMateria);
    }
    public Materia() {
    }
    public List<Aluno> getAlunos() {
        return alunos;
    }
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return String.valueOf(materias);
    }


}