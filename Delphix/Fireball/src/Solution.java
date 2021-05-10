/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
	public static void main(String[] args) throws EnptyResultException, DataRequestException {
		Solution s = new Solution();
		Location l1 = s.new Location("Boston", "42.354558N", "71.054254W");
		Location l2 = s.new Location("NCR", "28.574389N", "77.312638E");
		Location l3 = s.new Location("San Francisco", "37.793700N", "122.403906W");

		LocationService loc = s.new LocationService();
		loc.addLocation(l2);
		loc.addLocation(l1);
		loc.addLocation(l3);

		BrightestStartService br = s.new BrightestStartService(loc);
		Location li = br.getBrightestStarLocation();

		System.out.println(li.getPlace());

	}

	public class FireballData {

		private String date;
		private double energy;
		private double impactE;
		private double lat;
		private String latDir;
		private double lon;
		private String lonDir;
		private double altitude;
		private double velocity;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public double getEnergy() {
			return energy;
		}

		public void setEnergy(double d) {
			this.energy = d;
		}

		public double getImpactE() {
			return impactE;
		}

		public void setImpactE(double d) {
			this.impactE = d;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double d) {
			this.lat = d;
		}

		public String getLatDir() {
			return latDir;
		}

		public void setLatDir(String latDir) {
			this.latDir = latDir;
		}

		public double getLon() {
			return lon;
		}

		public void setLon(double lon) {
			this.lon = lon;
		}

		public String getLonDir() {
			return lonDir;
		}

		public void setLonDir(String lonDir) {
			this.lonDir = lonDir;
		}

		public double getAltitude() {
			return altitude;
		}

		public void setAltitude(double altitude) {
			this.altitude = altitude;
		}

		public double getVelocity() {
			return velocity;
		}

		public void setVelocity(double velocity) {
			this.velocity = velocity;
		}

	}

	public class Location {

		private String place;
		private double latitude;
		private double longitude;
		private String lat_dir;
		private String long_dir;

		Location(String in_place, String in_latitude, String in_longitude) {
			place = in_place;
			latitude = getLatitude(in_latitude);
			longitude = getLongitude(in_longitude);
			lat_dir = getLatdir(in_latitude);
			long_dir = getLongDir(in_longitude);

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
			if (in_longitude != null) {
				if (Character.isDigit(in_longitude.charAt(0)))
					return String.valueOf(in_longitude.charAt(in_longitude.length() - 1));
				else
					return String.valueOf(in_longitude.charAt(0));
			}

			return "";
		}

		private String getLatdir(String in_latitude) {
			// TODO Auto-generated method stub
			if (in_latitude != null) {
				if (Character.isDigit(in_latitude.charAt(0)))
					return String.valueOf(in_latitude.charAt(in_latitude.length() - 1));
				else
					return String.valueOf(in_latitude.charAt(0));
			}

			return "";
		}

		double getLatitude(String LatGeo) {

			return 0;
		}

		double getLongitude(String LonGeo) {

			return 0;
		}

	}

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

		void addLocation(Location loc) {
			locations.add(loc);
		}

	}

	public class Messages {
		public static final String MALFORMED_URL = "Url incorrect";
	}

	public enum ResponseIndex {

		DATE(0), ENERGY(1), IMPACT_E(2), LAT(3), LAT_DIR(4), LON(5), LON_DIR(6), ALT(7), VEL(8);

		private int value;

		ResponseIndex(int i) {
			// TODO Auto-generated constructor stub
			value = i;
		}

		public int getValue() {
			return value;
		}

	}

	public class Constants {

		private Constants() {
		}

		public final static String GET_API_URL = "https://ssd-api.jpl.nasa.gov/fireball.api";
		public final static String AFTER_DATE = "2017-01-01";

	}

	public class DataRequestException extends Exception {
		public DataRequestException(String message) {
			super(message);
		}
	}

	public class EnptyResultException extends Exception {

		public EnptyResultException(String arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}

	}

	public class DataRequest implements IDataRequest {

		private List<FireballData> fireballData;

		public List<FireballData> getFireballData() {
			return fireballData;
		}

		public DataRequest() {
			fireballData = new ArrayList<FireballData>();
		}

		@SuppressWarnings("unchecked")
		void getData(String in_url) throws UnsupportedEncodingException, DataRequestException {
			String query;
			try {
				query = "date-min=" + Constants.AFTER_DATE + "&req-loc=true";
				URL url;
				url = new URL(in_url + "?" + query);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();

				int responsecode = conn.getResponseCode();
				if (responsecode != 200)
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				else {
					String inline = "";
					Scanner sc = new Scanner(url.openStream());
					while (sc.hasNext()) {
						inline += sc.nextLine();
					}

					JSONParser parser = new org.json.simple.parser.JSONParser();
					JSONObject obj = (JSONObject) parser.parse(inline);
					int count = Integer.valueOf(obj.get("count").toString());
					JSONArray array = new JSONArray();
					array.add(obj.get("data"));
					for (int i = 0; i < count; i++) {
						JSONArray jsonArray = (JSONArray) ((JSONArray) array.get(0)).get(i);
						if (jsonArray.get(ResponseIndex.IMPACT_E.getValue()) != null) {
							FireballData fireball = new FireballData();
							fireball.setDate(jsonArray.get(ResponseIndex.DATE.getValue()).toString());
//                  fireball.setEnergy(Double.parseDouble(jsonArray.get(ResponseIndex.ENERGY.getValue()).toString()));
							fireball.setImpactE(
									Double.parseDouble(jsonArray.get(ResponseIndex.IMPACT_E.getValue()).toString()));
							fireball.setLat(Double.parseDouble(jsonArray.get(ResponseIndex.LAT.getValue()).toString()));
							fireball.setLatDir(jsonArray.get(ResponseIndex.LAT_DIR.getValue()).toString());
							fireball.setLon(Double.parseDouble(jsonArray.get(ResponseIndex.LON.getValue()).toString()));
							fireball.setLonDir(jsonArray.get(ResponseIndex.LON_DIR.getValue()).toString());
//                  fireball.setAltitude(Double.parseDouble(jsonArray.get(ResponseIndex.ALT.getValue()).toString()));
//                  fireball.setVelocity(Double.parseDouble(jsonArray.get(ResponseIndex.VEL.getValue()).toString()));
							fireballData.add(fireball);
						}
					}
				}

			} catch (Exception e) {
				throw new DataRequestException(Messages.MALFORMED_URL);
			}
		}

	}

	public class BrightestStartService {

		LocationService locSer;
		DataRequest dataReq;

		public BrightestStartService(LocationService locationlist) {
			locSer = locationlist;
			dataReq = new DataRequest();
		}

		Location getBrightestStarLocation() throws EnptyResultException, DataRequestException {
			double maxI = -1;
			Location brightest = null;
			try {

				List<Location> locList = locSer.getLocations();
				dataReq.getData(Constants.GET_API_URL);

				for (int i = 0; i < locList.size(); i++) {
					double inten = fireball(locList.get(i));
					if (inten > maxI) {
						maxI = inten;
						brightest = locList.get(i);
					}

				}

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (brightest == null)
				throw new EnptyResultException("No body saw");

			return brightest;
		}

		double fireball(Location loc) throws UnsupportedEncodingException {
			double max_e = -1;
			List<FireballData> data = dataReq.getFireballData();
			for (FireballData fd : data) {
				if (inRange(fd.getLat(), fd.getLon(), fd.getLatDir(), fd.getLonDir(), loc)) {
					if (fd.getImpactE() > max_e) {
						max_e = fd.getImpactE();
					}
				}
			}

			return max_e;
		}

		boolean inRange(double latitude, double longitude, String lat_dir, String lon_dir, Location loc) {
			// if(lat_dir!=loc.getLat_dir() || lon_dir!=loc.getLong_dir())
			// return false;

			if (latitude > loc.getLatitude() - 15 && latitude < loc.getLatitude() + 15) {
				if (longitude > loc.getLongitude() - 15 && longitude < loc.getLongitude() + 15)
					return true;
			}

			return false;
		}
	}

}
