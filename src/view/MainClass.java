package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClienteDAO;
import dao.DataSource;
import model.Cliente;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainClass extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass frame = new MainClass();
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
	public MainClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Recupera Dados...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataSource dataSource = new DataSource();
				
				ClienteDAO clieDao = new ClienteDAO(dataSource);
				
				ArrayList<Cliente> lista = clieDao.readAll();
				
				if(lista != null) {
					textArea.setText(null);
					for(Cliente c: lista) {
					textArea.setText(textArea.getText()+c/*.getNome()*/+"\n");
					//textArea.setText(c+"\n");
					}
				}
				dataSource.closeDataSource();
				
			}
		});
		contentPane.add(btnNewButton, BorderLayout.NORTH);
		
		
	}

}
