package com.mark.actions;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

@Data
public class AuthAction extends ActionSupport implements SessionAware{

    private Map<String, Object> session;
    public AuthAction() {
    }

    public String display() {
        if(session.containsKey("user")){
            return SUCCESS;
        }
        return INPUT;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
