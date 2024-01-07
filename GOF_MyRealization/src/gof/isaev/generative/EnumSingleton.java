package gof.isaev.generative;

public enum EnumSingleton {

	SINGLETON("");

	private String info;

	private EnumSingleton(String info) {
		this.info = info;
	}

	public EnumSingleton getInstance() {
		return SINGLETON;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
