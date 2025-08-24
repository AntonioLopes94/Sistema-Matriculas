package Main;

import br.com.lopesantonio.dataBase.*;

import java.util.*;


public class Main {

    private final AlunoRepo alunoRepo;
    private final MateriaRepo materiaRepo;

    private final static Scanner input = new Scanner(System.in);

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
        List<Aluno> alunos = alunoRepo.findAll();
        System.out.println("----------//----------"
                + "\n 1. Adicionar aluno"
                + "\n 2. Verificar lista de alunos"
                + "\n 3. Verificar inscrições de um aluno"
                + "\n 4. Remover o aluno"
                + "\n 5. Voltar ao inicio"
                + "\n ----------//----------");
        opcaoAluno = input.nextInt();
        input.nextLine();
        switch (opcaoAluno) {
            case 1:
                System.out.println("Insira o nome do Aluno");
                var nomeAluno = input.nextLine();
                Aluno aluno = new Aluno(nomeAluno);
                alunoRepo.save(aluno);
                break;
            case 2:
                alunos.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Insira o nome do Aluno");
                var nomeAluno = input.nextLine();
                if (!alunoRepo.existsByNome(nomeAluno)){
                System.out.println("Aluno nao existe nos registros. Por favor, verifique o nome e tente novamente");
                break; }
                


            case 4:
                System.out.println("Insira o nome do aluno a ser removido");
                var alunoNome = input.nextLine();
                if(alunoRepo.existsByNome(alunoNome)){
                    alunoRepo.deleteByName(alunoNome);}
                else{
                    System.out.println("Nome do aluno não encontrado");
                }
                break;
            case 5:
                break;
            default:
                System.out.println("Digite uma opção válida");

        }
    }

    public void menuMateria() {
        int opcaoMateria;
        System.out.println("----------//----------"
                + "\n 1. Inscrever aluno em uma matéria"
                + "\n 2. Verificar turmas"
                + "\n 3. Verificar matérias disponíveis"
                + "\n 4. Remover matéria da grade de um aluno"
                + "\n 5. Voltar ao inicio"
                + "\n ----------//----------");
        opcaoMateria = input.nextInt();
        input.nextLine();

        switch (opcaoMateria) {
            case 1:
                List<Aluno> alunos = alunoRepo.findAll();

                if (alunos.isEmpty()) {
                    System.out.println("Sem alunos cadastrados");
                    break;
                }

                System.out.println("Por favor, insira o nome do Aluno");
                var nomeAluno = input.nextLine();
                if(alunoRepo.existsByNome(nomeAluno)) {
                    Aluno aluno = new Aluno(nomeAluno);
                    Arrays.stream(EMaterias.values()).toList().forEach(System.out::println);
                    System.out.println("Por favor, insira o nome da matéria");
                    var nomeMateria = input.nextLine();
                    if (nomeMateria.isEmpty()) {
                        System.out.println("Nada foi inserido. Por favor, insira o nome da matéria");
                        break;
                    }

//                    try{
//                        EMaterias emateria = EMaterias.valueOf(nomeMateria);
//                    }catch (Exception e) {
//                        System.out.println("Nome materia inválido: " + nomeMateria);
//                    }
//                    Materia materia = materiaRepo.findByName(nomeMateria);
//                    if(materia == null) {
//                        materia = new Materia(nomeMateria);
//                    }


                    EMaterias enumMateria = EMaterias.valueOf(nomeMateria);
                    materiaRepo.existsByMaterias(enumMateria);
                    Materia materias = new Materia(nomeMateria);
                    aluno.adicionarMateria(materias);
                    System.out.println(materias.getAlunos() + " " + aluno.getMaterias());
                    alunoRepo.save(aluno);
                    break;
                }
            case 2:
//                System.out.println("Insira o nome da Materia");
//                String nome = input.nextLine();
//                materias.add(new Materia(nome));

            case 3:
                Arrays.stream(EMaterias.values()).toList().forEach(System.out::println);
////            case 4:
//                System.out.println("Insira o numero do aluno a ser removido");
//                int alunoRemove = input.nextInt();
//                materias.remove(alunoRemove-1);
//                for(int i = 0; i < materias.size(); i++){
//                    System.out.println(i+1 + ". " + materias.get(i));
//                }
//                break;
//            case 5:
////                break;
//            default:
//                System.out.println("Digite uma opção válida");

        }
    }
}