package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;


import java.util.ArrayList;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap ngm;
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        ArrayList m = new ArrayList<>();
        String ans = "";
        for (String x:words) {
            ans += x + ": ";
            String temp = ngm.weightHistory(x, startYear, endYear).toString();
            ans += temp;
            ans += "\n";
        }
        return ans;
    }

    public HistoryTextHandler(NGramMap map) {
        super();
        this.ngm = map;


    }
}
