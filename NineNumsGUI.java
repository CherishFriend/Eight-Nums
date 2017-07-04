import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class NineNumsGUI {
	/*
	布局
	**/
	private JFrame frame;
	private JPanel sideBar;
	private JPanel mainBar;
	private JPanel counterBar;
	private JLabel side;
	private JLabel bottom;
	/*
	菜单栏
	**/
	private JMenuBar menubar;
	private JMenu gameMenu;
	private JMenu helpMenu;
	private JMenuItem beginItem;
	private JMenuItem resetItem;
	private JMenuItem switchItem;
	private JMenuItem recordItemm;
	private JMenuItem helpItem;
	private JMenuItem aboutUsItem;
	//对话框
	JDialog aboutUs;
	JDialog help;

	/*
	游戏区
	**/
	private JButton[] b = new JButton[10];
	ImageIcon pic1;
	ImageIcon pic2;
	ImageIcon pic3;
	ImageIcon pic4;
	ImageIcon pic5;
	ImageIcon pic6;
	ImageIcon pic7;
	ImageIcon pic8;
	ImageIcon pic0;
	/*
	侧边栏
	**/
	int step;
	Calendar beginTime;
	Thread threadTime;
	//gameModel
	private JPanel cardSwitch;
	private JPanel infoPane;
	private JPanel buttonPane;
	private JPanel resetPane;
	private JPanel sizePane;
	private JButton size;
	private JButton reset;
	private JLabel time;
	private JLabel steps;
	private JTextField timeCount;
	private JTextField stepCount;
	//displayModel
	private JLabel stepsD;
	private JTextField stepCountD;
	private CardLayout myCardLayout;
	private JPanel oSideBar;
	private JButton bfs;
	private JButton dbfs;
	private JButton a;
	private	JButton dfs;
	/*
	GUI
	**/
	public NineNumsGUI() {
		frame = new JFrame();
		frame.setTitle("九宫格游戏");
		frame.setSize(400, 350);
		frame.setLayout(new BorderLayout(5, 5));

		/*
		设置图片
		**/
		ImageIcon titlePic = new ImageIcon("1.jpg");
		Image smallImage = titlePic.getImage().getScaledInstance(60, 40, Image.SCALE_FAST);
		/*
		菜单栏
		**/
		menubar = new JMenuBar();
		gameMenu = new JMenu("游戏(G)");
		gameMenu.setMnemonic('G');
		helpMenu = new JMenu("帮助(H)");
		helpMenu.setMnemonic('H');
		//游戏栏
		beginItem = new JMenuItem("开始(B)", 'B');
		resetItem = new JMenuItem("重置(R)", 'R');
		switchItem = new JMenuItem("改变模式(S)", 'S');
		recordItemm = new JMenuItem("纪录(C)", 'C');
		//帮助栏
		helpItem = new JMenuItem("有关游戏(I)", 'I');
		aboutUsItem = new JMenuItem("关于我们(U)", 'U');

		gameMenu.add(beginItem);
		gameMenu.add(resetItem);
		gameMenu.add(switchItem);
		gameMenu.addSeparator();
		gameMenu.add(recordItemm);

		helpMenu.add(helpItem);
		helpMenu.add(aboutUsItem);

		menubar.add(gameMenu);
		menubar.add(helpMenu);

		sideBar = new JPanel(new BorderLayout(10, 10));
		mainBar = new JPanel(new GridLayout(3, 3, 5, 10));
		counterBar = new JPanel();
		/*
		游戏区
		**/
		pic1 = new ImageIcon("1.png");
		pic2 = new ImageIcon("2.png");
		pic3 = new ImageIcon("3.png");
		pic4 = new ImageIcon("4.png");
		pic5 = new ImageIcon("5.png");
		pic6 = new ImageIcon("6.png");
		pic7 = new ImageIcon("7.png");
		pic8 = new ImageIcon("8.png");
		pic0 = new ImageIcon("9.png");
		b[0] = new JButton(pic1);
		b[1] = new JButton(pic2);
		b[2] = new JButton(pic3);
		b[3] = new JButton(pic4);
		b[4] = new JButton(pic5);
		b[5] = new JButton(pic6);
		b[6] = new JButton(pic7);
		b[7] = new JButton(pic8);
		b[8] = new JButton(pic0);
		for (int i = 0; i < 9; i++) {
			b[i].setOpaque(false);
			b[i].setContentAreaFilled(false);
			b[i].setMargin(new Insets(0, 0, 0, 0));
			b[i].setFocusPainted(false);
			b[i].setBorderPainted(false);
			b[i].setBorder(null);
			b[i].addActionListener(new move());
			mainBar.add(b[i]);
		}
		Border border = BorderFactory.createEtchedBorder();
		Border titledBorder = BorderFactory.createTitledBorder(border);

		mainBar.setBorder(border);
		//侧边栏
		step = 0;
		//第一张卡片 gameModel
		myCardLayout = new CardLayout();
		cardSwitch = new JPanel(myCardLayout);

		infoPane = new JPanel(new GridLayout(4, 1));
		buttonPane = new  JPanel(new GridLayout(3, 1));
		resetPane = new JPanel(new FlowLayout());
		sizePane = new JPanel(new FlowLayout());
		time = new JLabel("时间");
		timeCount = new JTextField(10);
		timeCount.setText("00:00");
		timeCount.setEditable(false);
		timeCount.setHorizontalAlignment(JTextField.CENTER);
		steps = new JLabel("步数");
		stepCount = new JTextField(10);
		stepCount.setText("00");
		stepCount.setEditable(false);
		stepCount.setHorizontalAlignment(JTextField.CENTER);
		size = new JButton("3X3");
		reset = new JButton("重置");

		infoPane.add(steps);
		infoPane.add(stepCount);
		infoPane.add(time);
		infoPane.add(timeCount);


		resetPane.add(reset);
		sizePane.add(size);

		infoPane.setBorder(border);

		buttonPane.add(size);
		buttonPane.add(new JLabel(" "));
		buttonPane.add(reset);
		sideBar.add(BorderLayout.CENTER, infoPane);
		sideBar.add(BorderLayout.SOUTH, buttonPane);




		//第二张卡片 serachModel
		oSideBar = new JPanel(new GridLayout(6, 1));
		stepsD = new JLabel("步数");
		stepCountD = new JTextField(10);
		stepCountD.setText("00");
		stepCountD.setEditable(false);
		stepCountD.setHorizontalAlignment(JTextField.CENTER);
		bfs = new JButton("BFS");
		dbfs = new JButton("DBFS");
		a = new JButton("A*");
		dfs = new JButton("DFS");

		oSideBar.add(stepsD);
		oSideBar.add(stepCountD);
		oSideBar.add(bfs);
		oSideBar.add(dbfs);
		oSideBar.add(a);
		oSideBar.add(dfs);

		cardSwitch.add(sideBar, "first");
		cardSwitch.add(oSideBar, "second");

		/*
		布局
		**/
		side = new JLabel("       ");
		bottom = new JLabel(" ");

		frame.setIconImage(smallImage);
		frame.add(BorderLayout.NORTH, menubar);
		frame.add(BorderLayout.CENTER, mainBar);
		frame.add(BorderLayout.EAST, cardSwitch);
		frame.add(BorderLayout.WEST, side);
		frame.add(BorderLayout.SOUTH, bottom);


		//事件处理
		beginItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step = 0;
				stepCount.setText(String.valueOf(step));
				beginTime = Calendar.getInstance();
				timer t = new timer();
				threadTime = new Thread(t);
				threadTime.start();
			}
		});
		resetItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b[0].setIcon(pic1);
				b[1].setIcon(pic2);
				b[2].setIcon(pic3);
				b[3].setIcon(pic4);
				b[4].setIcon(pic5);
				b[5].setIcon(pic6);
				b[6].setIcon(pic7);
				b[7].setIcon(pic8);
				b[8].setIcon(pic0);
				step = 0;
				stepCount.setText(String.valueOf(step));
			}
		});
		switchItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("执行");
				myCardLayout.next(cardSwitch);
				System.out.println("执行完毕");
			}
		});
		recordItemm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help.setVisible(true);
			}
		});
		aboutUsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutUs.setVisible(true);
			}
		});
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b[0].setIcon(pic1);
				b[1].setIcon(pic2);
				b[2].setIcon(pic3);
				b[3].setIcon(pic4);
				b[4].setIcon(pic5);
				b[5].setIcon(pic6);
				b[6].setIcon(pic7);
				b[7].setIcon(pic8);
				b[8].setIcon(pic0);
				step = 0;
				stepCount.setText(String.valueOf(step));
			}
		});

		frame.setVisible(true);
		/*
		可扩展关闭处理，关闭窗口事件
		**/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		/*对话框
		**/
		help = new JDialog(frame, "怎么玩？", true);
		help.setSize(300, 170);
		help.setLocationRelativeTo(frame);
		help.setLayout(new FlowLayout());
		JLabel instru = new JLabel("点击图片实现移动直到完成逆序（87654321）");
		ImageIcon show = new ImageIcon("start.JPG");
		Image showImage = show.getImage().getScaledInstance(240, 100, Image.SCALE_FAST);
		JLabel start = new JLabel(new ImageIcon(showImage));
		help.add(instru);
		help.add(start);
		help.setVisible(false);



		aboutUs = new JDialog(frame, "关于我们", true);
		aboutUs.setSize(300, 150);
		aboutUs.setLocationRelativeTo(frame);
		aboutUs.setLayout(new FlowLayout());
		JLabel logo = new JLabel(new ImageIcon(smallImage));
		JLabel copyright = new JLabel("copyright@software1502");
		JLabel author = new JLabel("曹洪 毛润菲 冯红阳 丁晨晨");
		aboutUs.add(logo);
		aboutUs.add(copyright);
		aboutUs.add(author);
		aboutUs.setVisible(false);


	}
