package bbb;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> list() {

    List<Aluno> alunos = new ArrayList<>();


    try(Connection conn = ConnectionFactory.getConnection()) {
        String sql = "SELECT * FROM aluno";

        PreparedStatement prst =  conn.prepareStatement(sql);

        ResultSet rs = prst.executeQuery();

       while(rs.next()) {

           int id = rs.getInt("id");
           String nome = rs.getString("nome");
           int idade = rs.getInt("idade");
           String estado = rs.getString("estado");

           alunos.add(new Aluno(
                   id,
                   nome,
                   idade,
                   estado
           ));
       }

    }catch (SQLException e) {
        System.out.println("Listagem de alunos FALHOU!");
        e.printStackTrace();
    }

    return alunos;

    }

    public Aluno getById(int id) {

        Aluno aluno = new Aluno();

        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM aluno WHERE id = ?";

            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();

                if (rs.next()) {
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setIdade(rs.getInt("idade"));
                    aluno.setEstado(rs.getString("estado"));
                }
            } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }

        return aluno;

    }

    public void create(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?)";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1 , aluno.getNome());
            prst.setInt(2, aluno.getIdade());
            prst.setString(3 , aluno.getEstado());

            int rowsAffected = prst.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA!. Foi adicionada" + rowsAffected + "linha");

        } catch (SQLException e) {
            System.out.println("Inserção FALHOU!");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(Connection conn = ConnectionFactory.getConnection()) {

            String sql = "DELETE FROM aluno WHERE id = ?";

            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1 , id);

            int rowsAffected = prst.executeUpdate();

            System.out.println("Delete BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Delete FALHOU!");
            e.printStackTrace();
        }
    }

    public void update(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";

            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, aluno.getNome());
            prst.setInt(2, aluno.getIdade());
            prst.setString(3, aluno.getEstado());
            prst.setInt(4, aluno.getId());

            //Executa atualização e armazena o numero de linhas afetadas
            int rowsAffected = prst.executeUpdate();

            System.out.println("Atualização BEM SUCEDIDA! Foi atualizada: " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }
    }
}

