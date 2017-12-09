package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import radiotaxi.Chamado;

public class ChamadoDao {
    
    private Connection conn;
        
    public ChamadoDao(){
        this.conn = Conexao.getConnection();
    }
    
    public void insertChamado(Chamado ch){
       try{
            conn.setAutoCommit(false);
            
            String sql = "insert into system.chamado (cod_conveniada, num_boleto, "
                    + "num_chamado, dat_abertura_chamado, "
                + "dat_agenda_corrida, num_pessoa_atendente, num_veiculo, "
                + "cod_centro_custo, num_contato, num_tel_DDI_contato, "
                + "num_tel_DDD_contato, num_tel_contato) values "
                + "(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);            
            
            stmt.setLong(1, ch.getCod_conveniada());
            stmt.setLong(2, ch.getNum_boleto());
            stmt.setLong(3, ch.getNum_chamado());
            stmt.setDate(4, ch.getDat_abertura_chamado());
            stmt.setDate(5, ch.getDat_agenda_corrida());
            stmt.setLong(6, ch.getNum_pessoa_atendente());
            stmt.setLong(7, ch.getNum_veiculo());
            stmt.setLong(8, ch.getCod_centro_custo());
            stmt.setString(9, ch.getNum_contato());
            stmt.setLong(10, ch.getNum_tel_DDI_contato());
            stmt.setLong(11, ch.getNum_tel_DDD_contato());
            stmt.setLong(12, ch.getNum_tel_contato());
        
            stmt.execute();
            stmt.close();
            conn.commit();
            System.out.println("Chamado cadastrado com sucesso :)");
            System.out.println("");
        
       } catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao cadastrar chamado :/");
           e.printStackTrace();
       } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
       }
    }
    
     public void insertChamadoDetalhe(Chamado ch){
       try{
            conn.setAutoCommit(false);
            
            String sql = "insert into system.chamado_detalhe "
                    + "(num_Chamado_seq, cod_conveniada, num_boleto, "
                    + "num_CEP, des_localizacao, "
                + "idt_origem_destino, num_municipio, "
                + "nom_UF) values "
                + "(system.num_Chamado_seq.nextval,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);            
            
            stmt.setLong(1, ch.getCod_conveniada());
            stmt.setLong(2, ch.getNum_boleto());
            stmt.setLong(3, ch.getNum_CEP());
            stmt.setString(4, ch.getDes_localizacao());
            stmt.setLong(5, ch.getIdt_origem_destino());
            stmt.setLong(6, ch.getNum_municipio());
            stmt.setString(7, ch.getNum_UF());
            
            stmt.execute();
            stmt.close();
            conn.commit();
            
            System.out.println("Detalhes do chamado cadastrado com sucesso :)");
            System.out.println("");
        
       } catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao cadastrar detalhes chamado :/");
           e.printStackTrace();
       } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
       }
    }
    
    public ArrayList<Chamado> visualizarDadosChamado(){
        Chamado ch;
        ArrayList<Chamado> chamados = new ArrayList<>();
        String sql = "";
        
        try{
            conn.setAutoCommit(false);
            
            sql = "SELECT * FROM system.CHAMADO";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ch = new Chamado(rs.getLong("cod_conveniada"), rs.getLong("num_boleto"), rs.getLong("num_chamado"),
                                 rs.getDate("dat_abertura_chamado"), rs.getDate("dat_agenda_corrida"), rs.getLong("num_pessoa_atendente"),
                                 rs.getLong("num_veiculo"), rs.getLong("cod_centro_custo"), rs.getString("num_contato"), 
                                 rs.getLong("num_tel_DDI_contato"), rs.getLong("num_tel_DDD_contato"), rs.getLong("num_tel_contato"));
                chamados.add(ch);
            }
            stmt.close();
            
            conn.commit();
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao buscar chamado :/");
           e.printStackTrace();
       } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
       }
        return chamados;
    }
    
    public ArrayList<Chamado> visualizarDadosChamadoDetalhe(){
        Chamado ch;
        ArrayList<Chamado> chamadosdetalhe = new ArrayList<>();
        String sql = "";
        
        try{
            conn.setAutoCommit(false);
            
            sql = "SELECT * FROM system.CHAMADO_DETALHE";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ch = new Chamado(rs.getLong("cod_conveniada"), rs.getLong("num_boleto"), rs.getLong("num_CEP"),
                                 rs.getString("des_localizacao"), rs.getLong("idt_origem_destino"), rs.getLong("num_municipio"),
                                 rs.getString("nom_UF"));
                ch.setNum_Chamado_seq(rs.getLong("num_Chamado_seq"));
                chamadosdetalhe.add(ch);
            }
            stmt.close();
            
            conn.commit();
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao buscar chamado detalhe :/");
           e.printStackTrace();
       } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
       }
        return chamadosdetalhe;
    }
    
    public void updateDetalhesChamado(Chamado ch){
        String sql;
        
        try{
            conn.setAutoCommit(false);
            
            sql = "update SYSTEM.CHAMADO_DETALHE " +
                    "set NUM_CHAMADO_SEQ = ?, COD_CONVENIADA = ?, NUM_BOLETO = ?, " +
                    "NUM_CEP = ?, DES_LOCALIZACAO = ?, IDT_ORIGEM_DESTINO = ?, " +
                    "NUM_MUNICIPIO = ?, NOM_UF = ? " +
                    "where num_chamado_seq = ? and COD_CONVENIADA = ? and NUM_BOLETO = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ch.getNum_Chamado_seq());
            stmt.setLong(2, ch.getCod_conveniada());
            stmt.setLong(3, ch.getNum_boleto());
            stmt.setLong(4, ch.getNum_CEP());
            stmt.setString(5, ch.getDes_localizacao());
            stmt.setLong(6, ch.getIdt_origem_destino());
            stmt.setLong(7, ch.getNum_municipio());
            stmt.setString(8, ch.getNum_UF());
            stmt.setLong(9, ch.getNum_Chamado_seq());
            stmt.setLong(10, ch.getCod_conveniada());
            stmt.setLong(11, ch.getNum_boleto());
            
            stmt.execute();
            stmt.close();
            conn.commit();
            System.out.println("Detalhes do chamado atualizados com sucesso!");
            System.out.println("");
            
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao atualizar detalhes chamado");
           e.printStackTrace();
        } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
        }
    }
    
    public void updateChamado(Chamado ch){
        String sql;
        
        try{
            conn.setAutoCommit(false);
            
            sql = "update SYSTEM.CHAMADO " +
                    "set num_chamado = ?, " +
                    "dat_abertura_chamado = ?, dat_agenda_corrida = ?, num_pessoa_atendente = ?, " +
                    "num_veiculo = ?, cod_centro_custo = ?, num_contato = ?, num_tel_DDI_contato = ?, " + 
                    "num_tel_DDD_contato = ?, num_tel_contato = ? " +
                    "where cod_conveniada = ? and num_boleto = ? ";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ch.getNum_chamado());
            stmt.setDate(2, ch.getDat_abertura_chamado());
            stmt.setDate(3, ch.getDat_agenda_corrida());
            stmt.setLong(4, ch.getNum_pessoa_atendente());
            stmt.setLong(5, ch.getNum_veiculo());
            stmt.setLong(6, ch.getCod_centro_custo());
            stmt.setString(7, ch.getNum_contato());
            stmt.setLong(8, ch.getNum_tel_DDI_contato());
            stmt.setLong(9, ch.getNum_tel_DDD_contato());
            stmt.setLong(10, ch.getNum_tel_contato());
            stmt.setLong(11, ch.getCod_conveniada());
            stmt.setLong(12, ch.getNum_boleto());

            stmt.execute();
            stmt.close();
            conn.commit();
            System.out.println("Chamado atualizado com sucesso!");
            System.out.println("");
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao atualizar chamado");
           e.printStackTrace();
        } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
        }
    }
    
    public void deleteChamado(Long cod_conveniada, Long num_boleto){
        String sql;
        
        try{
            conn.setAutoCommit(false);
            
            sql = "delete from SYSTEM.CHAMADO " +
                  "where cod_conveniada = ? and num_boleto = ? ";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, cod_conveniada);
            stmt.setLong(2, num_boleto);
            stmt.execute();
            stmt.close();
            conn.commit();
            System.out.println("Chamado deletado com sucesso!");
            System.out.println("");
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao deletar chamado");
           e.printStackTrace();
        } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
        }
    }
    
    public void deleteDetalhesChamado(Long num_Chamado_seq, Long cod_conveniada, Long num_boleto){
        String sql;
        
        try{
            conn.setAutoCommit(false);
            
            sql = "delete from SYSTEM.CHAMADO_DETALHE " +
                  "where num_Chamado_seq = ? and cod_conveniada = ? and num_boleto = ? ";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, num_Chamado_seq);
            stmt.setLong(2, cod_conveniada);
            stmt.setLong(3, num_boleto);
            
            stmt.execute();
            stmt.close();
            conn.commit();
            
            System.out.println("Detalhes do chamado deletados com sucesso!");
            System.out.println("");
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro ao deletar detalhes chamado");
           e.printStackTrace();
        } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
        }
    }
    
    public boolean chamadoExiste(Long cod_conveniada, Long num_boleto){
        String sql;
        boolean existe = false;
        
        try{
            conn.setAutoCommit(false);
            
            sql = "select count(*) as qtd from SYSTEM.CHAMADO " +
                  "where cod_conveniada = ? and num_boleto = ? ";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, cod_conveniada);
            stmt.setLong(2, num_boleto);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int qtd = rs.getInt("qtd");
                if(qtd > 0){
                    existe = true;
                    break;
                }
            }
            stmt.close();
            conn.commit();
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro verificar se chamado existe");
           e.printStackTrace();
        } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
        }
        return existe;
    }
    
    public boolean detalheChamadoExiste(Long num_Chamado_seq, Long cod_conveniada, Long num_boleto){
        String sql;
        boolean existe = false;
        
        try{
            conn.setAutoCommit(false);
            
            sql = "select count(*) as qtd from SYSTEM.CHAMADO_DETALHE " +
                  "where num_Chamado_seq = ? and cod_conveniada = ? and num_boleto = ? ";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, num_Chamado_seq);
            stmt.setLong(2, cod_conveniada);
            stmt.setLong(3, num_boleto);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int qtd = rs.getInt("qtd");
                if(qtd > 0){
                    existe = true;
                    break;
                }
            }
            
            stmt.close();
            conn.commit();
        }catch(SQLException e){
           try {
               e.printStackTrace();
               conn.rollback();
           } catch (SQLException ex) {
               System.out.println("Erro no roolback");
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               e.printStackTrace();
           }
           System.out.println("Erro verificar se detalhes chamado existe");
           e.printStackTrace();
        } finally{
           try {
               conn.setAutoCommit(true);
           } catch (SQLException ex) {
               Logger.getLogger(ChamadoDao.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
           }
        }
        return existe;
    }
    
}
