package com.backend.petshelter.util.format;

import jakarta.servlet.http.HttpServletRequest;

public class SiteURL {
    public static String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURI().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}