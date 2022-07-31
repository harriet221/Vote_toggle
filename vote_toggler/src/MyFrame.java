import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	
	private JButton proscons = new JButton("Action");
	private JLabel tag = new JLabel();
	private JLabel name = new JLabel();
	private JLabel num = new JLabel();
	private JLabel ber = new JLabel();
	private Color color = new Color(0);
	
	MyFrame() {
		setTitle("����2 �⸻���");
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(new WestPanel(), BorderLayout.WEST);
		cp.add(new MyPanel(), BorderLayout.CENTER);
		
		setLocationRelativeTo(null); // ����� GUI â �ߵ��� ��
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// event
			JButton b = (JButton)e.getSource();
			if (b.getText().equals("����")) {
				b.setText("�ݴ�");
				// �ݴ� - ���� ������

			}
			else {
				b.setText("����");
				// ���� - �Ķ� ������
			}
			
		}
		
	}
	class WestPanel extends JPanel {
		WestPanel() {
			setBackground(Color.YELLOW);
			setSize(100,400);
			this.setLayout(new GridLayout(15, 1));
			
			add(proscons);
			proscons.setText("����");
			proscons.addActionListener(new MyActionListener());
			
			JLabel empty = new JLabel();
			add(empty);
			empty.setText(" ");
			add(tag);
			tag.setText("�̸�:");
			add(name);
			name.setText("name");
			add(num);
			num.setText("�й�:");
			add(ber);
			ber.setText("studentnum");
			
		}
	}
	
	class MyPanel extends JPanel {
		Point xy[] = new Point[30];
		int n = 0;
		MyPanel() {
			setBackground(Color.lightGray);
			
			addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					xy[n++] = e.getPoint();
					repaint();
				}
				
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			for(int i = 0; i < n; i++) {
				g.fillOval((int)xy[i].getX(), (int)xy[i].getY(), 5, 5);
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

}
