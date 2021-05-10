import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BrightestStartService {

	LocationService locSer;
	DataRequest dataReq;
	
	public BrightestStartService(LocationService locationlist) {
		// TODO Auto-generated constructor stub
		locSer = locationlist;
		dataReq = new DataRequest();
	}
	
	Location getBrightestStarLocation() throws EnptyResultException, DataRequestException
	{
		double maxI = -1;
		Location brightest = null;
		try {
			
			List<Location> locList = locSer.getLocations();	
			dataReq.getData(Constants.GET_API_URL);
		
		
		for(int i =0 ; i< locList.size(); i++)
		{
			double inten = fireball(locList.get(i));
			if(inten>maxI)
			{
				maxI =  inten;
				locList.get(i).setMaxI(maxI);
				brightest = locList.get(i);
				
			}
			
		}
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(brightest == null)
			throw new EnptyResultException("No body saw");
		
		return brightest;
	}
	
	
	double fireball(Location loc) throws UnsupportedEncodingException
	{
		double max_e=-1;
		List<FireballData> data = dataReq.getFireballData();
		for(FireballData fd : data)
		{
			if(inRange(fd.getLat(), fd.getLon(),fd.getLatDir(),fd.getLonDir(),loc))
			{
				if(fd.getImpactE()>max_e)
				{
					max_e = fd.getImpactE();
				}
			}		
		}
				
		return max_e;
	}
	
	boolean inRange(double latitude, double longitude, String lat_dir, String lon_dir, Location loc)
	{
	//	if(lat_dir!=loc.getLat_dir() || lon_dir!=loc.getLong_dir())
	//		return false;
		
		if(latitude > loc.getLatitude()-15 && latitude < loc.getLatitude()+15)
		{
			if(longitude > loc.getLongitude()-15 && longitude < loc.getLongitude()+15)
				return true;
		}
				
			return false;		
	}	
}
