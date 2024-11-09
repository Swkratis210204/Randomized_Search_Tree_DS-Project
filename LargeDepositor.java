public class LargeDepositor{
    private int AFM; 
    private String firstName;
    private String lastName;
    private double savings; 
    private double taxedIncome; 

    public LargeDepositor(int AFM, String firstName, String lastName, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    // Getters
    public int getAFM() {
        return AFM;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSavings() {
        return savings;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }

    // Setters
    public void setAFM(int AFM) {
        this.AFM = AFM;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public void setTaxedIncome(double taxedIncome) {
        this.taxedIncome = taxedIncome;
    }
    int key() {return AFM;}

    public String toString() {
        return "Large Depositor: \n" +
                "\tFirstName : " + firstName + '\n'+
                "\tLastName : " + lastName + '\n' +
                "\tAFM = " + AFM + '\n'+
                "\tSavings : " + savings +'\n'+ 
                "\tTaxedIncome : " + taxedIncome+'\n';
    }

}