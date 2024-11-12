import java.util.ArrayList;
import java.util.List;

public class MACD {
    private static final int SHORT_PERIOD = 12;
    private static final int LONG_PERIOD = 26;
    private static final int SIGNAL_PERIOD = 9;

    public static class MACDResult {
        public List<Double> macdLine;
        public List<Double> signalLine;
        public List<Double> histogram;

        public MACDResult(List<Double> macdLine, List<Double> signalLine, List<Double> histogram) {
            this.macdLine = macdLine;
            this.signalLine = signalLine;
            this.histogram = histogram;
        }
    }

    public static MACDResult calculate(List<Double> prices) {
        // Étape 1: Calculer les MME
        List<Double> shortEMA = calculateEMA(prices, SHORT_PERIOD);
        List<Double> longEMA = calculateEMA(prices, LONG_PERIOD);

        // Étape 2: Calculer la ligne MACD
        List<Double> macdLine = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            if (i < LONG_PERIOD - 1) {
                macdLine.add(0.0);
            } else {
                macdLine.add(shortEMA.get(i) - longEMA.get(i));
            }
        }

        // Étape 3: Calculer la ligne de signal
        List<Double> signalLine = calculateEMA(macdLine, SIGNAL_PERIOD);

        // Étape 4: Calculer l'histogramme
        List<Double> histogram = new ArrayList<>();
        for (int i = 0; i < macdLine.size(); i++) {
            histogram.add(macdLine.get(i) - signalLine.get(i));
        }

        return new MACDResult(macdLine, signalLine, histogram);
    }

    private static List<Double> calculateEMA(List<Double> prices, int period) {
        List<Double> ema = new ArrayList<>();
        double multiplier = 2.0 / (period + 1);

        // Initialiser EMA avec SMA
        double sma = 0;
        for (int i = 0; i < period; i++) {
            sma += prices.get(i);
            ema.add(0.0);
        }
        sma /= period;
        ema.set(period - 1, sma);

        // Calculer EMA
        for (int i = period; i < prices.size(); i++) {
            double value = (prices.get(i) - ema.get(i - 1)) * multiplier + ema.get(i - 1);
            ema.add(value);
        }

        return ema;
    }

    public static void main(String[] args) {
        List<Double> prices = List.of(100.0, 102.0, 104.0, 103.0, 105.0, 107.0, 108.0, 107.0, 106.0, 108.0,
                                      109.0, 111.0, 112.0, 111.0, 113.0, 114.0, 115.0, 117.0, 116.0, 118.0);
        
        MACDResult result = calculate(prices);

        System.out.println("MACD Line: " + result.macdLine);
        System.out.println("Signal Line: " + result.signalLine);
        System.out.println("Histogram: " + result.histogram);
    }
}
