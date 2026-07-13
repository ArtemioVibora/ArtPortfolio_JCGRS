package com.iacademy.cselec05.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.iacademy.cselec05.model.User;

public class SessionUtil {

    public static void setSessionAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
    }

    public static Object getSessionAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        return (session != null) ? session.getAttribute(key) : null;
    }

    public static void removeSessionAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return getUser(request) != null;
    }

    public static User getUser(HttpServletRequest request) {
        return (User) getSessionAttribute(request, "user");
    }
}
