<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsd="http://www.w3.org/2001/XMLSchema">

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="xsd:schema">
		<xsl:copy>
			<xsl:copy-of select="@*" />
			<xsl:for-each select="./*">
				<xsl:call-template name="copyAll" />
			</xsl:for-each>
		</xsl:copy>
	</xsl:template>

	<xsl:template name="copyAll">
		<xsl:copy-of select="." />
	</xsl:template>

</xsl:stylesheet>