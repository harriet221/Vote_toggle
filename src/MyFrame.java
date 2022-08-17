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
	
	// 각 요소들
	private JButton proscons = new JButton("Action");
	private JLabel tag = new JLabel();
	//private JLabel name = new JLabel();
	private JLabel num = new JLabel();
	//private JLabel ber = new JLabel();
	public Color color = new Color(0, 0, 255); // 파랑
	
	// 메인 프레임 설정
	MyFrame() {
		setTitle("융프2 기말고사");
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(new WestPanel(), BorderLayout.WEST);
		cp.add(new MyPanel(), BorderLayout.CENTER);
		
		setLocationRelativeTo(null); // 화면 가운데서 GUI 창 뜨도록 함
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// 마우스 액션 리스너
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// 액션하는 버튼의 소스 가져와서 새 버튼 객체 생성
			JButton b = (JButton)e.getSource();
			
			// 버튼의 텍스트에 따라서, 누를 때 바뀌는 텍스트와 색깔 요소 설정
			if (b.getText().equals("찬성")) {
				b.setText("반대");
				color = Color.RED; // 반대일 때는 빨간색
			}
			else {
				b.setText("찬성");
				color = Color.BLUE; // 찬성일 때는 파란색
			}
		}
	}
	
	// 사이드 패널 설정
	class WestPanel extends JPanel {
		WestPanel() {
			setBackground(Color.YELLOW);
			setSize(100,400);
			this.setLayout(new GridLayout(10, 1));
			
			// 액션 리스너 추가한 버튼을 패널에 올리기
			add(proscons);
			proscons.setText("찬성");
			proscons.addActionListener(new MyActionListener());
			
			// 라벨들 패널에 올리기
			JLabel empty = new JLabel();
			add(empty);
			empty.setText(" "); // 어떻게 공간을 띄울까 하다 그냥 빈 라벨 넣음
			tag.setText("<html>이름:<br>홍길동</html>");
			num.setText("<html>학번:<br>11111111</html>");
			add(tag);
			//tag.setText("이름:");
			//add(name);
			//name.setText("name");
			add(num);
			//num.setText("학번:");
			//add(ber);
			//ber.setText("studentnum");
		}
	}
	
	// 메인 패널
	class MyPanel extends JPanel {
		// 좌표 저장할 배열 선언 및 각 인덱스로 사용할 정수 선언
		Point xy[][] = new Point[30][2];
		int pros = 0;
		int cons = 0;
		
		// 메인 패널 설정 및 마우스 어댑터 설정
		MyPanel() {
			setBackground(Color.lightGray);
			
			addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// 마우스 눌렀을 때 누른 자리의 좌표를 현재 색깔(찬/반)에 따라 다른 행에 저장
					super.mousePressed(e);
					if(color.equals(Color.BLUE)) {
						xy[pros++][0] = e.getPoint();
					}
					else {
						xy[cons++][1] = e.getPoint();
					}
					repaint(); // 매번 다시 그리기
				}
				
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			// 다시 그릴 때마다 지난 투표가 보여야 하므로 포인트 배열 사용해서 색칠된 원 그리기
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
