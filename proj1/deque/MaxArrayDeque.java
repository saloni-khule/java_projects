package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {


    private Comparator<T> comp;



    public MaxArrayDeque(Comparator<T> c) {
        comp =  c;




    }




    public T max() {
        if (this.isEmpty()) {
            return null;
        }

        int i = 0;

        T mix;
        mix = this.get(i);





        while (i < this.size() - 1) {
            if (comp.compare(mix, this.get(i + 1)) < 0) {
                i++;
                mix = this.get(i);
            }

            i++;

        }

        return mix;

    }



    public T max(Comparator<T> c) {




        if (this.isEmpty()) {
            return null;
        }

        int i = 0;

        T mix;
        mix = this.get(i);





        while (i < this.size() - 1) {
            if (c.compare(mix, this.get(i + 1)) < 0) {

                mix = this.get(i + 1);
            }

            i++;

        }

        return mix;


    }





}
