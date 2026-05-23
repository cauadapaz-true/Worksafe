package Service;

import DAO.ColaboradorDAO;
import Model.Colaborador;

import java.util.List;

public class ColaboradorService {

    private final ColaboradorDAO colaboradorDAO;

    public ColaboradorService() {
        this.colaboradorDAO = new ColaboradorDAO();
    }

    public void inserir(Colaborador colaborador) {

        validarStatus(colaborador.getStatus());

        colaboradorDAO.inserir(colaborador);
    }

    public void atualizar(Colaborador colaborador) {

        validarStatus(colaborador.getStatus());

        colaboradorDAO.atualizar(colaborador);
    }

    public void atualizarStatus(String cpf, String status) {

        validarStatus(status);

        colaboradorDAO.atualizarStatus(cpf, status);
    }

    public void deletar(String cpf) {

        colaboradorDAO.deletar(cpf);
    }

    public Colaborador buscarPorCpf(String cpf) {

        return colaboradorDAO.buscarPorCpf(cpf);
    }

    public List<Colaborador> listarTodos() {

        return colaboradorDAO.listarTodos();
    }

    // RN-01
    private void validarStatus(String status) {

        if (status == null) {
            throw new IllegalArgumentException(
                    "Status não pode ser nulo.");
        }

        status = status.toLowerCase();

        if (!status.equals("ativo") && !status.equals("inativo")  && !status.equals("afastado")) {
            throw new IllegalArgumentException(
                    "Status inválido. "
                            + "Valores permitidos: "
                            + "ativo, inativo ou afastado.");
        }
    }
}