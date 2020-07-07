package com.example.training.week3;

/**
 * @author chenjun
 * @date 2020-07-01
 */
public class LinkLabel extends Component {
    public LinkLabel(String text) {
        super(text);
    }

    @Override
    public void print() {
        System.out.println(String.format("print LinkLabel(%s)", text));
    }
}
