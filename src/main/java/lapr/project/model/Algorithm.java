package lapr.project.model;

public class Algorithm {
    
    /**
     * Default algorithm name
     */
    private static final String NOME_DEFAULT = "Algorithm";
    
    /**
     * Attributes that keeps the algorithm name
     */
    private String name;
   
    /**
     * Empty constructor
     */
    public Algorithm() {
        this.name=NOME_DEFAULT;
    }
    
    /**
     * Getter for the name attribute
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Setter for the name attribute
     * @param name - new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    
}
