package com.bcstp.helpdesk.enums;

public enum Department {
	
	 SELECIONE,
	 DSI,
	 DSARH,
	 RIP,
	 DCCI, 
	 DSC,
	 DEEF, 
	 DEE,
	 DSIF, 
	 DAC, 
	 DMGL; 

	Department() {
	}

	private String descricao;
	
	private Department(String descricao) {
		this.descricao = descricao;
	}
}
