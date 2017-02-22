package view;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import restaurante.Mesa;


public class Frame {

	public String nota = ""; 
	private JFrame window;
	private JLabel hello;
	private JPanel painelBotoes, cardapio, painelPrincipal;
	public JButton pedido, conta, sair;

	private JCheckBox prato01, prato02 ,prato03 , prato04, prato05, prato06, prato07, prato08, prato09, prato10, prato11, prato12, 
	prato13, prato14, prato15, prato16, prato17, prato18, prato19, prato20, prato21, prato22, prato23, prato24,
	prato25;
	private JScrollPane scroll;

	public Frame(){
		montaTela();
	}

	void montaTela(){
		preparaJanela();
		preparaPainelPrincipal();
		preparaPainelTitulo();
		preparaCardapio();
		preparaCheckBox();
		preparaPainelBotoes();  
		preparaBotaoPedido();
		preparaBotaoConta();
		preparaBotaoSair();
		mostraJanela();
	}

	private void preparaCardapio(){
		cardapio = new JPanel();
		cardapio.setLocation(100, 100);

		cardapio.setLayout(new BoxLayout(cardapio, BoxLayout.Y_AXIS));
		painelPrincipal.add(cardapio,BorderLayout.WEST);
	}

	private void preparaCheckBox(){
		prato01 = new JCheckBox("Prato01                                                                                         - 5.00");
		prato02 = new JCheckBox("Prato02                                                                                         - 12.00");
		prato03 = new JCheckBox("Prato03                                                                                         - 7.00");
		prato04 = new JCheckBox("Prato04                                                                                         - 10.00");
		prato05 = new JCheckBox("Prato05                                                                                         - 10.00");
		prato06 = new JCheckBox("Prato06                                                                                         - 10.00");
		prato07 = new JCheckBox("Prato07                                                                                         - 10.00");
		prato08 = new JCheckBox("Prato08                                                                                         - 10.00");
		prato09 = new JCheckBox("Prato09                                                                                         - 10.00");
		prato10 = new JCheckBox("Prato10                                                                                         - 10.00");
		prato11 = new JCheckBox("Prato11                                                                                         - 10.00");
		prato12 = new JCheckBox("Prato12                                                                                         - 10.00");
		prato13 = new JCheckBox("Prato13                                                                                         - 10.00");
		prato14 = new JCheckBox("Prato14                                                                                         - 10.00");
		prato15 = new JCheckBox("Prato15                                                                                         - 10.00");
		prato16 = new JCheckBox("Prato16                                                                                         - 10.00");
		prato17 = new JCheckBox("Prato17                                                                                         - 10.00");
		prato18 = new JCheckBox("Prato18                                                                                         - 10.00");
		prato19 = new JCheckBox("Prato19                                                                                         - 10.00");
		prato20 = new JCheckBox("Prato20                                                                                         - 10.00");
		prato21 = new JCheckBox("Prato21                                                                                         - 10.00");
		prato22 = new JCheckBox("Prato22                                                                                         - 10.00");
		prato23 = new JCheckBox("Prato23                                                                                         - 10.00");
		prato24 = new JCheckBox("Prato24                                                                                         - 10.00");
		prato25 = new JCheckBox("Prato25                                                                                         - 10.00");

		CheckBoxHandler tratador = new CheckBoxHandler();
		prato01.addItemListener(tratador);
		prato02.addItemListener(tratador);
		prato03.addItemListener(tratador);
		prato04.addItemListener(tratador);
		prato05.addItemListener(tratador);
		prato06.addItemListener(tratador);
		prato07.addItemListener(tratador);
		prato08.addItemListener(tratador);
		prato09.addItemListener(tratador);
		prato10.addItemListener(tratador);
		prato11.addItemListener(tratador);
		prato12.addItemListener(tratador);
		prato13.addItemListener(tratador);
		prato14.addItemListener(tratador);
		prato15.addItemListener(tratador);
		prato16.addItemListener(tratador);
		prato17.addItemListener(tratador);
		prato18.addItemListener(tratador);
		prato19.addItemListener(tratador);
		prato20.addItemListener(tratador);
		prato21.addItemListener(tratador);
		prato22.addItemListener(tratador);
		prato23.addItemListener(tratador);
		prato24.addItemListener(tratador);
		prato25.addItemListener(tratador);

		cardapio.add(prato01);
		cardapio.add(prato02);
		cardapio.add(prato03);
		cardapio.add(prato04);
		cardapio.add(prato05);
		cardapio.add(prato06);
		cardapio.add(prato07);
		cardapio.add(prato08);
		cardapio.add(prato09);
		cardapio.add(prato10);
		cardapio.add(prato11);
		cardapio.add(prato12);
		cardapio.add(prato13);
		cardapio.add(prato14);
		cardapio.add(prato15);
		cardapio.add(prato16);
		cardapio.add(prato17);
		cardapio.add(prato18);
		cardapio.add(prato19);
		cardapio.add(prato20);
		cardapio.add(prato21);
		cardapio.add(prato22);
		cardapio.add(prato23);
		cardapio.add(prato24);
		cardapio.add(prato25);

		scroll = new JScrollPane(painelPrincipal);
		window.add(scroll,BorderLayout.CENTER);

	}

