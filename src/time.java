public class time {
	//khai báo các thuộc tính 
	private int day;
	private int month;
	private int year;
	//phương thức khởi tạo k có tham số truyền vào
	public time() {
		super();
		// TODO Auto-generated constructor stub
	}//phương thức khởi tạo có tham số truyền vào là day, month, year
	public time(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}
	//getter và setter của các thuộc tính
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "" + day + "/" + month + "/" + year +"";
	}
	public time doitime(String s) {
		this.setDay(0);
		this.setMonth(0);
		this.setYear(0);
		String a = "";
		int j=0;
		s+="/";
		int n = s.length();
		for(int i=0;i<n;i++) {
			if(s.charAt(i) =='/') {//nếu kí tự ở vị trí i là /
				for(int k=j;k<i;k++) {
					a+=s.charAt(k);
				}
				if(this.getDay()==0) this.setDay(Integer.parseInt(a));//chuyển từ Strinh sang int
				else if(this.getMonth()==0) this.setMonth(Integer.parseInt(a));//chuyển từ Strinh sang int
				else if(this.getYear()==0) this.setYear(Integer.parseInt(a));//chuyển từ Strinh sang int
				j=i+1;
				a="";
			}
		}
		return this;
	}
	public static void main(String[] args) {
		time a = new time();//khai báo a thuộc lớp time
		String s = "22/3/2001";//phép gán
		System.out.println(a.doitime(s));
		System.out.println(a.getDay());
	}
	public boolean contains(String text) {
		// TODO Auto-generated method stub
		return false;
	}
}
