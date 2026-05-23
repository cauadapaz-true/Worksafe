package DAO;

import Model.Colaborador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {

    private final Connection connection;

    public ColaboradorDAO(Connection connection) {
        this.connection = connection;
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

            System.out.println("Colaborador inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void atualizarStatus(Colaborador colaborador) {

        String sql = """
                UPDATE colaborador
                SET status = ?,
                WHERE cpf = ?,
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {


            stmt.setString(1, colaborador.getStatus());
            stmt.setString(2, colaborador.getCpf());

            stmt.executeUpdate();

            System.out.println("Colaborador atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(String cpf) {

        String sql = "DELETE FROM colaborador WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,cpf);

            stmt.executeUpdate();

            System.out.println("Colaborador deletado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Colaborador buscarPorCpf(String cpf) {

        String sql = "SELECT * FROM colaborador WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Colaborador colaborador = new Colaborador();

                colaborador.setIdColaborador(rs.getLong("id_colaborador"));
                colaborador.setNome(rs.getString("nome"));
                colaborador.setCpf(rs.getString("cpf"));
                colaborador.setDataNascimento(
                        rs.getDate("data_nascimento").toLocalDate());
                colaborador.setDataAdmissao(
                        rs.getDate("data_admissao").toLocalDate());
                colaborador.setStatus(rs.getString("status"));
                colaborador.setIdEmpresa(rs.getLong("id_empresa"));
                colaborador.setIdCargo(rs.getLong("id_cargo"));

                return colaborador;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Colaborador> listarTodos() {

        List<Colaborador> lista = new ArrayList<>();

        String sql = "SELECT id_colaborador, nome, cpf FROM colaborador";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Colaborador colaborador = new Colaborador();

                colaborador.setIdColaborador(rs.getLong("id_colaborador"));
                colaborador.setNome(rs.getString("nome"));
                colaborador.setCpf(rs.getString("cpf"));

                lista.add(colaborador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}