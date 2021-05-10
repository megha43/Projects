import java.util.HashMap;
import java.util.Map;

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