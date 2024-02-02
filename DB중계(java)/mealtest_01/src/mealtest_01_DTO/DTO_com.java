package mealtest_01_DTO;

public class DTO_com {

	String com, time, grade,pass;


	DTO_com(){}

    public DTO_com(String time, String grade, String com,String pass) {
        this.time = time;
        this.grade = grade;
        this.com = com;
        this.pass = pass;
    }


    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }
    
    public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}
