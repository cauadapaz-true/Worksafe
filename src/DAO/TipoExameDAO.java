package DAO;

import Connection.Conexao;
import Model.TipoExame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoExameDAO {

    private final Connection connection;

    public TipoExameDAO() {

        this.connection =
                Conexao.getInstance()
                        .getConexao();
    }

    public List<TipoExame> listarTodos() {

        List<TipoExame> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM tipo_exame";

        try (
                PreparedStatement stmt =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                TipoExame tipoExame =
                        new TipoExame();

                tipoExame.setIdTipoExame(
                        rs.getLong(
                                "id_tipo_exame"));

                tipoExame.setNomeExame(
                        rs.getString(
                                "nome_exame"));

                tipoExame.setObrigatorioRiscoTipoExame(
                        rs.getString(
                                "obrigatorio_risco_tipo_exame"));

                tipoExame.setDescricaoExame(
                        rs.getString(
                                "descricao_exame"));

                lista.add(tipoExame);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao listar tipos de exame: "
                            + e.getMessage());
        }

        return lista;
    }
}