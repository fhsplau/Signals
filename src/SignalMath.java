import java.util.ArrayList;
import java.util.List;

/**
 * Created by paverell on 1/29/14.
 */
public class SignalMath {

    public double sigPow(List<Double> signal){
        int signalLength = signal.size();
        double signalPower=0;
        for(double sample: signal)
            signalPower+=sample*sample;
        return (1/(double)signalLength)*signalPower;
    }

    public List<Double> addTwoSignals(List<Double> signal1, List<Double> signal2){
        if(signal1.size()!=signal2.size())
            throw new ArrayIndexOutOfBoundsException();
        List<Double> sumOfSignals = new ArrayList<Double>();
        for(int sample=0; sample < signal1.size();sample++)
            sumOfSignals.add(signal1.get(sample)+signal2.get(sample));
        return sumOfSignals;
    }
}
