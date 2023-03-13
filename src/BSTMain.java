

/*Josh Benner
  CS 145
  Lab 6
  March 6 2023
  This is the main class that test the program.
 */

public class BSTMain {

    public static void main(String[] args) {
        //uncoment to use the add employee
        //Dictionairy dictionairy=new Dictionairy();// manage class
	    //boolean play=true;
	    //while(play) {
	    //play=dictionairy.menu();// returns false when "q" is entered
        //}
        // test the tree by filling it with different elements to loop through
        DictionairyFrame frame = new DictionairyFrame();
        
        //testTree(25);
	}
	public static void testTree(int elements){
        SearchTree<Integer> avlTree = new SearchTree<Integer>();
        for (int i= 1; i<elements; i++){
            avlTree.add(i);
           
        }
        System.out.println((avlTree.printPreOrder()));
    }
}
