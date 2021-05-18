package com.deepblue.inaction.system_out_error.chapter_03_share.thread_007_OneValueCache;

import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.*;
import java.io.IOException;

/**
 * 出处: java并发实战 Page77, 这种方式得首先确保 OneCacheValue类是 不可变对象, 然后 创建这个 不可变对象对 多个对象是可见的 才能保证 线程的安全性
 */
@ThreadSafe
public class OneValueCacheFactorizer implements Servlet {

    private volatile OneValueCache oneValueCache = new OneValueCache(null, null);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
