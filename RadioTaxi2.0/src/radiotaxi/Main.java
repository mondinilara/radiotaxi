package radiotaxi;

import JDBC.ChamadoDao;
import JDBC.Conexao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ParseException {
        
        Conexao dataBase = null; 
        
        Scanner sc = new Scanner(System.in);
        LeitorDados leitor = new LeitorDados();
        String user="", password="";
        int opcao, opGerente, opAt, opGeral;
        Boolean connect = false;
        opcao = opGerente = opAt = opGeral = -1;
        
        //dados cadastro chamado e detalhes chamado
        Long num_Chamado_seq, cod_conveniada, num_boleto, num_chamado;
        Long num_veiculo, cod_centro_custo, num_pessoa_atendente;
        String num_contato;
        Long num_tel_DDI_contato, num_tel_DDD_contato, num_tel_contato;
        Long num_CEP, idt_origem_destino, num_municipio;
        String des_localizacao, num_UF;
        
        String dataAbertura = null, dataAgenda = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Chamado chamado = null;
        ChamadoDao chDao;
        
        System.out.println("------------ Radio Taxi ------------");
        System.out.println("Seja bem-vindo(a)!");
        System.out.println("Por favor digite ");
        
        do{
            if(!connect){
                System.out.print("Usuario: ");
                user = sc.nextLine();
                System.out.print("Senha: ");
                password = sc.nextLine();
                dataBase = new Conexao("127.0.0.1", user, password);
                Conexao.getConnection();
                connect = Conexao.connected;
            }
            
            if(opGeral == 0)
                break;
            
            if(connect){
                if(user.equalsIgnoreCase("atendenteUser")){
                    do{
                        System.out.println("------------ Bem-vindo, "+user+" ------------");
                        System.out.println("Selecione a opção desejada");

                        System.out.println("");
                        System.out.println("CADASTRAR: ");
                        System.out.println("    1. Chamado"); //Lara OK
                        System.out.println("    2. Detalhes chamado"); //Lara OK

                        System.out.println("");
                        System.out.println("VISUALIZAR DADOS: ");
                        System.out.println("    3. Chamado");   //Leandro OK
                        System.out.println("    4. Detalhes chamado"); //Leandro OK
                        
                        System.out.print("0. SAIR ");
            
                        System.out.println("");
                        System.out.print("Opcao: ");
                        opAt = leitor.lerInt();
                        System.out.println("");

                        switch(opAt){

                            //cadastrar chamado
                            case 1:
                                System.out.print("Codigo conveniado: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.print("Codigo boleto: ");
                                num_boleto = leitor.lerLong();

                                System.out.print("Numero chamado: ");
                                num_chamado = leitor.lerLong();

                                System.out.print("Data abertura chamado: ");
                                java.sql.Date dat_abertura_chamado = leitor.lerData();

                                System.out.print("Data agenda corrida: ");
                                java.sql.Date dat_agenda_corrida = leitor.lerData();

                                System.out.print("Numero do atendente: ");
                                num_pessoa_atendente = leitor.lerLong();

                                System.out.print("Numero do veiculo: ");
                                num_veiculo = leitor.lerLong();

                                System.out.print("Codigo centro de custo: ");
                                cod_centro_custo = leitor.lerLong();

                                System.out.print("Nome de contato: ");
                                num_contato = sc.nextLine();

                                System.out.print("DDD internacional: ");
                                num_tel_DDI_contato = leitor.lerLong();

                                System.out.print("DDD nacional: ");
                                num_tel_DDD_contato = leitor.lerLong();

                                System.out.print("Telefone: ");
                                num_tel_contato = leitor.lerLong();

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

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
                                break;

                            //cadastrar detalhes chamado
                            case 2:
                                System.out.print("Codigo do conveniado: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.print("Numero do boleto: ");
                                num_boleto = leitor.lerLong();

                                System.out.print("Numero do CEP: ");
                                num_CEP = leitor.lerLong();

                                System.out.print("Destino da localizacao: ");
                                des_localizacao = sc.nextLine();

                                System.out.print("Id origem de destino: ");
                                idt_origem_destino = leitor.lerLong();

                                System.out.print("Numero do municipio: ");
                                num_municipio = leitor.lerLong();

                                System.out.print("UF: ");
                                num_UF = sc.nextLine();

                                chamado = new Chamado(cod_conveniada, num_boleto, 
                                        num_CEP, des_localizacao, idt_origem_destino, num_municipio, num_UF);

                                /*Chamado ch = new Chamado((long)1, (long)1, 
                                        (long)38400, "Universidade", (long)1, (long)1, "MG");*/

                                chDao = new ChamadoDao();
                                chDao.insertChamadoDetalhe(chamado);

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();

                                break;    

                            //visualizar dados chamado
                            case 3:
                                ArrayList<Chamado> chamados = new ArrayList<>();
                                chDao = new ChamadoDao();
                                chamados = chDao.visualizarDadosChamado();
                                int i = 1;
                                if(chamados.size() > 0){
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

                                    System.out.println("");
                                    System.out.println("0. Voltar ao menu anterior");
                                    opGeral = leitor.lerInt();
                                }else{
                                    System.out.println("Nenhum chamado encotrado");
                                }

                                break;

                            //visualizar dados detalhes chamado
                            case 4:
                                ArrayList<Chamado> chamadosDetalhe = new ArrayList<>();
                                chDao = new ChamadoDao();
                                chamados = chDao.visualizarDadosChamadoDetalhe();
                                i = 1;
                                if(chamados.size() > 0){
                                    for(Chamado c : chamados){
                                        System.out.println("DETALHES CHAMADO "+i);
                                        System.out.println("    Num chamado sequencia: "+c.getNum_Chamado_seq());
                                        System.out.println("    Codigo: "+c.getCod_conveniada());
                                        System.out.println("    Boleto: "+c.getNum_boleto());
                                        System.out.println("    CEP: "+c.getNum_CEP());
                                        System.out.println("    Localizacao: "+c.getDes_localizacao());
                                        System.out.println("    Identificacao origem destino: "+c.getIdt_origem_destino());
                                        System.out.println("    Numero do Municipio: "+c.getNum_municipio());
                                        System.out.println("    Nome estado: "+c.getNum_UF());
                                        System.out.println("");
                                        i++;
                                    }
                                    System.out.println("");
                                    System.out.println("0. Voltar ao menu anterior");
                                    opGeral = leitor.lerInt();

                                }else{
                                    System.out.println("Nenhum chamado encotrado");
                                }
                                break;

                            //sair 
                            case 0:
                                opGeral = 0;
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
                        System.out.println("    1. Chamado"); //Lara OK
                        System.out.println("    2. Detalhes chamado"); //Lara OK

                        System.out.println("");
                        System.out.println("VISUALIZAR: ");
                        System.out.println("    3. Chamado"); //Leandro OK
                        System.out.println("    4. Detalhes chamado"); //Leandro OK

                        System.out.println("");
                        System.out.println("ATUALIZAR: ");
                        System.out.println("    5. Chamado"); //Leandro OK
                        System.out.println("    6. Detalhes chamado"); //Lara OK

                        System.out.println("");
                        System.out.println("EXCLUIR: ");
                        System.out.println("    7. Chamado"); //Leandro OK
                        System.out.println("    8. Detalhes chamado"); //Lara

                        System.out.println("");
                        System.out.println("0. SAIR");

                        System.out.println("");
                        System.out.print("Opcao: ");
                        opGerente = sc.nextInt();
                        sc.nextLine();
                        System.out.println("");

                        switch(opGerente){

                            //cadastrar chamado
                            case 1:
                                System.out.print("Codigo conveniado: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.print("Codigo boleto: ");
                                num_boleto = leitor.lerLong();

                                System.out.print("Numero chamado: ");
                                num_chamado = leitor.lerLong();

                                System.out.print("Data abertura chamado: ");
                                java.sql.Date dat_abertura_chamado = leitor.lerData();

                                System.out.print("Data agenda corrida: ");
                                java.sql.Date dat_agenda_corrida = leitor.lerData();

                                System.out.print("Numero do atendente: ");
                                num_pessoa_atendente = leitor.lerLong();

                                System.out.print("Numero do veiculo: ");
                                num_veiculo = leitor.lerLong();

                                System.out.print("Codigo centro de custo: ");
                                cod_centro_custo = leitor.lerLong();

                                System.out.print("Nome de contato: ");
                                num_contato = sc.nextLine();

                                System.out.print("DDD internacional: ");
                                num_tel_DDI_contato = leitor.lerLong();

                                System.out.print("DDD nacional: ");
                                num_tel_DDD_contato = leitor.lerLong();

                                System.out.print("Telefone: ");
                                num_tel_contato = leitor.lerLong();

                                chamado = new Chamado(cod_conveniada, num_boleto, num_chamado, 
                                        dat_abertura_chamado, dat_agenda_corrida, num_pessoa_atendente, num_veiculo, 
                                        cod_centro_custo, num_contato, num_tel_DDI_contato, 
                                        num_tel_DDD_contato, num_tel_contato);

                               /*chamado = new Chamado((long)2, (long)2, (long)2356, 
                                new java.sql.Date(sdf.parse("10/10/2010").getTime()), 
                                new java.sql.Date(sdf.parse("11/10/2017").getTime()),(long)1 ,(long)1, 
                                (long) 1001, "Zuleica", (long)55, 
                                (long) 34, (long)996553593);*/

                                chDao = new ChamadoDao();
                                chDao.insertChamado(chamado);

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
                                break;

                            //cadastrar detalhes chamado
                            case 2:
                                System.out.print("Codigo do conveniado: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.print("Numero do boleto: ");
                                num_boleto = leitor.lerLong();

                                System.out.print("Numero do CEP: ");
                                num_CEP = leitor.lerLong();

                                System.out.print("Destino da localizacao: ");
                                des_localizacao = sc.nextLine();

                                System.out.print("Id origem de destino: ");
                                idt_origem_destino = leitor.lerLong();

                                System.out.print("Numero do municipio: ");
                                num_municipio = leitor.lerLong();

                                System.out.print("UF: ");
                                num_UF = sc.nextLine();

                                chamado = new Chamado(cod_conveniada, num_boleto, 
                                        num_CEP, des_localizacao, idt_origem_destino, num_municipio, num_UF);

                                /*chamado = new Chamado((long)1, (long)1, 
                                        (long)38400, "Universidade", (long)1, (long)1, "MG");*/

                                chDao = new ChamadoDao();
                                chDao.insertChamadoDetalhe(chamado);

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
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

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();

                                break;

                            //visualizar dados detalhes chamado
                            case 4:
                                ArrayList<Chamado> chamadosDetalhe = new ArrayList<>();
                                chDao = new ChamadoDao();
                                chamados = chDao.visualizarDadosChamadoDetalhe();
                                i = 1;
                                if(chamados.size() > 0){
                                    for(Chamado c : chamados){
                                        System.out.println("DETALHES CHAMADO "+i);
                                        System.out.println("    Num chamado sequencia: "+c.getNum_Chamado_seq());
                                        System.out.println("    Codigo: "+c.getCod_conveniada());
                                        System.out.println("    Boleto: "+c.getNum_boleto());
                                        System.out.println("    CEP: "+c.getNum_CEP());
                                        System.out.println("    Localizacao: "+c.getDes_localizacao());
                                        System.out.println("    Identificacao origem destino: "+c.getIdt_origem_destino());
                                        System.out.println("    Numero do Municipio: "+c.getNum_municipio());
                                        System.out.println("    Nome estado: "+c.getNum_UF());
                                        System.out.println("");
                                        i++;
                                    }
                                }else{
                                    System.out.println("Nenhum chamado encotrado");
                                }

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
                                break;

                            //atualizar chamado
                            case 5:
                                chDao = new ChamadoDao();

                                System.out.print("Digite o codigo do conveniado do chamado a ser alterado: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.print("Digite o codigo do boleto do chamado a ser alterado: ");
                                num_boleto = leitor.lerLong();

                                if(chDao.chamadoExiste(cod_conveniada, num_boleto)){
                                    System.out.println("");
                                    System.out.println("Chamado encontrado!");
                                    System.out.println("Digite os dados para alteracao");
                                    System.out.print("Numero chamado: ");
                                    num_chamado = leitor.lerLong();

                                    System.out.print("Data abertura chamado: ");
                                    dat_abertura_chamado = leitor.lerData();

                                    System.out.print("Data agenda corrida: ");
                                    dat_agenda_corrida = leitor.lerData();

                                    System.out.print("Numero do atendente: ");
                                    num_pessoa_atendente = leitor.lerLong();

                                    System.out.print("Numero do veiculo: ");
                                    num_veiculo = leitor.lerLong();

                                    System.out.print("Codigo centro de custo: ");
                                    cod_centro_custo = leitor.lerLong();

                                    System.out.print("Nome de contato: ");
                                    num_contato = sc.nextLine();

                                    System.out.print("DDD internacional: ");
                                    num_tel_DDI_contato = leitor.lerLong();

                                    System.out.print("DDD nacional: ");
                                    num_tel_DDD_contato = leitor.lerLong();

                                    System.out.print("Telefone: ");
                                    num_tel_contato = leitor.lerLong();

                                    chamado = new Chamado(cod_conveniada, num_boleto, num_chamado, 
                                            dat_abertura_chamado, dat_agenda_corrida, num_pessoa_atendente, num_veiculo, 
                                            cod_centro_custo, num_contato, num_tel_DDI_contato, 
                                            num_tel_DDD_contato, num_tel_contato);

                                    chDao.updateChamado(chamado);
                                }else{
                                    System.out.println("Chamado nao existe!");
                                }

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
                                break;

                            //atualizar detalhes chamado
                            case 6:
                                chDao = new ChamadoDao();
                                System.out.println("Para encontrarmos o chamado digite os campos abaixo");
                                System.out.print("Sequencia do chamado: ");
                                num_Chamado_seq = leitor.lerLong();

                                System.out.print("Codigo do conveniado: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.print("Numero do boleto: ");
                                num_boleto = leitor.lerLong();

                                if(chDao.detalheChamadoExiste(num_Chamado_seq, cod_conveniada, num_boleto)){
                                    System.out.println("");
                                    System.out.println("Chamado encontrado!");
                                    System.out.println("Digite os dados para alteracao");

                                    System.out.print("Codigo do conveniado: ");
                                    cod_conveniada = leitor.lerLong();

                                    System.out.print("Numero do boleto: ");
                                    num_boleto = leitor.lerLong();

                                    System.out.print("Numero do CEP: ");
                                    num_CEP = leitor.lerLong();

                                    System.out.print("Destino da localizacao: ");
                                    des_localizacao = sc.nextLine();

                                    System.out.print("Id origem de destino: ");
                                    idt_origem_destino = leitor.lerLong();

                                    System.out.print("Numero do municipio: ");
                                    num_municipio = leitor.lerLong();

                                    System.out.print("UF: ");
                                    num_UF = sc.nextLine();

                                    chamado = new Chamado(cod_conveniada, num_boleto, 
                                            num_CEP, des_localizacao, idt_origem_destino, num_municipio, num_UF);
                                    chamado.setNum_Chamado_seq(num_Chamado_seq);
                                    chDao.updateDetalhesChamado(chamado);
                                } else{
                                    System.out.println("Não ha nenhum registro de detalhes chamado!");
                                }

                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
                                break;

                            //deletar dados chamado
                            case 7:
                                chDao = new ChamadoDao();

                                System.out.println("Digite o codigo do conveniado do chamado a ser removido: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.println("Digite o codigo do boleto do chamado a ser removido: ");
                                num_boleto = leitor.lerLong();

                                if(chDao.chamadoExiste(cod_conveniada, num_boleto)){
                                    chDao.deleteChamado(cod_conveniada, num_boleto);
                                }else{
                                    System.out.println("Chamado nao existe!");
                                }
                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
                                break;

                            //deletar dados detalhe chamado
                            case 8:
                                chDao = new ChamadoDao();
                                System.out.println("Para deletar os detalhes do chamado digite os campos abaixo ");
                                System.out.print("Sequencia do chamado: ");
                                num_Chamado_seq = leitor.lerLong();

                                System.out.print("Codigo do conveniado: ");
                                cod_conveniada = leitor.lerLong();

                                System.out.print("Numero do boleto: ");
                                num_boleto = leitor.lerLong();

                                if(chDao.detalheChamadoExiste(num_Chamado_seq, cod_conveniada, num_boleto)){
                                    chDao.deleteDetalhesChamado(num_Chamado_seq, cod_conveniada, num_boleto);
                                } else{
                                    System.out.println("Detalhes desse chamado nao existe!");
                                }
                                
                                System.out.println("");
                                System.out.println("0. Voltar ao menu anterior");
                                opGeral = leitor.lerInt();
                                break;    

                            //sair
                            case 0:
                                opGeral = 0;
                                break; 

                            default:
                                break;
                        }    
                    }while(opGerente != 0);
                }
            } 
        } while(opGeral != 0);
                
        if(opGeral ==0 || opAt == 0 || opGerente == 0){
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
