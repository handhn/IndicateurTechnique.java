import java.util.ArrayList;
import java.util.List;

public class MACDCalculator {
    private final int shortPeriod;
    private final int longPeriod;
    private final int signalPeriod;

    public MACDCalculator(int shortPeriod, int longPeriod, int signalPeriod) {
        this.shortPeriod = shortPeriod;
        this.longPeriod = longPeriod;
        this.signalPeriod = signalPeriod;
    }

    private List<Double> calculateEMA(List<Double> prices, int period) {
        List<Double> ema = new ArrayList<>();
        double alpha = 2.0 / (period + 1);

        // Initialisation de l'EMA avec la première valeur du prix
        ema.add(prices.get(0));

        for (int i = 1; i < prices.size(); i++) {
            double newEma = prices.get(i) * alpha + ema.get(i - 1) * (1 - alpha);
            ema.add(newEma);
        }

        return ema;
    }

    public List<Double> calculate(List<Double> prices) {
        // Étape 1: Calcul des MME courte et longue
        List<Double> shortEMA = calculateEMA(prices, shortPeriod);
        List<Double> longEMA = calculateEMA(prices, longPeriod);

        // Étape 2: Calcul de la ligne MACD
        List<Double> macdLine = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            macdLine.add(shortEMA.get(i) - longEMA.get(i));
        }

        // Étape 3: Calcul de la ligne de signal
        List<Double> signalLine = calculateEMA(macdLine, signalPeriod);

        // Étape 4: Calcul de l'histogramme MACD
        List<Double> histogram = new ArrayList<>();
        for (int i = 0; i < macdLine.size(); i++) {
            histogram.add(macdLine.get(i) - signalLine.get(i));
        }

        return histogram;
    }


    // test 
    public static void main(String[] args) {
        List<Double> prices = List.of(22.27, 22.19, 22.08, 22.17, 22.18, 22.13, 22.23, 22.43, 22.24, 22.29,
                22.15, 22.39, 22.38, 22.61, 23.36, 24.05, 23.75, 23.83, 23.95, 23.63, 23.82, 23.87, 23.65, 23.19,
                23.10, 23.33, 22.68, 23.10, 22.40, 22.17);

        MACDCalculator macd = new MACDCalculator(12, 26, 9);
        List<Double> histogram = macd.calculate(prices);

        System.out.println("MACD Histogram:");
        for (int i = 0; i < histogram.size(); i++) {
            System.out.printf("Day %d: %.4f%n", i + 1, histogram.get(i));
        }
    }
}
