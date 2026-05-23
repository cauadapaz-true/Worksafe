package Model;

public class TipoExame {

    private Long idTipoExame;
    private String nomeExame;
    private String obrigatorioRiscoTipoExame;
    private String descricaoExame;

    public TipoExame() {
    }

    public Long getIdTipoExame() {
        return idTipoExame;
    }

    public void setIdTipoExame(Long idTipoExame) {
        this.idTipoExame = idTipoExame;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public String getObrigatorioRiscoTipoExame() {
        return obrigatorioRiscoTipoExame;
    }

    public void setObrigatorioRiscoTipoExame(
            String obrigatorioRiscoTipoExame) {

        this.obrigatorioRiscoTipoExame =
                obrigatorioRiscoTipoExame;
    }

    public String getDescricaoExame() {
        return descricaoExame;
    }

    public void setDescricaoExame(
            String descricaoExame) {

        this.descricaoExame =
                descricaoExame;
    }
}