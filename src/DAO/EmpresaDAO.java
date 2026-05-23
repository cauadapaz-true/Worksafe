package DAO;

import Connection.Conexao;
import Model.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    private final Connection connection;

    public EmpresaDAO() {

        this.connection =
                Conexao.getInstance()
                        .getConexao();
    }

    public List<Empresa> listarTodos() {

        List<Empresa> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM empresa";

        try (
                PreparedStatement stmt =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Empresa empresa =
                        new Empresa();

                empresa.setIdEmpresa(
                        rs.getLong("id_empresa"));

                empresa.setRazaoSocial(
                        rs.getString("razao_social"));

                empresa.setCnpj(
                        rs.getString("cnpj"));

                empresa.setSetor(
                        rs.getString("setor"));

                empresa.setCidade(
                        rs.getString("cidade"));

                lista.add(empresa);
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao listar empresas: "
                            + e.getMessage());
        }

        return lista;
    }
}