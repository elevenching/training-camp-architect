package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class Label extends Component {
    public Label(String text) {
        super(text);
    }

    @Override
    public void print() {
        System.out.println(String.format("print Label(%s)", text));
    }
}
