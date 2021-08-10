package bbb;

import java.util.List;

public class QueriesExecution {

    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        List<Aluno> alunos = alunoDAO.list();

        alunos.stream().forEach(System.out::println);

        Aluno alunoParaConsulta = alunoDAO.getById(1);

        Aluno alunoParaInsercao = new Aluno(
                "Matheus",
                43,
                "SP"
        );

        //alunoDAO.create(alunoParaInsercao);

        //alunoDAO.delete(1);


        Aluno alunoParaAtualizar = alunoDAO.getById(3);
        alunoParaAtualizar.setNome("Joaquim");
        alunoParaAtualizar.setIdade(18);
        alunoParaAtualizar.setEstado("RS");
    }
}
