package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Conexao instance;
    public final Connection conexao;
    private final String    url =
            "jdbc:postgresql://db.bvppxnzbndjmbysrygxt.supabase.co:5432/postgres?sslmode=require";
    private final String user = "postgres";
    private final String password = "projetobancodedados";

    private Conexao() {

        try {

            this.conexao =
                    DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao conectar: "+ ex.getMessage());
        }
    }

    public static Conexao getInstance() {
        if (instance == null) {
            instance = new Conexao();
        }
        return instance;
    }

    public Connection conexao() {
        return conexao;
    }
}