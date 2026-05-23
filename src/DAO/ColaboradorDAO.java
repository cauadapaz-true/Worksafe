package DAO;

import Connection.Conexao;
import Model.Colaborador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {

    private final Connection connection;

    public ColaboradorDAO() {
        this.connection = Conexao
                .getInstance()
                .getConexao();
    }

    public void inserir(Colaborador colaborador) {

        String sql = """
                INSERT INTO colaborador
                (nome, cpf, data_nascimento, data_admissao,
                 status, id_empresa, id_cargo)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, colaborador.getNome());
            stmt.setString(2, colaborador.getCpf());
            stmt.setDate(3, Date.valueOf(colaborador.getDataNascimento()));
            stmt.setDate(4, Date.valueOf(colaborador.getDataAdmissao()));
            stmt.setString(5, colaborador.getStatus());
            stmt.setLong(6, colaborador.getIdEmpresa());
            stmt.setLong(7, colaborador.getIdCargo());

            stmt.executeUpdate();

            System.out.println(
                    "Colaborador inserido com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao inserir colaborador: " + e.getMessage(), e);
        }
    }

    public void atualizar(Colaborador colaborador) {

        String sql = """
                UPDATE colaborador
                SET nome = ?,
                    cpf = ?,
                    data_nascimento = ?,
                    data_admissao = ?,
                    status = ?,
                    id_empresa = ?,
                    id_cargo = ?
                WHERE id_colaborador = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, colaborador.getNome());
            stmt.setString(2, colaborador.getCpf());
            stmt.setDate(3, Date.valueOf(colaborador.getDataNascimento()));
            stmt.setDate(4, Date.valueOf(colaborador.getDataAdmissao()));
            stmt.setString(5, colaborador.getStatus());
            stmt.setLong(6, colaborador.getIdEmpresa());
            stmt.setLong(7, colaborador.getIdCargo());
            stmt.setLong(8, colaborador.getIdColaborador());

            stmt.executeUpdate();

            System.out.println("Colaborador atualizado com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao atualizar colaborador: "
                            + e.getMessage(), e);
        }
    }

    public void atualizarStatus(String cpf, String status) {

        String sql = """
                UPDATE colaborador
                SET status = ?
                WHERE cpf = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
            System.out.println("Status atualizado com sucesso!");

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao atualizar status: " + e.getMessage(), e);
        }
    }

    public void deletar(String cpf) {

        String sql =
                "DELETE FROM colaborador WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            stmt.executeUpdate();

            System.out.println("Colaborador deletado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar colaborador: "+ e.getMessage(), e);
        }
    }

    public Colaborador buscarPorCpf(String cpf) {

        String sql =
                "SELECT * FROM colaborador WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearColaborador(rs);
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao buscar colaborador: " + e.getMessage(), e);
        }

        return null;
    }

    public List<Colaborador> listarTodos() {

        List<Colaborador> lista = new ArrayList<>();

        String sql = """
            SELECT
                c.id_colaborador,
                c.nome,
                c.cpf,
                c.status,
                e.razao_social,
                ca.nome_cargo
            FROM colaborador c
            INNER JOIN empresa e
                ON c.id_empresa = e.id_empresa
            INNER JOIN cargo ca
                ON c.id_cargo = ca.id_cargo
            """;

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Colaborador colaborador = new Colaborador();

                colaborador.setIdColaborador(rs.getLong("id_colaborador"));

                colaborador.setNome(rs.getString("nome"));

                colaborador.setCpf(rs.getString("cpf"));

                colaborador.setStatus(rs.getString("status"));

                colaborador.setNomeEmpresa(rs.getString("razao_social"));

                colaborador.setNomeCargo(rs.getString("nome_cargo"));

                lista.add(colaborador);
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao listar colaboradores: "
                            + e.getMessage());
        }

        return lista;
    }

    private Colaborador mapearColaborador(ResultSet rs) throws SQLException {

        Colaborador colaborador = new Colaborador();

        colaborador.setIdColaborador(rs.getLong("id_colaborador"));
        colaborador.setNome(rs.getString("nome"));
        colaborador.setCpf(rs.getString("cpf"));
        colaborador.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        colaborador.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
        colaborador.setStatus(rs.getString("status"));
        colaborador.setIdEmpresa(rs.getLong("id_empresa"));
        colaborador.setIdCargo(rs.getLong("id_cargo"));
        return colaborador;
    }
}