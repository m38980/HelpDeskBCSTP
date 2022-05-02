package com.bcstp.helpdesk.enums;

public enum Prioridade {
	
	SELECIONE ("SELECIONE"),
	BAIXA(0, "BAIXA"), 
	MEDIA(1, "MEDIA"), 
	ALTA(2, "ALTA");
	
	private Integer codigo;
	private String descricao;
	
	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	Prioridade(String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida");
	}
}
