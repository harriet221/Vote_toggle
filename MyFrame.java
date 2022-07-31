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
		setTitle("융프2 기말고사");
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(new WestPanel(), BorderLayout.WEST);
		cp.add(new MyPanel(), BorderLayout.CENTER);
		
		setLocationRelativeTo(null); // 가운데서 GUI 창 뜨도록 함
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// event
			JButton b = (JButton)e.getSource();
			if (b.getText().equals("찬성")) {
				b.setText("반대");
				// 반대 - 빨간 점으로

			}
			else {
				b.setText("찬성");
				// 찬성 - 파란 점으로
			}
			
		}
		
	}
	class WestPanel extends JPanel {
		WestPanel() {
			setBackground(Color.YELLOW);
			setSize(100,400);
			this.setLayout(new GridLayout(15, 1));
			
			add(proscons);
			proscons.setText("찬성");
			proscons.addActionListener(new MyActionListener());
			
			JLabel empty = new JLabel();
			add(empty);
			empty.setText(" ");
			add(tag);
			tag.setText("이름:");
			add(name);
			name.setText("name");
			add(num);
			num.setText("학번:");
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
