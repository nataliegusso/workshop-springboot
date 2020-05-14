package com.example.A301.entities.enums;

public enum OrderStatus {

/*	WAITING_PAYMENT,
	PAID,
	SHIPPED,
	DELIVERED,
	CANCELED;*/

	//Assim já funcionaria, mas na tabela mostra números (o java por padrão atribui números), e se futuramante precisar acrescentar status, o java irá renumerar e pode atrapalhar
	//Por isso é melhor já numerar cada status, mas fazendo isso, precisa implementar outras coisas
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);

	private int code;

	private OrderStatus(int code) {  //esse construtor é uma exceção pq é private
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OrderStatus valueOf(int code) {  //converte o valor numérico p o tipo enumerado  //estático pq não precisa instanciar
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}