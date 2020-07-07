package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class Frame extends AbstractTwigComponent {
    public Frame(String text) {
        super(text);
    }

    @Override
    public void print() {
        System.out.println(String.format("print Frame(%s)", text));
        super.print();
    }
}
