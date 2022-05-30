package com.group.wcfileinfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig(
        location = "C:\\Users\\ELCOT\\Desktop\\files",
        fileSizeThreshold = 1024*1024*1024,
        maxFileSize = 1024*1024*1024
)
@WebServlet(name = "FileUploadServlet", value = "/file-upload-servlet")

public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Part part = request.getPart("file");
        final String location = "C:\\Users\\ELCOT\\Desktop\\files\\";
        String fileName = part.getSubmittedFileName();
        String filePath = location + fileName;
        if(fileName!=null) {
            String[] filesPaths = (String[]) session.getAttribute("filesPaths");
            if(filesPaths==null) {
                filesPaths = new String[1];
                filesPaths[0] = filePath;
                session.setAttribute("filesPaths", filesPaths);
                System.out.println("session is new");
            } else {
                filesPaths = (String[]) resizeArray(filesPaths, filesPaths.length + 1);
                filesPaths[filesPaths.length - 1] = filePath;
                session.setAttribute("filesPaths", filesPaths);
            }
            for(String path : filesPaths){
                System.out.println("file " + path);
            }
            part.write(part.getSubmittedFileName());
            System.out.println("Writing complete");
        }
    }

    private static Object resizeArray (Object oldArray, int newSize) {
        int oldSize = java.lang.reflect.Array.getLength(oldArray);
        Class elementType = oldArray.getClass().getComponentType();
        Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0)
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        return newArray;
    }
}
