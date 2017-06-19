package utils;

import java.io.Serializable;

public class ScenarioContext implements Serializable{

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
