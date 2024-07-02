package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
	
	public List<HashMap<String, String>> jsonToMap() throws IOException {
		String rawJsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\DemoHome\\HomePage.json"), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> list = objectMapper.readValue(rawJsonData, new TypeReference<List<HashMap<String, String>>>(){});
		return list;
	}
	
}
