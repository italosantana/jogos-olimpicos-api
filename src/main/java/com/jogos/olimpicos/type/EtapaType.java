package com.jogos.olimpicos.type;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Tipos das Etapas dos Jogos Olimpicos 
 * @author vinicius
 * Etapa - Deve contemplar as seguintes opções: Eliminatórias, Oitavas de Final, Quartas de Final, Semifinal, Final
 */

public enum EtapaType {
	ELIMINATORIAS, OITAVAS, QUARTAS, SEMIFINAL, FINAL;
	
	@JsonValue
	public String toValue() {
		return name();
	}

}
