package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.Location;

public class NYCDao {
	
public List<String> listaProvider() {
		
		String sql = "SELECT Provider "
				+ "FROM nyc_wifi_hotspot_locations "
				+ "GROUP BY Provider";
		
		List<String> result = new ArrayList<String>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		
		while (res.next()) {
			
		result.add(res.getString("Provider"));	
		
		}
		
		conn.close();
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return result;

	}
	
	public void creaVertici(Map<String, Location> idMap, String provider) {
		
		String sql = "SELECT Location, Latitude, Longitude "
				+ "FROM nyc_wifi_hotspot_locations "
				+ "WHERE Provider = (?) "
				+ "GROUP BY Location";
		Connection conn = DBConnect.getConnection();
		
		try {
			
		PreparedStatement st = conn.prepareStatement(sql);
		 st.setString(1, provider);
		ResultSet res = st.executeQuery();
		
		while (res.next()) {
			
			
		Location loc = new Location(res.getString("Location"), res.getDouble("Latitude"), res.getDouble("Longitude"));
		idMap.put(res.getString("Location"), loc);
		}
		
		conn.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}

	}
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	

}
