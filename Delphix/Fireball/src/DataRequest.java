import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

//import ResponseIndex;
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
//		            	fireball.setEnergy(Double.parseDouble(jsonArray.get(ResponseIndex.ENERGY.getValue()).toString()));
						fireball.setImpactE(
								Double.parseDouble(jsonArray.get(ResponseIndex.IMPACT_E.getValue()).toString()));
						fireball.setLat(Double.parseDouble(jsonArray.get(ResponseIndex.LAT.getValue()).toString()));
						fireball.setLatDir(jsonArray.get(ResponseIndex.LAT_DIR.getValue()).toString());
						fireball.setLon(Double.parseDouble(jsonArray.get(ResponseIndex.LON.getValue()).toString()));
						fireball.setLonDir(jsonArray.get(ResponseIndex.LON_DIR.getValue()).toString());
//		            	fireball.setAltitude(Double.parseDouble(jsonArray.get(ResponseIndex.ALT.getValue()).toString()));
//		            	fireball.setVelocity(Double.parseDouble(jsonArray.get(ResponseIndex.VEL.getValue()).toString()));
						fireballData.add(fireball);
					}
				}
			}

		} catch (Exception e) {
			throw new DataRequestException(Messages.MALFORMED_URL);
		}
	}

}
