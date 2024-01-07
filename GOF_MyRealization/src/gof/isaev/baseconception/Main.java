package gof.isaev.baseconception;

public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		double area = window.getArea(new Rectangle(1.3, 2.5));
		System.out.println(area);

		Figure figure = new Rectangle(4.4, 2.3);
		area = figure.area();
		System.out.println(area);
	}

}
