<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
        <script>
            var BrowserDetect = {
            init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
            },
            searchString: function (data) {
		for (var i=0;i < data.length;i++)	{
                    var dataString = data[i].string;
                    var dataProp = data[i].prop;
                    this.versionSearchString = data[i].versionSearch || data[i].identity;
                    if (dataString) {
                        if (dataString.indexOf(data[i].subString) != -1)
                            return data[i].identity;
                    }
                    else if (dataProp)
			return data[i].identity;
		}
            },
            searchVersion: function (dataString) {
                var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
                    return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
            },
            dataBrowser: [
            {
                string: navigator.userAgent,
		subString: "Chrome",
		identity: "Chrome"
            },
            {
		string: navigator.vendor,
		subString: "Apple",
		identity: "Safari",
		versionSearch: "Version"
            },
            {
		prop: window.opera,
		identity: "Opera",
		versionSearch: "Version"
            },
            {
		string: navigator.userAgent,
		subString: "Firefox",
		identity: "Firefox"
            },
            {
		string: navigator.userAgent,
		subString: "MSIE",
                identity: "Explorer",
		versionSearch: "MSIE"
            },
            ],
            dataOS : [
            {
		string: navigator.platform,
		subString: "Win",
		identity: "Windows"
            },
            {
		string: navigator.platform,
		subString: "Mac",
		identity: "Mac"
            },
            {
         	string: navigator.userAgent,
		subString: "iPhone",
		identity: "iPhone/iPod"
            },
            {
		string: navigator.platform,
		subString: "Linux",
		identity: "Linux"
            }
            ]

            };
            BrowserDetect.init();
        </script>
    </head>
    <body>          
        <center>
            <c:forEach var="coment" items="${comentarios}" varStatus="status">
                <table border="1">
                    <tr>
                        <td>                            
                            <strong><c:out value="${coment.content}"/><br/></strong>
                            <c:out value="${coment.fecha}"/><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <script type="text/javascript">
                                document.write('<p>Escrito desde ' + BrowserDetect.browser + ' ' + BrowserDetect.version + ' en ' + BrowserDetect.OS + '!</p>');
                            </script>  
                        </td>
                    </tr>
                </table>                   
            </c:forEach>
        </center>
    </body>
</html>