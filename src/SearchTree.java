/*
  Josh Benner
  CS 145
  Lab 6
  March 6
  SearchTree stores and prints a binary search tree of
  objects of type E.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.spi.DirStateFactory.Result;


public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode<E> overallRoot; // root of overall tree
    private int balanceFactor;
    private int height;
    //constructs an empty search tree
    public SearchTree() {
        overallRoot = null;
        height=height();
        balanceFactor= balanceFactor();
    }
    public int height() {
        if(overallRoot==null){
            return 0;
        }// hef

        return result;
        
    }
    //add adds value to tree
    //param value is the data to be stored in root
    public void add(E value) {
        overallRoot = add(overallRoot, value);
    }

    //add creates a new searchtree node and adds the value as data
    // param root is the root of the current tree/subtree
    // param value is the data to be added to tree
    private SearchTreeNode<E> add(SearchTreeNode<E> root, E value) {
        if (root == null) {
            root = new SearchTreeNode<E>(value);
        } else if (root.data.compareTo(value) >= 0) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
            update(root);
        return balance(node);
        //return root;
    }

    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(overallRoot, value);
    }   

    //returns boolean value false if tree doesnt contain value and true if
    //it does
    //param root is the root of tree/subtree
    //param value is the value that is being searched in tree
    private boolean contains(SearchTreeNode<E> root, E value) {
        if (root == null) {
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(root.left, value);
            } else {   // compare > 0
                return contains(root.right, value);
            }
        }
    }

    // prints the tree in -order
    //returns array list of this order
    public List<E> printInOrder() {
        return printInorder(overallRoot);
    }

    //does the work for public method above
    private List<E> printInorder(SearchTreeNode<E> root) {
        List<E> list= new ArrayList<>();
    	if (root != null) {
            list.addAll(printInorder(root.left));
            list.add(root.data);
            System.out.println(root.data);//debug print 
            
            list.addAll(printInorder(root.right));
        }
    	return list;
    }
    
    // prints in pre order
    //returns a list of pre order 
    public List<E> printPreOrder(){
	   return printPreOrder(overallRoot);  
   }

    //does work for public method above
    private List<E> printPreOrder(SearchTree.SearchTreeNode<E> root) {
    	List<E> list= new ArrayList<>();
    	if (root != null) {
    		  list.add(root.data);
    		  System.out.println(root.data);//debug print 
    		  list.addAll(printInorder(root.left));  
    		  list.addAll(printInorder(root.right));
        }
   
    	return list;
    }
    //print post-order
    //return list of post-order
    public List<E> printPostOrder(){
    	return printPostOrder(overallRoot);
    }
    // does work for public method above
    private List<E> printPostOrder(SearchTree.SearchTreeNode<E> root) {
	   	List<E> list= new ArrayList<>();
    	if (root != null) {
    		
    		  list.addAll(printInorder(root.left));  
    		  list.addAll(printInorder(root.right));
    		  list.add(root.data);
    		  //System.out.println(root.data); // debug print 
        }
   
    	return list;

	}
    
    //calls private delete 
    public void delete(E value) {
		overallRoot=delete(overallRoot,value);  
	
	}
	
    //find node containg value and modifys the node.
    // param root is the current root/ subtree
    //param value is the value being looked for
    //return root is the modifyed root. 
    private SearchTreeNode<E> delete (SearchTree.SearchTreeNode<E> root, E value) {
		if(root==null) {
			return null;
		
		} else if(root.data.compareTo(value) >0) {
			
			root.left=delete(root.left,value);
		
		} else if(root.data.compareTo(value) <0) {
			
			root.right=delete(root.right,value);
		}else {
			if(root.right==null) {
				return root.left;
			}else if(root.left==null) {
				return root.right;
			}else {
				root.data=getMin(root.right);
				root.right=delete(root.right,root.data);
			}
		}
		return root;	  
	}

    //serches for the minimum value in tree
    public E getMin() {
		if(overallRoot==null) {
			throw new NoSuchElementException();
		}
		return getMin(overallRoot);
	}
    // param root is the tree/subree
    //return E is the minimum value found
    private E getMin(SearchTree.SearchTreeNode<E> root) {
		if (root.left==null) {
			return root.data;
		}else {
			return getMin(root.left);
		}
	}
    // static inner class where the object represents a node
    // in the BST

	private static class SearchTreeNode<E> {
        public E data;                   // data stored in this node
        public SearchTreeNode<E> left;   // left subtree
        public SearchTreeNode<E> right;  //  right subtree

        //  constructs a leaf node with given data
        public SearchTreeNode(E data) {
            this(data, null, null);
        }

        // constructs a node with the given data and links
        public SearchTreeNode(E data, SearchTreeNode<E> left,
                              SearchTreeNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}

