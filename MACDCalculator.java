import java.util.ArrayList;
import java.util.List;

public class MACD {
    
    public static List<Double> calculateEMA(List<Double> prices, int period) {
        List<Double> ema = new ArrayList<>(prices.size());
        double multiplier = 2.0 / (period + 1);
        
        // Initialisation de l'EMA avec la première valeur du prix
        ema.add(prices.get(0));
        
        // Calcul de l'EMA pour chaque prix
        for (int i = 1; i < prices.size(); i++) {
            double emaCurrent = (prices.get(i) - ema.get(i-1)) * multiplier + ema.get(i-1);
            ema.add(emaCurrent);
        }
        
        return ema;
    }
    
    public static List<Double> calculateMACD(List<Double> prices, int shortPeriod, int longPeriod, int signalPeriod) {
        // Étape 1: Calcul des moyennes mobiles exponentielles (MME)
        List<Double> shortEMA = calculateEMA(prices, shortPeriod);
        List<Double> longEMA = calculateEMA(prices, longPeriod);
        
        // Étape 2: Calcul de la ligne MACD
        List<Double> macdLine = new ArrayList<>(prices.size());
        for (int i = 0; i < prices.size(); i++) {
            macdLine.add(shortEMA.get(i) - longEMA.get(i));
        }
        
        // Étape 3: Calcul de la ligne de signal
        List<Double> signalLine = calculateEMA(macdLine, signalPeriod);
        
        // Étape 4: Calcul de l'histogramme MACD
        List<Double> histogram = new ArrayList<>(prices.size());
        for (int i = 0; i < prices.size(); i++) {
            histogram.add(macdLine.get(i) - signalLine.get(i));
        }
        
        return histogram;
    }
    
    // test 
    public static void main(String[] args) {
        List<Double> prices = List.of(100.0, 102.0, 104.0, 103.0, 105.0, 107.0, 108.0, 109.0);
        int shortPeriod = 12;
        int longPeriod = 26;
        int signalPeriod = 9;
        
        List<Double> macdHistogram = calculateMACD(prices, shortPeriod, longPeriod, signalPeriod);
        
        System.out.println("MACD Histogram: " + macdHistogram);
    }
}
