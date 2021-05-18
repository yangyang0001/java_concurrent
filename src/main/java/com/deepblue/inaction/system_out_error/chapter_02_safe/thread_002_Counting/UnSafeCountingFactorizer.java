package com.deepblue.inaction.system_out_error.chapter_02_safe.thread_002_Counting;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.*;
import java.io.IOException;

/**
 * 非线程安全的 统计点击量
 */
@NotThreadSafe
public class UnSafeCountingFactorizer implements Servlet {

    private long count = 0;

    public long getCount() {return count;}


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        long current = extractRequest(request);
        response.getWriter().write(String.valueOf(current));
    }

    private long extractRequest(ServletRequest request) {
        count ++;
        return count;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
