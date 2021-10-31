import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	
	public static List<QUANLYCUAHANGTHUCPHAM> read_database() throws SQLException {//đọc
		List<QUANLYCUAHANGTHUCPHAM> l_tp = new ArrayList<QUANLYCUAHANGTHUCPHAM>();//khai baos thuộc tính l_tl của lớp QUANLYCUAHANGTHUCPHAM
		Connection connection = null;//kết nối DB
		Statement stm = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//đường dẫn sql server
			String connectionURL = "jdbc:sqlserver://PC\\SQLEXPRESS:1433;databaseName=ThucPham;integratedSecurity=true;";//kết nối URL với name sever,tạo database tên ThucPham
			connection = DriverManager.getConnection(connectionURL, "", "");//kết nối tới URL, login và password rỗng
			stm=connection.createStatement();
			String sql = "Select * from ThucPham";//truy vấn và hiển thị dữ liệu từ ThucPham
			ResultSet rs = stm.executeQuery(sql);//khai báo thuộc tính rs
			while(rs.next()) {
				time a = new time();//khai báo biến của lớp time k tham số truyền vào
				time b = new time();//khai báo biến của lớp time k tham số truyền vào
				if(rs.getString(3).equals("Thực Phẩm Khô")) {//nếu rs là Thực phẩm khô thì ghi các thông tin 
					THUCPHAMKHO k = new THUCPHAMKHO(rs.getString(1).trim(), rs.getString(2).trim(), rs.getFloat(4), a.doitime(rs.getString(5).trim()), b.doitime(rs.getString(6).trim()));
					l_tp.add(k);//thêm vào list
				}
				if(rs.getString(3).equals("Thực Phẩm Tươi")) {//nếu rs là Thực phẩm tươi thì ghi các thông tin
					THUCPHAMTUOI t = new THUCPHAMTUOI(rs.getString(1).trim(), rs.getString(2).trim(), rs.getFloat(4), a.doitime(rs.getString(5).trim()), b.doitime(rs.getString(6).trim()));
					l_tp.add(t);//thêm vào list
				}
			}
		} catch (ClassNotFoundException e) {//Nếu k tìm thấy thì thông báo kết nối thật bại
			System.out.println("ket noi that bai");
			System.err.println(e.getMessage()+"\n"+e.getClass()+"\n"+e.getCause());//sau đó mới in ra giao diện 
		}
		return l_tp;
	}
	public static void add(QUANLYCUAHANGTHUCPHAM tp) throws SQLException {//add
		Connection connection = null;//kết nối DB
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//đường dẫn tới sql server
			String connectionURL = "jdbc:sqlserver://PC\\SQLEXPRESS:1433;databaseName=ThucPham;integratedSecurity=true;";//kết nối URL với tên server, 
			connection = DriverManager.getConnection(connectionURL, "","" );//kết nối URL k có user và password
			String sql = "INSERT INTO ThucPham VALUES(?,?,?,?,?,?)";//khai báo biến sql kiểu String 
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tp.getMsp() );//đọc và ghi Msp 
			stmt.setString(2, tp.getTensp());//đọc và ghi Tensp
			if(tp instanceof THUCPHAMKHO)//nếu tp là THUCPHAMKHO
				stmt.setString(3, "Thực Phẩm Khô");//ghi Thực phẩm khô
			else stmt.setString(3, "Thực Phẩm Tươi");//không thì ghi Thực phẩm tươi
			stmt.setFloat(4, tp.getKhoiluong());//đọc và ghi Khoiluong kiểu float
			stmt.setString(5, tp.getNgaynhap().toString());//đọc và ghi Ngaynhap
			stmt.setString(6, tp.getHsd().toString());//đọc và ghi Hsd
			stmt.executeUpdate();//gọi đến lớp update
			connection.close();//ngắt hết nối
		}catch (ClassNotFoundException e) {//nếu không tìm được thì kết nối thật bại, chỉ in ra mabf hình tab
			System.out.println("ket noi that bai");
			System.err.println(e.getMessage()+"\n"+e.getClass()+"\n"+e.getCause());
		}
	}
	public static void fix(QUANLYCUAHANGTHUCPHAM tp) throws SQLException {//sửa
		Connection connection = null;
		Statement stm = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://PC\\SQLEXPRESS:1433;databaseName=ThucPham;integratedSecurity=true;";
			connection = DriverManager.getConnection(connectionURL, "", "");
			stm=connection.createStatement();
			String sql = "UPDATE ThucPham\r\n"//khaiáo biến sql
					+ "SET tên = '"+tp.getTensp()+"',[khối lượng] = '"+tp.getKhoiluong()+"',[ngày nhập] = '"+tp.getNgaynhap().toString()+"',[hạn sử dụng] = '"+tp.getHsd().toString()+"'\r\n"
					+ "WHERE ThucPham.mã = '"+tp.getMsp()+"' ";//ghi các thông tin
			stm.executeUpdate(sql);//gọi đến phương thức update
		}catch (ClassNotFoundException e) {
			System.out.println("ket noi that bai");//k được thì thông báo kết nối thất bại
			System.err.println(e.getMessage()+"\n"+e.getClass()+"\n"+e.getCause());////sau đó in ra tab
		}
	}
	public static void delete(List<QUANLYCUAHANGTHUCPHAM> l_tp) throws SQLException {//xoas
		Connection connection = null;
		Statement stm = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String connectionURL = "jdbc:sqlserver://PC\\SQLEXPRESS:1433;databaseName=ThucPham;integratedSecurity=true;";
				connection = DriverManager.getConnection(connectionURL, "", "");
				stm=connection.createStatement();
				String sql = "delete from ThucPham";//khai báo biến sql
				stm.executeUpdate(sql);//gọi đến phương thức update
				connection.close();//ngắt kết nối
			} catch (ClassNotFoundException e) {
				System.out.println("ket noi that bai");//k thì kết nối thất bại
				System.err.println(e.getMessage()+"\n"+e.getClass()+"\n"+e.getCause());//sau đó mới in ra tab
			}
	}
	public static void bot(QUANLYCUAHANGTHUCPHAM tp) throws SQLException {
		Connection connection = null;
		Statement stm = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String connectionURL = "jdbc:sqlserver://PC\\SQLEXPRESS:1433;databaseName=ThucPham;integratedSecurity=true;";
				connection = DriverManager.getConnection(connectionURL, "", "");
				stm=connection.createStatement();
				String sql = "delete from ThucPham where mã = '"+tp.getMsp()+"' ";//khai báo biến sql 
				stm.executeUpdate(sql);//update
				connection.close();//ngắt kết nối
			} catch (ClassNotFoundException e) {
				System.out.println("ket noi that bai");//k được thì in kết nối thất bại
				System.err.println(e.getMessage()+"\n"+e.getClass()+"\n"+e.getCause());//sau đó in ra tab
			}
	}
//	public static void main(String[] args) throws SQLException {
//		List<QUANLYCUAHANGTHUCPHAM> l_tp = read_database();
//		System.out.println(l_tp);
//		THUCPHAMKHO thucphamkho = new THUCPHAMKHO("K1","hello",41,new time(22, 12, 2002),new time(22, 12, 2002));
//		l_tp.add(thucphamkho);
//		add(thucphamkho);
//		l_tp = read_database();
//	}
//		System.out.println(l_tp);
//		fix(thucphamkho);
//		l_tp = read_database();
//		System.out.println(l_tp);
//		bot(thucphamkho);
//		l_tp = read_database();
//		System.out.println(l_tp);
//		delete(l_tp);
//		System.out.println(l_tp);
//	}
}
