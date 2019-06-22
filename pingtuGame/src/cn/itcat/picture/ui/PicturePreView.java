package cn.itcat.picture.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * 图片预览
 */
public class PicturePreView extends JPanel{
	//重写绘制附件
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//指定当前图片的路径
		 //String filename="Pictute\\1.jpg";
		String filename="Pictute\\"+PictureCanves.pictureID+".jpg";
		 //获取对应图片的图像，
		 ImageIcon icon=new ImageIcon(filename);
		 //获取图片的图像
		Image image= icon.getImage();
		//把图像显示在图片预览区
		g.drawImage(image,15,15,450,600,this);
	}
	 

}
