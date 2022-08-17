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
	
	// �� ��ҵ�
	private JButton proscons = new JButton("Action");
	private JLabel tag = new JLabel();
	private JLabel num = new JLabel();
	public Color color = new Color(0, 0, 255); // blue
	
	// ���� ������ ����
	MyFrame() {
		setTitle("����2 �⸻���");
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(new WestPanel(), BorderLayout.WEST);
		cp.add(new MyPanel(), BorderLayout.CENTER);
		
		setLocationRelativeTo(null); // ȭ�� ����� GUI â �ߵ��� ��
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// ���콺 �׼� ������
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// �׼��ϴ� ��ư�� �ҽ� �����ͼ� �� ��ư ��ü ����
			JButton b = (JButton)e.getSource();
			
			// ��ư�� �ؽ�Ʈ�� ����, ���� �� �ٲ�� �ؽ�Ʈ�� ���� ��� ����
			if (b.getText().equals("����")) {
				b.setText("�ݴ�");
				color = Color.RED; // �ݴ��� ���� ������
			}
			else {
				b.setText("����");
				color = Color.BLUE; // ������ ���� �Ķ���
			}
		}
	}
	
	// ���̵� �г� ����
	class WestPanel extends JPanel {
		WestPanel() {
			setBackground(Color.YELLOW);
			setSize(100,400);
			this.setLayout(new GridLayout(10, 1)); // Flow�� �ȵǴ���...
			
			// �׼� ������ �߰��� ��ư�� �гο� �ø���
			add(proscons);
			proscons.setText("����");
			proscons.addActionListener(new MyActionListener());
			
			// �󺧵� �гο� �ø���
			JLabel empty = new JLabel();
			add(empty);
			empty.setText(" "); // ��� ������ ���� �ϴ� �׳� �� �� ����
			tag.setText("<html>�̸�:<br>ȫ�浿</html>");
			num.setText("<html>�й�:<br>11111111</html>");
			add(tag);
			add(num);
		}
	}
	
	// ���� �г�
	class MyPanel extends JPanel {
		// ��ǥ ������ �迭 ���� �� �� �ε����� ����� ���� ����
		Point xy[][] = new Point[30][2];
		int pros = 0;
		int cons = 0;
		
		// ���� �г� ���� �� ���콺 ����� ����
		MyPanel() {
			setBackground(Color.lightGray);
			
			addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// ���콺 ������ �� ���� �ڸ��� ��ǥ�� ���� ����(��/��)�� ���� �ٸ� �࿡ ����
					super.mousePressed(e);
					if(color.equals(Color.BLUE)) {
						xy[pros++][0] = e.getPoint();
					}
					else {
						xy[cons++][1] = e.getPoint();
					}
					repaint(); // �Ź� �ٽ� �׸���
				}
				
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			// �ٽ� �׸� ������ ���� ��ǥ�� ������ �ϹǷ� ����Ʈ �迭 ����ؼ� ��ĥ�� �� �׸���
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			for(int i = 0; i < pros; i++) {
				g.fillOval((int)xy[i][0].getX(), (int)xy[i][0].getY(), 7, 7);
			}
			g.setColor(Color.RED);
			for(int i = 0; i < cons; i++) {
				g.fillOval((int)xy[i][1].getX(), (int)xy[i][1].getY(), 7, 7);
			}
		}
	}
	
	// entry point
	public static void main(String[] args) {
		new MyFrame();
	}

}