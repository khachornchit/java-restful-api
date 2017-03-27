package utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WebReader extends JFrame {

    private JTextArea box = new JTextArea("Getting data ...");
    private String message;

    public String getMessage() {
        return message;
    }

    public WebReader() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        JScrollPane pane = new JScrollPane(box);
        add(pane);
        setVisible(true);
    }

    public String getData(String address) throws Exception {
        setTitle(address);
        URL page = new URL(address);
        StringBuffer text = new StringBuffer();
        HttpURLConnection conn = (HttpURLConnection) page.openConnection();
        conn.connect();
        InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
        BufferedReader buff = new BufferedReader(in);

        box.setText("Getting data ...");
        String line;
        do {
            line = buff.readLine();
            text.append(line + "\n");
        } while (line != null);
        box.setText(text.toString());

        return text.toString();
    }

    public boolean IsAliveUrl(String url) throws Exception {
        try {
            setTitle(url);
            URL page = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) page.openConnection();
            conn.connect();
            message = "Success";
            conn.disconnect();
            return true;
        } catch (Exception e) {
            message = e.getMessage();
            return false;
        }
    }
}
