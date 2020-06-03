import java.util.*;
import java.time.LocalDate;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

interface UGC
{
	public String payFee(int fees);
	// return reg no..
	default int getAdmission(double percentage, final int reg, int AdCount){
		if(percentage >= 60){
			System.out.println("you are selected through UGC");	
			return reg + AdCount+1;		
		}else
			return -1;
	}

}
interface AICTE
{
	public String payFee(int fees);

	default int getAdmission()		// returns token number
	{
		System.out.println("Your counselling is scheduled in 5 days from now");
		LocalDate today = LocalDate.now();
		System.out.println("Date : "+today);
		LocalDate reporting_day =  today.plusDays(5);
		System.out.println("Reporting Date : " + reporting_day);
		return (int)(Math.random()*10012);
	}
}
class University implements UGC, AICTE
{
	
	int fees_due;
	String admission;
	final int reg = 111000; 
	static int totalStudent = 0;

	int getAdmission(double persent)
	{	
		/// first it applyed through UGC
		int take = UGC.super.getAdmission( persent, reg, totalStudent);
		/// if it fails than wait for AICTE
		if(take != -1)
		{
			totalStudent += 1;
			admission = "UGC";
			fees_due = 25000;
		}
		else{
			totalStudent += 1;
			admission = "AICTE";			
			fees_due = 34000;
			return AICTE.super.getAdmission();		/// returns the token no
		}
		return 0;
	}
	public String payFee(int fees)
	{
		if(fees>0)
		{
		   int recept_no = (int)(Math.random()*12345);
		   fees_due = fees_due - fees;
			String status = fees_due < 0 ?"payed": "due";
			return "Recipt No : "+recept_no 
			 	 + "\nDue Amount : " + fees_due 
			     + "\nAmount Diposited : " + fees
			     + "\nStatus : "+ status ;
		}
		else if(fees <= 0){
		   System.out.println("invalid amount" );
			return null;
		}
		return null;
	}

}
class LPU extends University
{ 

	public static void main(String[] args) throws URISyntaxException,IOException
	{
		Scanner scanner = new Scanner(System.in);
	while(true){

		LPU lovely = new LPU();
		System.out.println("\t\tWelcome to lovely ____"); // write it
		System.out.print(
			"\t\t1. Take Admission\n\n"+
			"\t\t2. https://www.lpu.in/\n\n"+
			"\t\t3. Exit\n\n "
		);
		int ch = scanner.nextInt();
		switch (ch) {
			case 1:
				System.out.println("enter your percentage : ");
				float percentage = scanner.nextFloat();
				int result = lovely.getAdmission(percentage);
				while(lovely.fees_due>0){
					System.out.println("your fees is due : "+ lovely.fees_due );
					System.out.println("enter your fees :");
					int amount = scanner.nextInt();
					System.out.println("Recipt : \n" + lovely.payFee(amount) );
				}
				break;
			case 2:
				Desktop wlink = Desktop.getDesktop();
				wlink.browse( new URI("https://www.lpu.in/") );
				break;
			case 3:
				System.exit(0);
				break;
		}
		System.out.println("Press any key to continue");
		scanner.nextInt();
		System.out.print("\033[H\033[2J");  
    	System.out.flush();  

    	}
	}
}
