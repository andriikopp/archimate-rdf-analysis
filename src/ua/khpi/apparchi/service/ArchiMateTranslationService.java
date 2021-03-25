package ua.khpi.apparchi.service;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import ua.khpi.apparchi.dao.api.IElementDAO;
import ua.khpi.apparchi.dao.api.IModelDAO;
import ua.khpi.apparchi.dao.api.IStructureDAO;
import ua.khpi.apparchi.entity.ElementEntity;
import ua.khpi.apparchi.entity.ModelEntity;
import ua.khpi.apparchi.entity.StructureEntity;
import ua.khpi.apparchi.service.api.ITranslationService;

public class ArchiMateTranslationService implements ITranslationService {
	private static final String PATH = "file:\\D:\\GitHub\\archimate-rdf-analysis\\";
	private static final String XSLT_TRANSLATOR = "translator.xslt";

	private IModelDAO modelDAO;
	private IStructureDAO structureDAO;
	private IElementDAO elementDAO;

	@Override
	public void translateModel(ModelEntity model) {
		try (InputStream inputStream = new URL(PATH + model.getFile()).openStream()) {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Source xsltSource = new StreamSource(new File("apparchi\\" + XSLT_TRANSLATOR));
			Transformer transformer = transformerFactory.newTransformer(xsltSource);
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			Source xmlSource = new StreamSource(inputStream);
			StringWriter result = new StringWriter();
			transformer.transform(xmlSource, new StreamResult(result));
			String output = result.toString();
			String[] lines = output.split(";");
			Set<String> nodes = new HashSet<>();
			int edges = 0;
			Map<String, Integer> inDeg = new HashMap<>();
			Map<String, Integer> outDeg = new HashMap<>();

			for (String line : lines) {
				String trimmed = line.trim();
				String[] elements = trimmed.split(",");

				if (elements.length == 3) {
					String source = elements[0];
					String target = elements[2];
					edges++;
					nodes.add(source);
					nodes.add(target);
					
					if (inDeg.containsKey(target)) {
						int old = inDeg.get(target);
						inDeg.put(target, old + 1);
					} else {
						inDeg.put(target, 1);
					}
					
					if (outDeg.containsKey(source)) {
						int old = outDeg.get(source);
						outDeg.put(source, old + 1);
					} else {
						outDeg.put(source, 1);
					}
				}
			}
			
			modelDAO.create(model);
			StructureEntity structure = new StructureEntity(UUID.randomUUID().toString(),
					model.getId(),
					nodes.size(),
					edges,
					new Date().toString());
			structureDAO.create(structure);
			
			for (String node : nodes) {
				ElementEntity element = new ElementEntity(UUID.randomUUID().toString(),
						structure.getId(),
						node,
						"Application Component",
						inDeg.get(node),
						outDeg.get(node));
				elementDAO.create(element);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public IModelDAO getModelDAO() {
		return modelDAO;
	}

	public void setModelDAO(IModelDAO modelDAO) {
		this.modelDAO = modelDAO;
	}

	public IStructureDAO getStructureDAO() {
		return structureDAO;
	}

	public void setStructureDAO(IStructureDAO structureDAO) {
		this.structureDAO = structureDAO;
	}

	public IElementDAO getElementDAO() {
		return elementDAO;
	}

	public void setElementDAO(IElementDAO elementDAO) {
		this.elementDAO = elementDAO;
	}

}
