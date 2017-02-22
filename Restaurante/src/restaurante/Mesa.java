package restaurante;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import view.Frame;


public class Mesa extends JFrame{

	private static final long serialVersionUID = 1L;
	final static int defaultPort = 2347;
	public static String pedido;
	public static boolean pedidoOk, conta, pagamento;
	public static double preco = 0;
	static Socket s = null;
	static String order;

	public Mesa(){
		pedido = "";
		pedidoOk = false;
		conta = false;
		pagamento = false;
	}

	private static boolean testConn(int port){
		try {
			InetAddress address = InetAddress.getByName("localhost");
//			System.out.println("Name: " + address.getHostName());
//			System.out.println("Addr: " + address.getHostAddress());
//			System.out.println("Reach: " + address.isReachable(defaultPort));
			return address.isReachable(port);
		}
		catch (UnknownHostException e) {
			System.err.println("Unable to lookup");
			JOptionPane.showMessageDialog(null, "Máquina Indisponível.");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Unable to reach");
			JOptionPane.showMessageDialog(null, "Máquina Indisponível.");
			System.exit(1);
		}
		return false;
	}

	public static void main(String[] args) throws IOException {

		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}

		int port = -1;
		Frame f = new Frame();

		try {
			port = Integer.parseInt(args[0]);
		}catch (Exception ex) {
		} // end try

		if (port <= 1024 || port >= 65536) port = defaultPort;
		System.out.println(port);
		
		testConn(port);

		try{
			s = new Socket("localhost", port);
		}catch(ConnectException ce){
			System.out.println("Servidor offline");
			System.exit(0);
		}
		DataInputStream in = new DataInputStream(s.getInputStream());
		DataOutputStream out = new DataOutputStream(s.getOutputStream());


		while (true) {System.out.print("");
			if(Mesa.pagamento){ 
				f.sair.setEnabled(true);
				f.conta.setEnabled(false);
			}
			if(pedido != f.nota){
				pedido = f.nota;
				if(pedido == "Bye" && pagamento){
					try{
						out.writeUTF(pedido);
					}catch(SocketException se){
						pedido = "";
						pedidoOk = false; conta = false; pagamento = false;
						preco = 0;
						System.out.println("Servidor encerrado. Saída liberada");
						System.exit(0);
					}
					break;
				}else if(pedidoOk){
					conta = true;
					order = pedido;
					JOptionPane.showMessageDialog(null,"Pedido solicitado. \nSua saída será liberada com o pagamento.\nSeu pedido: \n"+ pedido+" Obrigado.");
					try{
						out.writeUTF(pedido);
					}catch(SocketException se){
						pedido = "";
						pedidoOk = false; conta = false; pagamento = false;
						preco = 0;
						System.out.println("Servidor encerrado. Não há pedidos no seu nome, saída liberada");
						break;
					}
					pedidoOk = false;			
				}else if (!pagamento && f.nota == "Efetua pagamento"){ 
					pagamento = true;
					JOptionPane.showMessageDialog(null,"Pagamento Efetuado. Saída liberada.");
				}else if(conta && f.nota == "Solicitacao"){
					pedido = f.nota;
					try{
						out.writeUTF(pedido);
					}catch(SocketException se){
						pedido = "";
						pedidoOk = false; conta = false; pagamento = false;
						preco = 0;
						System.out.println("Servidor encerrado. Chame um atendente com sua conta, e sua saída será liberada");
						break;
					}
					String[] p = {"Efetuar pagamento"};
					try{
						preco = in.readDouble();
					}catch(SocketException se){
						pedido = "";
						pedidoOk = false; conta = false; pagamento = false;
						preco = 0;
						System.out.println("Servidor encerrado. Chame um atendente com sua conta, e sua saída será liberada");
						break;
					}
					if(JOptionPane.showOptionDialog(null, "Consumo:\n" + order +"Valor a ser pago, incluido cover e 10%: "+ preco + "\n"
							+ "Clique no botão abaixo para efetuar o pagamento", 
							null, JOptionPane.OK_OPTION, 0, null, p, p[0])
							== JOptionPane.OK_OPTION){
						f.nota = "Efetua pagamento";
						conta = false;						
					}// end if
				}
			}
			pedido = "";
		}
		in.close();
		out.close();

		try {
			s.close();
		} catch (IOException ex) {
			Logger.getLogger(Restaurante.class.getName()).log(Level.SEVERE, null, ex);
		} // end try
		System.exit(0);
	}
}
