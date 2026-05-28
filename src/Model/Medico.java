package Model;

public class Medico {

    private Long idMedico;
    private String nomeMedico;
    private String crmMedico;
    private String especialidadeMedico;

    public Medico() {
    }

    public Medico( Long idMedico, String nomeMedico, String crmMedico, String especialidadeMedico) {
        this.idMedico = idMedico;
        this.nomeMedico = nomeMedico;
        this.crmMedico = crmMedico;
        this.especialidadeMedico = especialidadeMedico;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getEspecialidadeMedico() {
        return especialidadeMedico;
    }

    public void setEspecialidadeMedico(
            String especialidadeMedico) {

        this.especialidadeMedico =
                especialidadeMedico;
    }
}