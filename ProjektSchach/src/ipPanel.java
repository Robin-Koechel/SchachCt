
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robin
 */
public class ipPanel extends JFrame{
    private JPanel panel;
    
    private JLabel lblInfo;
    private JTextField txfIp;
    private JButton btnFertig;
    private String ip;
    
    public ipPanel(){
        panel = new JPanel();
        init();
    }
    
    private void init(){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        this.add(panel);
        
        
        lblInfo = new JLabel("Bitte Ip eintragen");
        txfIp = new JTextField();
        btnFertig = new JButton("fertig!");
        btnFertig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ip = txfIp.getText();     
            }
        });
         
        panel.add(lblInfo);
        panel.add(txfIp);
        panel.add(btnFertig);
        
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();
        
    }
    public String getIp(){
        return ip;
    }
}
