package ngordnet.ngrams;

import java.util.*;

/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     *  inclusive of both end points. */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();


        int i = startYear;
        while (i <= endYear) {
            if (!ts.containsKey(i)) {
                i++;
            } else {
                this.put(i, ts.get(i));
                i++;
            }

        }

    }
    private TimeSeries copy;

    /** Returns all years for this TimeSeries (in any order). */
    public List<Integer> years() {
        TimeSeries temp = (TimeSeries) this.clone();

        int count = temp.size();
        ArrayList store = new ArrayList();
        while (count > 0) {

            store.add(temp.firstKey());
            temp.remove(temp.firstKey());
            count--;
        }
        return store;

    }

    /** Returns all data for this TimeSeries (in any order).
     *  Must be in the same order as years(). */
    public List<Double> data() {
        TimeSeries temp = (TimeSeries) this.clone();
        int count = temp.size();
        ArrayList store = new ArrayList();
        while (count > 0) {

            store.add(temp.get(temp.firstKey()));
            temp.remove(temp.firstKey());

            count--;
        }
        return store;
    }

    /** Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     *  each year, sum the data from this TimeSeries with the data from TS. Should return a
     *  new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries plus(TimeSeries ts) {


        TimeSeries plusx = new TimeSeries();

        TimeSeries temp1 = (TimeSeries) this.clone();

        int count1 = temp1.size();

        while (count1 > 0) {
            plusx.put(temp1.firstKey(), temp1.get(temp1.firstKey()));
            temp1.remove(temp1.firstKey());
            count1--;
        }

        TimeSeries temp2 = (TimeSeries) ts.clone();
        int count2 = temp2.size();


        while (count2 > 0) {
            if (plusx.get(temp2.firstKey()) != null) {
                int x = temp2.firstKey();
                plusx.put(x, plusx.get(x) + temp2.get(x));
            } else {
                plusx.put(temp2.firstKey(), temp2.get(temp2.firstKey()));
            }

            temp2.remove(temp2.firstKey());
            count2--;
        }

        return  plusx;
    }

    /** Returns the quotient of the value for each year this TimeSeries divided by the
     *  value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
     *  throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
     *  Should return a new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries dividedBy(TimeSeries ts) {

        TimeSeries plusx = new TimeSeries();

        TimeSeries tempx = (TimeSeries) this.clone();


        int count1 = tempx.size();
        TimeSeries temp2 = ts;
        int count2 = temp2.size();




        while (count1 > 0) {

            int x = tempx.firstKey();

            if (temp2.containsKey(x)) {
                plusx.put(x, tempx.get(x) / temp2.get(x));
            } else if (!(temp2.containsKey(x))) {
                new IllegalArgumentException();
            }

            tempx.remove(tempx.firstKey());
            count1--;

        }

        return  plusx;
    }
}
