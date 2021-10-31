import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class dmoop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;// khai báo các thuộc tính
	private JTextField textma;
	private JTextField textten;
	private JTextField textkhoiluong;
	private JTextField textngaynhap;
	private JTextField texthsd;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JLabel lbltt = new JLabel("");
	private JTable table;
	private int click_fix=0;
	private int index;
	private List<QUANLYCUAHANGTHUCPHAM> l_tp = new ArrayList<>();//tạo mảng có thể thay đổi kích thước
	private List<QUANLYCUAHANGTHUCPHAM> l_tp1 = new ArrayList<>();
	private time a;
	private time b;
	private float tt=0;
	private int check=0;
	private DefaultTableModel dtm;
	private JTextField textnam;
	private JTextField textthang;
	private JLabel lbltt_1;
	
	public void search() {
		for (QUANLYCUAHANGTHUCPHAM thucPham : l_tp) {//foreach duyệt các phần tử mà k cần index
			time c = new time(0,0,0);//khai báo thuộc tính time với parameters là (0;0;0)
			if(texthsd.getText().equals("")==false)//nếu texthsd trống 
				c.doitime(texthsd.getText());// thì gọi đến phương thức doitime
			if(thucPham.getMsp().contains(textma.getText())==true&&thucPham.getTensp().contains(textten.getText())==true&&(c.toString().equals(thucPham.getHsd().toString())==true||thucPham.getHsd().toString().contains(texthsd.getText())==true))
				//nếu thông tin đưa vào khớp với kiểu DL thì add vào list
				l_tp1.add(thucPham);
		}
	}
	public void showResult(List<QUANLYCUAHANGTHUCPHAM> p) {
		dtm.setRowCount(0);//loại bỏ tất cả các hàng
		for (QUANLYCUAHANGTHUCPHAM thucPham : p) {//vòng lặp foreach
			if(thucPham instanceof THUCPHAMKHO) {//toán tử so sánh kiểu
				THUCPHAMKHO s = (THUCPHAMKHO) thucPham;//downcasting
				dtm.addRow(new Object[] {//thêm hàng với các đối tượng được nhập
					s.getMsp(),s.getTensp(),"Thực phẩm khô",s.getKhoiluong(),"10000",s.getNgaynhap(),s.getHsd(),s.tinhtien()
			});
			}
			else if(thucPham instanceof THUCPHAMTUOI) {//toán tử so sánh kiểu
				THUCPHAMTUOI s = (THUCPHAMTUOI) thucPham;//downcasting
				dtm.addRow(new Object[] {//add hàng
						s.getMsp(),s.getTensp(),"Thực phẩm tươi",s.getKhoiluong(),"20000",s.getNgaynhap(),s.getHsd(),s.tinhtien()
			});	
				}
			}
		}
	public void xuatgiatri(List<QUANLYCUAHANGTHUCPHAM> l_tp) {
		index = table.getSelectedRow();
		if(l_tp.get(index) instanceof THUCPHAMKHO) {//vị trí hàng trong list QUANLYTHUCPHAM mà là THUCPHAMKHO
			THUCPHAMKHO s = (THUCPHAMKHO) l_tp.get(index);//downcasting
			comboBox.setSelectedIndex(1);//ghi vị trí 1
			//đọc và ghi vào text
			textma.setText(s.getMsp());
			textten.setText(s.getTensp());
			textkhoiluong.setText(""+s.getKhoiluong());
			textngaynhap.setText(""+s.getNgaynhap());
			texthsd.setText(""+s.getHsd());
		}
		if(l_tp.get(index) instanceof THUCPHAMTUOI) {//toán tử so sánh instance
			THUCPHAMTUOI s = (THUCPHAMTUOI) l_tp.get(index);//downcasting
			comboBox.setSelectedIndex(0);//ghi vị trí 0
			//đọc và gi vào text
			textma.setText(s.getMsp());
			textten.setText(s.getTensp());
			textkhoiluong.setText(""+s.getKhoiluong());
			textngaynhap.setText(""+s.getNgaynhap());
			texthsd.setText(""+s.getHsd());
		}
	}
	public int checkInput() {
		if(textma.getText().equals("")) {//nếu textma = "" thì có thông báo
			JOptionPane.showMessageDialog(contentPane, "Mã thực phẩm không được để trống","Chú ý", HEIGHT);
			return 0;
		}
		if(comboBox.getSelectedIndex()==0&&textma.getText().charAt(0)!='T') {//nếu comboBox được đọc vị trí 0 và textma vị trí đầu tiên #T thì có thông báo
			JOptionPane.showMessageDialog(contentPane, "Mã thực phẩm tươi phải bắt đầu bằng kí tự T","Chú ý", HEIGHT);
			return 0;
		}
		if(comboBox.getSelectedIndex()==1&&textma.getText().charAt(0)!='K') {//nếu comboBox được đọc vị trí 1 và textma vị trí đầu tiên #K thì có thông báo
			JOptionPane.showMessageDialog(contentPane, "Mã thực phẩm khô phải bắt đầu bằng kí tự K","Chú ý", HEIGHT);
			return 0;
		}
		if(textten.getText().equals("")) {//nếu textten để trống thì có thông báo
			JOptionPane.showMessageDialog(contentPane, "Tên thực phẩm không được để trống","Chú ý", HEIGHT);
			return 0;
		}
		try {
			Float.parseFloat(textkhoiluong.getText());//chuyển đổi kiểu String sang float
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(contentPane, "Khối lượng phải là số thực","Chú ý", HEIGHT);
			return 0;
		}
		try {
			a = new time(0,0,0);
			b = new time(0,0,0);
			a.doitime(textngaynhap.getText());//gọi phương thức doitime với tham số đầu vào là textngaynhap 
			b.doitime(texthsd.getText());//gọi phương thức doitime với tham số đầu vào là texthsd
			if(a.getDay()==0||a.getMonth()==0||a.getYear()==0||b.getDay()==0||b.getMonth()==0||b.getYear()==0){ 
				//nếu ngày tháng năm của ngaynhao và hsd đều bằng 0 thì có thông báo
				JOptionPane.showMessageDialog(contentPane, "Ngày nhập và hsd phải đúng định dạng(dd/mm/yyyy)","Chú ý", HEIGHT);
				return 0;
			}
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(contentPane, "Ngày nhập và hsd phải đúng định dạng(dd/mm/yyyy)","Chú ý", HEIGHT);
			return 0;
		}
		return 1;
	}
	public void tinhtien() {
		tt=0;
		for (QUANLYCUAHANGTHUCPHAM list : l_tp) {
			tt+=list.tinhtien();//toán tử cộng
		}
		lbltt.setText(""+tt);//ghi số tiền vào lbltt
	}
	public void update() {
		if(check==1) {
			index=l_tp.lastIndexOf(l_tp1.get(index));
			check=0;
		}
		if(check==0) {
			if(l_tp.get(index) instanceof THUCPHAMKHO) {
				if(checkInput()==0) return;
				THUCPHAMKHO k = new THUCPHAMKHO(textma.getText(),textten.getText() ,Float.parseFloat(textkhoiluong.getText()),a.doitime(textngaynhap.getText()),b.doitime(texthsd.getText()));
				l_tp.set(index, k);
				try {
					Database.fix(k);//thử nếu kết nối csdl bị lỗi thì sẽ có thông báo
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(l_tp.get(index) instanceof THUCPHAMTUOI) {
				if(checkInput()==0) return;
				THUCPHAMTUOI k = new THUCPHAMTUOI(textma.getText(),textten.getText() ,Float.parseFloat(textkhoiluong.getText()),a.doitime(textngaynhap.getText()),b.doitime(texthsd.getText()));
				l_tp.set(index, k);
				try {
					Database.fix(k);//thử kết nối với csdl nếu bị lỗi sẽ có thông báo
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dmoop frame = new dmoop();
					frame.setLocation(350, 150);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public dmoop() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\hello\\images\\grocery-128.png"));
		init();
		this.setLocationRelativeTo(null);
		dtm = (DefaultTableModel) table.getModel();
		try {
			l_tp = Database.read_database();
			showResult(l_tp);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void init() {
		setBackground(new Color(240, 240, 240));
		setForeground(Color.GRAY);
		setFont(new Font("Goudy Old Style", Font.BOLD, 12));
		setType(Type.POPUP);
		setTitle("Quản lí cửa hàng thực phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Th\u1EF1c ph\u1EA9m t\u01B0\u01A1i", "Th\u1EF1c ph\u1EA9m kh\u00F4"}));
		comboBox.setBounds(10, 28, 126, 23);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("LO\u1EA0I TH\u1EF0C PH\u1EA8M");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(10, 10, 96, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblma = new JLabel("M\u00C3:");
		lblma.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblma.setBounds(232, 13, 45, 13);
		contentPane.add(lblma);
		
		textma = new JTextField();
		textma.setBounds(320, 10, 165, 19);
		contentPane.add(textma);
		textma.setColumns(10);
		
		JLabel lblten = new JLabel("T\u00CAN:");
		lblten.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblten.setBounds(232, 45, 45, 13);
		contentPane.add(lblten);
		
		textten = new JTextField();
		textten.setColumns(10);
		textten.setBounds(320, 39, 165, 19);
		contentPane.add(textten);
		
		JLabel lblkhoiluong = new JLabel("KH\u1ED0I L\u01AF\u1EE2NG:");
		lblkhoiluong.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblkhoiluong.setBounds(207, 76, 91, 13);
		contentPane.add(lblkhoiluong);
		
		textkhoiluong = new JTextField();
		textkhoiluong.setColumns(10);
		textkhoiluong.setBounds(320, 73, 165, 19);
		contentPane.add(textkhoiluong);
		
		textngaynhap = new JTextField();
		textngaynhap.setColumns(10);
		textngaynhap.setBounds(320, 108, 165, 19);
		contentPane.add(textngaynhap);
		
		JLabel lblngaynhap = new JLabel("NG\u00C0Y NH\u1EACP:");
		lblngaynhap.setBounds(212, 111, 65, 13);
		contentPane.add(lblngaynhap);
		lblngaynhap.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblhsd = new JLabel("HSD:");
		lblhsd.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblhsd.setBounds(232, 138, 45, 13);
		contentPane.add(lblhsd);
		
		texthsd = new JTextField();
		texthsd.setColumns(10);
		texthsd.setBounds(320, 137, 165, 19);
		contentPane.add(texthsd);
		JButton btntimkiem = new  JButton("TÌM KIẾM");
		btntimkiem.setIcon(new ImageIcon(dmoop.class.getResource("/icon/search-icon-24.png")));
		JButton btnbot = new  JButton("BỚT");
		btnbot.setIcon(new ImageIcon(dmoop.class.getResource("/icon/control_remove_blue.png")));
		JButton btnthem = new JButton("THÊM");
		btnthem.setIcon(new ImageIcon(dmoop.class.getResource("/icon/control_add_blue.png")));
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a = new time();
				b = new time();
				QUANLYCUAHANGTHUCPHAM s=null;
				if(checkInput()==0) return;
				if(comboBox.getSelectedIndex()==0) {//đọc giá trị của comboBox nếu bằng 0 thì gán nó là THUCPHAMTUOI
					s = new THUCPHAMTUOI(textma.getText(),textten.getText() ,Float.parseFloat(textkhoiluong.getText()),a.doitime(textngaynhap.getText()),b.doitime(texthsd.getText()));
				}
				if(comboBox.getSelectedIndex()==1) {//đọc giá trị của comboBox nếu bằng  thì gán nó là THUCPHAMKHO
					s = new THUCPHAMKHO(textma.getText(),textten.getText() ,Float.parseFloat(textkhoiluong.getText()),a.doitime(textngaynhap.getText()),b.doitime(texthsd.getText()));
				}
				for (QUANLYCUAHANGTHUCPHAM list : l_tp) {
					if(s.getMsp().equals(list.getMsp())) { //nếu mã sp nhập vaò bằng msp đã có trong list thì có thông báo
						JOptionPane.showMessageDialog(contentPane, "Mã này đã có trong cửa hàng vui lòng nhập mã khác","Chú ý", HEIGHT);
					return;
					}
				}
				l_tp.add(s);//không thì thêm vào list
				try {
					Database.add(s);//thử add s vào csdl nếu k được thì sẽ có thông báo không kết nối được
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
				}
				tinhtien();
				showResult(l_tp);
				check=0;
			}
		});
		btnthem.setBounds(36, 180, 100, 21);
		contentPane.add(btnthem);
		
		JButton btnXoa = new JButton("XÓA");
		btnXoa.setIcon(new ImageIcon(dmoop.class.getResource("/icon/Actions-edit-delete-icon-16.png")));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(l_tp.size()==0) {//nếu không có sản phẩm nào trong list thì có thông báo
					String cmd = "Cửa hàng đang trống không cần xóa";
			        JOptionPane.showMessageDialog(contentPane, cmd,"Chú ý", HEIGHT);
			        return;
				}
				//khai báo biến n gán cho nó 1 thông báo
				int n = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc chắn muốn xóa toàn bộ cửa hàng không?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {
					//nếu chọn yes thì làm trống toàn bộ thông tin 
					textma.setText("");
					textten.setText("");
					textkhoiluong.setText("");
					textngaynhap.setText("");
					texthsd.setText("");
					lbltt.setText("");
					l_tp.clear();
					lbltt.setText("");
					showResult(l_tp);
					check=0;
					try {
						Database.delete(l_tp);//thử xóa trong csdl nếu k được thì có thông báo không kết nối được
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnXoa.setBounds(307, 180, 85, 21);
		contentPane.add(btnXoa);
		
		JButton btnsua = new JButton("SỬA");
		btnsua.setIcon(new ImageIcon(dmoop.class.getResource("/icon/Actions-document-edit-icon-16.png")));
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(l_tp.size()==0) {
					//nếu k có sp nào trong list thì có thông báo
					String cmd = "Bạn cần thêm thực phẩm trước khi sửa";
			        JOptionPane.showMessageDialog(contentPane, cmd,"Chú ý", HEIGHT);
				}
				else {
					try {
						if(click_fix==0) {
							if(check==0)
								xuatgiatri(l_tp);//sửa các thông tin cuat list l_tp
							else {
								xuatgiatri(l_tp1);//sửa các thông tin của list l_tp1
							}
							textma.setEditable(false);//textma vô hiệu hóa
							click_fix=1;//sau đó gán click_fic=1;
							btnsua.setText("Cập nhập");//chuyển Sửa thành cập nhất
							comboBox.setEnabled(false);
							//các nút chức năng khác bị vô hiệu hóa
							btnthem.setEnabled(false);
							btnXoa.setEnabled(false);
							btnbot.setEnabled(false);
							btntimkiem.setEnabled(false);
							
						}
						else if(click_fix==1) {//textma có thể điền được
							textma.setEditable(true);
							btnsua.setText("Sửa");
							update();//gọi phương thức update
							tinhtien();//gọi phương thức tinhtien
							showResult(l_tp);
							click_fix=0;//gán lại click_fix=0 cho lần sửa tiếp theo
							comboBox.setEnabled(true);
							//các button trở lại trạng thái bình thường
							btnthem.setEnabled(true);
							btnXoa.setEnabled(true);
							btnbot.setEnabled(true);
							btntimkiem.setEnabled(true);
							//sau đó in ra thông báo sửa thành công
							JOptionPane.showMessageDialog(contentPane, "Sửa thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}}
					catch(IndexOutOfBoundsException ex) {//cẫn chọn vào dòng cần sửa
						String cmd = "Bạn cần chọn vào dòng chứa thực phẩm cần sửa trước khi sửa";
						JOptionPane.showMessageDialog(contentPane, cmd,"Chú ý", HEIGHT);		        
					}
				}
			}
			});
		btnsua.setBounds(429, 180, 85, 21);
		contentPane.add(btnsua);
		
		btntimkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				l_tp1.clear();
				check = 1;
				search();//gọi phương thức search()
				if(l_tp1.size()==0) {
					JOptionPane.showMessageDialog(contentPane, "Không tìm thấy thực phẩm nào có thông tin trên","Thông báo", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Tìm kiếm thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				showResult(l_tp1);//gọi phương thức showwResult
			}
		});
		btntimkiem.setBounds(537, 180, 113, 21);
		contentPane.add(btntimkiem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 207, 655, 96);
		contentPane.add(scrollPane);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==1) lblkhoiluong.setText("THỂ TÍCH:");//nếu đọc comboBox có giá trị =1 thì lblkhoiluong chuyển thành Thể tích
				else if(comboBox.getSelectedIndex()==0) lblkhoiluong.setText("KHỐI LƯỢNG:");//nếu đọc comboBox có giá trị =1 thì lblkhoiluong là khối lượng
			}
		});
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"M\u00E3 sp", "T\u00EAn sp", "Lo\u1EA1i","M/V", "\u0110\u01A1n gi\u00E1", "Ng\u00E0y nh\u1EADp", "HSD", "Th\u00E0nh ti\u1EC1n"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
//				Class[] columnTypes = new Class[] {
//					String.class, String.class, String.class, String.class, String.class, String.class, String.class
//				};
//				public Class getColumnClass(int columnIndex) {
//					return columnTypes[columnIndex];
//				}
			});
			table.getColumnModel().getColumn(0).setPreferredWidth(41);
			table.getColumnModel().getColumn(1).setPreferredWidth(46);
			table.getColumnModel().getColumn(2).setPreferredWidth(79);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(4).setPreferredWidth(60);
			table.getColumnModel().getColumn(5).setPreferredWidth(65);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
			table.getColumnModel().getColumn(7).setPreferredWidth(94);
			scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1_5 = new JLabel("T\u1ED5ng ti\u1EC1n:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1_5.setBounds(323, 309, 96, 18);
		contentPane.add(lblNewLabel_1_5);
		lbltt.setForeground(Color.RED);
		
		
		lbltt.setBackground(Color.RED);
		lbltt.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbltt.setBounds(429, 309, 94, 18);
		contentPane.add(lbltt);
		
		btnbot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(l_tp.size()==0) {//list trống sẽ có thông báo
					String cmd = "Bạn cần thêm thực phẩm trước khi bớt";
			        JOptionPane.showMessageDialog(contentPane, cmd,"Chú ý", HEIGHT);
			        return;
				}
				
				index = table.getSelectedRow();
				if(index==-1) JOptionPane.showMessageDialog(contentPane, "Bạn phải chọn vào thực phẩm mà bạn muốn bớt","Chú ý",JOptionPane.WARNING_MESSAGE);//k tìm thấy vị trí
				else {
					int n = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc chắn bớt thực phẩm này không", "Thông báo", JOptionPane.YES_NO_OPTION);
					if(n == JOptionPane.YES_OPTION) {
						if(check==0) {
							try {
								Database.bot(l_tp.get(index));//thử bớt l_tp ở Database nếu k đưuojc sẽ có thông báo
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
							}
							//được thì loại ra khỏi list, tính lại tiền và show
							l_tp.remove(index);
							tinhtien();
							showResult(l_tp);
						}
						else {
							try {
								Database.bot(l_tp1.get(index));//thử bớt l_tp1 Database nếu k được thì có thông báo
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
							}
							//được thì loại khoir l_tp1, tính lại tiền, show
							l_tp.remove(l_tp1.get(index));
							showResult(l_tp);
							tinhtien();
							check=0;
						}
					}
				}
			}
		});
		btnbot.setBounds(180, 180, 85, 21);
		contentPane.add(btnbot);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(dmoop.class.getResource("/icon/Student-3-icon.png")));
		lblNewLabel_1.setBounds(10, 59, 126, 119);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblthang = new JLabel("Tháng:");
		lblthang.setBounds(549, 12, 46, 14);
		contentPane.add(lblthang);
		
		JLabel lblnam = new JLabel("Năm:");
		lblnam.setBounds(549, 41, 46, 14);
		contentPane.add(lblnam);
		
		textnam = new JTextField();
		textnam.setColumns(10);
		textnam.setBounds(605, 38, 45, 20);
		contentPane.add(textnam);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int thang,nam;
				try {
					thang = Integer.parseInt(textthang.getText());// chuyển kiếu String sang int
					nam = Integer.parseInt(textnam.getText());//chuyển kiểu String sang int
				}catch(NumberFormatException ex)	{
					JOptionPane.showMessageDialog(contentPane,"tháng và năm phải là số nguyên","Chú ý", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				time c = new time(0,thang,nam);
				long s = 0;
				int n = JOptionPane.showConfirmDialog(contentPane, "Bạn có muốn loại bỏ những thực phẩm hết hạn không?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {
					for(int i=0;i<l_tp.size();i++) {
						if(l_tp.get(i).thang(c, l_tp.get(i).getHsd())<1) {
							try {
								Database.bot(l_tp.get(i));//thử bớt ở Database nếu k được thì có thông báo
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane,"Không kết nối được với csdl","Chú ý", JOptionPane.INFORMATION_MESSAGE);
							}
							l_tp.remove(i);//đước thì loại 
							i--;//toán tử trừ
						}
						else {
							s+=l_tp.get(i).tinhtien(1);
						}
					}
				
				showResult(l_tp);
				tinhtien();
				lbltt_1.setText(""+s);	
				}
			}
		});
		btnOk.setBounds(547, 71, 73, 23);
		contentPane.add(btnOk);
		
		textthang = new JTextField();
		textthang.setColumns(10);
		textthang.setBounds(605, 9, 45, 20);
		contentPane.add(textthang);
		
		JLabel lbltinhtien1 = new JLabel("<html>Số tiền bảo quản<br> trong tháng là:<html>");
		lbltinhtien1.setBounds(549, 111, 101, 30);
		contentPane.add(lbltinhtien1);
		
		lbltt_1 = new JLabel("");
		lbltt_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbltt_1.setForeground(Color.RED);
		lbltt_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltt_1.setBackground(Color.RED);
		lbltt_1.setBounds(549, 151, 94, 18);
		contentPane.add(lbltt_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(529, 0, 136, 176);
		contentPane.add(panel);
	}
}
