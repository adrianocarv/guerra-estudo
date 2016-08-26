package br.org.esplanada.guerraestudo.domain;

public class Ataque {

	private String nome;
	private boolean special;
	private float n;
	private int cosmo;
	private int magicPoint;

	public Ataque(boolean special, float n, int cosmo, int magicPoint, String nome) {
		this.nome = nome;
		this.special = special;
		this.n = n;
		this.cosmo = cosmo;
		this.magicPoint = magicPoint;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isSpecial() {
		return special;
	}
	public void setSpecial(boolean special) {
		this.special = special;
	}
	public float getN() {
		return n;
	}
	public void setN(float n) {
		this.n = n;
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
}
