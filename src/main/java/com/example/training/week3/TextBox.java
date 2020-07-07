package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class TextBox extends Component {
    private String text;

    public TextBox(String text) {
        super(text);
    }

    @Override
    public void print() {
        System.out.println(String.format("print TextBox(%s)", text));
    }
}
