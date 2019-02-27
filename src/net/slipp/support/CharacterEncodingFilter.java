package net.slipp.support;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
/*
@WebServlet(urlPatterns= {"/*"})
public class CharacterEncodingFilter implements Filter{
 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	System.out.println("charator encoding fiher init");	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	
	
	@Override
	public void destroy() {
		
	}

}
*/