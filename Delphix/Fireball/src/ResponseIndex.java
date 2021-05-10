
public enum ResponseIndex {
	 
	DATE(0),
    ENERGY(1),
    IMPACT_E(2),
    LAT(3),
    LAT_DIR(4),
    LON(5),
    LON_DIR(6),
    ALT(7),
    VEL(8);

    private int value;

    ResponseIndex(int i) {
		// TODO Auto-generated constructor stub
    	 value = i;
	}


    public int getValue() {
         return value;
    }

}
