import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;



public class ServerNit extends Thread{
	
	 BufferedReader ulazniTokOdKlijenta=null;
	 PrintStream izlazniTokKaKlijentu=null;
	 
	 BufferedReader TokOdKlijentaZaPodatke=null;
	 PrintStream TokKaKlijentuZaPodatke=null;
	 
	 Socket soketZaKomunikaciju=null;
	 Socket soketZaPodatke=null;
	 
	 ServerNit[] klijenti;
	/*
	public ServerNit(Socket  soketZaKomunikaciju,Socket soketZaPodatke,ServerNit[] klijent) {
	
		this.soketZaKomunikaciju=soketZaKomunikaciju;
		this.soketZaPodatke=soketZaPodatke;
		this.klijenti=klijent;
	}*/
	public ServerNit(Socket  soketZaKomunikaciju,ServerNit[] klijent) {
		
		this.soketZaKomunikaciju=soketZaKomunikaciju;
		this.klijenti=klijent;
	}
	
	 



	//svaka nit ima svoju run metodu i sluzi za osluskivanje

	@Override
	public void run() {

		String imeKorisnika;
		String linija;
		String operacija;
		
        
        	 
		
		try {
			ulazniTokOdKlijenta=new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			izlazniTokKaKlijentu=new PrintStream(soketZaKomunikaciju.getOutputStream());
			
			
			izlazniTokKaKlijentu.println("Unesite ime:");
			imeKorisnika=ulazniTokOdKlijenta.readLine();
			
			while(true){
			
			operacija=ulazniTokOdKlijenta.readLine();
			
			
			izlazniTokKaKlijentu.println("Unesite brojeve koje želite u izrazu(tako da budu razdvojeni razmakom): ");
			
			
			String brojevi;
			soketZaPodatke = Server.serverSoketZaPodatke.accept();
			TokOdKlijentaZaPodatke=new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));
			TokKaKlijentuZaPodatke=new PrintStream(soketZaPodatke.getOutputStream());
			
			brojevi=TokOdKlijentaZaPodatke.readLine();
			String[] nizbrojeva= brojevi.split(" ");
			
			
			

				
				
		        
		      while(true){
		    	  double resenje=0;
		    	  double prvi;
		  	      double proizvod=1;
		          double drugiSabirak=0;
		          double del;
		        	if(operacija.equals("+")||operacija.startsWith("s")) {
		        		
		        				for (int i = 0; i < nizbrojeva.length; i++) {
		        				resenje+=Double.parseDouble(nizbrojeva[i]); 
		        				
		        			}}
		        			
		        	else if(operacija.equals("-")||operacija.startsWith("o")){
		        		prvi=Double.parseDouble(nizbrojeva[0]);
			        		for (int i = 0; i < nizbrojeva.length-1; i++) {
			        			 
			        			drugiSabirak-=Double.parseDouble(nizbrojeva[i+1]); resenje=prvi+drugiSabirak;	
							}
			        		
			        	}
					
		        	else if(operacija.equals("*")||operacija.startsWith("m")) {
						for (int j = 0; j < nizbrojeva.length; j++) {
							proizvod *=Integer.parseInt(nizbrojeva[j]); 
							resenje=proizvod;
						}}
					 
		        
		        	else if(operacija.equals("/")||operacija.equals("d")){
						del=Double.parseDouble(nizbrojeva[0]);
						for (int j = 0; j < nizbrojeva.length-1; j++) {
							 
							del/=Double.parseDouble(nizbrojeva[j+1]);
							resenje=del;
						}
					}
					
		      	
		            TokKaKlijentuZaPodatke.println(resenje);
		            break;
		      }
		      
			
				
				soketZaPodatke.close();
			
		}
	//ova dva reda ?		
		//soketZaKomunikaciju.close();}
		
		}catch (IOException e) {
		System.out.println(e);
	}
		//oslobadja mesto za novog klijenta
		for (int i = 1; i < klijenti.length; i++) {
			if(klijenti[i]==this){
				klijenti[i]=null;
				System.out.println("Klijent br."+i+"ne zeli vise da se igra sa kalkulatorom.");
			}
			
			
			
		}


}
	}
			
	