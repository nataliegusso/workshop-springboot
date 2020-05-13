package com.example.A301.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity   //Mapear o JPA, as anotações auxiliam o JPA  //@Entity:
@Table(name = "tb_user")
public class User implements Serializable {  //serializable: transformado em cadeias de bits

	private static final long serialVersionUID = 1L;

	@Id  //Mapear o JPA: Chave primária da tabela do BD
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoimplementável
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;


	//por padrão no jpa sempre que chamar o objeto do muitos, chama o do 1 tbm (o pedido chamará o cliente associado)
	//lazy loading: qdo tem uma assossiação muitos p 1, se carrega um objeto do lado do muitos carrega automaticamente. O inverso não acontece p não estourar a memória	
	//spring.jpa.open-in-view=true é true para habilitar a possibilidade do json chamar o jpa p trazer os pedidos
	@JsonIgnore  //O one to many chama o many to one que chama... virou um looping. Esse comando elimina isso
	@OneToMany(mappedBy = "client")  //indica relacionamento entre o cliente e o pedido (opcional), obrigatório p pedido-cliente
	private List<Order> orders = new ArrayList<>(); //Um user pode ter várias orders
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() { 	//ArrayList só tem get e não tem set, não pode mudar a lista, só aumentar
		return orders;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}