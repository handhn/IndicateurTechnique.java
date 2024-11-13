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
}




// test 

List<Double> prices = /* vos données de prix */;
int period = 14;
double k = 2.0;

IndicateurTechnique rsi = IndicateurTechnique.createRSI(prices, period);
IndicateurTechnique sma = IndicateurTechnique.createSMA(prices, period);
IndicateurTechnique ema = IndicateurTechnique.createEMA(prices, period);
IndicateurTechnique macd = IndicateurTechnique.createMACD(prices, 12, 26, 9);
IndicateurTechnique bollingerBands = IndicateurTechnique.createBollingerBands(prices, period, k);

System.out.println(rsi.getNom() + ": " + rsi.getValeur());
System.out.println(sma.getNom() + ": " + sma.getValeur());
System.out.println(ema.getNom() + ": " + ema.getValeur());
System.out.println(macd.getNom() + ": " + macd.getValeur());
System.out.println(bollingerBands.getNom() + ": " + bollingerBands.getValeur());





