package Model;

import java.time.LocalDate;

public class Aso {

    private Long idAso;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private String tipoAso;
    private String resultado;
    private Long idColaborador;
    private Long idMedico;
    private String observacao;
    private String nomeColaborador;
    private String nomeMedico;

    public Aso() {
    }

    public Aso(Long idAso, LocalDate dataEmissao,LocalDate dataVencimento, String tipoAso,
               String resultado,Long idColaborador, Long idMedico) {
        this.idAso = idAso;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.tipoAso = tipoAso;
        this.resultado = resultado;
        this.idColaborador = idColaborador;
        this.idMedico = idMedico;
    }

    public Long getIdAso() {
        return idAso;
    }

    public void setIdAso(Long idAso) {
        this.idAso = idAso;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getTipoAso() {
        return tipoAso;
    }

    public void setTipoAso(String tipoAso) {
        this.tipoAso = tipoAso;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

}