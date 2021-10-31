public abstract class QUANLYCUAHANGTHUCPHAM {
	//khai báo các thuộc tính
     private String msp;
     private String tensp;
     private float khoiluong;
     private time ngaynhap;
	 private time hsd;
     abstract float tinhtien();
     abstract float tinhtien(int thang);
     public QUANLYCUAHANGTHUCPHAM(String msp, String tensp,float khoiluong, time ngaynhap, time hsd) {
    	 setMsp(msp);
    	 setTensp(tensp);
    	 setKhoiluong(khoiluong);
    	 setNgaynhap(ngaynhap);
    	 setHsd(hsd);
     }
     //getter cuar thuộc tính msp
	String getMsp() {
		return msp;
	}
	//setter của thuộc tính msp
	void setMsp(String msp) {
		this.msp = msp;
	}
	//getter của thuộc tính tensp
	String getTensp() {
		return tensp;
	}
	//setter của thuộc tính tensp
	void setTensp(String tensp) {
		this.tensp = tensp;
	}
	@Override
	public String toString() {
		return "QUANLYCUAHANGTHUCHPHAM [msp=" + msp + ", tensp=" + tensp + ", khoiluong=" + khoiluong + ", ngaynhap="
				+ ngaynhap + ", hsd=" + hsd + "]";
	}
	//getter của thuộc tính ngaynhap
	time getNgaynhap() {
		return ngaynhap;
	}
	//setter của thuộc tính ngaynhap
	void setNgaynhap(time ngaynhap2) {
		this.ngaynhap = ngaynhap2;
	}
	//getter của thuộc tính hsd
	time getHsd() {
		return hsd;
	}
	//setter của thuộc tính hsd
	void setHsd(time hsd2) {
		this.hsd = hsd2;
	}
	//getter của thuôc tính khoiluong()
	float getKhoiluong() {
		return khoiluong;
	}
	//setter của thuộc tính khoiluong()
	void setKhoiluong(float khoiluong) {
		this.khoiluong = khoiluong;
	}
	//khai báo phương thức time với parameters là time a, time b
    public int thang(time a,time b) {
    	if(b.getMonth()>=a.getMonth())//tính số tháng lưu trữ trong kho
    		return (b.getYear()-a.getYear())*12+b.getMonth()-a.getMonth();
    	else return (b.getYear()-a.getYear()-1)*12+b.getMonth()+12-a.getMonth();
    }

}
