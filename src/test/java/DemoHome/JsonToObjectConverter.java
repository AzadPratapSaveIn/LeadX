package DemoHome;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;

public class JsonToObjectConverter {
	private static final ModelMapper modelMapper = new ModelMapper();
	
	public static WebsiteUtils convertJsonToWebsiteUtils(String json) {
        return modelMapper.map(json, WebsiteUtils.class);
    }
	
	public static List<WebsiteUtils> convertJsonToList(String json) {
        WebsiteUtils[] websiteUtilsArray = modelMapper.map(json, WebsiteUtils[].class);
        
        return Arrays.asList(websiteUtilsArray);
	}
}
