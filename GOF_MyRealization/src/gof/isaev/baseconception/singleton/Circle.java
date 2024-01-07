package gof.isaev.baseconception.singleton;

public class Circle implements Figure {

	private final double pi = 3.14;
	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return pi * radius;
	}

}
