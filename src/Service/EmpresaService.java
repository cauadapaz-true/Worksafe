package Service;

import DAO.EmpresaDAO;
import Model.Empresa;

import java.util.List;

public class EmpresaService {

    private final EmpresaDAO empresaDAO;

    public EmpresaService() {

        this.empresaDAO =
                new EmpresaDAO();
    }

    public List<Empresa> listarTodos() {

        return empresaDAO.listarTodos();
    }
}