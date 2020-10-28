package com.deepblue.inaction.thread_002_Counting;

import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 无状态的 线程安全 统计点击量
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        BigInteger count = extractRequest(request);
        response.getWriter().write(count.toString());
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    /**
     * 计算 访问次数
     * @param request
     * @return
     */
    private BigInteger extractRequest(ServletRequest request) {
        // TODO 省略代码 默认线程安全
        return new BigInteger("0");
    }
}
