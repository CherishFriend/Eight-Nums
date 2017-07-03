import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class NineNumsGUI{
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
	/*
	游戏区
	**/
	private JButton[] b = new JButton[10];
	/*
	侧边栏
	**/
	private JPanel infoPane;
	private JPanel buttonPane;
	private JPanel resetPane;
	private JPanel sizePane;
	private JButton size;
	private JButton reset;
	private JLabel time;
	private JLabel steps;


	/*
	GUI
	**/
	public NineNumsGUI(){	
		frame = new JFrame();
		frame.setTitle("九宫格游戏");
		frame.setSize(400,350);
		frame.setLayout(new BorderLayout(5,5));

		/*
		设置图片
		**/
		ImageIcon titlePic = new ImageIcon("1.jpg");
		Image smallImage = titlePic.getImage().getScaledInstance(60,40,Image.SCALE_FAST);
		/*
		菜单栏
		**/
		menubar = new JMenuBar();
		gameMenu = new JMenu("游戏(G)");
		helpMenu = new JMenu("帮助(H)");

		menubar.add(gameMenu);
		menubar.add(helpMenu);

		sideBar = new JPanel(new BorderLayout(10,10));
		mainBar = new JPanel(new GridLayout(3,3,5,10));
		counterBar = new JPanel();
		/*
		游戏区
		**/
		b[0] = new JButton();
		b[1] = new JButton();
		b[2] = new JButton();
		b[3] = new JButton();
		b[4] = new JButton();
		b[5] = new JButton();
		b[6] = new JButton();
		b[7] = new JButton();
		b[8] = new JButton();
		for(int i = 0;i<9;i++){
			mainBar.add(b[i]);
		}
		Border border = BorderFactory.createEtchedBorder();
		Border titledBorder = BorderFactory.createTitledBorder(border);

		mainBar.setBorder(border);
		//侧边栏
		infoPane = new JPanel(new GridLayout(2,1));
		buttonPane = new  JPanel(new GridLayout(3,1));
		resetPane = new JPanel(new FlowLayout());
		sizePane = new JPanel(new FlowLayout());
		time = new JLabel("时间");
		steps = new JLabel("步数");
		size = new JButton("3X3");
		reset = new JButton("重置");
		
		resetPane.add(reset);
		sizePane.add(size);
		infoPane.add(steps);
		infoPane.add(time);
		infoPane.setBorder(border);

		buttonPane.add(size);
		buttonPane.add(new JLabel(" "));
		buttonPane.add(reset);
		sideBar.add(BorderLayout.CENTER,infoPane);
		sideBar.add(BorderLayout.SOUTH,buttonPane);


		/*
		布局
		**/
		side = new JLabel("       ");
		bottom = new JLabel(" ");

		frame.setIconImage(smallImage);
		frame.add(BorderLayout.NORTH,menubar);
		frame.add(BorderLayout.CENTER,mainBar);
		frame.add(BorderLayout.EAST,sideBar);
		frame.add(BorderLayout.WEST,side);
		frame.add(BorderLayout.SOUTH,bottom);


		frame.setVisible(true);
		/*
		可扩展关闭处理，关闭窗口事件
		**/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*
	程序运行入口GUI
	**/
	public static void main(String[] args){
		NineNumsGUI NNG = new NineNumsGUI();
	}
}
