package ngordnet.main;


import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;


public class HyponymsHandler extends NgordnetQueryHandler {
    NGramMap ngm;
    private Wordnet wn;

    public HyponymsHandler(Wordnet wn, NGramMap ngm) {
        super();
        this.wn = wn;
        this.ngm = ngm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        Set<String> set = new HashSet<String>();

        ArrayList<String> x;
        if (k > 0) {
            x = topK(q);
        } else {

            x = wn.hyponymListsOfWords(words);
        }

        if (x == null) {
            return "[]";
        }




        return x.toString();

    }

    public ArrayList topK(NgordnetQuery q) {

        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        ArrayList<String> wordsList = wn.hyponymListsOfWords(words);
        if (wordsList == null) {
            return wordsList;
        }
        HashMap<Double, ArrayList> hList = new HashMap<>();
        ArrayList<Double> compare = new ArrayList();
        Set compareH = new HashSet<>();
        for (String n:wordsList) {
            TimeSeries temp = ngm.countHistory(n, startYear, endYear);
            List<Double> temp2 = temp.data();
            double sum = 0;
            for (double m:temp2) {
                sum += m;

            }
            if (sum == 0) {
                continue;
            }


            compareH.add(sum);

            if (hList.containsKey(sum)) {
                hList.get(sum).add(n);
            } else {

                ArrayList temp3 = new ArrayList<>();
                temp3.add(n);
                hList.put(sum, temp3);
            }

        }
        compare.addAll(compareH);
        Collections.sort(compare);
        int count = compare.size() - 1;
        int ks = 0;
        ArrayList<Double> compare2 = new ArrayList<>();
        while (ks < k && count > -1) {
            compare2.add(compare.get(count));
            count--;
            ks++;
        }
        ArrayList<String> answer = new ArrayList();
        for (double j: compare2) {


            answer.addAll(hList.get(j));
        }



        ArrayList finalAns = new ArrayList();

        int topKcount = 0;
        while (topKcount < k && topKcount < answer.size()) {
            finalAns.add(answer.get(topKcount));
            topKcount++;
        }
        Collections.sort(finalAns);
        return finalAns;

    }

}
