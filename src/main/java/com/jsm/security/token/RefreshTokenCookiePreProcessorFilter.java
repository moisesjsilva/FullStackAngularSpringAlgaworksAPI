//package com.jsm.security.token;
//
//import java.io.IOException;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//
//import org.apache.catalina.util.ParameterMap;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class RefreshTokenCookiePreProcessorFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest)request;
//		
//		if(req.getRequestURI().equals("/oauth/token") 
//				&& "refresh_token".equals(req.getParameter("grant_type"))
//				&& req.getCookies() !=null) {
//			for(Cookie coo:req.getCookies()) {
//				if(coo.getName().equals("refresh_token")) {
//					String refreshToken = coo.getValue();
//					
//					req = new MyServletRequestWrapper(req, refreshToken);
//				}
//			}
//		}
//		
//		chain.doFilter(req, response);
//		
//	}
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		
//		
//	}
//
//
//	@Override
//	public void destroy() {
//		
//		
//	}
//	
//	static class MyServletRequestWrapper extends HttpServletRequestWrapper{
//        private String refreshToken;
//		public MyServletRequestWrapper(HttpServletRequest request,String refreshToken) {
//			super(request);
//			this.refreshToken = refreshToken;
//		}
//		
//		@Override
//		public Map<String, String[]> getParameterMap() {
//			ParameterMap<String,String[]> mapa = new ParameterMap<>(getRequest().getParameterMap());
//			mapa.put("refresh_token", new String[] {refreshToken});
//			mapa.setLocked(true);
//			
//			return mapa;
//		}
//	}
//
//}
