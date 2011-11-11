<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <title>Project PopCorn</title>
    </head>
    <body>
        <div style="text-align:center; background-color: silver;">
            <img src="Image/Project_Popcorn.png" width="500" height="500">
            </img>
            <br/><br/>
            <center>
                <form action="/ir_crear_pelicula">
                    <input type="submit" value="CREAR PELICULA">                 
                    </input>                    
                </form>                        
                <br/>
                <form action="/ir_ver_pelicula">
                    <input type="submit" value="VER PELICULA"></input>                    
                </form>                
            </center>
            <br/>
        </div>
    </body>
</html>