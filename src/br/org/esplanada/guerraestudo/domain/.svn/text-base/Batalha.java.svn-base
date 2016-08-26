package br.org.esplanada.guerraestudo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Batalha {

	private int custoCosmoMinuto;
	private Map<Integer, Integer> valoresQualidades = new HashMap<Integer, Integer>();
	
	private List<Equipe> equipes = new ArrayList<Equipe>();
	
	private int indexEquipeAtacante = 0;


	public Batalha(int custoCosmoMinuto, Map<Integer, Integer> valoresQualidades) {
		this.custoCosmoMinuto = custoCosmoMinuto;
		this.valoresQualidades = valoresQualidades;
	}

	public List<Equipe> getEquipesAtivas(){
		List<Equipe> equipesAtivas = new ArrayList<Equipe>();
		for(Equipe equipe : this.getEquipes()){
			if(equipe.isActive())
				equipesAtivas.add(equipe);
		}
		return equipesAtivas;
	}
	
	public Equipe getEquipeAtacante() {
		Equipe equipe = this.getEquipesAtivas().get(indexEquipeAtacante);
		return equipe;
	}

	public void proximaEquipeAtacante() {
		//TODO tratar o caso das equipes inativas
		indexEquipeAtacante++;
		if(indexEquipeAtacante >= this.getEquipesAtivas().size())
			indexEquipeAtacante = 0;
	}

	public int getCustoCosmoMinuto() {
		return custoCosmoMinuto;
	}
	public void setCustoCosmoMinuto(int custoCosmoMinuto) {
		this.custoCosmoMinuto = custoCosmoMinuto;
	}
	public List<Equipe> getEquipes() {
		return equipes;
	}
	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Map<Integer, Integer> getValoresQualidades() {
		return valoresQualidades;
	}

	public void setValoresQualidades(Map<Integer, Integer> valoresQualidades) {
		this.valoresQualidades = valoresQualidades;
	}

	public boolean fimBatalha() {
		//Verifica se é a última equipe da rodada
		if(this.getEquipes().size() - 1 != indexEquipeAtacante)
			return false;
		
		if(this.isGameOverAutomatico())
			return true;
		
		return JOptionPane.showConfirmDialog(null, "Encerra a batalha?") == 0 ? true : false ;
	}

	private boolean isGameOverAutomatico() {
		//Se tiver apenas uma equipe ativa
		if(this.getEquipesAtivas().size() == 1)
			return true;
		
		boolean temCosmo = false;
		for(Equipe equipe : this.getEquipesAtivas()){
			if(equipe.getCosmo() > 0){
				temCosmo = true;
				break;
			}
		}
		return !temCosmo;
	}
}
