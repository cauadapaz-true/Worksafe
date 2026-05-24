package Sistema;

import Model.Aso;
import Model.Cargo;
import Model.Colaborador;
import Model.Empresa;
import Model.Exame;
import Model.Medico;
import Model.ViewColaboradorCompleto;

import Service.AsoService;
import Service.CargoService;
import Service.ColaboradorService;
import Service.EmpresaService;
import Service.ExameService;
import Service.MedicoService;
import Service.ViewColaboradorCompletoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ColaboradorService colaboradorService = new ColaboradorService();
        AsoService asoService = new AsoService();
        EmpresaService empresaService = new EmpresaService();
        CargoService cargoService = new CargoService();
        MedicoService medicoService = new MedicoService();
        ExameService exameService = new ExameService();
        ViewColaboradorCompletoService viewService = new ViewColaboradorCompletoService();

        int opcao = -1;
        while (opcao != 0) {

            System.out.println("\n=================================");
            System.out.println("    WorkSafe - Gestão de ASO       ");
            System.out.println("===================================");

            System.out.println("1 - Inserir colaborador");
            System.out.println("2 - Listar colaboradores");
            System.out.println("3 - Buscar colaborador por CPF");
            System.out.println("4 - Atualizar status");
            System.out.println("5 - Deletar colaborador");
            System.out.println("6 - Relatório completo colaboradores");
            System.out.println();

            System.out.println("7 - Inserir ASO");
            System.out.println("8 - Listar ASOs");
            System.out.println("9 - Buscar ASO por ID");
            System.out.println("10 - Atualizar resultado ASO");
            System.out.println("11 - Deletar ASO");
            System.out.println("12 - Verificar status ASO");
            System.out.println();

            System.out.println("13 - Listar exames");
            System.out.println();

            System.out.println("0 - Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            try {
                if (opcao == 1) {
                    Colaborador colaborador = new Colaborador();
                    System.out.println("\n===== CADASTRO COLABORADOR =====");
                    System.out.print("Nome: ");
                    colaborador.setNome(scanner.nextLine());
                    System.out.print("CPF: ");
                    colaborador.setCpf(scanner.nextLine());
                    System.out.print("Data nascimento (AAAA-MM-DD): ");
                    colaborador.setDataNascimento(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Data admissão (AAAA-MM-DD): ");
                    colaborador.setDataAdmissao(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Status (ativo/inativo/afastado): ");
                    colaborador.setStatus(scanner.nextLine());

                    System.out.println("\n===== EMPRESAS =====");
                    List<Empresa> empresas = empresaService.listarTodos();
                    for (Empresa empresa : empresas) {
                        System.out.println(empresa.getIdEmpresa() + " - " + empresa.getRazaoSocial());
                    }

                    System.out.print("\nEscolha o ID da empresa: ");
                    colaborador.setIdEmpresa(scanner.nextLong());

                    System.out.println("\n===== CARGOS =====");

                    List<Cargo> cargos = cargoService.listarTodos();
                    for (Cargo cargo : cargos) {

                        System.out.println(cargo.getIdCargo() + " - " + cargo.getNomeCargo());
                    }

                    System.out.print("\nEscolha o ID do cargo: ");

                    colaborador.setIdCargo(scanner.nextLong());
                    scanner.nextLine();

                    colaboradorService.inserir(colaborador);
                    System.out.println("\nColaborador cadastrado!");
                }

                if (opcao == 2) {
                    List<Colaborador> lista = colaboradorService.listarTodos();
                    System.out.println("\n===== COLABORADORES =====");
                    for (Colaborador c : lista) {

                        System.out.println(
                                "\nID: "
                                        + c.getIdColaborador());

                        System.out.println(
                                "Nome: "
                                        + c.getNome());

                        System.out.println(
                                "CPF: "
                                        + c.getCpf());

                        System.out.println(
                                "Status: "
                                        + c.getStatus());

                    }
                }

                if (opcao == 3) {

                    System.out.println("\n===== BUSCAR COLABORADOR =====");

                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();
                    Colaborador colaborador = colaboradorService.buscarPorCpf(cpf);
                    if (colaborador != null) {
                        System.out.println(
                                "\nID: "
                                        + colaborador.getIdColaborador());

                        System.out.println(
                                "Nome: "
                                        + colaborador.getNome());

                        System.out.println(
                                "CPF: "
                                        + colaborador.getCpf());

                        System.out.println(
                                "Status: "
                                        + colaborador.getStatus());

                    } else {

                        System.out.println("\nColaborador não encontrado.");
                    }
                }

                if (opcao == 4) {
                    System.out.println("\n===== ATUALIZAR STATUS =====");
                    System.out.print("CPF do colaborador: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Novo status: ");
                    String status = scanner.nextLine();
                    colaboradorService.atualizarStatus(cpf, status);
                    System.out.println("\nStatus atualizado!");
                }

                if (opcao == 5) {
                    System.out.println("\n===== DELETAR COLABORADOR =====");
                    System.out.print("CPF do colaborador: ");
                    String cpf = scanner.nextLine();
                    colaboradorService.deletar(cpf);
                    System.out.println("\nColaborador deletado!");
                }

                if (opcao == 6) {

                    List<ViewColaboradorCompleto> lista = viewService.listarTodos();

                    System.out.println("\n===== RELATÓRIO =====");

                    for (ViewColaboradorCompleto v: lista) {

                        System.out.println("\nID: " + v.getIdColaborador());

                        System.out.println("Nome: " + v.getNome());

                        System.out.println("CPF: " + v.getCpf());

                        System.out.println("Status: " + v.getStatus());

                        System.out.println("Empresa: " + v.getRazaoSocial());

                        System.out.println("Cargo: " + v.getNomeCargo());
                    }
                }

                if (opcao == 7) {
                    Aso aso = new Aso();
                    System.out.println("\n===== CADASTRO ASO =====");
                    System.out.print("Data emissão (AAAA-MM-DD): ");
                    aso.setDataEmissao(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Data vencimento (AAAA-MM-DD): ");
                    aso.setDataVencimento(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Tipo ASO: ");
                    aso.setTipoAso(scanner.nextLine());
                    System.out.print("Resultado (APTO/INAPTO): ");
                    aso.setResultado(scanner.nextLine());

                    System.out.println("\n===== COLABORADORES =====");

                    List<Colaborador> colaboradores = colaboradorService.listarTodos();

                    for (Colaborador c : colaboradores) {
                        System.out.println(c.getIdColaborador() + " - " + c.getNome());
                    }

                    System.out.print("\nEscolha o ID do colaborador: ");
                    aso.setIdColaborador(scanner.nextLong());

                    System.out.println("\n===== MÉDICOS =====");

                    List<Medico> medicos = medicoService.listarTodos();

                    for (Medico medico : medicos) {
                        System.out.println(medico.getIdMedico() + " - " + medico.getNomeMedico());
                    }

                    System.out.print("\nEscolha o ID do médico: ");
                    aso.setIdMedico(scanner.nextLong());
                    scanner.nextLine();

                    asoService.inserir(aso);

                    System.out.println("\nASO cadastrado!");
                }

                if (opcao == 8) {

                    List<Aso> lista = asoService.listarTodos();
                    System.out.println("\n===== ASOS =====");
                    for (Aso a : lista) {

                        System.out.println(
                                "\nID ASO: "
                                        + a.getIdAso());

                        System.out.println(
                                "Tipo: "
                                        + a.getTipoAso());

                        System.out.println(
                                "Resultado: "
                                        + a.getResultado());

                        System.out.println(
                                "Colaborador: "
                                        + a.getNomeColaborador());

                        System.out.println(
                                "Médico: "
                                        + a.getNomeMedico());
                    }
                }

                if (opcao == 9) {

                    System.out.println("\n===== BUSCAR ASO =====");

                    System.out.print("Digite o ID do ASO: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    Aso aso = asoService.buscarPorId(id);
                    if (aso != null) {

                        System.out.println(
                                "\nID ASO: "
                                        + aso.getIdAso());

                        System.out.println(
                                "Tipo: "
                                        + aso.getTipoAso());

                        System.out.println(
                                "Resultado: "
                                        + aso.getResultado());

                    } else {

                        System.out.println(
                                "\nASO não encontrado.");
                    }
                }

                if (opcao == 10) {
                    System.out.println("\n===== ATUALIZAR RESULTADO =====");

                    System.out.print("ID ASO: ");
                    Long idAso = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Novo resultado: ");
                    String resultado = scanner.nextLine();

                    asoService.atualizarResultado(
                            idAso,
                            resultado);

                    System.out.println("\nResultado atualizado!");
                }


                if (opcao == 11) {
                    System.out.println("\n===== DELETAR ASO =====");

                    System.out.print("ID ASO: ");

                    Long id = scanner.nextLong();
                    asoService.deletar(id);

                    System.out.println("\nASO deletado!");
                }

                if (opcao == 12) {

                    System.out.println("\n===== STATUS ASO =====");

                    System.out.print("CPF do colaborador: ");

                    String cpf = scanner.nextLine();

                    String status = asoService.verificarStatusAso(cpf);

                    System.out.println( "\nStatus do ASO: " + status);
                }

                if (opcao == 13) {

                    System.out.println("\n===== LISTAR EXAMES DO ASO =====");

                    System.out.print("Digite o ID do ASO: ");
                    Long idAso = scanner.nextLong();
                    scanner.nextLine();
                    List<Exame> lista =  exameService.listarPorAso(idAso);

                    if (lista.isEmpty()) {

                        System.out.println("\nNenhum exame encontrado.");

                    } else {

                        for (Exame e : lista) {

                            System.out.println(
                                    "\nID Exame: "
                                            + e.getIdExame());

                            System.out.println(
                                    "Exame: "
                                            + e.getNomeExame());

                            System.out.println(
                                    "Resultado: "
                                            + e.getResultadoExame());

                            System.out.println(
                                    "Valor Referência: "
                                            + e.getValorReferenciaExame());

                            System.out.println(
                                    "ID ASO: "
                                            + e.getIdAso());

                            System.out.println(
                                    "Tipo ASO: "
                                            + e.getTipoAso());

                            System.out.println(
                                    "ID Colaborador: "
                                            + e.getIdColaborador());

                            System.out.println(
                                    "Colaborador: "
                                            + e.getNomeColaborador());
                        }
                    }
                }


                if (opcao == 0) {

                    System.out.println("\nSistema encerrado.");
                }

            } catch (Exception e) {

                System.out.println("\nERRO: " + e.getMessage());
            }
        }
        scanner.close();
    }
}