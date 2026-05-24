package Service;

import DAO.AsoDAO;
import Model.Aso;

import java.util.List;

public class AsoService {

    private final AsoDAO asoDAO;

    public AsoService() {
        this.asoDAO = new AsoDAO();
    }

    public void inserir(Aso aso) {

        validarTipoAso(aso.getTipoAso());
        validarResultado(aso.getResultado());
        validarDatas(aso.getDataEmissao(), aso.getDataVencimento());

        asoDAO.inserir(aso);
    }

    public void atualizar(Aso aso) {

        validarTipoAso(aso.getTipoAso());
        validarResultado(aso.getResultado());
        validarDatas( aso.getDataEmissao(), aso.getDataVencimento());

        asoDAO.atualizar(aso);
    }

    public void atualizarResultado(Long idAso, String resultado) {

        validarResultado(resultado);

        asoDAO.atualizarResultado(idAso, resultado);
    }

    public void deletar(Long id) {

        asoDAO.deletar(id);
    }

    public Aso buscarPorId(Long id) {

        return asoDAO.buscarPorId(id);
    }

    public List<Aso> listarTodos() {

        return asoDAO.listarTodos();
    }

    //Function
    public String verificarStatusAso( String cpf) {
        return asoDAO.verificarStatusAso(cpf);
    }

    // RN-02
    private void validarDatas(java.time.LocalDate dataEmissao, java.time.LocalDate dataVencimento) {

        if (dataVencimento.isBefore(dataEmissao)) {

            throw new IllegalArgumentException(
                    "A data de vencimento "
                            + "não pode ser inferior "
                            + "à data de emissão.");
        }
    }

    // RN-03
    private void validarTipoAso(String tipoAso) {

        if (tipoAso == null || tipoAso.isBlank()) {

            throw new IllegalArgumentException(
                    "O tipo do ASO é obrigatório.");
        }
    }

    // RN-04
    private void validarResultado(String resultado) {

        if (resultado == null) {

            throw new IllegalArgumentException("Resultado não pode ser nulo.");
        }

        resultado = resultado.toUpperCase();

        if (!resultado.equals("APTO") && !resultado.equals("INAPTO")) {

            throw new IllegalArgumentException(
                    "Resultado inválido. "
                            + "Valores permitidos no tipo do ASO: "
                            + "APTO ou INAPTO.");
        }
    }
}