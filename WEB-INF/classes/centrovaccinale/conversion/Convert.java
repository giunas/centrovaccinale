package centrovaccinale.conversion;

import java.util.List;

public class Convert {
	//private static List<List<String>> sqlresult;
	private static String JSON;
	
	public static String toJSON(List<List<String>> result) {
		JSON = "";
		JSON += "{ \"data\":[";
		for (int r = 1; r < result.size(); r++) {
			JSON += "{";
			for (int i = 0; i < result.get(0).size(); i++) {
				JSON += '"'+result.get(0).get(i)+'"';
				JSON += ":"+'"'+result.get(r).get(i)+'"';
				if(i != result.get(0).size()-1)
					JSON += ",";
			}
			if(r != result.size()-1)
				JSON += "},";
			else
				JSON += "}]}";
		}	
		
		return JSON;
		
	}
}
