package ht.tm.dev.iot.classes.temperature;

public class TemperatureDTO {

	private final double temp;
	
	public TemperatureDTO(double temp) {
		this.temp = temp;
	}

	public double getTEMP_IN_CELCIUS() {
		return temp;
	}
	
	@Override
	public String toString() {
		return temp + " Degrees Celcius";
	}
	
}
