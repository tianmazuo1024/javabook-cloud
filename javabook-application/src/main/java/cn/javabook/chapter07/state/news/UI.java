package cn.javabook.chapter07.state.news;

import javax.swing.*;
import java.awt.*;

/**
 * 测试状态模式的UI界面
 *
 */
public class UI {
    private final Context context;
    private static final JTextField textField = new JTextField();

    public UI(Context context) {
        this.context = context;
    }

    public void init() {
        JFrame frame = new JFrame("测试状态模式");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(jPanel);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textField.setHorizontalAlignment(JTextField.CENTER);
        jPanel.add(textField);
        jPanel.add(buttons);

        JButton ordered = new JButton("下单");
        ordered.addActionListener(e -> textField.setText(context.getState().onOrdered()));
        JButton cancel = new JButton("取消订单");
        cancel.addActionListener(e -> textField.setText(context.getState().onCancel()));
        JButton paid = new JButton("订单付款");
        paid.addActionListener(e -> textField.setText(context.getState().onPaid()));
        JButton confirm = new JButton("确认收货");
        confirm.addActionListener(e -> textField.setText(context.getState().onConfirm()));
        frame.setVisible(true);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        buttons.add(ordered);
        buttons.add(cancel);
        buttons.add(paid);
        buttons.add(confirm);
    }

    public static void main(String[] args) {
        Context context = new Context();
        UI ui = new UI(context);
        ui.init();
    }
}
