<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='security' uri='http://www.springframework.org/security/tags'%> 
<!doctype html>
<html lang='nl'>
<head>
	<v:head title="${filiaal.naam}"></v:head>
</head>
<body>
	<v:menu/>
	<c:choose>
		<c:when test="${not empty filiaal}">
			<h1>${filiaal.naam}</h1>
			<c:if test="${param.optimisticlockingexception}">
				<div class='fout'>Filiaal werd door andere gebruiker als volgt gewijzigd:</div>
			</c:if>
			<dl>
				<dt>Straat</dt><dd>${filiaal.adres.straat}</dd>
				<dt>Huisnr.</dt><dd>${filiaal.adres.huisNr}</dd>
				<dt>Postcode</dt><dd>${filiaal.adres.postcode}</dd>
				<dt>Gemeente</dt><dd>${filiaal.adres.gemeente}</dd>
				<dt>Type</dt><dd>${filiaal.hoofdFiliaal ? "Hoofdfiliaal" : "Bijfiliaal"}</dd>
				<dt>Waarde gebouw</dt>
				<dd>&euro; <spring:eval expression="filiaal.waardeGebouw"/>
				<spring:url value="/euro/{euro}/naardollar" var="naarDollarURL">
					<spring:param name="euro" value="${filiaal.waardeGebouw}"/>
				</spring:url>
				<a href="${naarDollarURL}">in $</a>
				<dt>Ingebruikname</dt>
				<dd><spring:eval expression="filiaal.inGebruikName"/></dd>
			</dl>
			<spring:url value="/filialen/{id}/verwijderen" var="verwijderURL">
				<spring:param name="id" value="${filiaal.id}"/>
			</spring:url>
			<form action="${verwijderURL}" method="post">
				<input type="submit" value="Verwijderen">
			</form>
			<security:csrfInput/>
			<spring:url value="/filialen/{id}/wijzigen" var="wijzigURL">
				<spring:param name="id" value="${filiaal.id}"/>
			</spring:url>
			<form action="${wijzigURL}">
				<input type="submit" value="Wijzigen">
			</form>
		</c:when>
		<c:otherwise>
			<div class='fout'>Filiaal niet gevonden</div>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty param.fout}">
		<div class='fout'>${param.fout}</div>
	</c:if>
</body>
</html>