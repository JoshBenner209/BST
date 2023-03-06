/*
  Josh Benner
  Lab 6
  CS 145
  Dictionairy manages and manipulate the BST.
  It provides the users with easy to use menu
  to store employee in a data base without having
  to know about BST. 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionairy {
    private SearchTree<Integer> tree;
    private Map <Integer,PersonInfo>mapIntToPerson;
    private Scanner scan;
    //constructor 
    public Dictionairy() {
	tree=new SearchTree<Integer>();
	mapIntToPerson= new HashMap<>();
	scan=new Scanner(System.in); 
	
    }
    // add info for person
    public void add() {
		
	System.out.println("Enter first name:");
	String firstName=scan.next();
	scan.nextLine();
	System.out.println("Enter Last Name:");
	String LastName=scan.next();
	scan.nextLine();
	System.out.println("Enter Email Address:");
	String email=scan.next();
	scan.nextLine();
	System.out.println("Enter phone Number:");
	String phoneNumber=scan.nextLine();
	System.out.println("Enter Address:");
	String address=scan.nextLine();
	System.out.println("Enter City:");
	String city=scan.next();
	scan.nextLine();
	System.out.println("Enter state:");
	String state= scan.next();
	scan.nextLine();
	System.out.println("Enter zip Code:");
	String zip= scan.next();
	scan.nextLine();
	boolean exist=true;
	while(exist) {
	    System.out.println("Enter primary key: ");
	    int key= scan.nextInt();
	    scan.nextLine();	
	    if (!verify(key)) {
		exist=false;
		PersonInfo person=new PersonInfo(firstName,LastName,
						 state,address,phoneNumber,city,zip,email);
		this.tree.add(key);
		this.mapIntToPerson.put(key, person);
		System.out.println("\nadded:\n"+person.toString()+"\nkey for "+firstName+": "+key+"\n");
	    }
    }
    }
    
    //print in order		
    public String printInOrder() {
	String print="";
	List <Integer>result=new ArrayList<>();
	result=tree.printInOrder();
	for(int i: result) {
	    print+="key: "+i;
	    print+="\n";
	    PersonInfo store=mapIntToPerson.get(i);
	    print+=store.toString()+"\n";
	    print+="\n";
	}
	return print;
		
		
    }
    // get size of dataBase
    public int getSize() {
	List<Integer> list=tree.printPostOrder();
	return list.size();
    }


    // print in order
    public String printPreOrder() {
	String print="";
	List <Integer>result=new ArrayList<>();
	result=tree.printPreOrder();
	for(int i: result) {
	    print+="key: "+i;
	    print+="\n";
	    PersonInfo store=mapIntToPerson.get(i);
	    print+=store.toString()+"\n";
	    print+="\n";
	}
	return print;
    }
    //print postOrder	
    public String printPostOrder() {
	String print="";
	List <Integer>result=new ArrayList<>();
	result=tree.printPostOrder();
	for(int i: result) {
	    print+="key: "+i;
	    print+="\n";
	    PersonInfo store=mapIntToPerson.get(i);
	    print+=store.toString()+"\n";
	    print+="\n";
	}
	return print;
    }
    // remove an employee
    //param key is the key that maps to employee
    public void remove(int key) {
		
	tree.delete(key);
    }
    //edit an employee
    //param key is the key that maps to an employee
    public void edit(int key) {

	boolean done=true;
	while(done) {
	    System.out.println("to edit field enter:\nf: first name \nl: lastname"+
			       "\ne:email\na: adrees\np:phone number\nz:zip\ns:state\nc:city\nq:quit\n");
	    String value=scan.next();
	    scan.nextLine();
	    done = edit(key, value);
			
	}
    }
    // menu to edit employee fields
    // param key is the key to map to employee
    //param value is the one of the case options
    private boolean edit(int key, String value ) {
	PersonInfo person= mapIntToPerson.get(key);
	switch(value.substring(0).toLowerCase()) {
	case "f":
	    System.out.println("enter First Name: ");
	    String firstName=scan.next();
	    scan.nextLine();
	    person.setFirstName(firstName);
	    break;
	case "l":
	    System.out.println("Enter Last Name: ");
	    String lastName= scan.next();
	    scan.nextLine();
	    person.setLastName(lastName);
	    break;
	case "e":
	    System.out.println("enter email: ");
	    String email= scan.next();
	    scan.nextLine();
	    person.setEmail(email);
	    break;
	case "a":
	    System.out.println("enter address: ");
	    String adress=scan.nextLine();
	    person.setAddress(adress);
	    break;
	case "p":
	    System.out.println("enter phone Number: ");
	    String phoneNum =  scan.nextLine();
	    person.setPhoneNumb(phoneNum);
	    break;
	case "z":
	    System.out.println("enter zip: ");
	    String zip= scan.next();
	    scan.nextLine();
	    person.setZip(zip);
	    break;
	case "s":
	    System.out.println("enter State: ");
	    String state= scan.next();
	    scan.nextLine();
	    person.setState(state);
	    break;
	case "c":
	    System.out.println("enter city: ");
	    String city= scan.nextLine();
	    person.setCity(city);
	    break;
	case "q":
	    return false;
	default:
	    System.out.println("invalid input");
	}
	return true;
    }
    //verify that the tree contains key
    // param key is the value stored in tree
    private boolean verify(int key) {
	if(tree.contains(key)){
	    return true;
	}else {
	    System.out.println("key not found");
	    return false;
	}
    }
    // the main menu displayed
    //returns false if q is entered
    public boolean menu() {
	boolean done=false;
	while (!done) {
	    ASCIIARt art= new ASCIIARt();
	    art.print(16);
	    System.out.println("\na to (a)dd record\nd to (d)elete record\nm to (m)odify record\nl to (l)ook up"+
			       "\nlt to (l)is(t) number of records\n q to (q)uit\n");
	    String result=scan.next();
	    scan.nextLine();
	    switch(result.toLowerCase()) {
	    case "a"://add person
		add();
		break;
	    case "d"://delet person
		//display keys and who they map to here
		System.out.println("\nenter key of person to delete: ");
		int key= scan.nextInt();
		scan.nextLine();
		if(verify(key)) {
		    if(confirmation(key,"delet")){
			remove(key);
		    }
		}
		break;
	    case "m"://modify person object
		//display keys and who they map to here
		System.out.println("enter key of record to edit");
		int key1 =scan.nextInt();
		scan.nextLine();
		if(verify(key1)) {
		    if(confirmation(key1, "edit")) {
			edit(key1);
		    }
		}
		break;
	    case "l"://look up
		System.out.println(" 1 for in-order\n2 for post-order\n3 for pre-order");
		int order = scan.nextInt();
		if(order==1) {
		    System.out.println(printInOrder());
		}else if(order==2) {
		    System.out.println(printPostOrder());
		}else {
		    System.out.println(printPreOrder());
		}
			
		break;
	    case"lt"://list size of data base
		System.out.println("number of records: "+getSize());
		break;
	    case"q"://quit
		done=false;
		return false;
			
	    default:
		System.out.println("invalid input");
	    }
	}
				
		
	return true;	
    }
    //confirm that the action about to perform is what user wants to do
    //param key is the key that maps to a person
    //param action is the action the user want to perfrom
    //return true if user confirms action
    private boolean confirmation(int key,String action) {
	System.out.println("\nenter y to "+action+ " \" c\" to cancel):\n "+mapIntToPerson.get(key));
	String y= scan.next();
	scan.nextLine();
	if(y.toLowerCase().equals("y")) {
	    return true;
	}
	return false;
    }

}




