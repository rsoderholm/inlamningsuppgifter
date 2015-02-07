package f3;

//public class RealNbr implements Comparable<RealNbr> {
public class RealNbr {
    private double value;
    
    public RealNbr(double value) {
        this.value = value;
    }
    
    public double getValue() {
        return value;
    }
    
    public int compareTo( RealNbr nbr ) {
        double value2 = nbr.getValue();
        if( this.value < value2 )
            return -1;
        else if( this.value == value2 )
            return 0;
        else
            return 1;
            
    }
    
    // Hur blir körresultatet om equals-metoden tas bort?
    public boolean equals(Object obj) {
        boolean res = false;
        if( obj != null ) {
            RealNbr nbr = ( RealNbr ) obj;
            res = ( this.value == nbr.value );
        }
        return res;
    }
    
    public String toString() {
    	return String.format("%1.1f", value);
    }
}
