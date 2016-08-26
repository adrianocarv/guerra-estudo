package br.org.esplanada.guerraestudo.domain;


public class Equipe {

	private String cor;
	private int minutoEstudoSemana;
	private int minutoEstudoAcumulado;
	private int cosmo;
	private int magicPoint;
	private int pontosIniciais;
	private int pontosGanhos;
	private boolean active = true;

	private Guerreiro guerreiroFrente;
	private Guerreiro guerreiroAtras;
	
	public Equipe(String cor, int minutoEstudoSemana, int minutoEstudoAcumulado, int magicPoint, int pontosIniciais, int custoCosmoMinuto) {
		this.cor = cor;
		this.minutoEstudoSemana = minutoEstudoSemana;
		this.minutoEstudoAcumulado = minutoEstudoAcumulado;
		this.magicPoint = magicPoint;
		this.pontosIniciais = pontosIniciais;
		this.cosmo = (int)(minutoEstudoSemana + (0.8 * minutoEstudoAcumulado)) / custoCosmoMinuto; 
	}

	public void addPontosGanhos(int pontosGanhos) {
		this.pontosGanhos += pontosGanhos;
	}

	public boolean podeAtacarDoisGuerreiros(){
		return guerreiroFrente != null && guerreiroAtras != null && magicPoint > 0;
	}
	
	public void debitarAtaque(Ataque ataque){
		this.cosmo -= ataque.getCosmo();
		if(ataque.isSpecial())
			this.magicPoint -= ataque.getMagicPoint();
	}
	
	public int getPontosTotais() {
		return pontosIniciais + pontosGanhos;
	}

	public int getMinutoEstudoSemana() {
		return minutoEstudoSemana;
	}
	public void setMinutoEstudoSemana(int minutoEstudoSemana) {
		this.minutoEstudoSemana = minutoEstudoSemana;
	}
	public int getMinutoEstudoAcumulado() {
		return minutoEstudoAcumulado;
	}
	public void setMinutoEstudoAcumulado(int minutoEstudoAcumulado) {
		this.minutoEstudoAcumulado = minutoEstudoAcumulado;
	}
	public int getCosmo() {
		return cosmo;
	}
	public void setCosmo(int cosmo) {
		this.cosmo = cosmo;
	}
	public int getMagicPoint() {
		return magicPoint;
	}
	public void setMagicPoint(int magicPoint) {
		this.magicPoint = magicPoint;
	}
	public int getPontosIniciais() {
		return pontosIniciais;
	}
	public void setPontosIniciais(int pontosIniciais) {
		this.pontosIniciais = pontosIniciais;
	}
	public int getPontosGanhos() {
		return pontosGanhos;
	}
	public void setPontosGanhos(int pontosGanhos) {
		this.pontosGanhos = pontosGanhos;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Guerreiro getGuerreiroFrente() {
		return guerreiroFrente;
	}
	public void setGuerreiroFrente(Guerreiro guerreiroFrente) {
		this.guerreiroFrente = guerreiroFrente;
		
		//this.cosmo = guerreiroFrente.getQualidade()
		
	}
	public Guerreiro getGuerreiroAtras() {
		return guerreiroAtras;
	}
	public void setGuerreiroAtras(Guerreiro guerreiroAtras) {
		this.guerreiroAtras = guerreiroAtras;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
}