//事件处理类

	class move implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			step++;
			stepCount.setText(String.valueOf(step));
			stepCountD.setText(String.valueOf(step));
			if (e.getSource() == b[0]) {
				if (b[1].getIcon() == pic0) {
					b[1].setIcon(b[0].getIcon());
					b[0].setIcon(pic0);
				} else if (b[3].getIcon() == pic0) {
					b[3].setIcon(b[0].getIcon());
					b[0].setIcon(pic0);
				}
			}
			if (e.getSource() == b[1]) {
				if (b[0].getIcon() == pic0) {
					b[0].setIcon(b[1].getIcon());
					b[1].setIcon(pic0);
				} else if (b[2].getIcon() == pic0) {
					b[2].setIcon(b[1].getIcon());
					b[1].setIcon(pic0);
				} else if (b[4].getIcon() == pic0) {
					b[4].setIcon(b[1].getIcon());
					b[1].setIcon(pic0);
				}
			}
			if (e.getSource() == b[2]) {
				if (b[1].getIcon() == pic0) {
					b[1].setIcon(b[2].getIcon());
					b[2].setIcon(pic0);
				} else if (b[5].getIcon() == pic0) {
					b[5].setIcon(b[2].getIcon());
					b[2].setIcon(pic0);
				}
			}
			if (e.getSource() == b[3]) {
				if (b[0].getIcon() == pic0) {
					b[0].setIcon(b[3].getIcon());
					b[3].setIcon(pic0);
				} else if (b[4].getIcon() == pic0) {
					b[4].setIcon(b[3].getIcon());
					b[3].setIcon(pic0);
				} else if (b[6].getIcon() == pic0) {
					b[6].setIcon(b[3].getIcon());
					b[3].setIcon(pic0);
				}
			}
			if (e.getSource() == b[4]) {
				if (b[1].getIcon() == pic0) {
					b[1].setIcon(b[4].getIcon());
					b[4].setIcon(pic0);
				} else if (b[5].getIcon() == pic0) {
					b[5].setIcon(b[4].getIcon());
					b[4].setIcon(pic0);
				} else if (b[3].getIcon() == pic0) {
					b[3].setIcon(b[4].getIcon());
					b[4].setIcon(pic0);
				} else if (b[7].getIcon() == pic0) {
					b[7].setIcon(b[4].getIcon());
					b[4].setIcon(pic0);
				}
			}
			if (e.getSource() == b[5]) {
				if (b[2].getIcon() == pic0) {
					b[2].setIcon(b[5].getIcon());
					b[5].setIcon(pic0);
				} else if (b[4].getIcon() == pic0) {
					b[4].setIcon(b[5].getIcon());
					b[5].setIcon(pic0);
				} else if (b[8].getIcon() == pic0) {
					b[8].setIcon(b[5].getIcon());
					b[5].setIcon(pic0);
				}
			}
			if (e.getSource() == b[6]) {
				if (b[3].getIcon() == pic0) {
					b[3].setIcon(b[6].getIcon());
					b[6].setIcon(pic0);
				} else if (b[7].getIcon() == pic0) {
					b[7].setIcon(b[6].getIcon());
					b[6].setIcon(pic0);
				}
			}
			if (e.getSource() == b[7]) {
				if (b[6].getIcon() == pic0) {
					b[6].setIcon(b[7].getIcon());
					b[7].setIcon(pic0);
				} else if (b[4].getIcon() == pic0) {
					b[4].setIcon(b[7].getIcon());
					b[7].setIcon(pic0);
				} else if (b[8].getIcon() == pic0) {
					b[8].setIcon(b[7].getIcon());
					b[7].setIcon(pic0);
				}
			}
			if (e.getSource() == b[8]) {
				if (b[7].getIcon() == pic0) {
					b[7].setIcon(b[8].getIcon());
					b[8].setIcon(pic0);
				} else if (b[5].getIcon() == pic0) {
					b[5].setIcon(b[8].getIcon());
					b[8].setIcon(pic0);
				}
			}
			int[] a = new int[8];
			int count = 0;
			for (int i = 0; i < 9; i++) {
				if (b[i].getIcon() == pic0) {
					continue;
				}
				if (b[i].getIcon() == pic8) {
					a[count] = 8;
					count++;
				}
				if (b[i].getIcon() == pic7) {
					a[count] = 7;
					count++;
				}
				if (b[i].getIcon() == pic6) {
					a[count] = 6;
					count++;
				}
				if (b[i].getIcon() == pic5) {
					a[count] = 5;
					count++;
				}
				if (b[i].getIcon() == pic4) {
					a[count] = 4;
					count++;
				}
				if (b[i].getIcon() == pic3) {
					a[count] = 3;
					count++;
				}
				if (b[i].getIcon() == pic2) {
					a[count] = 2;
					count++;
				}
				if (b[i].getIcon() == pic1) {
					a[count] = 1;
					count++;
				}
			}
			int flag = 0;
			for (int i = 7; i >= 0; i--) {
				System.out.println(i + " " + a[i]);
				if (a[i] != 7 - i + 1) {
					flag = 1;
				}
			}
			System.out.println("\n");
			if (flag == 0) {
				System.out.println("success");
				threadTime.stop();
				JComponent gp = (JComponent)frame.getGlassPane();
				JButton imag = new JButton(new ImageIcon("firework.gif"));
				JLabel tex = new JLabel("Congradulations!");
				JLabel tex2 = new JLabel("click picture to return");
				imag.setOpaque(false);
				imag.setContentAreaFilled(false);
				imag.setMargin(new Insets(0, 0, 0, 0));
				imag.setFocusPainted(false);
				imag.setBorderPainted(false);
				imag.setBorder(null);
				imag.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gp.setVisible(false);
						gp.setOpaque(true);
						frame.repaint();
					}
				});
				gp.add(imag);
				gp.add(tex);
				gp.add(tex2);
				gp.setVisible(true);
				gp.setOpaque(true);
				frame.repaint();
			}
		}
	}
	class timer implements Runnable {
		public void run() {
			while (true) {
				Calendar currentTime = Calendar.getInstance();
				int cm = currentTime.get(Calendar.MINUTE);
				int cs = currentTime.get(Calendar.SECOND);
				int bm = beginTime.get(Calendar.MINUTE);
				int bs = beginTime.get(Calendar.SECOND);
				if (cs - bs < 0) {
					timeCount.setText(String.valueOf(cm - bm - 1) + ":" + String.valueOf(cs + 60 - bs));
				} else {
					timeCount.setText(String.valueOf(cm - bm) + ":" + String.valueOf(cs - bs));
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/*
	程序运行入口GUI
	**/
	public static void main(String[] args) {
		NineNumsGUI NNG = new NineNumsGUI();
	}
}