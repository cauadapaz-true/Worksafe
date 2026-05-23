package Model;

public class Exame {

    private Long idExame;
    private String resultadoExame;
    private String valorReferenciaExame;
    private String observacaoExame;

    private Long idAso;
    private Long idTipoExame;

    public Exame() {
    }

    public Long getIdExame() {
        return idExame;
    }

    public void setIdExame(Long idExame) {
        this.idExame = idExame;
    }

    public String getResultadoExame() {
        return resultadoExame;
    }

    public void setResultadoExame(
            String resultadoExame) {

        this.resultadoExame =
                resultadoExame;
    }

    public String getValorReferenciaExame() {
        return valorReferenciaExame;
    }

    public void setValorReferenciaExame(
            String valorReferenciaExame) {

        this.valorReferenciaExame =
                valorReferenciaExame;
    }

    public String getObservacaoExame() {
        return observacaoExame;
    }

    public void setObservacaoExame(
            String observacaoExame) {

        this.observacaoExame =
                observacaoExame;
    }

    public Long getIdAso() {
        return idAso;
    }

    public void setIdAso(Long idAso) {
        this.idAso = idAso;
    }

    public Long getIdTipoExame() {
        return idTipoExame;
    }

    public void setIdTipoExame(Long idTipoExame) {
        this.idTipoExame = idTipoExame;
    }
}