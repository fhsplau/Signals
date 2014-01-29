import java.util.*;

/**
 * Created by paverell on 1/24/14.
 */
public class SignalGenerator implements SignalProperties{
    private Map<String, Double> properties = new HashMap<String, Double>();
    private static final double PI = Math.PI;

    public SignalGenerator(){
        String[] propertiesKeys = {"Fs","A","T","Fp"};
        for(String propertyKey: propertiesKeys)
            this.properties.put(propertyKey,null);
    }

    @Override
    public double getProperty(String propertyName) {
        return this.properties.get(propertyName);
    }

    @Override
    public void setProperty(String propertyName, double propertyValue) {
        this.properties.put(propertyName,propertyValue);
    }

    @Override
    public void printAllProperties() {
        Set set = this.properties.entrySet();
        for (Object aSet : set) {
            Map.Entry property = (Map.Entry) aSet;
            System.out.println(property.getKey() + ": " + property.getValue());
        }
    }

    public List<Double> timeVectorGenerator(double t1, double t2, double samplingFrequency){
        List<Double> timeVector = new ArrayList<Double>();
        double T = 1/samplingFrequency;

        this.setProperty("T", T);
        this.setProperty("Fp", samplingFrequency);

        for(double dt = t1; dt <= t2; dt += T)
            timeVector.add(dt);
        return timeVector;
    }

    public List<Double> timeVectorGenerator(double t1, double t2, int numberOfSamples){
        List<Double> timeVector = new ArrayList<Double>();
        double T = (t2-t1)/((double)numberOfSamples-1);

        this.setProperty("T", T);
        this.setProperty("Fp", 1/T);

        for(double dt = t1; dt <= t2; dt += T)
            timeVector.add(dt);
        return timeVector;
    }

    public List<Double> sinusGenerator(double frequency,double amplitude, List<Double> timeVector){
        List<Double> sinus = new ArrayList<Double>();
        double A = amplitude;
        double f = frequency;

        this.setProperty("Fs",f);
        this.setProperty("A", A);

        for(double t : timeVector)
            sinus.add(A*Math.sin(2*PI*f*t));
        return sinus;
    }

    public List<Double> generateOnesVector(int length){
        List<Double> onesVector = new ArrayList<Double>(length);
        for(int index = 0; index < length; index++)
            onesVector.add((double)1);
        return onesVector;
    }

    public List<Double> generateZerosVector(int length){
        List<Double> zerosVector = new ArrayList<Double>(length);
        for(int index = 0; index < length; index++)
            zerosVector.add((double)0);
        return zerosVector;
    }

    public static void main(String[] args){
        SignalGenerator signal = new SignalGenerator();
        double fp = 10;
        int N=100;
        double Fs = (1/(2*PI));
        double A = 2;
        List<Double> t = signal.timeVectorGenerator(0,2*PI,N);
        System.out.println("Time: " + t);
        List<Double> sinus = signal.sinusGenerator(Fs, A, t);
        System.out.println("Sinus: " + sinus);
        signal.printAllProperties();
        SignalMath signalMath = new SignalMath();
        System.out.println("Signal power: " + signalMath.sigPow(sinus));
        System.out.println(signalMath.addTwoSignals(sinus,t));
        System.out.println(signal.generateOnesVector(10));
        System.out.println(signal.generateZerosVector(10));
        System.out.println(signalMath.addTwoSignals(signal.generateOnesVector(10),signal.generateOnesVector(10)));
    }

}
