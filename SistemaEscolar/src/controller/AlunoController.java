package controller;

import database.Conexao;
import model.Aluno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoController {
    private Conexao bd;

    public AlunoController() {
        this.bd = new Conexao();
    }

    public void testarConexao() {
        this.bd.getConexao();
        System.out.println("Conectou");
    }

    public void salvar(Aluno aluno) throws SQLException {
        String sql;
        try {
            sql = "INSERT INTO aluno (nome, matricula, dt_nascimento) values (?, ?, ?)";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getDt_nascimento());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public ResultSet listar() {
        String sql;
        try {
            sql = "SELECT * FROM aluno";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sql;
        try {
            sql = "UPDATE aluno SET nome = ?, matricula = ?, dt_nascimento = ? WHERE id = ?";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);

            stmt.setInt(4, aluno.getId());
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getDt_nascimento());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql;
        try {
            sql = "DELETE FROM aluno WHERE id = ?";
            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public ResultSet buscar(Aluno aluno) throws SQLException {
        String sql = "";
        try {
            if (!aluno.getNome().isEmpty()) {
                sql = "SELECT * FROM aluno WHERE nome LIKE '%" + aluno.getNome() + "%'";

            } else if (!aluno.getMatricula().isEmpty()) {
                sql = "SELECT * FROM aluno WHERE matricula LIKE '%" + aluno.getMatricula() + "%'";

            } else if (!aluno.getDt_nascimento().isEmpty()) {
                sql = "SELECT * FROM aluno WHERE dt_nascimento LIKE '%" + aluno.getDt_nascimento() + "%'";
            }

            PreparedStatement stmt = this.bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
