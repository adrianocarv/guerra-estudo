package br.org.esplanada.guerraestudo.domain;

import br.org.esplanada.guerraestudo.infrastructure.Util;

public class Guerreiro {

	private String nome;
	private int at;
	private int df;
	private int sa;
	private int sd;
	private int to;
	private int lf;
	private int hp;
	private int qualidade;
	private Ataque ataqueEspecial1;
	private Ataque ataqueEspecial2;
	private String equipe;

	public Guerreiro(String nome, int at, int df, int sa, int sd, int to, int lf, int qualidade, Ataque ataqueEspecial1, Ataque ataqueEspecial2) {
		this.nome = nome;
		this.at = at;
		this.df = df;
		this.sa = sa;
		this.sd = sd;
		this.to = to;
		this.lf = lf;
		this.hp = lf * 5;
		this.qualidade = qualidade;
		this.ataqueEspecial1 = ataqueEspecial1;
		this.ataqueEspecial2 = ataqueEspecial2;
	}
	public Guerreiro(String nome, int at, int df, int sa, int sd, int to, int lf, int qualidade, Ataque ataqueEspecial1) {
		this.nome = nome;
		this.at = at;
		this.df = df;
		this.sa = sa;
		this.sd = sd;
		this.to = to;
		this.lf = lf;
		this.hp = lf * 5;
		this.qualidade = qualidade;
		this.ataqueEspecial1 = ataqueEspecial1;
	}
	
	public String getNomeFormatado(){
		return Util.getCompletedString(this.getNome(), 15, ' ');
	}
	
	public int getAt() {
		return at;
	}
	public void setAt(int at) {
		this.at = at;
	}
	public int getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
	}
	public int getSa() {
		return sa;
	}
	public void setSa(int sa) {
		this.sa = sa;
	}
	public int getSd() {
		return sd;
	}
	public void setSd(int sd) {
		this.sd = sd;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getLf() {
		return lf;
	}
	public void setLf(int lf) {
		this.lf = lf;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getQualidade() {
		return qualidade;
	}
	public void setQualidade(int qualidade) {
		this.qualidade = qualidade;
	}
	public Ataque getAtaqueEspecial1() {
		return ataqueEspecial1;
	}
	public void setAtaqueEspecial1(Ataque ataqueEspecial1) {
		this.ataqueEspecial1 = ataqueEspecial1;
	}
	public Ataque getAtaqueEspecial2() {
		return ataqueEspecial2;
	}
	public void setAtaqueEspecial2(Ataque ataqueEspecial2) {
		this.ataqueEspecial2 = ataqueEspecial2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	
}
