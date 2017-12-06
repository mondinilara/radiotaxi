package radiotaxi;

import JDBC.ChamadoDao;
import JDBC.Conexao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ParseException {
        
        Conexao dataBase = null; 
        
        Scanner sc = new Scanner(System.in);
        String user="", password="";
        int opcao, opGerente, opAt;
        Boolean connect = false;
        opcao = opGerente = opAt = -1;
        
        //dados cadastro chamado e detalhes chamado
        Long cod_conveniada, num_boleto, num_chamado;
        Long num_veiculo, cod_centro_custo, num_pessoa_atendente;
        String num_contato;
        Long num_tel_DDI_contato, num_tel_DDD_contato, num_tel_contato;
        Long num_CEP, idt_origem_destino, num_municipio;
        String des_localizacao, num_UF;
        String dataAbertura = null, dataAgenda = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Chamado chamado;
        ChamadoDao chDao;
        
        try{
            do{
                System.out.println("------------ Radio Taxi ------------");
                System.out.println("Seja bem-vindo(a) ao nosso sistema!");
                System.out.println("Por favor selecione uma opção:");
                System.out.println("1. Logar");
                System.out.println("0. Sair");
                System.out.print("Opcao: ");

                opcao = sc.nextInt(); 
                sc.nextLine();

                if(opcao == 0)
                    break;

                if(opcao == 1){
                    System.out.print("Usuario: ");
                    user = sc.nextLine();
                    System.out.print("Senha: ");
                    password = sc.nextLine();

                    dataBase = new Conexao("127.0.0.1", user, password);
                    Conexao.getConnection();
                    connect = Conexao.connected;

                    if(connect){
                        if(user.equalsIgnoreCase("atendenteUser")){
                            do{
                                System.out.println("------------ Bem-vindo, "+user+" ------------");
                                System.out.println("Selecione a opção desejada");

                                System.out.println("");
                                System.out.println("CADASTRAR: ");
                                System.out.println("    1. Chamado"); //Lara
                                System.out.println("    2. Detalhes chamado"); //Lara

                                System.out.println("");
                                System.out.println("VISUALIZAR DADOS: ");
                                System.out.println("    3. Chamado");   //Leandro
                                System.out.println("    4. Detalhes chamado"); //Leandro

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");

                                System.out.println("");
                                System.out.print("Opcao: ");
                                opAt = sc.nextInt();
                                sc.nextLine();
                                System.out.println("");

                                switch(opAt){

                                    //cadastrar chamado (so alegria)
                                    case 1:
                                        System.out.print("Codigo conveniado: ");
                                        cod_conveniada = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Codigo boleto: ");
                                        num_boleto = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero chamado: ");
                                        num_chamado = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Data abertura chamado: ");
                                        dataAbertura = sc.nextLine();
                                        java.sql.Date dat_abertura_chamado = 
                                                new java.sql.Date(sdf.parse(dataAbertura).getTime());

                                        System.out.print("Data agenda corrida: ");
                                        dataAgenda = sc.nextLine();
                                        java.sql.Date dat_agenda_corrida = 
                                                new java.sql.Date(sdf.parse(dataAgenda).getTime());

                                        System.out.print("Numero do atendente: ");
                                        num_pessoa_atendente = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do veiculo: ");
                                        num_veiculo = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Codigo centro de custo: ");
                                        cod_centro_custo = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Nome de contato: ");
                                        num_contato = sc.nextLine();

                                        System.out.print("DDD internacional: ");
                                        num_tel_DDI_contato = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("DDD nacional: ");
                                        num_tel_DDD_contato = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Telefone: ");
                                        num_tel_contato = sc.nextLong();
                                        sc.nextLine();

                                        chamado = new Chamado(cod_conveniada, num_boleto, num_chamado, 
                                                dat_abertura_chamado, dat_agenda_corrida, num_pessoa_atendente, num_veiculo, 
                                                cod_centro_custo, num_contato, num_tel_DDI_contato, 
                                                num_tel_DDD_contato, num_tel_contato);

                                       /* Chamado chamado = new Chamado((long)2, (long)2, (long)2356, 
                                        new java.sql.Date(sdf.parse("10/10/2010").getTime()), 
                                        new java.sql.Date(sdf.parse("11/10/2017").getTime()),(long)1 ,(long)1, 
                                        (long) 1001, "Zuleica", (long)55, 
                                        (long) 34, (long)996553593);*/

                                        chDao = new ChamadoDao();
                                        chDao.insertChamado(chamado);

                                        break;

                                    //cadastrar detalhes chamado (so satisfação)
                                    case 2:
                                        System.out.print("Codigo do conveniado: ");
                                        cod_conveniada = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do boleto: ");
                                        num_boleto = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do CEP: ");
                                        num_CEP = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Destino da localizacao: ");
                                        des_localizacao = sc.nextLine();

                                        System.out.print("Id origem de destino: ");
                                        idt_origem_destino = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do municipio: ");
                                        num_municipio = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("UF: ");
                                        num_UF = sc.nextLine();

                                        chamado = new Chamado(cod_conveniada, num_boleto, 
                                                num_CEP, des_localizacao, idt_origem_destino, num_municipio, num_UF);

                                        /*Chamado ch = new Chamado((long)1, (long)1, 
                                                (long)38400, "Universidade", (long)1, (long)1, "MG");*/

                                        chDao = new ChamadoDao();
                                        chDao.insertChamadoDetalhe(chamado);

                                        break;    

                                    //visualizar dados chamado
                                    case 3:
                                        ArrayList<Chamado> chamados = new ArrayList<>();
                                        chDao = new ChamadoDao();
                                        chamados = chDao.visualizarDadosChamado();
                                        int i = 1;

                                        for(Chamado c : chamados){
                                            System.out.println("CHAMADO "+i);
                                            System.out.println("    Codigo: "+c.getCod_conveniada());
                                            System.out.println("    Boleto: "+c.getNum_boleto());
                                            System.out.println("    Chamado: "+c.getNum_chamado());
                                            System.out.println("    Data abertura: "+c.getDat_abertura_chamado());
                                            System.out.println("    Data agendada: "+c.getDat_agenda_corrida());
                                            System.out.println("    Atendente: "+c.getNum_pessoa_atendente());
                                            System.out.println("    Veiculo: "+c.getNum_veiculo());
                                            System.out.println("    Codigo custo: "+c.getCod_centro_custo());
                                            System.out.println("    Contato: "+c.getNum_contato());
                                            System.out.println("    DDI: "+c.getNum_tel_DDI_contato());
                                            System.out.println("    DDD: "+c.getNum_tel_DDD_contato());
                                            System.out.println("    Telefone: "+c.getNum_tel_contato());
                                            System.out.println("");
                                            i++;
                                        }                                  

                                        break;

                                    //visualizar dados detalhes chamado
                                    case 4:
                                        break;

                                    //voltar ao menu anterior 
                                    case 0:
                                        break; 

                                    default:
                                        break;
                                }


                            } while(opAt != 0);
                        }

                        if(user.equalsIgnoreCase("gerenteUser")){
                            do{
                                System.out.println("------------ Bem-vindo, "+user+" ------------");
                                System.out.println("Selecione a opção desejada");

                                System.out.println("");
                                System.out.println("CADASTRAR: ");
                                System.out.println("    1. Chamado"); //Lara
                                System.out.println("    2. Detalhes chamado"); //Lara

                                System.out.println("");
                                System.out.println("VISUALIZAR: ");
                                System.out.println("    3. Chamado"); //Leandro
                                System.out.println("    4. Detalhes chamado"); //Leandro

                                System.out.println("");
                                System.out.println("ATUALIZAR: ");
                                System.out.println("    5. Chamado"); //Leandro
                                System.out.println("    6. Detalhes chamado"); //Lara

                                System.out.println("");
                                System.out.println("EXCLUIR: ");
                                System.out.println("    7. Chamado"); //Leandro
                                System.out.println("    8. Detalhes chamado"); //Lara

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");

                                System.out.println("");
                                System.out.print("Opcao: ");
                                opGerente = sc.nextInt();
                                sc.nextLine();
                                System.out.println("");

                                switch(opGerente){

                                    //cadastrar chamado
                                    case 1:
                                        System.out.print("Codigo conveniado: ");
                                        cod_conveniada = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Codigo boleto: ");
                                        num_boleto = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero chamado: ");
                                        num_chamado = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Data abertura chamado: ");
                                        dataAbertura = sc.nextLine();
                                        java.sql.Date dat_abertura_chamado = 
                                                new java.sql.Date(sdf.parse(dataAbertura).getTime());

                                        System.out.print("Data agenda corrida: ");
                                        dataAgenda = sc.nextLine();
                                        java.sql.Date dat_agenda_corrida = 
                                                new java.sql.Date(sdf.parse(dataAgenda).getTime());

                                        System.out.print("Numero do atendente: ");
                                        num_pessoa_atendente = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do veiculo: ");
                                        num_veiculo = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Codigo centro de custo: ");
                                        cod_centro_custo = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Nome de contato: ");
                                        num_contato = sc.nextLine();

                                        System.out.print("DDD internacional: ");
                                        num_tel_DDI_contato = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("DDD nacional: ");
                                        num_tel_DDD_contato = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Telefone: ");
                                        num_tel_contato = sc.nextLong();
                                        sc.nextLine();

                                        chamado = new Chamado(cod_conveniada, num_boleto, num_chamado, 
                                                dat_abertura_chamado, dat_agenda_corrida, num_pessoa_atendente, num_veiculo, 
                                                cod_centro_custo, num_contato, num_tel_DDI_contato, 
                                                num_tel_DDD_contato, num_tel_contato);

                                       /* Chamado chamado = new Chamado((long)2, (long)2, (long)2356, 
                                        new java.sql.Date(sdf.parse("10/10/2010").getTime()), 
                                        new java.sql.Date(sdf.parse("11/10/2017").getTime()),(long)1 ,(long)1, 
                                        (long) 1001, "Zuleica", (long)55, 
                                        (long) 34, (long)996553593);*/

                                        chDao = new ChamadoDao();
                                        chDao.insertChamado(chamado);

                                        break;

                                    //cadastrar detalhes chamado
                                    case 2:
                                        System.out.print("Codigo do conveniado: ");
                                        cod_conveniada = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do boleto: ");
                                        num_boleto = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do CEP: ");
                                        num_CEP = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Destino da localizacao: ");
                                        des_localizacao = sc.nextLine();

                                        System.out.print("Id origem de destino: ");
                                        idt_origem_destino = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("Numero do municipio: ");
                                        num_municipio = sc.nextLong();
                                        sc.nextLine();

                                        System.out.print("UF: ");
                                        num_UF = sc.nextLine();

                                        chamado = new Chamado(cod_conveniada, num_boleto, 
                                                num_CEP, des_localizacao, idt_origem_destino, num_municipio, num_UF);

                                        /*Chamado ch = new Chamado((long)1, (long)1, 
                                                (long)38400, "Universidade", (long)1, (long)1, "MG");*/

                                        chDao = new ChamadoDao();
                                        chDao.insertChamadoDetalhe(chamado);

                                        break;    

                                    //visualizar dados chamado
                                    case 3:
                                        ArrayList<Chamado> chamados = new ArrayList<>();
                                        chDao = new ChamadoDao();
                                        chamados = chDao.visualizarDadosChamado();
                                        int i = 1;

                                        for(Chamado c : chamados){
                                            System.out.println("CHAMADO "+i);
                                            System.out.println("    Codigo: "+c.getCod_conveniada());
                                            System.out.println("    Boleto: "+c.getNum_boleto());
                                            System.out.println("    Chamado: "+c.getNum_chamado());
                                            System.out.println("    Data abertura: "+c.getDat_abertura_chamado());
                                            System.out.println("    Data agendada: "+c.getDat_agenda_corrida());
                                            System.out.println("    Atendente: "+c.getNum_pessoa_atendente());
                                            System.out.println("    Veiculo: "+c.getNum_veiculo());
                                            System.out.println("    Codigo custo: "+c.getCod_centro_custo());
                                            System.out.println("    Contato: "+c.getNum_contato());
                                            System.out.println("    DDI: "+c.getNum_tel_DDI_contato());
                                            System.out.println("    DDD: "+c.getNum_tel_DDD_contato());
                                            System.out.println("    Telefone: "+c.getNum_tel_contato());
                                            System.out.println("");
                                            i++;
                                        }                                  

                                        break;

                                    //visualizar dados detalhes chamado
                                    case 4:
                                        break;

                                    //atualizar chamado
                                    case 5:
                                        break;

                                    //atualizar detalhes chamado
                                    case 6:
                                        System.out.println("Digite o codigo do conveniado do chamado");
                                        cod_conveniada = sc.nextLong();
                                        sc.nextLine();
                                        
                                        
                                        
                                        
                                        break;

                                    //deletar dados chamado
                                    case 7:
                                        break;

                                    //deletar dados detalhe chamado
                                    case 8:
                                        break;    

                                    //voltar ao menu anterior
                                    case 0:
                                        break; 

                                    default:
                                        break;
                                }


                            }while(opGerente != 0);

                        }
                    } 
                }else{
                    System.out.println("Opcao invalida!");
                }
            } while(opcao != 0);
        } catch(InputMismatchException e){
                System.out.println("Digite apenas opcoes validas!");
        }
        
        if(opcao == 0 || opAt == 0 || opGerente == 0){
            try {
                if(dataBase.getConnection() != null){
                    dataBase.getConnection().close();
                }
                System.out.println(user+" saiu do sistema!");
            } catch (SQLException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
