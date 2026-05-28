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

    private static final String ID = "ID: ";
    private static final String NOME = "Nome: ";
    private static final String CPF = "CPF: ";
    private static final String STATUS = "Status: ";
    private static final String RESULTADO = "Resultado: ";
    private static final String ID_ASO = "ID ASO: ";
    private static final String CPF_COLABORADOR = "CPF do colaborador: ";

    private static final ColaboradorService colaboradorService = new ColaboradorService();
    private static final AsoService asoService = new AsoService();
    private static final EmpresaService empresaService = new EmpresaService();
    private static final CargoService cargoService = new CargoService();
    private static final MedicoService medicoService = new MedicoService();
    private static final ExameService exameService = new ExameService();
    private static final ViewColaboradorCompletoService viewService =  new ViewColaboradorCompletoService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal = -1;

        while (opcaoPrincipal != 0) {

            System.out.println("\n=================================");
            System.out.println("    WorkSafe - Menu Principal");
            System.out.println("=================================");
            System.out.println("1 - Módulo de Colaboradores");
            System.out.println("2 - Módulo de ASOs");
            System.out.println("3 - Módulo de Exames");
            System.out.println("0 - Sair do Sistema");
            System.out.print("\nEscolha um módulo: ");

            opcaoPrincipal = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcaoPrincipal) {
                    case 1 -> menuColaboradores(scanner);
                    case 2 -> menuAsos(scanner);
                    case 3 -> menuExames(scanner);
                    case 0 -> System.out.println("\nSistema encerrado.");
                    default -> System.out.println("\nOpção inválida.");
                }
            } catch (Exception e) {
                System.out.println("\nERRO: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void menuColaboradores(Scanner scanner) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n--- Módulo de Colaboradores ---");

            System.out.println("1 - Inserir colaborador");
            System.out.println("2 - Listar colaboradores");
            System.out.println("3 - Buscar colaborador por CPF");
            System.out.println("4 - Atualizar status");
            System.out.println("5 - Deletar colaborador");
            System.out.println("6 - Relatório completo");
            System.out.println("7 - Inativar colaboradores com ASO vencido");
            System.out.println("0 - Voltar ao Menu Principal");

            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {

                    case 1 -> inserirColaborador(scanner);

                    case 2 -> listarColaboradores();

                    case 3 -> buscarColaborador(scanner);

                    case 4 -> atualizarStatus(scanner);

                    case 5 -> deletarColaborador(scanner);

                    case 6 -> relatorioCompleto();

                    case 7 -> inativarColaboradoresAsoVencido();

                    case 0 ->
                            System.out.println(
                                    "\nVoltando ao Menu Principal..."
                            );

                    default ->
                            System.out.println(
                                    "\nOpção inválida."
                            );
                }

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "\nErro de validação: "
                                + e.getMessage());

            } catch (RuntimeException e) {

                System.out.println(
                        "\nErro de execução: "
                                + e.getMessage());

            } catch (Exception e) {

                System.out.println(
                        "\nErro inesperado: "
                                + e.getMessage());
            }
        }
    }

    private static void menuAsos(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Módulo de ASOs ---");
            System.out.println("1 - Inserir ASO");
            System.out.println("2 - Listar ASOs");
            System.out.println("3 - Buscar ASO por ID");
            System.out.println("4 - Atualizar resultado ASO");
            System.out.println("5 - Deletar ASO");
            System.out.println("6 - Verificar status ASO");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirAso(scanner);
                case 2 -> listarAsos();
                case 3 -> buscarAso(scanner);
                case 4 -> atualizarResultado(scanner);
                case 5 -> deletarAso(scanner);
                case 6 -> verificarStatusAso(scanner);
                case 0 -> System.out.println("\nVoltando ao Menu Principal...");
                default -> System.out.println("\nOpção inválida.");
            }
        }
    }

    private static void menuExames(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Módulo de Exames ---");
            System.out.println("1 - Listar exames de um ASO");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarExames(scanner);
                case 0 -> System.out.println("\nVoltando ao Menu Principal...");
                default -> System.out.println("\nOpção inválida.");
            }
        }
    }

    private static void inserirColaborador(Scanner scanner) {

        Colaborador colaborador = new Colaborador();

        System.out.println("\n===== CADASTRO COLABORADOR =====");

        System.out.print(NOME);
        colaborador.setNome(scanner.nextLine());

        System.out.print(CPF);
        colaborador.setCpf(scanner.nextLine());

        System.out.print("Data nascimento (AAAA-MM-DD): ");
        colaborador.setDataNascimento(LocalDate.parse(scanner.nextLine()));

        System.out.print("Data admissão (AAAA-MM-DD): ");
        colaborador.setDataAdmissao(LocalDate.parse(scanner.nextLine()));

        System.out.print("Status (ativo/inativo/afastado): ");
        colaborador.setStatus(scanner.nextLine());

        selecionarEmpresa(scanner, colaborador);

        selecionarCargo(scanner, colaborador);

        colaboradorService.inserir(colaborador);

        System.out.println("\nColaborador cadastrado!");
    }

    private static void inativarColaboradoresAsoVencido() {

        System.out.println(
                "\n===== ROTINA ASO VENCIDO ====="
        );

        colaboradorService.inativarColaboradoresComAsoVencido();

        System.out.println("\nColaboradores com ASO vencido " + "foram inativados com sucesso!"
        );
    }

    private static void selecionarEmpresa(Scanner scanner,Colaborador colaborador) {

        System.out.println("\n===== EMPRESAS =====");

        List<Empresa> empresas = empresaService.listarTodos();

        for (Empresa empresa : empresas) {

            System.out.println(
                    empresa.getIdEmpresa()
                            + " - "
                            + empresa.getRazaoSocial());
        }

        System.out.print("\nEscolha o ID da empresa: ");

        colaborador.setIdEmpresa(scanner.nextLong());
    }

    private static void selecionarCargo(Scanner scanner, Colaborador colaborador) {

        System.out.println("\n===== CARGOS =====");

        List<Cargo> cargos = cargoService.listarTodos();

        for (Cargo cargo : cargos) {

            System.out.println(
                    cargo.getIdCargo()
                            + " - "
                            + cargo.getNomeCargo());
        }

        System.out.print("\nEscolha o ID do cargo: ");

        colaborador.setIdCargo(scanner.nextLong());

        scanner.nextLine();
    }

    private static void listarColaboradores() {

        List<Colaborador> lista = colaboradorService.listarTodos();

        System.out.println("\n===== COLABORADORES =====");

        for (Colaborador c : lista) {

            exibirColaborador(c);
        }
    }

    private static void buscarColaborador(Scanner scanner) {

        System.out.println("\n===== BUSCAR COLABORADOR =====");

        System.out.print("Digite o CPF: ");

        String cpf = scanner.nextLine();

        Colaborador colaborador = colaboradorService.buscarPorCpf(cpf);

        if (colaborador != null) {

            exibirColaborador(colaborador);

        } else {

            System.out.println("\nColaborador não encontrado.");
        }
    }

    private static void exibirColaborador(Colaborador colaborador) {

        System.out.println("\n" + ID + colaborador.getIdColaborador());
        System.out.println(NOME + colaborador.getNome());
        System.out.println(CPF + colaborador.getCpf());
        System.out.println(STATUS + colaborador.getStatus());
    }

    private static void atualizarStatus(Scanner scanner) {

        System.out.println("\n===== ATUALIZAR STATUS =====");

        System.out.print(CPF_COLABORADOR);

        String cpf = scanner.nextLine();

        System.out.print("Novo status: ");

        String status = scanner.nextLine();

        colaboradorService.atualizarStatus(cpf, status);

        System.out.println("\nStatus atualizado!");
    }

    private static void deletarColaborador(Scanner scanner) {

        System.out.println("\n===== DELETAR COLABORADOR =====");

        System.out.print(CPF_COLABORADOR);

        String cpf = scanner.nextLine();

        colaboradorService.deletar(cpf);

        System.out.println("\nColaborador deletado!");
    }

    private static void relatorioCompleto() {

        List<ViewColaboradorCompleto> lista = viewService.listarTodos();

        System.out.println("\n===== RELATÓRIO =====");

        for (ViewColaboradorCompleto v : lista) {

            System.out.println("\n" + ID + v.getIdColaborador());
            System.out.println(NOME + v.getNome());
            System.out.println(CPF + v.getCpf());
            System.out.println(STATUS + v.getStatus());
            System.out.println("Empresa: " + v.getRazaoSocial());
            System.out.println("Cargo: " + v.getNomeCargo());
        }
    }

    private static void inserirAso(Scanner scanner) {

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

        selecionarColaborador(scanner, aso);

        selecionarMedico(scanner, aso);

        asoService.inserir(aso);

        System.out.println("\nASO cadastrado!");
    }

    private static void selecionarColaborador(Scanner scanner, Aso aso) {

        System.out.println("\n===== COLABORADORES =====");

        List<Colaborador> colaboradores =
                colaboradorService.listarTodos();

        for (Colaborador c : colaboradores) {

            System.out.println(
                    c.getIdColaborador()
                            + " - "
                            + c.getNome());
        }

        System.out.print("\nEscolha o ID do colaborador: ");

        aso.setIdColaborador(scanner.nextLong());
    }

    private static void selecionarMedico(Scanner scanner, Aso aso) {

        System.out.println("\n===== MÉDICOS =====");

        List<Medico> medicos = medicoService.listarTodos();

        for (Medico medico : medicos) {

            System.out.println(
                    medico.getIdMedico()
                            + " - "
                            + medico.getNomeMedico());
        }

        System.out.print("\nEscolha o ID do médico: ");

        aso.setIdMedico(scanner.nextLong());

        scanner.nextLine();
    }

    private static void listarAsos() {

        List<Aso> lista = asoService.listarTodos();

        System.out.println("\n===== ASOS =====");

        for (Aso aso : lista) {

            exibirAso(aso);
        }
    }

    private static void buscarAso(Scanner scanner) {

        System.out.println("\n===== BUSCAR ASO =====");

        System.out.print("Digite o ID do ASO: ");

        Long id = scanner.nextLong();

        scanner.nextLine();

        Aso aso = asoService.buscarPorId(id);

        if (aso != null) {

            exibirAso(aso);

        } else {

            System.out.println("\nASO não encontrado.");
        }
    }

    private static void exibirAso(Aso aso) {

        System.out.println("\n" + ID_ASO + aso.getIdAso());
        System.out.println("Tipo: " + aso.getTipoAso());
        System.out.println(RESULTADO + aso.getResultado());

        if (aso.getNomeColaborador() != null) {

            System.out.println(
                    "Colaborador: "
                            + aso.getNomeColaborador());
        }

        if (aso.getNomeMedico() != null) {

            System.out.println(
                    "Médico: "
                            + aso.getNomeMedico());
        }
    }

    private static void atualizarResultado(Scanner scanner) {

        System.out.println("\n===== ATUALIZAR RESULTADO =====");

        System.out.print(ID_ASO);

        Long idAso = scanner.nextLong();

        scanner.nextLine();

        System.out.print("Novo resultado: ");

        String resultado = scanner.nextLine();

        asoService.atualizarResultado(idAso, resultado);

        System.out.println("\nResultado atualizado!");
    }

    private static void deletarAso(Scanner scanner) {

        System.out.println("\n===== DELETAR ASO =====");

        System.out.print(ID_ASO);

        Long id = scanner.nextLong();

        scanner.nextLine();

        asoService.deletar(id);

        System.out.println("\nASO deletado!");
    }

    private static void verificarStatusAso(Scanner scanner) {

        System.out.println("\n===== STATUS ASO =====");

        System.out.print(CPF_COLABORADOR);

        String cpf = scanner.nextLine();

        String status = asoService.verificarStatusAso(cpf);

        System.out.println("\nStatus do ASO: " + status);
    }

    private static void listarExames(Scanner scanner) {

        System.out.println("\n===== LISTAR EXAMES DO ASO =====");

        System.out.print("Digite o ID do ASO: ");

        Long idAso = scanner.nextLong();

        scanner.nextLine();

        List<Exame> lista = exameService.listarPorAso(idAso);

        if (lista.isEmpty()) {

            System.out.println("\nNenhum exame encontrado.");

            return;
        }

        for (Exame exame : lista) {

            exibirExame(exame);
        }
    }

    private static void exibirExame(Exame exame) {

        System.out.println("\nID Exame: " + exame.getIdExame());

        System.out.println(
                "Exame: "
                        + exame.getNomeExame());

        System.out.println(
                RESULTADO
                        + exame.getResultadoExame());

        System.out.println(
                "Valor Referência: "
                        + exame.getValorReferenciaExame());

        System.out.println(
                ID_ASO
                        + exame.getIdAso());

        System.out.println(
                "Tipo ASO: "
                        + exame.getTipoAso());

        System.out.println(
                "ID Colaborador: "
                        + exame.getIdColaborador());

        System.out.println(
                "Colaborador: "
                        + exame.getNomeColaborador());
    }
}