package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;

import java.util.TreeMap;

/** An object that provides utility methods for making queries on the
 *  Google NGrams dataset (or a subset thereof).
 *
 *  An NGramMap stores pertinent data from a "words file" and a "counts
 *  file". It is not a map in the strict sense, but it does provide additional
 *  functionality.
 *
 *  @author Josh Hug
 */
public class NGramMap {
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */

    private TreeMap<String, TimeSeries> mew;

    private  TimeSeries tnew;

    public NGramMap(String wordsFilename, String countsFilename) {
        In wrd = new In(wordsFilename);
        In cnt = new In(countsFilename);
        mew = new TreeMap();

        while (wrd.hasNextLine()) {
            String ln = wrd.readLine();
            String[] cntln = ln.split("\\s");
            String word = cntln[0];
            int yr = Integer.parseInt(cntln[1]);
            double freq = Double.parseDouble(cntln[2]);


            TimeSeries tempx = new TimeSeries();


            if (mew.containsKey(word)) {

                mew.get(word).put(yr, freq);

            } else {
                mew.put(word, tempx);
                tempx.put(yr, freq);
            }

        }
        tnew = new TimeSeries();

        while (cnt.hasNextLine()) {
            String line = cnt.readLine();
            String[] cntln = line.split(",");
            int x = Integer.parseInt(cntln[0]);
            double y = Double.parseDouble(cntln[1]);
            tnew.put(x, y);

        }

    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word) {


        return mew.get(word);
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word, int startYear, int endYear) {

        TimeSeries tempx2 = new TimeSeries();
        tempx2 = tempx2.plus(mew.get(word));
        TimeSeries ex = new TimeSeries(tempx2, startYear, endYear);

        return ex;
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public TimeSeries totalCountHistory() {
        TimeSeries tempx2 = new TimeSeries();
        tempx2 = tempx2.plus(tnew);
        return tempx2;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public TimeSeries weightHistory(String word) {
        TimeSeries temp = new TimeSeries();
        temp = temp.plus(mew.get(word).dividedBy(tnew));

        return temp;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries temp = new TimeSeries();
        temp = weightHistory(word);
        temp = new TimeSeries(temp, startYear, endYear);

        return temp;
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries temp = new TimeSeries();

        for (String w : words) {
            temp = temp.plus(weightHistory(w));
        }


        return temp;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {



        TimeSeries temp = summedWeightHistory(words);
        temp = new TimeSeries(temp, startYear, endYear);
        return temp;
    }


}
