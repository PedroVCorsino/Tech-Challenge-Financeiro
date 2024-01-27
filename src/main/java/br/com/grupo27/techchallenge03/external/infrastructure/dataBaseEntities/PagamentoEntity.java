package br.com.grupo27.techchallenge03.external.infrastructure.dataBaseEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAGAMENTO")
public class PagamentoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "de_id_cobranca")
    private String idCobranca;

    @Column(name = "de_idtx")
    private String idtx;
    
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "pago")
    private Boolean pago;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "valor")
    private String valor;

    public PagamentoEntity(){}

    public PagamentoEntity(Long id, String idCobranca, String idtx, Long idPedido, Boolean pago, String cliente, String valor) {
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
