package com.mark.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.dispatcher.HttpParameters;

import java.util.Map;

/**
 * Created by yuuto on 12/2/17.
 */
public class AuthInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        HttpParameters params = ActionContext.getContext().getParameters();
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session.containsKey("user")) {
            if (params.get("logout").isDefined()) {
                session.remove("user");
                return "authError";
            }
            return actionInvocation.invoke();
        }
        return "authError";
    }
}
