package br.org.esplanada.guerraestudo.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import br.org.esplanada.guerraestudo.domain.Ataque;
import br.org.esplanada.guerraestudo.domain.Batalha;
import br.org.esplanada.guerraestudo.domain.Equipe;
import br.org.esplanada.guerraestudo.domain.Guerreiro;
import br.org.esplanada.guerraestudo.infrastructure.Util;

public class GuerraEstudoService {

	private List<Guerreiro> todosGuerreiros = new ArrayList<Guerreiro>();
	private List<Ataque> ataquesFisicos = new ArrayList<Ataque>();

	private Batalha batalha = null;
	boolean gameOver = false;
	
	public void execute(){
		//Guerra
		this.loadGuerreiros();
		this.loadAtaquesFisicos();

		//Batalha	
		this.prepararBatalha();
		
		//Duelo
		while(!gameOver){

			this.exibirPlacar();
			
			Equipe equipeAtacante = batalha.getEquipeAtacante();

			boolean vaiAtacar = JOptionPane.showConfirmDialog(null, "Vai atacar?", equipeAtacante.getCor(), 0) == 0 ? true : false ;

			//Se n�o for atacar, pula a vez
			if(!vaiAtacar){
				batalha.proximaEquipeAtacante();
				gameOver = batalha.fimBatalha();
				continue;
			}
			
			
			//Sele��o do guerreiro atacante
			Guerreiro atacante = null;
			String frenteTras = null;
			if(equipeAtacante.podeAtacarDoisGuerreiros()){
				frenteTras = JOptionPane.showInputDialog("Frente (F) ou atr�s (A): ");
				atacante = "F".equalsIgnoreCase(frenteTras) ? equipeAtacante.getGuerreiroFrente() : equipeAtacante.getGuerreiroAtras();
			}else{
				atacante = equipeAtacante.getGuerreiroFrente();
				frenteTras = "F";
			}
			
			//1� Ataque
			this.atacar(equipeAtacante, atacante, frenteTras);
			
			this.exibirPlacar();

			//2� ataque - Verifica se pode fazer. Se sim, pergunta se quer e seleciona o Guerreiro n�o usado no 1� ataque
			if(equipeAtacante.podeAtacarDoisGuerreiros()){
				boolean novoAtaque = JOptionPane.showConfirmDialog(null, "Deseja fazer mais um ataque?") == 0 ? true : false ;
				if(novoAtaque){
					if("F".equalsIgnoreCase(frenteTras)){
						atacante = equipeAtacante.getGuerreiroAtras();
						frenteTras = "A";
					}else{
						atacante = equipeAtacante.getGuerreiroFrente();
						frenteTras = "F";
					}
					
					//2� Ataque
					this.atacar(equipeAtacante, atacante, frenteTras);
				}
			}
			
			batalha.proximaEquipeAtacante();
			gameOver = batalha.fimBatalha();
		}
		
		//Calcular pontua��o final
		this.calcularPontuacaoFinalBatalha();
	}

	private void exibirPlacar() {
		String str = "\n\n\n" + Util.getCompletedString("", 100, '*');
		str += "\nPlacar:";
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tEquipe         : " + equipe.getCor();
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tPontos iniciais: " + equipe.getPontosIniciais();
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tPontos ganhos  : " + equipe.getPontosGanhos();
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tCosmos         : " + equipe.getCosmo();
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tMagic Points   : " + equipe.getMagicPoint();
		str += "\n\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\t" + equipe.getGuerreiroFrente().getNomeFormatado() + "\t";
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tAT: " + equipe.getGuerreiroFrente().getAt() + "  |  SA: " + equipe.getGuerreiroFrente().getSa() + "\t";
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tDF: " + equipe.getGuerreiroFrente().getDf() + "  |  SD: " + equipe.getGuerreiroFrente().getSd() + "\t";
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tTO: " + equipe.getGuerreiroFrente().getTo() + "  |  HP: " + equipe.getGuerreiroFrente().getHp();
		str += "\n\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\t" + (equipe.getGuerreiroAtras() == null ? "\t\t" : equipe.getGuerreiroAtras().getNomeFormatado()) + "\t";
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tAt: " + (equipe.getGuerreiroAtras() == null ? "\t\t" : equipe.getGuerreiroAtras().getAt() + "  |  SA: " + equipe.getGuerreiroAtras().getSa()) + "\t";
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tDF: " + (equipe.getGuerreiroAtras() == null ? "\t\t" : equipe.getGuerreiroAtras().getDf() + "  |  SD: " + equipe.getGuerreiroAtras().getSd()) + "\t";
		str += "\n";
		for(Equipe equipe : batalha.getEquipes())
			str += "\t\tTO: " + (equipe.getGuerreiroAtras() == null ? "\t\t" : equipe.getGuerreiroAtras().getTo() + "  |  HP: " + equipe.getGuerreiroAtras().getHp());
		System.out.println(str);
		
	}

