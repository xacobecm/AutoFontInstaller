package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.API;
import model.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView extends JFrame {

	//TODO: SET FIXED WINDOW SCREEN
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8077248379143648257L;
	private JPanel contentPane;
	private JTextField txtSearch;
	JList<Font> listFont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateList(String search) throws Exception {
		DefaultListModel<Font> listModel = new DefaultListModel<Font>();
		API api = new API();
		ArrayList<Font> fonts = api.getFontList();
		for( int i = 0; i < fonts.size(); i++ ) {
			if ( fonts.get(i).getName().matches("(.*)" + search + "(.*)") ) {
				listModel.addElement(fonts.get(i));
			}
		}
		
		listFont.setModel(listModel);
	}

	public MainView() throws Exception {
		
		DefaultListModel<Font> listModel = new DefaultListModel<Font>();
		API api = new API();
		ArrayList<Font> fonts = api.getFontList();
		for( int i = 0; i < fonts.size(); i++ ) {
			listModel.addElement(fonts.get(i));
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panelSearch = new JPanel();
		contentPane.add(panelSearch);
		panelSearch.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		
		txtSearch = new JTextField();
		panelSearch.add(txtSearch);
		txtSearch.setColumns(30);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String searchString = txtSearch.getText();
					System.out.println(searchString);
					updateList(searchString);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panelSearch.add(btnNewButton);
		
		JPanel panelTable = new JPanel();
		contentPane.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		listFont = new JList<Font>();
		listFont.setModel(listModel);
		scrollPane.setViewportView(listFont);
		
		JPanel panelButton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		contentPane.add(panelButton);
		
		JButton btnNewButton_1 = new JButton("Open");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FontView fv = new FontView();
				fv.setFont(listFont.getSelectedValue());
				fv.setVisible(true);
			}
		});
		panelButton.add(btnNewButton_1);
	}

}
