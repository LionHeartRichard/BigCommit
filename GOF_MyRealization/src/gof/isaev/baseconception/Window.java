package gof.isaev.baseconception;

public class Window {

	private Figure insideFigure;

	public double getArea(Figure figure) {
		return figure.area();
	}

	public void printlnArea(double radius) {
		insideFigure = new Circle(radius);
		System.out.println(insideFigure.area());
	}

	public void printlnArea(double height, double width) {
		insideFigure = new Rectangle(height, width);
		System.out.println(insideFigure.area());
	}

}
