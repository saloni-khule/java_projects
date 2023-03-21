package ngordnet.proj2b_testing;

import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.main.Wordnet;
import ngordnet.ngrams.NGramMap;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        NGramMap ngm = new NGramMap(wordFile, countFile);

        Wordnet wn = new Wordnet(synsetFile,hyponymFile);


        //hns.register("history", new HistoryHandler(ngm));
        // hns.register("historytext", new HistoryTextHandler(ngm));
        return new  HyponymsHandler(wn, ngm);
    }
}
