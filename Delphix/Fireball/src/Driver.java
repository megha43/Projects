
public class Driver {

	public static void main(String[] args) throws EnptyResultException, DataRequestException {

		Location l1 = new Location("Boston","42.354558N","71.054254W");
		Location l2 = new Location( "NCR","28.574389N","77.312638E");
		Location l3 = new Location( "San Francisco","37.793700N","122.403906W");
		
		LocationService loc = new LocationService();
		loc.addLocation(l2);
		loc.addLocation(l1);
		loc.addLocation(l3);
		
		BrightestStartService br = new BrightestStartService(loc);
		Location li = br.getBrightestStarLocation();
		
		System.out.println(li.getPlace() + " " + li.getMaxI());
		
	}

}
