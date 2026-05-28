package Model;

import java.time.LocalDate;

public class Colaborador {

    private Long idColaborador;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private LocalDate dataAdmissao;
    private String status;
    private Long idEmpresa;
    private Long idCargo;
    private String nomeEmpresa;
    private String nomeCargo;

    public Colaborador(Long idColaborador, String nome, String cpf) {

        this.idColaborador = idColaborador;
        this.nome = nome;
        this.cpf = cpf;

    }

    public Colaborador() {
    }

    public Colaborador(Long idColaborador, String nome, String cpf, LocalDate dataNascimento,
                       LocalDate dataAdmissao, String status,  Long idEmpresa) {

        this.idColaborador = idColaborador;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.dataAdmissao = dataAdmissao;
        this.status = status;
        this.idEmpresa = idEmpresa;
    }

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}

