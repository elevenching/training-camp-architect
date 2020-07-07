package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class MainApplication {
    public static void main(String[] args) {
        WinForm winForm = new WinForm("WINDOW 窗口");
        winForm.add(new Picture("LOGO 图片"));
        winForm.add(new Button("登陆"));
        winForm.add(new Button("注册"));
        Frame frame1 = new Frame("FRAME1");
        frame1.add(new Label("用户名"));
        frame1.add(new TextBox("文本框"));
        frame1.add(new Label("密码"));
        frame1.add(new PasswordBox("密码框"));
        frame1.add(new CheckBox("复选框"));
        frame1.add(new TextBox("记住用户名"));
        frame1.add(new Label("忘记密码"));
        winForm.add(frame1);
        winForm.print();
    }
}
