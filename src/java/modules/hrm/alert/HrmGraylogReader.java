/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.alert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import modules.core.GraylogReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmGraylogReader extends GraylogReader {

    public static String getWorkflowWaS() {
        return "WaS";
    }

    public static String getWorkflowInteressent() {
        return "Interessent";
    }

    private void setQueryHrmLevel3WaS() {
        this.query = "project:SugarHRM AND (application_name:\"Workflow of New WaS (Error handing)\" OR application_name:\"Workflow of New WaS (Error handling)\"  OR application_name:\"Workflow of New WaS\") AND level:3";
    }

    private void setQueryHrmLevel3Interessent() {
        this.query = "project:SugarHRM AND (application_name:\"Workflow of New WaS (Error handing)\" OR application_name:\"Workflow of New WaS (Error handling)\"  OR application_name:\"Workflow of New Interessent\") AND level:3";
    }

    private void setQueryHrmAllWaS() {
        this.query = "project:SugarHRM AND (application_name:\"Workflow of New WaS (Error handing)\" OR application_name:\"Workflow of New WaS (Error handling)\"  OR application_name:\"Workflow of New WaS\") AND level:6";
    }

    private void setQueryHrmAllInteressent() {
        this.query = "project:SugarHRM AND (application_name:\"Workflow of New Interessent (Error handing)\" OR application_name:\"Workflow of New Interessent (Error handling)\" OR application_name:\"Workflow of New Interessent\") AND level:6";
    }
    
    private void setQueryHrmAllError() {
        this.query = "project:SugarHRM AND level:3";
    }

    private void setQueryHrmAllSuccess() {
        this.query = "project:SugarHRM AND level:6";
    }

    private void setQueryHrmWaSTestError() {
        this.query = "project:SugarHRM AND application_name:\"Workflow of New WaS\" AND level:3";
    }

    private void setQueryHrmWaSLiveError() {
        this.query = "project:SugarHRM AND application_name:\"Workflow of New WaS\" AND level:3";
    }

    private void setQueryHrmInteressentTestError() {
        this.query = "project:SugarHRM AND application_name:\"Workflow of New Interessent\" AND level:3";
    }

    private void setQueryHrmInteressentLiveError() {
        this.query = "project:SugarHRM AND application_name:\"Workflow of New Interessent\" AND level:3";
    }

    public HttpURLConnection getWaSTestToTextYesterday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmWaSTestError();
        return toTextYesterday();
    }

    public HttpURLConnection getWaSLiveToTextYesterday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmWaSLiveError();
        return toTextYesterday();
    }

    public HttpURLConnection getWaSTestToTextToday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmWaSTestError();
        return toTextToday();
    }

    public HttpURLConnection getWaSLiveToTextToday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmWaSLiveError();
        return toTextToday();
    }

    public HttpURLConnection getInteressentTestToTextYesterday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmInteressentTestError();
        return toTextYesterday();
    }

    public HttpURLConnection getInteressentLiveToTextYesterday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmInteressentLiveError();
        return toTextYesterday();
    }

    public HttpURLConnection getInteressentTestToTextToday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmInteressentTestError();
        return toTextToday();
    }

    public HttpURLConnection getInteressentLiveToTextToday() throws MalformedURLException, ProtocolException, IOException {
        setQueryHrmInteressentLiveError();
        return toTextToday();
    }

    public HttpURLConnection getWaSLiveToTextFixDate(String fixDate) throws ProtocolException, IOException {
        setQueryHrmWaSLiveError();
        return toTextFixDate(fixDate);
    }

    public HttpURLConnection getInteressentLiveFixDate(String fixDate) throws ProtocolException, IOException {
        setQueryHrmInteressentLiveError();
        return toTextFixDate(fixDate);
    }

    public HttpURLConnection getErrorHandling3WaS() throws ProtocolException, IOException {
        setQueryHrmLevel3WaS();
        return toTextErrorHandling();
    }

    public HttpURLConnection getErrorHandling3Interessent() throws ProtocolException, IOException {
        setQueryHrmLevel3Interessent();
        return toTextErrorHandling();
    }

    public HttpURLConnection getAllWaS() throws ProtocolException, IOException {
        setQueryHrmAllWaS();
        return toTextErrorHandling();
    }

    public HttpURLConnection getAllInteressent() throws ProtocolException, IOException {
        setQueryHrmAllInteressent();
        return toTextErrorHandling();
    }
    
    public HttpURLConnection getAllSuccess() throws ProtocolException, IOException {
        setQueryHrmAllSuccess();
        return toTextAllWithApplicantName();
    }
    
    public HttpURLConnection getAllError() throws ProtocolException, IOException {
        setQueryHrmAllError();
        return toTextAllWithApplicantName();
    }
}
