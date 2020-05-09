import java.time.LocalDateTime;
import java.util.*;
/**
  * Q5: Write a java program to implement “Restaurant Management System”. 
  * In which you have to include at least three from the given topics as required: 
  * Inheritance, Overriding Methods, Polymorphism, Abstract Classes, Nested Classes, 
  * Interfaces, Lambda Expressions, Exceptional Handling and I/O Fundamentals. 
  * Avoid copying from given solution on website. 
  * Try to implement using the concept covered in the class. 
  * Solution must be unique for each student.
  * 
  * Roll Number: 57
  **/

//
//	Name    	 : Pradeep Suthar
//	e-mail  	 : sutharp777@gmail.com
//	git-hub		 : "https://github.com/sutharp777/Restorent-Management-System-In-Java"
//	submitted to : Shailja Sharma

//		concepts used
//		1.  interface
//		2.  overrding
//		3.  overloading
//		4.  Exceptional Handling 
//		5.  Polymorphism
//		6.  I/O Fundamentals

interface Organization 
{
	void clearScreen();
	void run();
}

class Order
{
	int ordre_id;
	LocalDateTime ordre_time;
	static int id_series = 1100000;
	static int id_count = 0;
	SortedMap <String, Integer> order_list;
	SortedMap <String, Float> price_list;
	
	Order(SortedMap <String, Integer> list, SortedMap <String, Float> price_lists)
	{

		ordre_time = LocalDateTime.now();
		ordre_id = id_series + id_count+1;
		id_count++;
		order_list = list;
		price_list = price_lists;
	}

	public String toString(){
		return ""+ordre_id;
	}
}

class Recipt
{
	boolean payed;	// false
	Order order;
	float bill_amount;
	Recipt(Order order){
		this.order = order;
	}
	public void pay(){
		Scanner sc = new Scanner(System.in);
		float bill = sc.nextFloat();
		if(bill >= bill_amount){
			payed = true;
			bill_amount = 0;
		}
		else{
			System.out.println("plese give at least "+ bill_amount );
		}
		// sc.close();
	}

	public String toString(){
		String list = "\n";
		int item_count = 0;
		Float price = 0.0f;
		Float final_price = 0.0f;
		Float total = 0.0f;
		for(String item : order.order_list.keySet()){
			price = order.price_list.get(item);
			item_count = order.order_list.get( item );
			final_price = price*item_count;
			total += final_price;
			list += item+" > "+ item_count+" x "+ price +" : " +final_price+"\n"; 
			}
		bill_amount = total;
		return "Date and Time > " + order.ordre_time
		+"\nOrdre id > "+order.ordre_id
		+list+"\ntotal bill > "+total;
	}

}

class Restorent implements Organization{
	SortedMap<String, Float> price_list = new TreeMap<String, Float>();
	HashMap<Integer, String> index_to_item = new HashMap<Integer, String>();

	@Override
	public void clearScreen()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// overloaded funciton
	void setMenu(SortedMap<String, Float> list){
		price_list = new TreeMap<String, Float> (list);
	}

	void setMenu(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number Of Items in Menu > ");

		int n = sc.nextInt();

		System.out.print("Enter " + n + " Items Name and Price\n");
		System.out.print("Item Name :	 Items Price\n");

		String item_name = null;
		Float item_prece = 0.0f;

		for(int i=0;i<n;i++){

			item_name = sc.next();
			item_prece = sc.nextFloat();
			price_list.put(item_name, item_prece);

		}

		// sc.close();
	}

	void getMenu(){

		String space = "    ";
		String endl = "\n";
		System.out.println(" Restorent Menu");
		System.out.print(space + "Item Name " + space + "Price" + endl );

		Float price = null;
		int i = 1;
		
		index_to_item.clear();
		
		for (String item:price_list.keySet()) {
		
			price = price_list.get(item);
			index_to_item.put( i, item);
			System.out.print(space+"["+i++ +"]"+ space + item + space + price + endl );
		}

	}

	@Override
	public void run(){

		boolean close = false;
		String space = "\t\t";
		Scanner sc = new Scanner(System.in);
		TreeMap<String,Integer> input_list = null;

		while(!close){

			System.out.print("\n"+
				space + "Select from following \n"+
				space + "[1] press for Order\n"+
				space + "[2] press for Dishes Menu\n"+
				space + "[3] press for Help\n"+
				space + "[4] press for Exit\n");

			String select = sc.next();

			switch(select){

				case "1":
					clearScreen();
					getMenu();
					System.out.println("Select your choice from menu");
					System.out.println("Number of Items You Want >");
					int n = sc.nextInt();
					int item_number = 0;
					int item_quntity = 0;
					String item_name = null;
					input_list = new TreeMap<String,Integer>();

					System.out.print("\nEnter Item Number And Quantity > ");
					for(int i=0;i<n;i++){
						item_number = sc.nextInt();
						item_quntity = sc.nextInt();
						item_name  = index_to_item.get( item_number );
						input_list.put(item_name, item_quntity);
						
						}

						Order order = new Order(input_list, price_list);

						Recipt recipt = new Recipt(order);

						System.out.println(recipt);

						while(!recipt.payed){
							recipt.pay();
					}
					System.out.println("Thank you for Come to Our Restorent");

					break;

				case "2":
					clearScreen();
					getMenu();
					break;

				case "3":
					System.out.println(space+"Application developed by Pradeep ");
					System.out.println(space+"https://github.com/sutharp777/Restorent-Management-System-In-Java");

					break;

				case "4":
					close = true;
					break;

				default:
					System.out.println("enter valid key ..");

			}

			System.out.print(space + "Press any key and enter to continue ... ");
			sc.next();
			clearScreen();
		}
		// sc.close();

	}

}

class Test {
	public static void main(String[] args) {

						// please avoid spelling mistake

		Scanner sc = new Scanner(System.in);
		String space = "\t\t\t";
		System.out.println(	space + "_______________________________\n");
		System.out.println(	space + "Restorent Management System");
		System.out.println(	space + "_______________________________");
		System.out.println(	space + ": Developer    > Pradeep Suthar");
		System.out.println(	space + ": Submitted To > Shailja Sharma\n");
		System.out.println(	space + "Enter The Details of Restorent");
		Restorent lovely = new Restorent();
		int choice = 0;
		try{

			System.out.println(	space + "[1]	Enter Available Dishes");
			System.out.println(	space + "[2]	Default");
			System.out.println(	space + " > ");
			choice = sc.nextInt();
			TreeMap<String,Float> list = new TreeMap<String, Float>();
			list.put( "Meal", 40.5f );
			list.put( "Rice", 30.0f );
			list.put( "Dosa", 40.0f );
			list.put( "Itly", 40.0f );
			list.put( "Roti", 50.0f );

			switch(choice){
				case 1:
					lovely.setMenu();
					break;		
				case 2:
					lovely.setMenu(list);
					break;
				default:
					lovely.setMenu(list);
			}
			lovely.clearScreen();
			lovely.run();
		
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			System.out.println("thank you");
		}
	}
}

/*
test case:
Meal 40.5
Rice 30.0 
Dosa 40.0
Itly 40.0
Roti 50.0
*/
