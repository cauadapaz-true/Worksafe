package DAO;

import Connection.Conexao;
import Model.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    private final Connection connection;

    public CargoDAO() {

        this.connection =
                Conexao.getInstance()
                        .getConexao();
    }

    public List<Cargo> listarTodos() {

        List<Cargo> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM cargo";
        try (
                PreparedStatement stmt =connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getLong("id_cargo"));
                cargo.setNomeCargo(rs.getString("nome_cargo"));
                cargo.setNivelRiscoCargo(rs.getString("nivel_risco_cargo"));
                cargo.setDescricaoCargo(rs.getString("descricao_cargo"));
                lista.add(cargo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar cargos: " + e.getMessage());
        }
        return lista;
    }
}