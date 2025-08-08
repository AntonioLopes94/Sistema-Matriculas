package br.com.lopesantonio.dataBase;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "aluno_materia",
            joinColumns = @JoinColumn(name = "aluno_matricula"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<Materia> alunoMateria = new ArrayList<>();

    //Setter e Getters

    public Long getMatricula() {
        return matricula;
    }
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Materia> getMaterias() {
        return alunoMateria;
    }
    public void setMaterias(List<Materia> materias) {
        this.alunoMateria = materias;
    }
    public void adicionarMateria (Materia materia){
    this.alunoMateria.add(materia);
    materia.getAlunos().add(this);
    }
    public void removerMateria (Materia materia){
        this.alunoMateria.remove(materia);
        materia.getAlunos().remove(this);
    }


    //Constructor
    public Aluno(){}
    public Aluno(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return nome;
        //+ "\nMatricula: " + matricula;
    }

}
