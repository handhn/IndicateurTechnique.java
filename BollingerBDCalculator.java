import java.util.List;

public class BollingerBDCalculator {

    public static class calculateBollingerBD {
        public double sma;
        public double upperBand;
        public double lowerBand;

        public calculateBollingerBD(double sma, double upperBand, double lowerBand) {
            this.sma = sma;
            this.upperBand = upperBand;
            this.lowerBand = lowerBand;
        }
    }

    public static calculateBollingerBD calculate(List<Double> prices, int period, double k) {
        // Étape 1 : Calcul de la Moyenne Mobile Simple (SMA)
        double sma = calculateSMA(prices, period);

        // Étape 2 : Calcul de l'Écart-Type
        double ecartType = calculateEcartType(prices, period, sma);

        // Étape 3 : Calcul des Bandes de Bollinger
        double upperBand = sma + (k * ecartType);
        double lowerBand = sma - (k * ecartType);

        return new calculateBollingerBD(sma, upperBand, lowerBand);
    }

    private static double calculateSMA(List<Double> prices, int period) {
        double sum = 0;
        for (int i = prices.size() - period; i < prices.size(); i++) {
            sum += prices.get(i);
        }
        return sum / period;
    }

    private static double calculateEcartType(List<Double> prices, int period, double sma) {
        double sum = 0;
        for (int i = prices.size() - period; i < prices.size(); i++) {
            sum += Math.pow(prices.get(i) - sma, 2);
        }
        return Math.sqrt(sum / period);
    }

    
    // test
    public static void main(String[] args) {
        List<Double> prices = List.of(22.1, 22.5, 22.8, 23.0, 23.5, 23.7, 24.0, 24.2, 24.3, 24.5, 
                                      24.6, 24.8, 25.0, 25.5, 25.8, 26.0, 26.1, 26.7, 27.0, 27.5);
        int period = 20;
        double k = 2;

        calculateBollingerBD result = calculate(prices, period, k);

        System.out.printf("SMA: %.2f%n", result.sma);
        System.out.printf("Bande Supérieure: %.2f%n", result.upperBand);
        System.out.printf("Bande Inférieure: %.2f%n", result.lowerBand);
    }
}
