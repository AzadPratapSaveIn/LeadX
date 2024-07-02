package DemoHome;

public class WebsiteUtils {
	private String name;
	private String xpath;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXpath() {
		return xpath;
	}
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	public WebsiteUtils(String name, String xpath) {
		super();
		this.name = name;
		this.xpath = xpath;
	}
	public WebsiteUtils() {
		super();
	}
	
	
}
