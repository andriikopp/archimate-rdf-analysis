package ua.khpi.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import ua.khpi.app.DataExtractionUtil;

public class ModelTranslator {

	public static final String XSLT_PROCESSOR = "processor.xslt";

	public static Model toRDFGraph(String modelPath, String triplesPath) {
		try {
			try (InputStream inputStream = new URL(DataExtractionUtil.PATH + modelPath).openStream()) {
				TransformerFactory transformerFactory = TransformerFactory.newInstance();

				Source xsltSource = new StreamSource(new File(XSLT_PROCESSOR));

				Transformer transformer = transformerFactory.newTransformer(xsltSource);
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

				Source xmlSource = new StreamSource(inputStream);

				transformer.transform(xmlSource, new StreamResult(new File(triplesPath)));
			}

			Path path = Paths.get(triplesPath);

			String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
			content = content.replaceAll("&lt;", "<");
			content = content.replaceAll("&gt;", ">");

			Files.write(path, content.getBytes(StandardCharsets.UTF_8));

			Model archiMateModel = ModelFactory.createDefaultModel();
			archiMateModel.read(DataExtractionUtil.PATH + triplesPath, "N-TRIPLES");

			return archiMateModel;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
