package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class CheckBox extends Component {
    public CheckBox(String text) {
        super(text);
    }

    @Override
    public void print() {
        System.out.println(String.format("print CheckBox(%s)", text));
    }
}
