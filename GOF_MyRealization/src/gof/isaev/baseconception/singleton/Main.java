package gof.isaev.baseconception.singleton;

public class Main {

	public static void main(String[] args) {
		SingletonWindow window = SingletonWindow.WINDOW.getInstance();
		window.println(new Rectangle(12, 3.5));

		window.setFigure(2);
		Figure f1 = window.getFigure();
		System.out.println(f1.area());
		window.setFigure(12, 3.5);
		f1 = window.getFigure();
		System.out.println(f1.area());
	}

}
