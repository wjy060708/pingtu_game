package cn.itcat.picture.ui;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * 拼图区域
 */
public class PictureCanves extends JPanel implements MouseListener {
	// 成员变量
	private Cell[] cells;
	private boolean hasAddActilistener = false;// 表明当前方格是否添加了监听
	// 静态变量
	public static int pictureID = 1;// 图片的id
	public static int stepNum = 0;// 图片的移动步数
	private Rectangle nullCell;

	// 构造方法
	public PictureCanves() {
		// 设置拼图区的布局
		this.setLayout(null);// 帧布局
		// 创建十二个小方格，拼接到拼图区
		cells = new Cell[12];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++) {
				// 加载图片
				// ImageIcon icon=new ImageIcon("Pictute\\1_01.gif");
				// int num=i*3+j;
				ImageIcon icon = new ImageIcon("Pictute\\" + pictureID + "_"
						+ (i * 3 + j + 1) + ".gif");
				// 创建图片小方格
				cells[i * 3 + j] = new Cell(icon);
				// 指定图片小方格的位置
				cells[i * 3 + j].setLocation(j * 150 + 15, i * 150 + 15);
				// 把当前的小方格添加到拼图区；
				this.add(cells[i * 3 + j]);
			}
		// 指定一个空的小方格
		this.remove(cells[11]);
		nullCell = new Rectangle(315, 465, 150, 150);

	}

	// 重新加载图片，并添加数字提示
	public void ReloadPictureAddNum() {
		// 获取每一个小方格
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++) {
				// 获取小方格

				// 设置小方格对应的图片
				ImageIcon icon = new ImageIcon("Pictute\\" + pictureID + "_"
						+ (i * 3 + j + 1) + ".gif");
				cells[i * 3 + j].setIcon(icon);
				// 设置显示的数字提示
				cells[i * 3 + j].setText("第几" + (i * 3 + j + 1) + "张图片");
				cells[i * 3 + j].setVerticalTextPosition(this.getY() / 2);
				cells[i * 3 + j].setHorizontalTextPosition(this.getX() / 2);

			}

	}

	// 重新加载图片，并去除狮子提示
	public void ReloadClearNum() {
		// 获取每一个小方格
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++) {
				// 获取小方格

				// 设置小方格对应的图片
				ImageIcon icon = new ImageIcon("Pictute\\" + pictureID + "_"
						+ (i * 3 + j + 1) + ".gif");
				cells[i * 3 + j].setIcon(icon);
				// 设置显示的数字提示
				cells[i * 3 + j].setText("");

			}
	}

	public void start() {
		// 如果对小方格没有监听，对小方格添加监听
		if (!hasAddActilistener) {
			// 添加监听
			for (int i = 0; i < 11; i++) {
				cells[i].addMouseListener(this);
			}
			// 鼠标监听的状态
			hasAddActilistener = true;

		}

		// 如果当前第一个小方格距离左上角较进时，此时该方格与空方格互换
		// 如果第一个小方格在左上角四个方格内，就不断的进行互换
		while (cells[0].getBounds().x <= 165 && cells[0].getBounds().y <= 165) {
			// 获取空方格的位置
			int nullx = nullCell.getBounds().x;
			int nully = nullCell.getBounds().y;
			// 随机产生一个方向，进行空方格与小方格的互换
			// 产生0-3的随机数对应方格的移动
			int direct = (int) (Math.random() * 4);// 0-3的随机数
			switch (direct) {
			case 0://空方格向左移动，左侧方格向右移动
                nullx-=150;
                cellMove(nullx,nully,"Right");
				break;
			case 1:////空方格向右移动，左侧方格向左移动
                nullx+=150;
                cellMove(nullx,nully,"Left");
				break;
			case 2:////空方格向上移动，左侧方格向下移动
                nully-=150;
                cellMove(nullx,nully,"Down");
				break;
			case 3://空方格向下移动，左侧方格向上移动
				 nully+=150;
	                cellMove(nullx,nully,"Up");
				break;

			}

		}

	}
    //方格与空方格的互换
	private void cellMove(int nullx, int nully, String direct) {
		// TODO Auto-generated method stub
		for(int i=0;i<11;i++)
		{
			if(cells[i].getBounds().x==nullx&&cells[i].getBounds().y==nully)
			{
				//当前空方格的移动
				cells[i].move(direct);
				//空方格移动
				nullCell.setLocation(nullx,nully);
				//完成交换后跳出循环
				break;
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//获取所点击的小方格
		Cell buttonCell =(Cell)e.getSource();
		
		//获取点击方格的的想，x,y值
		int x=buttonCell.getBounds().x;
		int y=buttonCell.getBounds().y;
		//判断空方格是否满足交换的条件，空方格的想，x,y;
		int nullx=nullCell.getBounds().x;
		int nully=nullCell.getBounds().y;
		if(x==nullx&&y-nully==150)//点击的为空方格下面的方格
		{
		buttonCell.move("Up");
		}else if(x==nullx&&y-nully==-150)//点击的为空方格上面的方格
		{
			buttonCell.move("Down");
		}else if(y==nully&&x-nullx==150)//点击的为空方格左面的方格
		{
			buttonCell.move("Left");
		}else if(y==nully&&x-nullx==-150)//点击的为空方格右面的方格
		{
			buttonCell.move("Right");	
		}else {
			return;
		}
		//更新空方格的位置
		nullCell.setLocation(x,y);
		//拼图区界面重新绘制
		this.repaint();
		//重新更新游戏状态区，更新步数
		stepNum++;
		PictureMainFrame.step.setText("步数："+stepNum);
		//判断游戏是否完成
		if(isFish())
		{
			//弹出窗口提示
			JOptionPane.showMessageDialog(this,"恭喜你完成我们的拼图！\n"+stepNum);
			//撤销方格监听撤销
			for(int i=0;i<11;i++)
			{
				cells[i].removeMouseListener(this);
			}
			//更新方格的状态
			hasAddActilistener=false;
			
		}
		
	}
	//根据坐标判断是否拼图成功
	private boolean  isFish()
	{
		for(int i=0;i<11;i++)
		{
			//获取每个方格的位置
			int x=cells[i].getBounds().x;
			int y=cells[i].getBounds().y;
			if((y-15)/150*3+(x-15)/150!=i)
			{
				return false;
			}
			
		}
		
		return true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
