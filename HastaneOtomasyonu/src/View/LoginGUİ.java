package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUİ extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUİ frame = new LoginGUİ();
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
	public LoginGUİ() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medical logo1.jpg")));
		lbl_logo.setBounds(193, 11, 82, 80);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblNewLabel.setBounds(70, 82, 343, 49);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 125, 464, 225);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblgeldiniz = new JLabel("T.C. Numaranız:");
		lblgeldiniz.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblgeldiniz.setBounds(10, 11, 169, 33);
		w_hastaLogin.add(lblgeldiniz);

		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblifre.setBounds(10, 73, 169, 33);
		w_hastaLogin.add(lblifre);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hastaTc.setBounds(154, 11, 295, 33);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btn_register = new JButton("KAYIT OL");
		btn_register.setBackground(new Color(255, 255, 255));
		btn_register.setForeground(Color.BLACK);
		btn_register.setFont(new Font("Serif", Font.BOLD, 12));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(10, 137, 157, 49);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("GİRİŞ YAP");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_hastaTc.getText().equals(rs.getString("tcno"))
									&& fld_hastaPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;						
								}

							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Böyle bir hasta bulunamadı lütfen kayıt olunuz !");
					}
				}
			}
		});
		btn_hastaLogin.setForeground(Color.BLACK);
		btn_hastaLogin.setFont(new Font("Serif", Font.BOLD, 12));
		btn_hastaLogin.setBackground(new Color(255, 255, 255));
		btn_hastaLogin.setBounds(292, 137, 157, 49);
		w_hastaLogin.add(btn_hastaLogin);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(154, 76, 294, 33);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);

		JLabel lblgeldiniz_1 = new JLabel("T.C. Numaranız:");
		lblgeldiniz_1.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblgeldiniz_1.setBounds(10, 11, 169, 33);
		w_doctorLogin.add(lblgeldiniz_1);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(154, 11, 295, 33);
		w_doctorLogin.add(fld_doctorTc);

		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblifre_1.setBounds(10, 73, 169, 33);
		w_doctorLogin.add(lblifre_1);

		JButton btn_doctorLogin = new JButton("GİRİŞ YAP");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& fld_doctorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setForeground(Color.BLACK);
		btn_doctorLogin.setFont(new Font("Serif", Font.BOLD, 12));
		btn_doctorLogin.setBackground(new Color(255, 255, 255));
		btn_doctorLogin.setBounds(10, 137, 439, 49);
		w_doctorLogin.add(btn_doctorLogin);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(155, 73, 294, 33);
		w_doctorLogin.add(fld_doctorPass);
	}
}
