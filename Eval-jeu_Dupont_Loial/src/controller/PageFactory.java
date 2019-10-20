package controller;

import javax.servlet.http.HttpServletRequest;

public class PageFactory {
    private static final String PAGE_LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    private static final String PAGE_LOGIN = "/login";
    private static final String PAGE_HOME_JSP = "/WEB-INF/jsp/home.jsp";
    private static final String PAGE_HOME = "/home";
    private static final String PAGE_QUEST_JSP = "/WEB-INF/jsp/question.jsp";
    private static final String PAGE_QUEST = "/question";

    public static String getLoginJsp() {
        return PAGE_LOGIN_JSP;
    }
    public static String getHomeJsp() {
        return  PAGE_HOME_JSP;
    }
    public static String getQuestJsp() {
        return PAGE_QUEST_JSP;
    }

    public static String getLoginPath(HttpServletRequest req) {
        return req.getContextPath() + PAGE_LOGIN;
    }
    public static String getHomePath(HttpServletRequest req) {
        return req.getContextPath() + PAGE_HOME;
    }
    public static String getQuestPath(HttpServletRequest req) {
        return req.getContextPath() + PAGE_QUEST;
    }
}
