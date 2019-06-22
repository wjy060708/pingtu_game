package cn.itcat.picture.ui;

import javax.swing.Icon;
import javax.swing.JButton;

/*
 * 图片小方格
 */
public class Cell extends JButton {
	// 带有图片的小方格
	public Cell(Icon icon) {
		super(icon);
		// 设置小方格大小
		this.setSize(150, 150);

	}

	// 带有图片并带有文字
	public Cell(String text, Icon icon) {
		super(text, icon);
		this.setSize(150, 150);
		this.setHorizontalTextPosition(CENTER);// 文字水平居中显示
		this.setVerticalTextPosition(CENTER);// 文字垂直居中显示

	}

	public void move(String direct) {
		// TODO Auto-generated method stub
		switch (direct) {
		case "Right":
			 this.setLocation(this.getBounds().x+150,this.getBounds().y);
			break;
		case "Left":
			 this.setLocation(this.getBounds().x-150,this.getBounds().y);
			break;
		case "Down":
			 this.setLocation(this.getBounds().x,this.getBounds().y+150);
			break;
		case "Up":
			 this.setLocation(this.getBounds().x,this.getBounds().y-150);
			break;
	
		}
	}

}
