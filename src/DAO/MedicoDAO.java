package DAO;

import Connection.Conexao;
import Model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    private final Connection connection;
    public MedicoDAO() {
        this.connection =Conexao.getInstance().getConexao();
    }

    public List<Medico> listarTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdMedico(rs.getLong("id_medico"));
                medico.setNomeMedico(rs.getString("nome_medico"));
                medico.setCrmMedico(rs.getString("crm_medico"));
                medico.setEspecialidadeMedico(rs.getString("especialidade_medico"));
                lista.add(medico);
            }

        } catch (Exception e) {

            throw new RuntimeException("Erro ao listar médicos: " + e.getMessage());
        }

        return lista;
    }
}