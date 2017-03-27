/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author kajornjit.songsaen
 */
public class StringUtilities {

    private final Double number = 1.23456;

    public Double getNumber() {
        return number;
    }

    public void FormatNumber() {
        String value = String.format("Three numbers after decimal: %1$.3f", number);
        System.out.println(value);
    }

    public void FormatPadZero() {
        for (int i = 0; i < 5; i++) {
            // Pad with zeros and a width of 5 chars.
            String result = String.format("%1$05d %2$05d", i, i + 10);
            System.out.println(result);
        }
    }

    public void FormatMultiString() {
        String var1 = "jdbc";
        String var2 = "mysql";
        String var3 = "localhost";

        String url = String.format("%s:%s://%s/", var1, var2, var3);
        System.out.println(url);
    }
}
