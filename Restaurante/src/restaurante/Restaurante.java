package restaurante;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.*;

public class Restaurante implements Runnable{

	final static int defaultPort = 2347;
	static ServerSocket server;
	static Socket clientConn;
	final static int numberOfThreads = 10;
	DataInputStream in;
	DataOutputStream out;
	final static double cover = 5.00;
	static SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy 'at' hh-mm-ss a");
	static FileWriter arq = null;
	static PrintWriter gravarArq = null;
	static Date d = new Date();	
	static String logEntry = "";
	String n = "";
	static String newLine = System.getProperty("line.separator");
	private static Restaurante instance = new Restaurante();

	private Restaurante() {
	} // end Constructor

	public static Restaurante getInstance(Socket ns){
		clientConn = ns;
		return instance;
	}

	public void run() {
		double valor = 0.0;
		int number;
		if((Thread.currentThread().getId()-7)%numberOfThreads == 0){
			number = numberOfThreads;
		}else number = (int)(Thread.currentThread().getId()-7)%numberOfThreads;
		try{
			in = new DataInputStream(clientConn.getInputStream());
			out = new DataOutputStream(clientConn.getOutputStream());
			while (true) {
				try {
					n = in.readUTF();
				}catch(InterruptedIOException iioe) {
					arq.close();
					System.out.println("timeout during client request");
					server.close();
				}// end try-catch


				if (n.contains("-----")) {
					System.out.print("Mesa: "+number+ "\nPedido:\n"+n);
					String[] n1 = n.split("\n");
					for(int i = 0;i < n1.length; i++){
						valor += Float.parseFloat(n1[i].split("- ")[1]);
						logEntry += "Mesa: "+number +" "+ n1[i].substring(0, 7)+" - "+ n1[i].substring(27) + newLine;
					} // end for
				} // end if
				if(n.equalsIgnoreCase("Solicitacao")){
					double valorFinal = valor + valor/10 + cover;
					try {
						out.writeDouble(valorFinal);
					}catch(InterruptedIOException iioe) {
						arq.close();
						System.out.println("timeout during client request");
						server.close();
					}// end try-catch
					if(server.isClosed()) break;
				} // end if
				out.flush();
				if (n.equalsIgnoreCase("Bye")) break;

			} // end while

		} catch (IOException ex) {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end try-catch

		try {	

			System.out.println("Mesa "+number+" encerrada.");
			gravarArq.printf(logEntry);
			logEntry = "";
			clientConn.close();
		} catch (IOException ex) {
			Logger.getLogger(Restaurante.class.getName()).log(Level.SEVERE, null, ex);
		} // end try-catch
	} // end run

	public static void main(String[] args) throws IOException {
		long startValue = System.currentTimeMillis();
		int port = -1;

		try {
			port = Integer.parseInt(args[0]);
		}
		catch (Exception ex) {
		} // // end try-catch

		if (port <= 0 || port >= 65536) port = defaultPort;
		System.out.println(port);
		try {
			arq = new FileWriter("D:\\LogRestaurante\\logRestaurante " + ft.format(d)+".txt");
		} catch (IOException e1) {
			new File(System.getProperty("user.home"), "LogRestaurante").mkdirs();
			arq = new FileWriter(System.getProperty("user.home")+"\\LogRestaurante\\logRestaurante" + ft.format(d)+".txt");
		}// end try-catch
		gravarArq = new PrintWriter(arq);
		gravarArq.printf("logRestaurante "+ ft.format(new Date()) + newLine);

		Executor exec = Executors.newFixedThreadPool(numberOfThreads);
		server = new ServerSocket(port);

		while (true) {
			server.setSoTimeout((1000*60*60*24)/2 /*60000*/ - (int)(System.currentTimeMillis() - startValue));
			try {
				Socket ns = server.accept();
				exec.execute(Restaurante.getInstance(ns));
			}catch(InterruptedIOException iioe) {
				try{
					arq.close();
					System.out.println("Log gerado na pasta LogRestaurante");
				}catch(NullPointerException npe){					
					System.out.println("No log");
				} // end try-catch
				server.close();
				break;
			}// end try-catch
			if(server.isClosed()) break;
		} // end While
		System.out.println("Closing Server");
		System.exit(0);
	}
}