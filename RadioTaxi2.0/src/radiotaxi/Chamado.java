package radiotaxi;

import java.sql.Date;

public class Chamado {
    private Long cod_conveniada, num_boleto, num_chamado, num_Chamado_seq;
    private Date dat_abertura_chamado, dat_agenda_corrida;
    private Long num_pessoa_atendente, num_veiculo, cod_centro_custo;
    private String num_contato;
    private Long num_tel_DDI_contato, num_tel_DDD_contato, num_tel_contato;
    private Long num_CEP, idt_origem_destino, num_municipio;
    private String des_localizacao, num_UF;
    
    public Chamado(Long cod_conveniada, Long num_boleto, Long num_chamado,
    Date dat_abertura_chamado, Date dat_agenda_corrida, Long num_pessoa_atendente,
    Long num_veiculo, Long cod_centro_custo, String num_contato, 
    Long num_tel_DDI_contato, Long num_tel_DDD_contato, Long num_tel_contato){
        this.cod_conveniada = cod_conveniada;
        this.num_boleto = num_boleto;
        this.num_chamado = num_chamado;
        this.dat_abertura_chamado = dat_abertura_chamado;
        this.dat_agenda_corrida = dat_agenda_corrida;
        this.num_pessoa_atendente = num_pessoa_atendente;
        this.num_veiculo = num_veiculo;
        this.cod_centro_custo = cod_centro_custo;
        this.num_contato = num_contato;
        this.num_tel_DDI_contato = num_tel_DDI_contato;
        this.num_tel_DDD_contato = num_tel_DDD_contato;
        this.num_tel_contato = num_tel_contato;
    }
    
    public Chamado(Long cod_conveniada, Long num_boleto, Long num_CEP,
    String des_localizacao, Long idt_origem_destino, 
    Long num_municipio, String num_UF){
        this.cod_conveniada = cod_conveniada;
        this.num_boleto = num_boleto;
        this.num_CEP = num_CEP;
        this.des_localizacao = des_localizacao;
        this.idt_origem_destino = idt_origem_destino;
        this.num_municipio = num_municipio;
        this.num_UF = num_UF;
    }
    
    public Long getCod_conveniada() {
        return cod_conveniada;
    }

    public void setCod_conveniada(Long cod_conveniada) {
        this.cod_conveniada = cod_conveniada;
    }

    public Long getNum_boleto() {
        return num_boleto;
    }

    public void setNum_boleto(Long num_boleto) {
        this.num_boleto = num_boleto;
    }

    public Long getNum_chamado() {
        return num_chamado;
    }

    public void setNum_chamado(Long num_chamado) {
        this.num_chamado = num_chamado;
    }

    public Date getDat_abertura_chamado() {
        return dat_abertura_chamado;
    }

    public void setDat_abertura_chamado(Date dat_abertura_chamado) {
        this.dat_abertura_chamado = dat_abertura_chamado;
    }

    public Date getDat_agenda_corrida() {
        return dat_agenda_corrida;
    }

    public void setDat_agenda_corrida(Date dat_agenda_corrida) {
        this.dat_agenda_corrida = dat_agenda_corrida;
    }

    public Long getNum_veiculo() {
        return num_veiculo;
    }

    public void setNum_veiculo(Long num_veiculo) {
        this.num_veiculo = num_veiculo;
    }

    public Long getCod_centro_custo() {
        return cod_centro_custo;
    }

    public void setCod_centro_custo(Long cod_centro_custo) {
        this.cod_centro_custo = cod_centro_custo;
    }

    public String getNum_contato() {
        return num_contato;
    }

    public void setNum_contato(String num_contato) {
        this.num_contato = num_contato;
    }

    public Long getNum_tel_DDI_contato() {
        return num_tel_DDI_contato;
    }

    public void setNum_tel_DDI_contato(Long num_tel_DDI_contato) {
        this.num_tel_DDI_contato = num_tel_DDI_contato;
    }

    public Long getNum_tel_DDD_contato() {
        return num_tel_DDD_contato;
    }

    public void setNum_tel_DDD_contato(Long num_tel_DDD_contato) {
        this.num_tel_DDD_contato = num_tel_DDD_contato;
    }

    public Long getNum_tel_contato() {
        return num_tel_contato;
    }

    public void setNum_tel_contato(Long num_tel_contato) {
        this.num_tel_contato = num_tel_contato;
    }

    public Long getNum_CEP() {
        return num_CEP;
    }

    public void setNum_CEP(Long num_CEP) {
        this.num_CEP = num_CEP;
    }

    public Long getIdt_origem_destino() {
        return idt_origem_destino;
    }

    public void setIdt_origem_destino(Long idt_origem_destino) {
        this.idt_origem_destino = idt_origem_destino;
    }

    public Long getNum_municipio() {
        return num_municipio;
    }

    public void setNum_municipio(Long num_municipio) {
        this.num_municipio = num_municipio;
    }

    public String getDes_localizacao() {
        return des_localizacao;
    }

    public void setDes_localizacao(String des_localizacao) {
        this.des_localizacao = des_localizacao;
    }

    public String getNum_UF() {
        return num_UF;
    }

    public void setNum_UF(String num_UF) {
        this.num_UF = num_UF;
    }

    public Long getNum_pessoa_atendente() {
        return num_pessoa_atendente;
    }

    public void setNum_pessoa_atendente(Long num_pessoa_atendente) {
        this.num_pessoa_atendente = num_pessoa_atendente;
    }

    public Long getNum_Chamado_seq() {
        return num_Chamado_seq;
    }
    
    public void setNum_Chamado_seq(Long num_Chamado_seq) {
        this.num_Chamado_seq = num_Chamado_seq;
    }
    
    
    
}
