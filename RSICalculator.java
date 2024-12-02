import java.util.List;
import java.lang.Math;


public class RSICalculator {

    private static final int DEFAULT_PERIOD = 14;

    public static double calculateRSI(List<Double> closingPrices, int period) {
        if (period <= 0) {
            period = DEFAULT_PERIOD;
        }

        if (closingPrices.size() < period + 1) {
            throw new IllegalArgumentException("Not enough data points to calculate RSI");
        }

        // Étape 1: Calcul des hausses et baisses
        double[] gains = new double[closingPrices.size()];
        double[] losses = new double[closingPrices.size()];

        for (int i = 1; i < closingPrices.size(); i++) {
            double difference = closingPrices.get(i) - closingPrices.get(i - 1);
            if (difference > 0) {
                gains[i] = difference;
                losses[i] = 0;
            } else {
                gains[i] = 0;
                losses[i] = Math.abs(difference);
            }
        }

        // Étape 2: Calcul des moyennes des hausses et des baisses
        double avgGain = calculateAverage(gains, period);
        double avgLoss = calculateAverage(losses, period);

        // Étape 3: Calcul de RS
        double rs = avgGain / avgLoss;

        // Étape 4: Calcul du RSI
        double rsi = 100 - (100 / (1 + rs));

        return rsi;
    }

    private static double calculateAverage(double[] values, int period) {
        double sum = 0;
        for (int i = values.length - period; i < values.length; i++) {
            sum += values[i];
        }
        return sum / period;
    }


    //  test 
  
//    public static void main(String[] args) {
//        List<Double> closingPrices = List.of(44.34, 44.09, 44.15, 43.61, 44.33, 44.83, 45.10, 45.42,
//                45.84, 46.08, 45.89, 46.03, 45.61, 46.28, 46.28, 46.00);
        
//        double rsi = calculateRSI(closingPrices, DEFAULT_PERIOD);
//        System.out.printf("RSI: %.2f", rsi);
//    }
}
