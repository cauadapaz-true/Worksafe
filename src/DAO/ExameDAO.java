package DAO;

import Connection.Conexao;
import Model.Exame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO {

    private final Connection connection;

    public ExameDAO() {

        this.connection =
                Conexao.getInstance()
                        .getConexao();
    }

    //Uso JOIN
    public List<Exame> listarPorAso(Long idAso) {

        List<Exame> lista =
                new ArrayList<>();

        String sql = """
            SELECT
                e.id_exame,
                e.resultado_exame,
                e.valor_referencia_exame,
                te.nome_exame,
                a.id_aso,
                a.tipo_aso,
                c.id_colaborador,
                c.nome AS nome_colaborador
            FROM exame e
            INNER JOIN tipo_exame te
                ON e.id_tipo_exame = te.id_tipo_exame
            INNER JOIN aso a
                ON e.id_aso = a.id_aso
            INNER JOIN colaborador c
                ON a.id_colaborador = c.id_colaborador
            WHERE a.id_aso = ?
            """;

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {

            stmt.setLong(1, idAso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Exame exame = new Exame();

                exame.setIdExame(rs.getLong("id_exame"));
                exame.setResultadoExame(rs.getString("resultado_exame"));
                exame.setValorReferenciaExame(rs.getString("valor_referencia_exame"));
                exame.setNomeExame(rs.getString("nome_exame"));
                exame.setIdAso(rs.getLong("id_aso"));
                exame.setTipoAso(rs.getString("tipo_aso"));
                exame.setIdColaborador(rs.getLong("id_colaborador"));
                exame.setNomeColaborador(rs.getString("nome_colaborador"));
                lista.add(exame);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar exames do ASO: " + e.getMessage());
        }

        return lista;
    }
}