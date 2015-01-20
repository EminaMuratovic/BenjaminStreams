import java.io.*;

import javax.swing.JFileChooser;

public class StreamReadWrite {
	
	//čisti buffer
	//uzima numRead kao broj byteova koji je ispisan jer mozda nije cijeli niz byteova popunjen
	private static void cleanBuffer(byte[] buffer, int numRead) {
		for(int i = 0; i < numRead; i++)
			buffer[i] = 0;
	}
	
	public static void main(String[] args) {
		
		JFileChooser filePicker = new JFileChooser();
		filePicker.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents/workspace"));
		int open = filePicker.showOpenDialog(filePicker);
		if(open != filePicker.APPROVE_OPTION) {
			System.exit(-1);
		
		}
		
		FileInputStream fs = null; //varijabla koja nam sluzi za ubacivanje file-a
		
		//InputStream is;
		
		Reader is;
		
		OutputStream os;
		
		BufferedReader bs;
		
		FileOutputStream ofs; //varijabla koja nam sluzi za upisivanje podataka u file
		
		byte[] inputBuffer = new byte[10]; //pravljenje buffera u koji spasavamo podatke
		
		
		//pravi se 'string' outputBuilder u koji cemo spasavati sve sto bude korisnik unio
		StringBuilder outputBuilder = new StringBuilder(); 
		
		try {
			File pickerFile = filePicker.getSelectedFile();
			
			
			//koristimo kad nema pickerFile
			//fs = new FileInputStream("./File/input.txt"); //dodaje sadrzaj file-a u varijablu
			
			//is = new DataInputStream(fs); //unos podataka, unosi podatke iz file-a
			
										// ako hocemo da korisnik unosi podatke : umjesto fs pisemo System.in
										//kad nemamo BufferedReader
			
			is = new InputStreamReader(fs);
			
			bs = new BufferedReader(is);
			
			
			
			ofs = new FileOutputStream("./File/input.txt", true);
			
			os = new DataOutputStream(ofs); //ispis podataka
			
			
			try {
				int numRead = 0; //broj byteova koje je korisnik unio, kao neki counter
			
			//provjerava da li je broj byteova manji od nule, tj da li je došao do kraja
			//korisnik pritisne CTRL+D kada zeli da zavrsi unos podataka
			//dok god ima podataka stavljamo ga u vec napravljeni 'string' outputBuilder i onda pozivamo f-ju cleanBuffer koja cisti cijeli buffer da bi mogao novi podatak da spasi
			//while petlju koristimo ako nema BufferdReader
//			while(( numRead = is.read(inputBuffer) ) >= 0) { 
//				outputBuilder.append(new String(inputBuffer));
//				cleanBuffer(inputBuffer, numRead);
//			}
			
				String line = "";
				
				while((line = bs.readLine()) != null) {
					outputBuilder.append(line).append("\n");
//					os.write(line.getBytes());
//					os.write("\n".getBytes());
				}
				os.write(outputBuilder.toString().getBytes());
				
			System.out.println("End");
			
			//dvije zadnje linije se pisu ako nema BufferedReader
//			String outputString = outputBuilder.toString(); //pravi novi string u koji ubacuje sve ono sto je korisnik unio
//			
//			//mijenja sysout
//			os.write(outputString.getBytes()); 
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("\nend");
		}
	

	}
}
