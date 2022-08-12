<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="icon" href="<c:url value="/resources/images/ant.png" />" type="image/gif" sizes="16x16">
        <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
    </head>
    <body>
        <tiles:insertAttribute name="navbar" />
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="content" />
        <tiles:insertAttribute name="modal" />
        <script src="<c:url value="/resources/js/home.js" />"></script>
    </body>
</html>
