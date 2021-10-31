public class THUCPHAMTUOI extends QUANLYCUAHANGTHUCPHAM{

	public THUCPHAMTUOI(String msp, String tensp, float khoiluong, time ngaynhap, time hsd) {
		super(msp, tensp, khoiluong, ngaynhap, hsd);
		// TODO Auto-generated constructor stub
	}

	@Override
	float tinhtien() {
		// TODO Auto-generated method stub
		float tt=(float)(getKhoiluong()*20000)*this.thang(this.getNgaynhap(),this.getHsd());
		return tt;
	}

	@Override
	float tinhtien(int thang) {
		float tt=(float)(getKhoiluong()*20000)*thang;
		return tt;
	}
	

}
