package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Hasta;


public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setBounds(10, 7, 70, 22);
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		w_pane.add(label);
		
		fld_name = new JTextField();
		fld_name.setBounds(10, 36, 264, 27);
		fld_name.setColumns(10);
		w_pane.add(fld_name);
		
		JLabel lblTc = new JLabel("T.C. Numarası");
		lblTc.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblTc.setBounds(10, 70, 137, 22);
		w_pane.add(lblTc);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 99, 264, 27);
		w_pane.add(fld_tcno);
		
		JLabel lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblifre.setBounds(10, 133, 137, 22);
		w_pane.add(lblifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		fld_pass.setBounds(10, 162, 264, 27);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length() == 0 || fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUİ login = new LoginGUİ();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btn_register.setBounds(10, 196, 264, 38);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUİ login = new LoginGUİ();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btn_backto.setBounds(10, 241, 264, 38);
		w_pane.add(btn_backto);
	}
}
