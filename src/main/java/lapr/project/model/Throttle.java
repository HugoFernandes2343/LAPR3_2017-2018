package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lapr.project.utils.DatabaseExchangable;

public class Throttle extends DatabaseExchangable implements Serializable {

    private static final long serialVersionUID = 100L;

    private static int flag = 0;

    private int id;
    private String percentage;
    private List<Regime> regimeList = new LinkedList<>();

    /**
     * Empty constructor
     */
    public Throttle() {
        this.regimeList = new LinkedList<>();
        this.id = flag;
        flag++;
    }

    /**
     * Full constructor of the throttle type object
     *
     * @param percentage id of the throttle object
     * @param regimeList List of regiments that will be placed in this object
     */
    public Throttle(String percentage, List<Regime> regimeList) {
        this.percentage = percentage;
        this.regimeList = regimeList;
        this.id = flag;
        flag++;
    }

    /**
     * Setter for the Throttle id
     *
     * @param percentage - throttle id
     */
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id id of the throttle
     */
    public String getPercentage() {
        return percentage;
    }

    public int getId() {
        return this.id;
    }

    public static void setFlag(int newFlag) {
        flag = newFlag;
    }

    /**
     * @return the regime_list list of regiments for this Throttle
     */
    public List<Regime> getRegimeList() {
        return regimeList;
    }

    /**
     * HashCode method for throttle type objects
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 6;
        hash = 19 * hash + Objects.hashCode(this.getPercentage());
        return hash;
    }

    /**
     * Equals method for throttle type objects
     *
     * @param obj the object to compare to the throttle
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Throttle)) {
            return false;
        }
        Throttle other = (Throttle) obj;
        return this.percentage.equalsIgnoreCase(other.percentage);

    }

    /**
     * method that returns the data to relate to the dataBase
     */
    @Override
    public List<DatabaseExchangable> getDBData() {
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.add(this);
        return temp;
    }

    public List<DatabaseExchangable> getDBRegimeData() {
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.addAll(this.regimeList);
        return temp;
    }

}
