package com.example.training.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjun
 * @date 2020/7/1
 * @since V1.0
 */
public abstract class AbstractTwigComponent extends Component {
    protected List<Component> leafComponents = new ArrayList<>();

    public AbstractTwigComponent(String text) {
        super(text);
    }

    boolean add(Component component) {
        leafComponents.add(component);
        return true;
    }

    @Override
    public void print() {
        for (Component leafComponent : leafComponents) {
            leafComponent.print();
        }
    }
}
