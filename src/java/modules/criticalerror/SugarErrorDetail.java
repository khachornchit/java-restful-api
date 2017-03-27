/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.criticalerror;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import modules.graylog.base.BaseGraylog;
import modules.graylog.GraylogSugar;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarErrorDetail {

    private int cainotfoundcount;
    private String cainotfoundname;
    private String cainotfoundcolor;
    private String cainotfoundmessages;

    private int caierrorcount;
    private String caierrorname;
    private String caierrorcolor;
    private String caierrormessages;

    private int sugarcount;
    private String sugarname;
    private String sugarcolor;
    private String sugarmessages;

    private int totalcount;
    private String totalname;
    private String totalcolor;

    List<BaseGraylog> sugarerrors;
    List<BaseGraylog> caierrors;
    List<BaseGraylog> cainotfounds;

    public SugarErrorDetail() throws IOException {
        CaiErrorTextCsv caiText = new CaiErrorTextCsv();
        getCaiErrorFromTextCsv(caiText);

        CaiNotFoundTextCsv caiNotFoundText = new CaiNotFoundTextCsv();
        getCaiNotFoundFromTextCsv(caiNotFoundText);

        SugarErrorTextCsv sugarText = new SugarErrorTextCsv();
        getSugarErrorFromTextCsv(sugarText);

        this.totalcount = this.caierrorcount + this.cainotfoundcount + this.sugarcount;
        setVariable(this.sugarcount, this.caierrorcount, this.cainotfoundcount);
    }

    private void getConstructorJson() throws IOException {
        SugarError sugar = new SugarError();
        CaiError cai = new CaiError();
        CaiNotFound cainotfound = new CaiNotFound();

        setVariable(sugar.getCount(), cai.getCount(), cainotfound.getCount());

        if (sugar.getCount() > 0) {
            getSugarErrorsFromJson(sugar);
        }

        if (cai.getCount() > 0) {
            getCaiErrorFromJson(cai);
        }

        if (cainotfound.getCount() > 0) {
            getCaiNotFoundFromJson(cainotfound);
        }
    }

    private void setVariable(int sugarcount, int caicount, int cainotfoundcount) {
        sugarname = "Sugar Error Yesterday";
        caierrorname = "Cai Error Yesterday ";
        cainotfoundname = "Cai Not Found Yesterday";
        totalname = "Total Error";

        this.sugarcount = sugarcount;
        this.caierrorcount = caicount;
        this.cainotfoundcount = cainotfoundcount;
        totalcount = sugarcount + caicount;

        if (sugarcount > 0) {
            sugarcolor = "red";
        } else {
            sugarcolor = "green";
        }

        if (caicount > 0) {
            caierrorcolor = "red";
        } else {
            caierrorcolor = "green";
        }

        if (totalcount > 0) {
            totalcolor = "red";
        } else {
            totalcolor = "green";
        }

        if (cainotfoundcount > 0) {
            cainotfoundcolor = "yellow";
        } else {
            cainotfoundcolor = "green";
        }
    }

    private void getSugarErrorsFromJson(SugarError sugar) {
        JsonObject json = sugar.getSugarErrors();
        JsonArray jsonarray = json.getJsonArray("messages");

        try {
            int limit = 20;
            sugarerrors = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                JsonValue jsonValue = jsonarray.get(i);
                sugarerrors.add(new GraylogSugar(jsonValue.toString()));
                if (i > limit) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private void getCaiErrorFromJson(CaiError cai) {

        try {
            JsonObject json = cai.getCaiErrors();
            JsonArray jsonarray = json.getJsonArray("messages");
            int limit = 20;

            caierrors = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                JsonValue jsonValue = jsonarray.get(i);
                caierrors.add(new GraylogSugar(jsonValue.toString()));
                if (i > limit) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private void getCaiNotFoundFromJson(CaiNotFound cai) {

        try {
            JsonObject json = cai.getCaiNotFounds();
            JsonArray jsonarray = json.getJsonArray("messages");
            int limit = 20;

            cainotfounds = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                JsonValue jsonValue = jsonarray.get(i);
                cainotfounds.add(new GraylogSugar(jsonValue.toString()));
                if (i > limit) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private void getCaiErrorFromTextCsv(CaiErrorTextCsv cai) {
        try {
            HttpURLConnection request = cai.getCaiErrorTextCsv();
            caierrors = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");

                    if (!bHeader && Count < 20) {
                        caierrors.add(new GraylogSugar(thisLine, true));
                    }

                    if (!bHeader) {
                        Count++;
                    }
                }
                this.caierrorcount = Count;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private void getCaiNotFoundFromTextCsv(CaiNotFoundTextCsv cai) {
        try {
            HttpURLConnection request = cai.getCaiNotFoundTextCsv();
            cainotfounds = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && Count < 20) {
                        cainotfounds.add(new GraylogSugar(thisLine, true));
                    }
                    if (!bHeader) {
                        Count++;
                    }
                }
                this.cainotfoundcount = cainotfounds.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private void getSugarErrorFromTextCsv(SugarErrorTextCsv sugar) {
        try {
            HttpURLConnection request = sugar.getSugarErrorTextCsv();
            sugarerrors = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && Count < 20) {
                        sugarerrors.add(new GraylogSugar(thisLine, true));
                    }
                    if (!bHeader) {
                        Count++;
                    }
                }
                this.sugarcount = sugarerrors.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public int getCaicount() {
        return caierrorcount;
    }

    public void setCaicount(int caicount) {
        this.caierrorcount = caicount;
    }

    public String getCainame() {
        return caierrorname;
    }

    public void setCainame(String cainame) {
        this.caierrorname = cainame;
    }

    public String getCaicolor() {
        return caierrorcolor;
    }

    public void setCaicolor(String caicolor) {
        this.caierrorcolor = caicolor;
    }

    public String getCaimessages() {
        return caierrormessages;
    }

    public void setCaimessages(String caimessages) {
        this.caierrormessages = caimessages;
    }

    public int getSugarcount() {
        return sugarcount;
    }

    public void setSugarcount(int sugarcount) {
        this.sugarcount = sugarcount;
    }

    public String getSugarname() {
        return sugarname;
    }

    public void setSugarname(String sugarname) {
        this.sugarname = sugarname;
    }

    public String getSugarcolor() {
        return sugarcolor;
    }

    public void setSugarcolor(String sugarcolor) {
        this.sugarcolor = sugarcolor;
    }

    public String getSugarmessages() {
        return sugarmessages;
    }

    public void setSugarmessages(String sugarmessages) {
        this.sugarmessages = sugarmessages;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sugar Count=%d, Name=%s, Color=%s\n", sugarcount, sugarname, sugarcolor));
        sb.append(String.format("CAI Count=%d, Name=%s, Color=%s\n", caierrorcount, caierrorname, caierrorcolor));
        sb.append(String.format("Total Count=%d, Name=%s, Color=%s", totalcount, totalname, totalcolor));
        return sb.toString();
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public String getTotalname() {
        return totalname;
    }

    public void setTotalname(String totalname) {
        this.totalname = totalname;
    }

    public String getTotalcolor() {
        return totalcolor;
    }

    public void setTotalcolor(String totalcolor) {
        this.totalcolor = totalcolor;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
