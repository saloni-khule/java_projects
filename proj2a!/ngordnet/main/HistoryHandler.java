package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;

public class HistoryHandler extends NgordnetQueryHandler  {
    private NGramMap ngm;




    @Override
        public String handle(NgordnetQuery q) {


        ArrayList<String> labels = new ArrayList<>();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        for (String x: q.words()) {
            TimeSeries temp = ngm.weightHistory(x, q.startYear(), q.endYear());
            lts.add(temp);
            labels.add(x);
        }










        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }



    public HistoryHandler(NGramMap map) {
        super();
        this.ngm = map;

    }
}


