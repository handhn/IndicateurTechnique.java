import java.util.List;

public class IndicateurTechnique {
    private String nom;
    private Object valeur;

    // Constructeur privé pour empêcher l'instanciation directe
    private IndicateurTechnique(String nom, Object valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }

    public String getNom() {
        return nom;
    }

    public Object getValeur() {
        return valeur;
    }

    // Méthodes statiques pour créer des instances d'IndicateurTechnique pour chaque type d'indicateur

    public static IndicateurTechnique createRSI(List<Double> closingPrices, int period) {
        double rsi = RSICalculator.calculateRSI(closingPrices, period);
        return new IndicateurTechnique("RSI", rsi);
    }

    public static IndicateurTechnique createSMA(List<Double> prices, int period) {
        double sma = MAsCalculator.calculateSMA(prices, period);
        return new IndicateurTechnique("SMA", sma);
    }

    public static IndicateurTechnique createEMA(List<Double> prices, int period) {
        double ema = MAsCalculator.calculateEMA(prices, period);
        return new IndicateurTechnique("EMA", ema);
    }

    public static IndicateurTechnique createMACD(List<Double> prices, int shortPeriod, int longPeriod, int signalPeriod) {
        MACDCalculator macdCalculator = new MACDCalculator(shortPeriod, longPeriod, signalPeriod);
        List<Double> macd = macdCalculator.calculateMACD(prices);
        return new IndicateurTechnique("MACD", macd);
    }

    public static IndicateurTechnique createBollingerBands(List<Double> prices, int period, double k) {
        BollingerBDCalculator.calculateBollingerBD bollingerBands = BollingerBDCalculator.calculate(prices, period, k);
        return new IndicateurTechnique("BollingerBands", bollingerBands);
    }





// test 

@Override
    public String toString() {
        switch (nom) {
            case "RSI":
                return String.format("%s: %.2f", nom, (Double) valeur);
            case "SMA":
            case "EMA":
                return String.format("%s: %.2f", nom, (Double) valeur);
            case "MACD":
                List<Double> macdValues = (List<Double>) valeur;
                return String.format("%s: %s", nom, macdValues.toString());
            case "BollingerBands":
                BollingerBDCalculator.calculateBollingerBD bb = (BollingerBDCalculator.calculateBollingerBD) valeur;
                return String.format("%s: SMA=%.2f, Upper=%.2f, Lower=%.2f", nom, bb.sma, bb.upperBand, bb.lowerBand);
            default:
                return nom + ": " + valeur;
        }
    }

}


