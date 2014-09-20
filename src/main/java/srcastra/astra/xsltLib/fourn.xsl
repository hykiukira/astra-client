<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:template match="/">

<html>
<head><title>Fournisseur</title></head>
<body>
<table border="1" width="100%">
<tr>
<th>REFERENCE</th>
<th>NOM</th>
</tr>

<xsl:for-each select="fournisseur/info_generale">
<tr>
<td><xsl:value-of select="frreference1"/></td>
<td><xsl:value-of select="frnom1"/></td>
</tr>
</xsl:for-each>

</table>
</body></html>
</xsl:template>
</xsl:stylesheet>
