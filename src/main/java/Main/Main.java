package Main;

import br.com.lopesantonio.dataBase.*;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private final AlunoRepo alunoRepo;
    private final MateriaRepo materiaRepo;

    private static Scanner input = new Scanner(System.in);

    public Main(AlunoRepo alunoRepo, MateriaRepo materiaRepo) {
        this.alunoRepo = alunoRepo;
        this.materiaRepo = materiaRepo;
    }

    public void menu() {
        boolean sistemaAtivo = true;
        int opcaoMain;
        while (sistemaAtivo) {
            System.out.println("----------//----------"
                    + "\n 1. Aluno"
                    + "\n 2. Cursos"
                    + "\n 3. Sair"
                    + "\n ----------//----------");
            opcaoMain = input.nextInt();
            input.nextLine();

            switch (opcaoMain) {
                case 1:
                    menuAluno();
                    break;

                case 2:
                    menuMateria();
                    break;

                case 3:
//                    sistemaAtivo = false;
                    System.exit(0);

                default:
                    System.out.println("Digite uma opção válida");

            }
        }
    }

    private void menuAluno() {
        int opcaoAluno;
        System.out.println("----------//----------"
                + "\n 1. Adicionar aluno"
                + "\n 2. Verificar lista de alunos"
                + "\n 3. Remover o aluno"
                + "\n 4. Voltar ao inicio"
                + "\n ----------//----------");
        opcaoAluno = input.nextInt();
        input.nextLine();
        switch (opcaoAluno) {
            case 1:
                System.out.println("Insira o nome do Aluno");
                var nome = input.nextLine();
                Aluno aluno = new Aluno(nome);
                alunoRepo.save(aluno);
                break;
            case 2:
                List<Aluno> alunos = alunoRepo.findAll();
                alunos.forEach(System.out::println);

                break;
            case 3:
                System.out.println("Insira o numero do aluno a ser removido");
                var alunoRemove = input.nextLong();
                alunoRepo.deleteById(alunoRemove);
                break;
            case 4:
                break;
            default:
                System.out.println("Digite uma opção válida");

        }
    }

    public void menuMateria() {
        int opcaoMateria;
        System.out.println("----------//----------"
                + "\n 1. Inscrever aluno em uma matéria"
                + "\n 2. Verificar inscrições de um aluno"
                + "\n 3. Verificar matérias disponíveis"
                + "\n 4. Remover matéria da grade de um aluno"
                + "\n 5. Voltar ao inicio"
                + "\n ----------//----------");
        opcaoMateria = input.nextInt();
        input.nextLine();

        switch (opcaoMateria) {
            case 1:
                List<Aluno> alunos = alunoRepo.findAll();
                List<Materia> materias = materiaRepo.findAll();

                if (alunos.isEmpty()) {
                    System.out.println("Sem alunos cadastrados");
                    break;
                }

                System.out.println("Por favor, insira o nome do Aluno");
                var nomeAluno = input.nextLine();
                Optional<Aluno> acharAluno = alunos.stream().filter(a -> a.getNome().equalsIgnoreCase(nomeAluno)).findFirst();

                if(acharAluno.isPresent()) {
                    Aluno aluno = acharAluno.get();

                    List<EMaterias> Ematerias = List.of(EMaterias.values());
                    Ematerias.stream().sorted(Comparator.comparing(Enum::name)).forEach(m -> System.out.println("- " + m));
                    System.out.println("Por favor, insira o nome da materia");
                    var nomeMateria = input.nextLine();
//                    Materia.EMaterias materiaEnum = Materia.EMaterias.valueOf(nomeMateria.toUpperCase());
                    Optional<Materia> acharMateria = Arrays.stream(EMaterias.values())
                            .filter(e -> e.name().equalsIgnoreCase(nomeMateria))
                            .findFirst()
                            .map(materiaEnum -> new Materia(materiaEnum.name()));

                    if (acharMateria.isEmpty()) {
                        System.out.println("Materia não encontrada");
                        break;
                    }

                    Materia materia = acharMateria.get();

                    aluno.adicionarMateria(materia);
                    alunoRepo.save(aluno);
                    System.out.println("Materia cadastrada com sucesso");
                    break;
                }
                System.out.println("Matéria não encontrada");
                break;


//                if(turmas.size() == 0){
//                    System.out.println("Lista está vazia");
//                }else{
//                    for(int i = 0; i < turmas.size(); i++){
//                        System.out.println(i+1 + ". " + turmas.get(i));
//                    }
//                }
//                break;
//            case 2:
//                System.out.println("Insira o nome da Materia");
//                String nome = input.nextLine();
//                materias.add(new Materia(nome));

            case 3:
                List<EMaterias> Ematerias = List.of(EMaterias.values());
                Ematerias.stream().sorted(Comparator.comparing(Enum::name)).forEach(m -> System.out.println("- " + m));
                break;
//            case 4:
//                System.out.println("Insira o numero do aluno a ser removido");
//                int alunoRemove = input.nextInt();
//                materias.remove(alunoRemove-1);
//                for(int i = 0; i < materias.size(); i++){
//                    System.out.println(i+1 + ". " + materias.get(i));
//                }
//                break;
//            case 5:
//                break;
            default:
                System.out.println("Digite uma opção válida");

        }
    }


}