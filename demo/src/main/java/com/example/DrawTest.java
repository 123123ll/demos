package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DrawTest extends JFrame {
    public DrawTest() {
        super();
        initialize();// 调用初始化方法
    }

    private void initialize() {// 初始化方法
        this.setSize(500, 300);// 设置窗体大小
        setDefaultCloseOperation(EXIT_ON_CLOSE);/// 设置窗体关闭方式
        this.setTitle("绘制几何图形");// 设置窗体标题
        MyCanvas c = new MyCanvas();// 创建画布对象
        add(c);// 将画布添加到窗体中
    }

    public static void main(String[] args) {
        new DrawTest().setVisible(true);// 设置窗体可见


    }

    private class MyCanvas extends Canvas {// 创建内部类MyCanvas继承Canvas
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 调用新画图类Graphics2D（强制转化为Graphics2D这个类）

            g2.drawOval(5, 5, 100, 100);// 画一个圆形-坐标、宽高（Draw方法绘制的图形是空心的）
            g2.fillOval(110, 5, 100, 100);// 画一个圆形（fill方法绘制的图形是实心的）
            g2.drawRect(220, 5, 90, 90);// 画一个矩形
            g2.fillRect(320, 5, 90, 90);// 画一个矩形（带填充）

            Shape shape1 = new Ellipse2D.Double(5, 110, 100, 100);
            g2.draw(shape1);// 画矩形

            g2.drawArc(110, 110, 100, 100, 220, 200);// 画弧形（扇形）
            g2.fillArc(220, 110, 100, 100, 220, 200);// 画弧形（扇形）

            int x[] = { 350, 400, 350, 400 };
            int y[] = { 130, 130, 200, 200 };
            g2.drawPolygon(x, y, 4);// 多边形（依次将四个点连接起来）

            g2.fillOval(120, 200, 200, 100);

            g2.draw3DRect(100,200,100,400,true);
            g2.fill3DRect(100,200,300,100,true);

        }

    }

}