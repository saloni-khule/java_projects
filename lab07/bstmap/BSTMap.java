package bstmap;
import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    public BSTMap(){
        clear();

    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    //MAKE A NODE CLASS WITH KEY, VALUE,, AND NODES LEFT AND RIGHT
    //AND MAKE A CONDTRUCTOR NODE(K,V)

//MAKE A SIZE PRIVATE VAR
    //MAKE A PRIVATE NODE ROOT
    //
    //CONTAINSKEY FUNC:: MAKE A HELPER FUNC FOR IT...HELPER TAKES IN ROOT(CURR) AND KEY....
    // FOR HELPER, IF CURR ==NULL, RETURN FALSE, THEN COMPARE, DECIDE WHETHER TO GO LEFT OR RIGHT ...FOR THIS USE
    //COMPARATOR METHOD

    //FOR GET FUNC: GENERALIZE THE PREVIOUS HELPER METHOD, RETURN NODE INSTEAD OF BOOLEAN


    //PUT FUNC ---MAKE HELPER FUNC WITH ROOT,KEY AND A VALUE, --- COPY PASTE PREV HELPER,,,NOW MODIFY...
    //

    //clear-- root=null, size =0;


    private class Node{

        private K key;
        private V value;
        private Node left;
        private Node right;
        private Node( K key,  V value){

            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    private Node root;

    private int size;


    public void clear(){
        root = null;
        size =0;
    }

    public boolean containsKey(K key){

        return helper(root, key)!=null;






    }


    private Node helper(Node curr, K key ){

        if (curr == null) {
            return null;
        }
        else if (key.compareTo(curr.key) < 0 ){

            return helper(curr.left,key);

        }
        else if (key.compareTo(curr.key) > 0){
            return helper(curr.right,key);
        }
        else{
            return curr;
        }






    }
    public V get(K key){

        if (helper(root, key) == null){
            return null;
        }

        return helper(root, key).value;

    }


    public int size(){
        return size;

    }
@Override
public void put(K key, V value){

     root = putHelper(root, key,value);

    }

    private Node putHelper(Node curr, K key, V value){

        if (curr == null) {
            curr = new Node(key, value);

             size++;
        }

        else if(key.compareTo(curr.key) < 0 ){

            curr.left = putHelper(curr.left,key,value);

        }
        else if (key.compareTo(curr.key) > 0){
            curr.right = putHelper(curr.right,key,value);
        }
        else if  (key.compareTo(curr.key) == 0){
            curr.value = value;
        }
        return curr;


    }
    public Set<K> keySet(){
        throw new UnsupportedOperationException("not done");
    }
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException("not done");
    }
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException("not done");
    }

    public void printInOrder(){

        throw new UnsupportedOperationException("not done");


        }


    }





