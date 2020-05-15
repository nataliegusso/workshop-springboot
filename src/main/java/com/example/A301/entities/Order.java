package com.example.A301.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.A301.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order")  //@Table: Order é uma palavre reservada do sql, p não entrar em conflito, avisa com esse comando
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")  //para garantir a formatação UTC
	private Instant moment;  //Instant: p registrar um instante (melhor que date)

	private Integer orderStatus;  //Vou colocar o integer pq vou lidar com números, mas externamente é OrderStatus. 
	
	//@JsonIgnore  //se o json ficar deste lado ele relaciona as ordens ao usuário. 
	@ManyToOne  //indica relacionamento entre o pedido e o cliente  //esse relacionamento é opcional p cliente-pedido. Olhe lá no User
	@JoinColumn(name = "client_id")  //client_id: nome da chave estrangeira
	private User client;  //um pedido tem um cliente, ver diagrama (veja lá no usuário)

	@OneToMany(mappedBy = "id.order")  //O OrderItemPK que chama o Order, mas aqui eu chamo o OrderItem pq o OrderItemPK é um atributo dele. O id é que tem o pedido
	private Set<OrderItem> items = new HashSet<>();
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);  //Método (F3) p converter inteiro p OrderStatus
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();  //p converter OrderStatus p inteiro
		}
	}
	
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}