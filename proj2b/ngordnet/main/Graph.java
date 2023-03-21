package ngordnet.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Graph {

//    private class Node<T> {
//        private ArrayList<Node>  neighbours;
//        private int id;
//        private ArrayList key;
//
//
//
//        private Node(int  id, ArrayList key, ArrayList neighbours) {
//            this.id = id;
//            this.key = key;
//            this.neighbours= neighbours;
//        }
//
//    }

    private HashMap<Integer, ArrayList<Integer>> map;



    public Graph(){
        map = new HashMap<>();
    }

    public void addNode(int id, ArrayList<Integer> neighbors){
        if (map.containsKey(id)){
            map.get(id).addAll(neighbors);
        }else{
        map.put(id,neighbors);}

    }

    public HashSet<Integer> getNeighbours(int id){
        Stack< Integer> stack = new Stack<>();
        stack.push(id);
        //ArrayList<Integer> neigh = new ArrayList();
       HashSet<Integer> neigh = new HashSet<>();

        while(! stack.empty()){
            int i = (int) stack.pop();
           // if (! neigh.contains(i)){
                neigh.add(i);
            //}
            if(map.containsKey(i)){
                for(Integer j: map.get(i)){
                   // if (! neigh.contains(j)){
                        stack.push(j);
                   // }
                }
            }

        }

        return neigh;

    }








}
