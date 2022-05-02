package com.bcstp.helpdesk.enums;

public enum Tecnico {
	SELECIONE ("SELECIONE"),
	ENGºAYRES ("ENGENHEIRO AYRES"),
	ENGºSECA ("ENGENHEIRO SECA"),
	ENGºSILVIO ("ENGENHEIRO SILVIO"),
	ENGªDJALMA ("ENGENHEIRA DJALMA"),
	ENGºFILIPE ("ENGENHEIRO FILIPE"),
	ENGªANAYANSI ("ENGENHEIRO ANAYANSI"),
	ENGºKALANE ("ENGENHEIRO KALANE");
	
	private String nome;

	private Tecnico(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
