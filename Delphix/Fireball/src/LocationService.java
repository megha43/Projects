import java.util.LinkedList;
import java.util.List;

public class LocationService {

	List<Location> locations;
	
	
	public LocationService() {
		// TODO Auto-generated constructor stub
		locations = new LinkedList<Location>();
	}
	
	
	public List<Location> getLocations() {
		return locations;
	}


	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}


	void addLocation(Location loc)
	{
		locations.add(loc);
	}
	
}
