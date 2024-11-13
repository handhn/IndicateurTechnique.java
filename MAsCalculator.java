import java.util.List;

public class MAsCalculator {

    public static double calculateSMA(List<Double> prices, int period) {
        if (prices.size() < period) {
            throw new IllegalArgumentException("Not enough data points");
        }

        // Étape a : Choisir une période N
        int N = period;

        // Étape b : Additionner les prix de clôture des N derniers jours
        double sum = 0;
        for (int i = prices.size() - N; i < prices.size(); i++) {
            sum += prices.get(i);
        }

        // Étape c : Diviser la somme par N
        return sum / N;
    }

    public static double calculateEMA(List<Double> prices, int period) {
        if (prices.size() < period) {
            throw new IllegalArgumentException("Not enough data points");
        }

        // Étape a : Calculer le facteur de lissage (α)
        double alpha = 2.0 / (period + 1);

        // Étape b : Calculer l'EMA initiale (utiliser SMA comme valeur initiale)
        double ema = calculateSMA(prices.subList(0, period), period);

        // Étape c : Calculer l'EMA pour chaque période suivante
        for (int i = period; i < prices.size(); i++) {
            double price = prices.get(i);
            ema = price * alpha + ema * (1 - alpha);
        }

        return ema;
    }


    // test 
    
    public static void main(String[] args) {
        List<Double> prices = List.of(10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0);
        int period = 5;

        double sma = calculateSMA(prices, period);
        System.out.printf("SMA (%d jours): %.2f%n", period, sma);

        double ema = calculateEMA(prices, period);
        System.out.printf("EMA (%d jours): %.2f%n", period, ema);
    }
}
