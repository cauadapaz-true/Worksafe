package Model;

public class Cargo {

    private Long idCargo;
    private String nomeCargo;
    private String nivelRiscoCargo;
    private String descricaoCargo;

    public Cargo() {
    }

    public Cargo(Long idCargo, String nomeCargo, String nivelRiscoCargo, String descricaoCargo) {
        this.idCargo = idCargo;
        this.nomeCargo = nomeCargo;
        this.nivelRiscoCargo = nivelRiscoCargo;
        this.descricaoCargo = descricaoCargo;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public String getNivelRiscoCargo() {
        return nivelRiscoCargo;
    }

    public void setNivelRiscoCargo(String nivelRiscoCargo) {
        this.nivelRiscoCargo = nivelRiscoCargo;
    }

    public String getDescricaoCargo() {
        return descricaoCargo;
    }

    public void setDescricaoCargo(String descricaoCargo) {
        this.descricaoCargo = descricaoCargo;
    }
}