package ngordnet.main;

import edu.princeton.cs.algs4.In;
import ngordnet.ngrams.NGramMap;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Wordnet {

    private HashMap<String, ArrayList<Integer>> hmapword;
    private HashMap<Integer, ArrayList<String>> hmapword2;

    public Graph graph;
    public Wordnet(String synsets, String hyponyms){
        graph = new Graph();

        In hypo = new In(hyponyms);
        while(hypo.hasNextLine()) {
            String hypoln = hypo.readLine();
            String[] hypoln2 = hypoln.split(",");

            int one = (Integer.parseInt(hypoln2[0]));

            ArrayList<Integer> two = new ArrayList<>();
            int i = hypoln2.length;
            for(int j=1; j<i;j++){
                two.add(Integer.parseInt(hypoln2[j]));
            }

            graph.addNode(one, two);
        }

        In syn =  new In(synsets);

        hmapword = new HashMap<>();

        while(syn.hasNextLine()) {
            String synln = syn.readLine();
            String[] synln2 = synln.split(",");

            int one =Integer.parseInt(synln2[0]);


            String[] synln3 = synln2[1].split("\\s");


            int i = synln3.length;
            for(int j=0; j<i;j++){
                if(!hmapword.containsKey(synln3[j])){
                    ArrayList<Integer> two = new ArrayList<>();
                    two.add(one);
                    hmapword.put((synln3[j]),two);
                }
                else{
                    hmapword.get(synln3[j]).add(one);
                }
            }



        }

        In syn2 = new In(synsets);
        hmapword2= new HashMap<>();
        while(syn2.hasNextLine()) {
            String synln = syn2.readLine();
            String[] synln2 = synln.split(",");

            int one =Integer.parseInt(synln2[0]);

            ArrayList<String> two = new ArrayList<>();
//            int i = synln2.length;
//            for(int j=1; j<i;j++){
//                two.add(synln2[j]);
//            }

            String[] synln3 = synln2[1].split("\\s");
            int i = synln3.length;
            for(int j=0; j<i;j++){
                two.add(synln3[j]);
            }

            hmapword2.put(one, two);

        }







    }


    public ArrayList<String> hyponymReader(String word){


        if(!hmapword.containsKey(word)){
            return null;
        }
        ArrayList<Integer> links = hmapword.get(word);
        int count = 0;
       HashSet<Integer> ans = new HashSet<>();
        while(count<links.size()){

            ans.addAll(graph.getNeighbours((Integer) links.get(count)));

            count++;


        }

//        System.out.println(ans);


        HashSet<String> ans2 = new HashSet<>();
//        ans2.add(word);
       for(Integer m: ans){
           ans2.addAll(hmapword2.get(m));

       }

//       Set temp = new HashSet();
//       temp.addAll(ans2);

        ArrayList<String> answer = new ArrayList<>(ans2);

       Collections.sort(answer);
       return answer;


    }


    public ArrayList<String> hyponymListsOfWords( List<String> listOfWords){

        ArrayList<String> comp1 = new ArrayList();

        Collection<String > ans = new ArrayList<>();

        int count = listOfWords.size();
        int i =1;

//        if(hmapword.containsKey(listOfWords.get(0))){
//            comp1.addAll(hyponymReader(listOfWords.get(0)));


//        }
        /**
         * just added
         */

        if(count==0){
            return null;
        }
ArrayList zer = hyponymReader(listOfWords.get(0));
        if(zer !=null){

       comp1.addAll(zer);}


        while(i<count){
            ArrayList il=hyponymReader(listOfWords.get(i));

//            if(hmapword.containsKey(listOfWords.get(i))){
            ArrayList<String> comp2 = new ArrayList();

            if( il!=null){

                comp2.addAll(il);
                comp1.retainAll(comp2);}

            else{
                comp1.retainAll(comp2);
            }
                i++;
//          }


        }

        Set temp = new HashSet();
        if( comp1!=null){
        temp.addAll(comp1);
        ArrayList answer = new ArrayList<>();
        answer.addAll(temp);

        Collections.sort(answer);
        return answer;}

        return null;


    }
























}
