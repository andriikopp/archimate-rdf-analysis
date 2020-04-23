<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:arc="http://www.opengroup.org/xsd/archimate"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
<xsl:template match="/">
	<xsl:variable name="base">http://www.w3.org/1999/02/22-rdf-syntax-ns#</xsl:variable>
	<xsl:for-each select="arc:model/arc:relationships/arc:relationship">
		<xsl:variable name="Source" select="concat('',@source,'')"></xsl:variable>
		<xsl:variable name="Target" select="concat('',@target,'')"></xsl:variable>
		<xsl:if test="contains(@xsi:type, 'TriggeringRelationship')">
			&lt;<xsl:copy-of select="$base"/>
				<xsl:value-of select="translate(//arc:element[@identifier=$Source]/@xsi:type, ' ', '_')"/>::<xsl:value-of select="translate(//arc:element[@identifier=$Source]/arc:label, ' ', '_')"/>&gt;
			&lt;<xsl:copy-of select="$base"/>
				<xsl:value-of select="@xsi:type"/>&gt;
			&lt;<xsl:copy-of select="$base"/>
				<xsl:value-of select="translate(//arc:element[@identifier=$Target]/@xsi:type, ' ', '_')"/>::<xsl:value-of select="translate(//arc:element[@identifier=$Target]/arc:label, ' ', '_')"/>&gt; .
		</xsl:if>
	</xsl:for-each>
</xsl:template>
</xsl:stylesheet>