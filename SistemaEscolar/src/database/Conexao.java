package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Connection conn;

    public Conexao() {
        try {
            System.out.println("Conectado!");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/db_aula_2021", "root", "");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Connection getConexao() {
        return this.conn;
    }
}
