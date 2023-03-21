package ngordnet.main;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


public class HyponymsReader {

//    private class Node<T> {
//        private Node  first;
//        private Node  rest;
//        private T  item;
//
//        private Node(Node   f, T  i, Node  r) {
//            this.first = f;
//            this.rest = r;
//            this.item = i;
//        }
//
//    }
    private HashMap<Integer,  ArrayList<Integer>> hmap;
    private HashMap<Integer,  ArrayList<String>> hmapword;

    private String[] wrd;
    private int[] link;

    private TreeMap<String,ArrayList> link2;


    public HyponymsReader(String synsets, String hyponyms){
    In syn =  new In(synsets);
    In hypo = new In(hyponyms);

    In syn2 = new In(synsets);

    hmap = new HashMap<>();

    while(hypo.hasNextLine()) {
        String hypoln = hypo.readLine();
        String[] hypoln2 = hypoln.split(",");

        int one = (Integer.parseInt(hypoln2[0]));

        ArrayList two = new ArrayList<>();
        int i = hypoln2.length;
        for(int j=1; j<i;j++){
            two.add(Integer.parseInt(hypoln2[j]));
        }


        hmap.put(one, two);

    }

    hmapword = new HashMap<>();

        while(syn.hasNextLine()) {
            String synln = syn.readLine();
            String[] synln2 = synln.split(",");

            int one =Integer.parseInt(synln2[0]);

            ArrayList two = new ArrayList<>();
            int i = synln2.length;
            for(int j=1; j<i;j++){
                two.add(synln2[j]);
            }

            hmapword.put(one, two);

        }

        wrd = new String[100];
        link = new int[100];

        int size = 0;



//        while(syn2.hasNextLine()) {
//            String syn2ln = syn2.readLine();
//            String[] syn2ln2 = syn2ln.split(",");
//
//            int one = (Integer.parseInt(syn2ln2[0]));
//
//            ArrayList two = new ArrayList<>();
//            int i = syn2ln2.length;
//            for(int j=1; j<i;j++){
//                //two.add(Integer.parseInt(hypoln2[j]));
//                wrd[size] = syn2ln2[j];
//                link[size] = one;
//                size++;
//
//            }
//
//
//        }





        while(syn2.hasNextLine()) {
            String syn2ln = syn2.readLine();
            String[] syn2ln2 = syn2ln.split(",");
            ArrayList onelist = new ArrayList<>();

            int one = (Integer.parseInt(syn2ln2[0]));
            onelist.add(one);

            int i = syn2ln2.length;
            for(int j=1; j<i;j++){
                if(link2.containsKey(syn2ln2[j])){
                    link2.get(syn2ln2[j]).add(one);

                }else{
              link2.put(syn2ln2[j],onelist);}

            }
        }

    }


    public void reader(String word){

        if(! link2.containsKey(word)){
            return;
        }

        ArrayList x = link2.get(word);

        int size = x.size();
        ArrayList answer = new ArrayList<>();

//        for(int j =0; j<size;j++){
//            x.get(j);
//
//        }

        Object[] M = x.toArray();






    }






}
