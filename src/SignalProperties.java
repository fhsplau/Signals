import java.util.HashMap;

/**
 * Created by paverell on 1/24/14.
 */
public interface SignalProperties {
    public double getProperty(String propertyName);
    public void setProperty(String propertyName, double propertyValue);
    public void printAllProperties();
}
