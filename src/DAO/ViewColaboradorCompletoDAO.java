package DAO;

import Connection.Conexao;
import Model.ViewColaboradorCompleto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class ViewColaboradorCompletoDAO {

    private final Connection connection;

    public ViewColaboradorCompletoDAO() {

        this.connection =
                Conexao.getInstance()
                        .getConexao();
    }

    public List<ViewColaboradorCompleto>
    listarTodos() {

        List<ViewColaboradorCompleto>
                lista = new ArrayList<>();

        String sql =
                "SELECT * FROM vw_colaborador_completo";

        try (

                PreparedStatement stmt =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()

        ) {

            while (rs.next()) {

                ViewColaboradorCompleto v =
                        new ViewColaboradorCompleto();

                v.setIdColaborador(
                        rs.getLong(
                                "id_colaborador"));

                v.setNome(
                        rs.getString(
                                "nome"));

                v.setCpf(
                        rs.getString(
                                "cpf"));

                v.setStatus(
                        rs.getString(
                                "status"));

                v.setRazaoSocial(
                        rs.getString(
                                "razao_social"));

                v.setNomeCargo(
                        rs.getString(
                                "nome_cargo"));

                lista.add(v);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao listar VIEW: "
                            + e.getMessage());
        }

        return lista;
    }
}