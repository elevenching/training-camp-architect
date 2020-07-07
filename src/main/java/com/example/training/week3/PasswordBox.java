package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class PasswordBox extends Component {
    public PasswordBox(String text) {
        super(text);
    }

    @Override
    public void print() {
        System.out.println(String.format("print PasswordBox(%s)", text));
    }
}
