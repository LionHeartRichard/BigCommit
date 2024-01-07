package gof.isaev.baseconception.singleton;

public enum SingletonWindow {

	WINDOW;

	private Figure insideFigure;

	private SingletonWindow() {
	}

	public SingletonWindow getInstance() {
		return WINDOW;
	}

	public void println(Figure figure) {
		System.out.println(figure.area());
	}

	public void setFigure(double radius) {
		this.insideFigure = new Circle(radius);
	}

	public void setFigure(double height, double width) {
		this.insideFigure = new Rectangle(height, width);
	}

	public Figure getFigure() {
		return insideFigure;
	}
}
