package hi;

import java.util.HashMap;
import java.util.Iterator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame{
	
	private JTextField inTxt;
	private JButton btn;
	private JLabel lb1,lb2,cntlb;
	private JLabel[] word = new JLabel[10];
	private JTextArea textArea;
	HashMap<String, Integer> hm = new HashMap<String, Integer>();

	

	 
	public Main() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(650, 650));
		this.setTitle("word");
		componetAdd();
	}

	public void showUI() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		this.pack();
		this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}
	
	private void componetAdd() {

		
		JPanel leftPane = new JPanel();
		leftPane.setLayout(new BorderLayout());
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		leftPane.add(scrollPane, BorderLayout.CENTER);
		btn = new JButton("click");
		btn.addActionListener((ActionListener) new ClickAction());
		leftPane.add(btn,BorderLayout.PAGE_END);
		
		JPanel rightPane = new JPanel();
		rightPane.setSize(300, 0);
		rightPane.setLayout(new BorderLayout());
		JPanel rtPan = new JPanel();
		JPanel rcPan = new JPanel();
		lb1 = new JLabel("단어 수:");
		cntlb = new JLabel("0");
		rtPan.add(lb1);
		rtPan.add(cntlb);
		lb2 = new JLabel("많이 나오는 단어 :");
		for(int i = 0; i <10; i++)
		{
			word[i] = new JLabel("");
			
		}
		
		rcPan.add(lb2);
		for(int i = 0; i < 10 ; i++)
		{
			rcPan.add(word[i]);
		}

		rightPane.add(rtPan,BorderLayout.PAGE_START);
		rightPane.add(rcPan,BorderLayout.CENTER);
		
		
		

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(leftPane, BorderLayout.CENTER);
		this.getContentPane().add(rightPane, BorderLayout.EAST);

	}

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main m = new Main();
				m.showUI();
			}
		});

	}
	
	
	private class ClickAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			hm.clear();
			// TODO Auto-generated method stub
			if (e.getSource() == btn) {
				String txt = textArea.getText();
			
				boolean isStrEnd = false;
				String tmpstr = "";
				for (int i = 0; i < txt.length(); i++) {
					char c = txt.charAt(i);
					if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c=='\'')  {
						tmpstr += c;
						isStrEnd = true;
					} else {
						if (isStrEnd) {
							if(hm.containsKey(tmpstr)) 
								hm.put(tmpstr,hm.get(tmpstr)+1);
							else 
								hm.put(tmpstr, 1);

							tmpstr = "";
							isStrEnd = false;
						}
					}

				}
				
				if(tmpstr.length() != 0)
				{
					if(hm.containsKey(tmpstr)) 
						hm.put(tmpstr,hm.get(tmpstr)+1);
					else 
						hm.put(tmpstr, 1);
				}
				
				
				cntlb.setText(String.valueOf(hm.size()));
				
				for(int i = 0; i <10 && hm.size() > 1; i++)
				{
				    Iterator<String> keys = hm.keySet().iterator();
				    String maxkey = "";
				    maxkey = keys.next();
			        while( keys.hasNext() ){
			            String key = keys.next();
			            if(hm.get(key) > hm.get(maxkey))
			            	maxkey = key;
			            word[i].setText(maxkey);
			        }
			        
			        hm.remove(maxkey);
				}
				
				
				
			} 
					
		}

	}
	
	
	
	
	

}
