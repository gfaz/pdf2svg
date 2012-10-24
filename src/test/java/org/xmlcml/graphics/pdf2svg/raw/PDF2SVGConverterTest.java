package org.xmlcml.graphics.pdf2svg.raw;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.graphics.pdf2svg.raw.PDF2SVGConverter;
import org.xmlcml.graphics.svg.SVGPath;
import org.xmlcml.graphics.svg.SVGSVG;
import org.xmlcml.graphics.svg.SVGText;
import org.xmlcml.graphics.svg.SVGUtil;

public class PDF2SVGConverterTest {

	@Test
	public void testPage6() {
		File page6File = new File("target/page1.svg");  // yes, this serial number is what is is output as
		if (page6File.exists()) {
			page6File.delete();
		}
		PDF2SVGConverter converter = new PDF2SVGConverter();
		converter.run("src/test/resources/page6.pdf");
		// results have been written to target
		Assert.assertTrue(page6File.exists());
		Assert.assertEquals("Page count", 1, converter.getPageList().size());
		SVGSVG svgPage = converter.getPageList().get(0);
		List<SVGText> texts = SVGText.extractTexts(SVGUtil.getQuerySVGElements(svgPage, "//svg:text"));
		int nTexts = texts.size();
		Assert.assertTrue("count: ("+nTexts+")", nTexts > 4090 && nTexts < 4100);
		List<SVGPath> paths = SVGPath.extractPaths(SVGUtil.getQuerySVGElements(svgPage, "//svg:path"));
		int nPaths = paths.size();
		Assert.assertTrue("count: ("+nPaths+")", nPaths > 195 && nPaths < 200);
	}
	
	
	@Test
//	@Ignore // do not normally run this
	public void testAJC() {
		PDF2SVGConverter converter = new PDF2SVGConverter();
		converter.run("../pdfs/ajctest/xx.pdf");
	}
}