	private class CheckBoxHandler implements ItemListener{


		@Override
		public synchronized void itemStateChanged(ItemEvent evento) {
			nota = "";			

			if(prato01.isSelected()){
				String s = prato01.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato02.isSelected()){
				String s = prato02.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato03.isSelected()){
				String s = prato03.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato04.isSelected()){
				String s = prato04.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato05.isSelected()){
				String s = prato05.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato06.isSelected()){
				String s = prato06.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato07.isSelected()){
				String s = prato07.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato08.isSelected()){
				String s = prato08.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato09.isSelected()){
				String s = prato09.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato10.isSelected()){
				String s = prato10.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato11.isSelected()){
				String s = prato11.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato12.isSelected()){
				String s = prato12.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato13.isSelected()){
				String s = prato13.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato14.isSelected()){
				String s = prato14.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato15.isSelected()){
				String s = prato15.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato16.isSelected()){
				String s = prato16.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato17.isSelected()){
				String s = prato17.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato18.isSelected()){
				String s = prato18.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato19.isSelected()){
				String s = prato19.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato20.isSelected()){
				String s = prato20.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato21.isSelected()){
				String s = prato21.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato22.isSelected()){
				String s = prato22.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato23.isSelected()){
				String s = prato23.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato24.isSelected()){
				String s = prato24.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(prato25.isSelected()){
				String s = prato25.getText();
				String[] s2 = s.split("- ");
				//System.out.println(s2[0]+","+s2[1]);
				nota += s2[0].trim() +" ------------------ "+ s2[1] + "\n";
				pedido.setEnabled(true);
			}

			if(!(prato01.isSelected() || prato02.isSelected() || prato03.isSelected() || prato04.isSelected() || prato05.isSelected()
					|| prato06.isSelected() || prato07.isSelected() || prato08.isSelected() || prato09.isSelected() || prato10.isSelected()
					|| prato11.isSelected() || prato12.isSelected() || prato13.isSelected() || prato14.isSelected() || prato15.isSelected()
					|| prato16.isSelected() || prato17.isSelected() || prato18.isSelected() || prato19.isSelected() || prato20.isSelected()
					|| prato21.isSelected() || prato22.isSelected() || prato23.isSelected() || prato24.isSelected() || prato25.isSelected())
					) pedido.setEnabled(false);
		}
	}

	private void preparaPainelTitulo(){
		hello = new JLabel("Bem Vindo!!");
		hello.setFont( new Font("SansSerif", Font.ITALIC, 20));
		hello.setHorizontalAlignment(SwingConstants.CENTER);
		painelPrincipal.add(hello,BorderLayout.NORTH);
	}
	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		window.add(painelPrincipal);
	}

	private void preparaJanela() {
		window = new JFrame("Cardápio");
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setSize(400, 600);		
		window.setLocationRelativeTo(null);
		window.setResizable(false);
	}

	private void mostraJanela() {
		window.setVisible(true);		
	}

	private void preparaPainelBotoes() {
		painelBotoes = new JPanel(new GridLayout());
		painelBotoes.setLayout(new GridLayout(1,3,10,10));
		painelBotoes.setSize(400, 50);
		painelPrincipal.add(painelBotoes);		
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void preparaBotaoPedido(){
		pedido = new JButton("Fazer pedido");
		painelBotoes.add(pedido);
		pedido.setEnabled(false);


		pedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				prato01.setEnabled(false);prato02.setEnabled(false);prato03.setEnabled(false);prato04.setEnabled(false);
				prato05.setEnabled(false);prato06.setEnabled(false);prato07.setEnabled(false);prato08.setEnabled(false);
				prato09.setEnabled(false);prato10.setEnabled(false);prato11.setEnabled(false);prato12.setEnabled(false);
				prato13.setEnabled(false);prato14.setEnabled(false);prato15.setEnabled(false);prato16.setEnabled(false);
				prato17.setEnabled(false);prato18.setEnabled(false);prato19.setEnabled(false);prato20.setEnabled(false);
				prato21.setEnabled(false);prato22.setEnabled(false);prato23.setEnabled(false);prato24.setEnabled(false);
				prato25.setEnabled(false);pedido.setEnabled(false);
				synchronized(this){
					Mesa.pedidoOk = true;
					conta.setEnabled(true);
				}
			}
		}
				);
	}

	private void preparaBotaoConta(){
		conta = new JButton("Conta");
		painelBotoes.add(conta);
		conta.setEnabled(false);

		synchronized(this){
			conta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nota = "Solicitacao";
					pedido.setEnabled(false);
				}
			}
					);
		}
	}

	private void preparaBotaoSair() {
		sair = new JButton("Sair");
		painelBotoes.add(sair);	
		sair.setEnabled(false);

		synchronized(this){
			sair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nota = "Bye";
					System.exit(0);
				}
			}
					);
		}
	}

}
