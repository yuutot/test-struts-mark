package com.mark.actions.execute;
import com.mark.dao.EmployerDAO;
import com.mark.entity.Employer;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
import java.util.Optional;

@Data
public class AuthExecuteAction extends ActionSupport implements SessionAware{

    private Map<String, Object> session;
    private String errorMessage;

    private String login;
    private String password;

    public AuthExecuteAction() {
    }

    @Override
    public void validate() {
        if(!isValidString(login)){
            addFieldError("login", "Field is empty");
        }
        if (!isValidString(password)){
            addFieldError("password", "Field is empty");
        }
    }

    @Override
    public String execute() throws Exception {
        Optional<Employer> employer = EmployerDAO.getInstance().findEmployerByLogin(login);
        if(!employer.isPresent() || !employer.get().getPassword().equals(DigestUtils.md5Hex(password))){
            errorMessage = "Login/pass";
            return INPUT;
        }
        session.put("user", employer.get());
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    private boolean isValidString(String str){
        return str != null && !str.isEmpty();
    }
}
