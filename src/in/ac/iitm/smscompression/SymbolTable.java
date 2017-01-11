package in.ac.iitm.smscompression;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class SymbolTable<Key extends Comparable<Key>, Value extends Comparable<Value>> implements Iterable<Key> {
    
    private Node root;                      // root of SymbolTable
    private int N;                          // number of nodes
    
    private class Node {
        private Key key;                    // sorted by key
        private Value val;                  // associated data
        private Node left, right;           // left and right subtrees
        
        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            N++;
        }
    }
    
    // Create a symbol table
    public SymbolTable() {        
        root = null;
        N = 0;        
    }
    
    // number of key-value pairs in the table
    public int size() {        
        return N;
    }
       
    /***************************************************************************
     * Insert key-value pair into the SymbolTable
     * If key already exists, update with new value
     * @param key
     * @param value 
     **************************************************************************/
    public void put(Key key, Value value) {
        if(value == null) delete(key);
        root = insert(root, key, value);
    }
    
    public void printOut() {
        print(root);
    }
    
    private Node insert(Node x, Key key, Value value) {
        if(x == null) return new Node(key, value);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = insert(x.left, key, value);
        else if (cmp > 0) x.right = insert(x.right, key, value);
        else x.val = value;
        return x;
    }
    
    /***************************************************************************
     * Search SymbolTable for given key, and return associated value if found,
     * return null if not found 
     **************************************************************************/
    
    // does there exist a key-value pair with given key?
    public boolean contains(Key key) {
        return get(key) != null;
    }
    
    // return value associated with the given key, or null if no such key exists
    public Value get(Key key) {
        Node x = root;
        while(x != null) {
            int cmp = key.compareTo(x.key);
            //StdOut.println(cmp);
            if  (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;        
    }
    
    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        //StdOut.println(x.key);
        print(x.right);
    }
          
    /***************************************************************************
     * Delete the key and associated value
     * @param key 
     **************************************************************************/    
    public void delete(Key key) {
        throw new RuntimeException("Delete operation not supported");
    }
    
    /***************************************************************************
     *  Iterate using an in-order traversal. Implement with a stack.
     *  Iterating through N elements takes 0(N) time.
     **************************************************************************/
    @Override
	public Iterator<Key> iterator() {
        return new Inorder();
    }
    
    // An Iterator
    private class Inorder implements Iterator<Key> {
        private Stack<Node> stack = new Stack<Node>();
        
        public Inorder() {
            pushLeft(root);
        }
        
        // don't implement remove() - its optional
        @Override
		public void remove()        {throw new UnsupportedOperationException();}
        @Override
		public boolean hasNext()    {return !stack.isEmpty();}
        
        @Override
		public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node x = stack.pop();
            pushLeft(x.right);
            return x.key;
        }
        
        public void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }
    }  
}