	private void calcularPontuacaoFinalBatalha() {
		for(Equipe equipe : batalha.getEquipesAtivas()){
			int pontuacaoBatalha = equipe.getPontosGanhos() / 3 + equipe.getPontosGanhos();
			equipe.setPontosGanhos(pontuacaoBatalha);
		}
	}

	private void atacar(Equipe equipeAtacante, Guerreiro atacante, String frenteTras){
		//Sele��o do oponente
		String rgb =  JOptionPane.showInputDialog("Atacante: " + equipeAtacante.getCor() + "\n Contra quem: Red (R), Green (G) ou Blue(B): ");
		Equipe oponente = this.getEquipe(rgb);
		
		//Sele��o do golpe
		Ataque ataque = this.getAtaque(atacante, frenteTras);
		
		//Pagar Ataque
		equipeAtacante.debitarAtaque(ataque);
		
		//Duelar
		this.duelar(equipeAtacante, atacante, oponente, ataque);
	}
	
	private void duelar(Equipe equipeAtacante, Guerreiro atacante, Equipe equipeOponente, Ataque ataque) {
		int dadoAtaque = new Integer(JOptionPane.showInputDialog("Dado de ataque: "));
		int dadoDefesa = new Integer(JOptionPane.showInputDialog("Dado de defesa: "));
		
		//For�a de ataque
		float n = dadoAtaque == 6 ? 2 * ataque.getN() : ataque.getN();
		int pontoAtaque = ataque.isSpecial() ? atacante.getSa() : atacante.getAt();
		int forcaAtaque = (int)(n * pontoAtaque) + dadoAtaque;
		
		//For�a de dafesa
		int pontoDefesa = ataque.isSpecial() ? equipeOponente.getGuerreiroFrente().getSd() : equipeOponente.getGuerreiroFrente().getDf();
		int forcaDefesa = pontoDefesa + dadoDefesa;
		
		//Dano - Oponente
		int dano = forcaAtaque - forcaDefesa;
		int danoReal = this.aplicarDano(equipeOponente, dano);
		
		//eventual morte do Guerreiro atacado
		Guerreiro guerreiroMorto = null;
		if(equipeOponente.getGuerreiroFrente().getHp() <= 0)
			guerreiroMorto = matarOponente(equipeOponente);
		
		//Pontua��o recebida - Atacante
		this.calcularPontuacaoRecebida(equipeAtacante, equipeOponente, danoReal, guerreiroMorto);
	}

	private void calcularPontuacaoRecebida(Equipe equipeAtacante, Equipe equipeOponente, int danoReal, Guerreiro guerreiroMorto) {
		//Se o Guerreiro oponente n�o morreu
		if(guerreiroMorto == null){
			equipeAtacante.addPontosGanhos(danoReal);
		}else{
			int pontuacaoGuerreiroMorto = this.batalha.getValoresQualidades().get(guerreiroMorto.getQualidade());
			equipeAtacante.addPontosGanhos(danoReal + pontuacaoGuerreiroMorto + (equipeOponente.getPontosTotais() / 3));
		}
	}

	private int aplicarDano(Equipe equipeOponente, int dano){
		if(dano <= 0)
			return 0;
		
		int hp = equipeOponente.getGuerreiroFrente().getHp() - dano;
		
		if(hp <= 0){
			int dadoResistencia = new Integer(JOptionPane.showInputDialog("Dado de resist�ncia: "));
			if(dadoResistencia <= equipeOponente.getGuerreiroFrente().getTo()){
				hp = 1;
			} else {
				hp = 0;
			}
		}
		int hpAnterior = equipeOponente.getGuerreiroFrente().getHp();
		equipeOponente.getGuerreiroFrente().setHp(hp);
		
		return hpAnterior - hp;
	}
	
	/**
	 * Retorno o Guerreiro morto
	 * @param oponente
	 */
	private Guerreiro matarOponente(Equipe oponente) {
		Guerreiro morto = oponente.getGuerreiroFrente();
		
		//Se tiver Guerreuiro atr�s, desloco-o para frente. Sen�o, a equipe perdeu
		if(oponente.getGuerreiroAtras() != null){
			oponente.setGuerreiroFrente(oponente.getGuerreiroAtras());
			oponente.setGuerreiroAtras(null);
		}else{
			oponente.setActive(false);
		}
		return morto;
	}

