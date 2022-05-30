package com.group.wcfileinfo;

import module.reader.wc.Wc;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "WcServlet", value = "/wc-servlet")
public class WcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session  = request.getSession(false);
        if(session==null || session.isNew()){
            System.out.println("session is new");
            response.sendRedirect("index.html");
            return;
        }
        String[] filesPaths = (String[])session.getAttribute("filesPaths");
        if(filesPaths==null || filesPaths.length==0){
            response.sendRedirect("index.html");
            return;
        }
        for (String filePath : filesPaths) {
            System.out.println(filePath);
        }
        session.removeAttribute("filesPaths");
        result(request, response , filesPaths);
    }

    private void result(HttpServletRequest request , HttpServletResponse response , String[] filePaths) throws IOException, ServletException {
        Wc wc = new Wc();
        String[] result = wc.printResult(filePaths).split("\n");
        request.setAttribute("result", result);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
