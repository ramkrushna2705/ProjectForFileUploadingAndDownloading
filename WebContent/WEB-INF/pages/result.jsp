<%@page isELIgnored="false"%>
<h1 style="color: red; text-align: center">Result page</h1>


<h3 style="color: blue;">File uploaded successfully...</h3>

<table align="left" border="1" cols="2">
	<tr>
		<th>File Name</th>
		<td>Operation</td>
	</tr>
	<tr>
		<td>${file1}</td>
		<td><a href="download.htm?file_name=${file1}">Download</a>
	</tr>
	<tr>
		<td>${file2}</td>
		<td><a href="download.htm?file_name=${file2}">Download</a>
	</tr>
</table>
