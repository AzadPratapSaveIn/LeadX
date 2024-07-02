package example;

@MyAnno()
public class MainExample {

	public static void main(String[] args) {
		
		MainExample d = new MainExample();
		Class c = d.getClass();
		System.out.println(c.getName());
		MyAnno anno = (MyAnno) c.getAnnotation(MyAnno.class);
		System.out.println(anno.myValue());
		System.out.println(anno.name());
		

	}

}
