<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files Info</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background: #6990F2;
        }
        table, td, th {
            border: 2px solid white;
            border-collapse: collapse;
        }
        th {
            color: rgb(107, 250, 178);
        }
        td {
            color: rgb(218, 255, 5)
        }
        td , th {
            padding: 10px ;
        }
    </style>
</head>
<body>
<%String[] result = (String[])request.getAttribute("result");%>
<div class="wrapper">
    <table>
        <tr>
            <th>File name</th>
            <th>Bytes</th>
            <th>Characters</th>
            <th>Words</th>
            <th>Lines</th>
        </tr>
        <%
            for (String s : result) {
                String[] fileInfo = s.split(" ");
                fileInfo[0] = fileInfo[0].substring(fileInfo[0].lastIndexOf('\\')+1);
        %>
        <tr>
            <td><%=fileInfo[0]%>></td>
            <td><%=fileInfo[1]%></td>
            <td><%=fileInfo[2]%></td>
            <td><%=fileInfo[3]%></td>
            <td><%=fileInfo[4]%></td>
        </tr>
        <%}%>
    </table>
</div>

</body>
</html>
