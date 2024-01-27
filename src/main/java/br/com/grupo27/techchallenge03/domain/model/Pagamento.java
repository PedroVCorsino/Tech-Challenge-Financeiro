package br.com.grupo27.techchallenge03.domain.model;

public class Pagamento {
    private Long id;
    private String idCobranca;
    private String idtx;
    private Long idPedido;
    private Boolean pago;   
    private String cliente;
    private String valor;
    public Pagamento(Long id, String idCobranca, String idtx, Long idPedido, Boolean pago, String cliente, String valor) {
        this.id = id;
        this.idCobranca = idCobranca;
        this.idtx = idtx;
        this.idPedido = idPedido;
        this.pago = pago;
        this.cliente = cliente;
        this.valor = valor;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIdCobranca() {
        return idCobranca;
    }
    public void setIdCobranca(String idCobranca) {
        this.idCobranca = idCobranca;
    }
    public String getIdtx() {
        return idtx;
    }
    public void setIdtx(String idtx) {
        this.idtx = idtx;
    }
    public Long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
    public Boolean getPago() {
        return pago;
    }
    public void setPago(Boolean pago) {
        this.pago = pago;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }    

    
}
