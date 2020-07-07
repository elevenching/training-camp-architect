package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020/7/1
 * @since V1.0
 */
public abstract class Component {

    protected String text;

    public Component(String text) {
        this.text = text;
    }

    /**
     * 打印文案
     */
    public abstract void print();
}
