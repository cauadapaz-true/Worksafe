package Service;

import DAO.ExameDAO;
import Model.Exame;

import java.util.List;

public class ExameService {

    private final ExameDAO exameDAO;

    public ExameService() {

        this.exameDAO =
                new ExameDAO();
    }

    public List<Exame> listarPorAso(Long idAso) {

        return exameDAO.listarPorAso(idAso);
    }
}