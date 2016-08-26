package br.org.esplanada.guerraestudo.interfaces.daemon;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MeuPrimeiroFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public MeuPrimeiroFrame() {
        super("Minha primeira janela");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public static void main(String[] args) {
        final MeuPrimeiroFrame mpf = new MeuPrimeiroFrame();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mpf.setVisible(true);
            }
        });
    }
}
