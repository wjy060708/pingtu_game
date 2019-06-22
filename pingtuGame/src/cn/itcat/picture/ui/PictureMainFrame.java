package cn.itcat.picture.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/*
 * 主界面
 * 
 */
public class PictureMainFrame extends JFrame {
	private String[] items = { "小女孩", "女明星" };
	private JRadioButton addNumInfo;// 数字提示
	private JRadioButton clearNummInfo;// 清除数字提示
	private PictureCanves canves;// 拼图区
	private PicturePreView pView;// 预览区
	private JComboBox<String> box;// 下拉框
	private JTextField name;// 图片名称
	public static JTextField step;// 移动步数,在其他类中调用而不创建对象 
	private JButton startButton;

	// 空参数构造方法
	public PictureMainFrame() {
		// super();
		init();// 初始化界面
		// 添加组件
		addComponent();

		addPreViewImage();// 添加预览图片和拼图图片

		addActionListener();// 为组件添加时间监听
		addActionListener1();

	}

	// 清除提示
	private void addActionListener1() {
		// TODO Auto-generated method stub
		clearNummInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canves.ReloadClearNum();

			}
		});
		// 下拉框
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// 获取选取图片的id
				int num = box.getSelectedIndex();// 默认从零开始
				// 更新预览区
				PictureCanves.pictureID = num + 1;
				// 更新图片区
				pView.repaint();// 重新绘制
				canves.ReloadClearNum();
				// 更新按钮区
				// 设置图片name
				name.setText("图片名称： " + box.getSelectedItem());
				// 设置步数
				int stepNum = PictureCanves.stepNum = 0;
				step.setText("步数：" + stepNum);
				// 把按钮设置清除状态
				clearNummInfo.setSelected(true);
			}
		});
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 步数清零
				PictureCanves.stepNum = 0;
				// 更新状态区的步数gengxing
				step.setText("步数： " + PictureCanves.stepNum);
				// 对小方格打乱顺序
				canves.start();

			}
		});
	}

	private void addActionListener() {
		// 为当前的数字提示
		addNumInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				canves.ReloadPictureAddNum();

			}
		});

	}

	private void addPreViewImage() {
		// 创建一个面板，包含拼图区和预览区
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));// 面板设置为表格布局
		canves = new PictureCanves();
		canves.setBorder(new TitledBorder("拼图区"));
		pView = new PicturePreView();
		pView.setBorder(new TitledBorder("预览区"));
		// 添加拼图区和预览区到中间的面板中
		panel.add(canves, BorderLayout.EAST);
		panel.add(pView, BorderLayout.WEST);
		// 吧面板显示在主界面中
		this.add(panel, BorderLayout.CENTER);// 居中显示

	}

	/**
	 * 添加组件方法
	 */
	private void addComponent() {
		// 创建一个面板，在当前的面板包括按钮区和状态区
		JPanel panel = new JPanel();
		// panel.setBackground(Color.PINK);// 是指当前面板的背景色为粉色
		panel.setLayout(new GridLayout(1, 2));// 设置面板为一行两列的单元格布局

		// 创建一个按钮区面板
		JPanel leftPanel = new JPanel();
		panel.add(leftPanel, BorderLayout.WEST);// 左边
		leftPanel.setBorder(new TitledBorder("按钮区"));// 设置边框
		leftPanel.setBackground(Color.PINK);// 设置背景色

		addNumInfo = new JRadioButton("数字提示", false);
		clearNummInfo = new JRadioButton("清除提示", true);
		// 添加单选按钮组
		ButtonGroup addNumGroup = new ButtonGroup();
		box = new JComboBox<String>(items);
		startButton = new JButton("Start");
		// 添加组件到面板中
		addNumGroup.add(addNumInfo);
		addNumGroup.add(clearNummInfo);
		// 设置背景色
		addNumInfo.setBackground(Color.PINK);
		clearNummInfo.setBackground(Color.PINK);
		startButton.setBackground(Color.PINK);

		// 添加单选按钮到按钮组中
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNummInfo);
		leftPanel.add(new JLabel("      选择图片"));
		leftPanel.add(box);
		leftPanel.add(startButton);

		// 创建一个状态区面板
		JPanel rightPanel = new JPanel();
		panel.add(rightPanel, BorderLayout.EAST);// 右边
		rightPanel.setBorder(new TitledBorder("游戏状态"));// 添加右边边框
		rightPanel.setBackground(Color.PINK);
		rightPanel.setLayout(new GridLayout(1, 2));
		name = new JTextField("图片名称:小女孩");
		step = new JTextField("当前步数：0");
		// 设置文本框不能编辑
		name.setEditable(false);
		step.setEditable(false);
		rightPanel.add(name, BorderLayout.WEST);
		rightPanel.add(step, BorderLayout.EAST);
		// 设置当前的面板在窗口的上方位置显示
		this.add(panel, BorderLayout.NORTH);

	}

	/*
	 * 界面初始化界面
	 */

	private void init() {
		// TODO Auto-generated method stub
		// 设置窗体标题
		this.setTitle("拼图游戏");
		// 设置窗体大小
		this.setSize(1000, 720);
		// 设置窗体位置
		this.setLocation(150, 10);
		// 设置窗体大小为固定大小
		this.setResizable(false);
		// 设置窗体的默认关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
