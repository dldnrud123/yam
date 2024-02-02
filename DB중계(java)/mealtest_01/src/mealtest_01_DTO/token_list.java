package mealtest_01_DTO;

public class token_list {
	
	String token;
	int morning, sun, night;
	
	public token_list(){}
	
	

	public token_list(String token, int morning, int sun, int night) {
		this.token = token;
		this.morning = morning;
		this.sun = sun;
		this.night = night;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getMorning() {
		return morning;
	}

	public void setMorning(int morning) {
		this.morning = morning;
	}

	public int getSun() {
		return sun;
	}

	public void setSun(int sun) {
		this.sun = sun;
	}

	public int getNight() {
		return night;
	}

	public void setNight(int night) {
		this.night = night;
	}
	
}
	
	