	private Ataque getAtaque(Guerreiro atacante, String frenteTras) {
		boolean isFisico = false;
		Ataque ataque = null;
		if("F".equalsIgnoreCase(frenteTras)){
			//Ataque F�sico ou Especial
			String fisicoEspecial = JOptionPane.showInputDialog("Ataque F�sico (1) ou Ataque Especial (2): ");
			isFisico = "1".equals(fisicoEspecial) ? true : false;
		}
		
		if(isFisico){
			//Sele��o do ataque f�sico
			String af = JOptionPane.showInputDialog("Punch (0), Kick (1) ou Mega Puch (2): ");
			ataque = this.ataquesFisicos.get(new Integer(af));
		}else {
			//Sele��o do ataque f�sico
			if(atacante.getAtaqueEspecial2() != null){
				String especial = JOptionPane.showInputDialog(atacante.getAtaqueEspecial1().getNome() + " (0) ou " + atacante.getAtaqueEspecial2().getNome() + " (1)");
				ataque = "0".equalsIgnoreCase(especial) ? atacante.getAtaqueEspecial1() : atacante.getAtaqueEspecial2();
			}
		}
		
		return ataque;
	}

	private Equipe getEquipe(String rgb){
		for (Equipe equipe : batalha.getEquipes()) {
			if(equipe.getCor().toUpperCase().charAt(0) == rgb.toUpperCase().charAt(0))
				return equipe;
		}
		return null;
	}
	
	private void prepararBatalha() {
		
		//Valores das Qualidades
		Map<Integer, Integer> valoresQualidades = new HashMap<Integer, Integer>();
		valoresQualidades.put(1, 80);
		valoresQualidades.put(2, 50);
		valoresQualidades.put(3, 40);
		valoresQualidades.put(4, 30);
		valoresQualidades.put(5, 20);
		
		this.batalha = new Batalha(3, valoresQualidades);
		
		//Dados iniciais das equipes
		Equipe green = new Equipe("Green", 400, 20, 3, 100, batalha.getCustoCosmoMinuto());
		Equipe red = new Equipe("Red", 500, 10, 3, 240, batalha.getCustoCosmoMinuto());
		Equipe blue = new Equipe("Blue", 200, 3, 8, 115, batalha.getCustoCosmoMinuto());

		//Sele��o dos Guerreiros de cada equipe para a batalha
		green.setGuerreiroFrente(this.getGuerreiro("Seiya"));
		green.setGuerreiroAtras(this.getGuerreiro("Milo"));
		red.setGuerreiroFrente(this.getGuerreiro("Shiryu"));
		red.setGuerreiroAtras(this.getGuerreiro("Aioria"));
		blue.setGuerreiroFrente(this.getGuerreiro("Hyoga"));
		blue.setGuerreiroAtras(this.getGuerreiro("Orfeu"));

		batalha.getEquipes().add(green);
		batalha.getEquipes().add(red);
		batalha.getEquipes().add(blue);
	}

	private Guerreiro getGuerreiro(String nome){
		for(Guerreiro guerreiro : this.todosGuerreiros){
			if(nome.equals(guerreiro.getNome()))
				return guerreiro;
		}
		return null;
	}
	
	private void loadAtaquesFisicos() {
		this.ataquesFisicos.add(new Ataque(false, 1, 1, 0, "Punch"));
		this.ataquesFisicos.add(new Ataque(false, 1.3f, 3, 0, "Kich"));
		this.ataquesFisicos.add(new Ataque(false, 1.5f, 5, 0, "Mega Puch"));
	}

