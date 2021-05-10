
public class Location {

	private String place;
	private double latitude;
	private double longitude;
	private String lat_dir;
	private String long_dir;
	private double maxI;

	public double getMaxI() {
		return maxI;
	}




	Location(String in_place, String in_latitude, String in_longitude)
	{
		place = in_place;
		latitude = getLatitude(in_latitude);
		longitude = getLongitude(in_longitude);
		lat_dir = getLatdir(in_latitude);
		long_dir = getLongDir(in_longitude);
		maxI=-1;
	}
	
	
	public Location(Location location) {
		// TODO Auto-generated constructor stub
		place = location.getPlace();
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		lat_dir = location.getLat_dir();
		long_dir = location.getLong_dir();
	}

	public String getPlace() {
		return place;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getLat_dir() {
		return lat_dir;
	}

	public String getLong_dir() {
		return long_dir;
	}

	private String getLongDir(String in_longitude) {
		// TODO Auto-generated method stub
		if(in_longitude!=null)
		{
			if(Character.isDigit(in_longitude.charAt(0)))
				return String.valueOf(in_longitude.charAt(in_longitude.length()-1));
			else
				return String.valueOf(in_longitude.charAt(0));
		}
		
		return "";
	}


	private String getLatdir(String in_latitude) {
		// TODO Auto-generated method stub
		if(in_latitude!=null)
		{
			if(Character.isDigit(in_latitude.charAt(0)))
				return String.valueOf(in_latitude.charAt(in_latitude.length()-1));
			else
				return String.valueOf(in_latitude.charAt(0));
		}
		
		return "";
	}


	double getLatitude(String LatGeo)
	{
		
		return 0;
	}
	
	double getLongitude(String LonGeo)
	{
		
		return 0;
	}


	public void setMaxI(double maxI2) {
		// TODO Auto-generated method stub
		maxI = maxI2;
		
	}
	
	
	
}
