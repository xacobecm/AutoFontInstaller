package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.API;
import model.Font;
import model.Variant;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FontView extends JFrame {

	private JPanel contentPane;
	Font font;
	JList<Variant> listVariant;
	API api = new API();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FontView frame = new FontView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setFont(Font f) {
		this.font = f;
		populateList();
	}
	
	public void populateList() {
		DefaultListModel<Variant> listModel = new DefaultListModel<>();
		for( Variant v : font.getVariants() ) {
			listModel.addElement(v);
		}
		listVariant.setModel(listModel);
	}

	/**
	 * Create the frame.
	 */
	public FontView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		listVariant = new JList<Variant>();
		scrollPane.setViewportView(listVariant);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_1);
		
		JButton btnInstall = new JButton("Install");
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Variant selected = listVariant.getSelectedValue();
				boolean success = api.installFont(selected);
				if ( success ) {
					JOptionPane.showMessageDialog(null, "Font installed succesfully");
				}
				
			}
		});
		panel_1.add(btnInstall);
	}

}
