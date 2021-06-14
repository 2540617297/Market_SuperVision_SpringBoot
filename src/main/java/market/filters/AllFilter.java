package market.filters;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*",filterName = "allFilter")
public class AllFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("url:" + url);
        //得到session中“username”属性，以便接下来判断是否已登录
        String userId = (String) request.getSession().getAttribute("userId");

        if (url.startsWith("/admin/index")){
            if(userId==null){
                response.sendRedirect(request.getContextPath()+"/admin/begin");
                return;
            }else {
                filterChain.doFilter(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