	private void loadGuerreiros() {
		//Bronze
		this.todosGuerreiros.add(new Guerreiro("Seiya", 3, 2, 3, 2, 3, 3, 5, new Ataque(true, 1.7f, 3, 1,"Pegasus RyuuseiKen")));
		this.todosGuerreiros.add(new Guerreiro("Hyoga", 2, 3, 3, 2, 3, 3, 5, new Ataque(true, 1.7f, 3, 1,"Diamond Dust")));
		this.todosGuerreiros.add(new Guerreiro("Shiryu", 1, 3, 2, 3, 3, 4, 5, new Ataque(true, 2.5f, 3, 1,"Rozan Sho Ryu Ha")));
		this.todosGuerreiros.add(new Guerreiro("Shun", 1, 4, 2, 3, 3, 3, 5, new Ataque(true, 2f, 3, 1,"Nebula Chain")));
		this.todosGuerreiros.add(new Guerreiro("Ikki", 3, 1, 4, 1, 3, 4, 5, new Ataque(true, 1.8f, 3, 1,"Hoyoku Tem Sho")));
		
		//Bronze dourado
		this.todosGuerreiros.add(new Guerreiro("Seiya dourado", 4, 3, 4, 3, 3, 3, 4, new Ataque(true, 1.7f, 3, 1,"Pegasus RyuuseiKen")));
		this.todosGuerreiros.add(new Guerreiro("Hyoga dourado", 3, 4, 4, 3, 3, 3, 4, new Ataque(true, 1.7f, 3, 1,"Diamond Dust")));
		this.todosGuerreiros.add(new Guerreiro("Shiryu dourado", 2, 4, 3, 4, 3, 4, 4, new Ataque(true, 2.5f, 3, 1,"Rozan Sho Ryu Ha")));
		this.todosGuerreiros.add(new Guerreiro("Shun dourado", 2, 5, 3, 4, 3, 3, 4, new Ataque(true, 2f, 3, 1,"Nebula Chain")));
		this.todosGuerreiros.add(new Guerreiro("Ikki dourado", 4, 2, 5, 2, 3, 4, 4, new Ataque(true, 1.8f, 3, 1,"Hoyoku Tem Sho")));

		//Bronze divino
		this.todosGuerreiros.add(new Guerreiro("Seiya divino", 5, 4, 5, 4, 3, 3, 3, new Ataque(true, 1.7f, 3, 1,"Pegasus RyuuseiKen")));
		this.todosGuerreiros.add(new Guerreiro("Hyoga divino", 4, 5, 5, 4, 3, 3, 3, new Ataque(true, 1.7f, 3, 1,"Diamond Dust")));
		this.todosGuerreiros.add(new Guerreiro("Shiryu divino", 3, 5, 4, 5, 3, 4, 3, new Ataque(true, 2.5f, 3, 1,"Rozan Sho Ryu Ha")));
		this.todosGuerreiros.add(new Guerreiro("Shun divino", 3, 5, 4, 5, 3, 3, 3, new Ataque(true, 2f, 3, 1,"Nebula Chain")));
		this.todosGuerreiros.add(new Guerreiro("Ikki divino", 5, 3, 5, 3, 3, 4, 3, new Ataque(true, 1.8f, 3, 1,"Hoyoku Tem Sho")));

		//Prata
		this.todosGuerreiros.add(new Guerreiro("Marin", 2, 5, 2, 4, 2, 2, 2, new Ataque(true, 1.5f, 3, 1,"Eagle Flash")));
		this.todosGuerreiros.add(new Guerreiro("Shina", 2, 5, 2, 3, 2, 3, 2, new Ataque(true, 1.5f, 3, 1,"Thunder Claw")));
		this.todosGuerreiros.add(new Guerreiro("Orfeu", 2, 3, 5, 2, 2, 3, 2, new Ataque(true, 1.6f, 3, 1,"String Requiem")));

		//Ouro
		this.todosGuerreiros.add(new Guerreiro("Mu", 3, 3, 5, 3, 1, 3, 1, new Ataque(true, 1.2f, 3, 1,"Stardust Revolution"), new Ataque(true, 1.6f, 10, 2,"Starlight Extiction")));
		this.todosGuerreiros.add(new Guerreiro("Aldebaran", 5, 3, 5, 2, 1, 2, 1, new Ataque(true, 1.6f, 8, 2,"Greatest Horn")));
		this.todosGuerreiros.add(new Guerreiro("Kanon", 4, 3, 5, 3, 1, 2, 1, new Ataque(true, 1.4f, 3, 1,"Another Dimension"), new Ataque(true, 1.8f, 10, 2,"Galaxian Explosion")));
		this.todosGuerreiros.add(new Guerreiro("Aioria", 5, 2, 5, 2, 1, 3, 1, new Ataque(true, 1.4f, 3, 1,"Lightning Bolt"), new Ataque(true, 1.8f, 10, 2,"Lightning Plasma")));
		this.todosGuerreiros.add(new Guerreiro("Shaka", 1, 5, 5, 4, 1, 2, 1, new Ataque(true, 1.4f, 3, 1,"Tenma Kofuku"), new Ataque(true, 1.8f, 10, 2,"Tenbu Horin")));
		this.todosGuerreiros.add(new Guerreiro("Dohko", 2, 4, 4, 4, 1, 3, 1, new Ataque(true, 1.5f, 3, 1,"Rozan Fight Dragon"), new Ataque(true, 2f, 10, 2,"Rozan Hyaku Ryu Ha")));
		this.todosGuerreiros.add(new Guerreiro("Milo", 4, 2, 5, 3, 1, 3, 1, new Ataque(true, 1.4f, 3, 1,"Scarlet Needle"), new Ataque(true, 1.8f, 10, 2,"Scarlet Needle Antares")));
	}

	public void gravarResultado() {
		for(Equipe equipe : batalha.getEquipes()){
			System.out.println("Equipe: " + equipe.getCor());
			System.out.println("Pontos: " + equipe.getPontosTotais());
			System.out.println("Cosmo: " + equipe.getCosmo());
			System.out.println("\n");
		}
	}
}
