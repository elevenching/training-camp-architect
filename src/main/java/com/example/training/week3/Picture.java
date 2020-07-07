package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class Picture extends Component {
    private String text;

    public Picture(String text) {
        super(text);
    }

    @Override
    public void print() {
        System.out.println(String.format("print Picture(%s)", text));
    }
}
