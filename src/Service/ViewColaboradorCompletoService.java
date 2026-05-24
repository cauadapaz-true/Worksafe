package Service;

import DAO.ViewColaboradorCompletoDAO;
import Model.ViewColaboradorCompleto;

import java.util.List;

public class ViewColaboradorCompletoService {

    private final
    ViewColaboradorCompletoDAO dao;

    public ViewColaboradorCompletoService() {

        this.dao =
                new ViewColaboradorCompletoDAO();
    }

    public List<ViewColaboradorCompleto>
    listarTodos() {

        return dao.listarTodos();
    }
}