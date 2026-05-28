package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Conexao instance;
    private Connection connection;

    private static final String URL =
            "jdbc:postgresql://db.bvppxnzbndjmbysrygxt.supabase.co:5432/postgres?sslmode=require";

    private static final String USER = "postgres";
    private static final String PASSWORD = "projetobancodedados";

    private Conexao() {
        conectar();
    }

    public static synchronized Conexao getInstance() {
        if (instance == null) {
            instance = new Conexao();
        }
        return instance;
    }

    private void conectar() {
        try {

            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao conectar com banco: " + ex.getMessage(), ex);
        }
    }

    public Connection getConexao() {

        try {

            if (connection == null || connection.isClosed()) {
                conectar();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao validar conexão: " + ex.getMessage(), ex);
        }

        return connection;
    }

    public void fecharConexao() {

        try {

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar conexão: " + ex.getMessage(), ex);
        }
    }
}