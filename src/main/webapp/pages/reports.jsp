<%--
  Created by IntelliJ IDEA.
  User: Ar
  Date: 25.01.2016
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%--pageEncoding="UTF-8"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all" >
<html>
<head>
</head>
<body>
<meta charset=""/>
<h1 class="h1">ScandJavaJsp</h1>
<h2 class="h2">Select Reports</h2>
<script type="text/javascript" src="js/script.js"></script>

<s:form action="addReportsAction" onsubmit="return checkForm(this)" >
  <div id="errEndDate" class="error" >
  <s:textfield  name="startDate" id="strStartDateId" autofocus="autofocus" label="Start date" value=""/>
      <div id="errStartDate" class="error" >
  <s:textfield name="endDate" id="strEndDateId" label="End Date" value="" />
  <s:textfield name="id" label="id"/>
  <s:select label="Performer:"
            headerKey="-1"
            headerValue="All Performers"
            list="performerList"
            name="performer"/>
  <s:select label="Time Period" onchange="timePeriod()"
            id="period"
            headerKey="-1"
            headerValue=""
            list="#{'Last Qtr':'Last Qtr','Last Month':'Last Month',
            'Last Calendar Year':'Last Calendar Year','Current Year to Date':'Current Year to Date',
            'Current Qtr to Date':'Current Qtr to Date','Current Month to Date':'Current Month to Date'}"
            name="yourPeriodTime"/>
  <s:submit />
</s:form>


<h3 class="h3">Reports</h3>

<s:if test="reportsList.size() > 0">
  <table border="1px" cellpadding="8px">
    <tr>
      <th>Reports Id</th>
      <th>Start Date</th>
      <th>End Date</th>
      <th>Performer</th>
      <th>Activity</th>
    </tr>
    <s:iterator value="reportsList" status="reportsStatus">
      <tr>
        <td><s:property value="id" /></td>
        <td><s:date name="startDate" format="MMM d,yyyy" /></td>
        <td><s:date name="endDate" format="MMM d,yyyy"  /></td>
        <td><s:property value="performer" /></td>
        <td><s:property value="activity" /></td>
      </tr>
    </s:iterator>
  </table>
</s:if>
<br/>
<br/>

</body>
</html>
