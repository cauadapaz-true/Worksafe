package DAO;

import Connection.Conexao;
import Model.Aso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsoDAO {

    private final Connection connection;

    public AsoDAO() {
        this.connection = Conexao
                .getInstance()
                .getConexao();
    }

    public void inserir(Aso aso) {

        String sql = """
                INSERT INTO aso
                (data_emissao_aso, data_validade, tipo_aso,
                 resultado_aso, id_colaborador, id_medico)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(aso.getDataEmissao()));
            stmt.setDate(2, Date.valueOf(aso.getDataVencimento()));
            stmt.setString(3, aso.getTipoAso());
            stmt.setString(4, aso.getResultado());
            stmt.setLong(5, aso.getIdColaborador());
            stmt.setLong(6, aso.getIdMedico());

            stmt.executeUpdate();

            System.out.println("ASO inserido com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao inserir ASO: " + e.getMessage(), e);
        }
    }

    public void atualizar(Aso aso) {

        String sql = """
                UPDATE aso
                SET data_emissao_aso = ?,
                    data_validade = ?,
                    tipo_aso = ?,
                    resultado_aso = ?,
                    id_colaborador = ?,
                    id_medico = ?
                WHERE id_aso = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(aso.getDataEmissao()));
            stmt.setDate(2, Date.valueOf(aso.getDataVencimento()));
            stmt.setString(3, aso.getTipoAso());
            stmt.setString(4, aso.getResultado());
            stmt.setLong(5, aso.getIdColaborador());
            stmt.setLong(6, aso.getIdMedico());
            stmt.setLong(7, aso.getIdAso());

            stmt.executeUpdate();

            System.out.println("ASO atualizado com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao atualizar ASO: " + e.getMessage(), e);
        }
    }

    public void atualizarResultado(Long idAso, String resultado) {

        String sql = """
                UPDATE aso
                SET resultado_aso = ?
                WHERE id_aso = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, resultado);
            stmt.setLong(2, idAso);

            stmt.executeUpdate();

            System.out.println("Resultado atualizado com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao atualizar resultado: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {

        String sql = "DELETE FROM aso WHERE id_aso = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();

            System.out.println("ASO deletado com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao deletar ASO: " + e.getMessage(), e);
        }
    }

    public Aso buscarPorId(Long id) {

        String sql = "SELECT * FROM aso WHERE id_aso = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Aso aso = new Aso();

                    aso.setIdAso(rs.getLong("id_aso"));
                    aso.setDataEmissao(rs.getDate("data_emissao_aso").toLocalDate());
                    aso.setDataVencimento(rs.getDate("data_validade").toLocalDate());
                    aso.setTipoAso(rs.getString("tipo_aso"));
                    aso.setResultado(rs.getString("resultado_aso"));
                    aso.setIdColaborador(rs.getLong("id_colaborador"));
                    return aso;
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao buscar ASO: " + e.getMessage(), e);
        }

        return null;
    }

    //Uso JOIN
    public List<Aso> listarTodos() {

        List<Aso> lista = new ArrayList<>();

        String sql = """
            SELECT
                a.id_aso,
                a.tipo_aso,
                a.resultado_aso,
                a.data_emissao_aso,
                a.data_validade,
                c.nome AS nome_colaborador,
                m.nome_medico
            FROM aso a
            INNER JOIN colaborador c
                ON a.id_colaborador = c.id_colaborador
            INNER JOIN medico m
                ON a.id_medico = m.id_medico
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearAso(rs));
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao listar ASOs: " + e.getMessage(), e);
        }

        return lista;
    }

    private Aso mapearAso(ResultSet rs) throws SQLException {

        Aso aso = new Aso();

        aso.setIdAso(rs.getLong("id_aso"));
        aso.setDataEmissao(rs.getDate("data_emissao_aso").toLocalDate());
        aso.setDataVencimento(rs.getDate("data_validade").toLocalDate());
        aso.setTipoAso(rs.getString("tipo_aso"));
        aso.setResultado(rs.getString("resultado_aso"));
        aso.setNomeColaborador(rs.getString("nome_colaborador"));
        aso.setNomeMedico(rs.getString("nome_medico"));

        return aso;
    }
}