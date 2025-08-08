package br.com.lopesantonio.dataBase;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "aluno_materia",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<Materia> materias = new ArrayList<>();

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
        return materias;
    }
    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
    public void adicionarMateria (Materia materia){
    this.materias.add(materia);
    materia.getAlunos().add(this);
    }
    public void removerMateria (Materia materia){
        this.materias.remove(materia);
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
