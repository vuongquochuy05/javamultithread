package Java;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimeZone;

public class Main extends JFrame {
	public Main() {
		setTitle("Simple Clock");
		setSize(250, 150);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setLayout(new BorderLayout());
		JPanel jp = new JPanel(new FlowLayout());
		JButton bt = new JButton("Open");
		Clock clock1 = new Clock();
		Clock clock2 = new Clock();
		JTextField jt = new JTextField(10);
		jp.add(jt);
		jp.add(bt);
		this.add(clock1, BorderLayout.NORTH);
		this.add(jp, BorderLayout.SOUTH);
		clock1.Start();
		clock2.Start();
		Thread t1 = new Thread(clock1);
		Thread t2 = new Thread(clock2);
		try {
			t1.start();
			t1.join();
			t2.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String time = jt.getText();
				TimeZone timezone = TimeZone.getTimeZone(time);
				clock2.timeLabel.setText(timezone.getDisplayName());
				TimeZone.setDefault(TimeZone.getTimeZone(time));
				Main1();
			}
		});

	}

	
	private void Main1() {
		Main m = new Main();
		m.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Main();
		});
	}
}