package com.example.A301.entities.pk;  //.pk = classe auxiliar

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.A301.entities.Order;
import com.example.A301.entities.Product;

//No paradigma oo não existe chave primária composta. O atributo identificador (id) é um só, por isso é necessário 
//criar uma classe auxiliar (pk) para representar o par produto-pedido, isso que vai identificar o item de pedido.
//Resumindo: chave primária composta -> classe auxiliar pk 
//Embedable e Embedded permite diminuir as classes sem dividir as tabelas ou reutilizar mapeamentos de entidades
//Classe usa @Embeddable (diz q não existirá no BD como uma tabela separada)
//declara que uma classe será incorporada por outras entidades que já existem (não cria novamente, busca a que já existe)
//Campo usa @Embedded 


@Embeddable  //Classe auxiliar de chave primária composta
public class OrderItemPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	//essa classe auxiliar não terá construtores
		
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public int hashCode() {  //aqui as duas variáveis são usadas
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {  //aqui as duas variáveis são usadas
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}	
}