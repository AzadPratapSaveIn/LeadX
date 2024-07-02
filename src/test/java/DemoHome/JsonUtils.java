package DemoHome;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DemoHome.model.PageElements;
import lombok.NonNull;

public class JsonUtils {
	private static final String FILE_PATH = 
			"C:\\Users\\ADMIN\\eclipse-workspace\\DemoLeadX\\src\\test\\java\\DemoHome\\HomePage.json";
	
	private Map<String, PageElements> pageElementsMap;
	
	@SuppressWarnings("unchecked")
	public JsonUtils() {
		pageElementsMap = new HashMap();
		final String fileDetails = getFileDetails();
		final Gson gson = new Gson();
		
		final JSONArray jsonArray= new JSONArray(fileDetails);
		for(int i=0; i<jsonArray.length(); i++) {
			Type type = new TypeToken<Map<String, PageElements>>(){}.getType();
			Map<String, PageElements> elementMap = gson.fromJson(jsonArray.get(i).toString(), type);
			pageElementsMap.putAll(elementMap);
		}
	
	}
	
	public PageElements getPageElements(@NonNull final String name) {
 		return pageElementsMap.get(name);
	}
	
	private String getFileDetails() {
		String str= "";
		File file = new File(FILE_PATH);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			
			while ((st = br.readLine()) != null){
				str += st;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	
}
