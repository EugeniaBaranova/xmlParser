package com.epam.xmlparsing.parser.dom;

import com.epam.xmlparsing.entity.greenhouse.Flower;
import com.epam.xmlparsing.parser.FlowerFieldFiller;
import com.epam.xmlparsing.utils.FileUtil;
import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.exception.XmlParserException;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

import static com.epam.xmlparsing.parser.entity.FlowerFieldNames.NAME;
import static com.epam.xmlparsing.parser.entity.FlowerNames.PEONY;
import static com.epam.xmlparsing.parser.entity.FlowerNames.ROSE;

public class DomParser implements Parser {

    private static final Logger logger = Logger.getLogger(DomParser.class);

    private FileUtil fileUtil = new FileUtil();

    private FlowerFieldFiller flowerFieldFiller = new FlowerFieldFiller();

    private List<String> flowerNames = new ArrayList<>(Arrays.asList(ROSE, PEONY));

    public List<Flower> parse(String fileName) throws XmlParserException {

        try {
            Optional<String> filePathOptional = fileUtil.getFilePath(fileName);
            if (filePathOptional.isPresent()) {
                String filePath = filePathOptional.get();

                Document document = buildDocument(filePath);
                document.normalize();
                Element root = document.getDocumentElement();

                List<Flower> flowerList = new ArrayList<>();
                for (String flowerName : flowerNames) {
                    NodeList flowers = root.getElementsByTagName(flowerName);

                    for (int i = 0; i < flowers.getLength(); i++) {

                        Node node = flowers.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {

                            Map<String, String> fieldsValues = new HashMap<>();
                            putChildNodesValues(node, fieldsValues);
                            putAttributesValues(node, fieldsValues);

                            String nodeName = node.getNodeName();

                            Optional<Flower> flowerOptional = flowerFieldFiller.fillFields(nodeName, fieldsValues);

                            if (flowerOptional.isPresent()) {
                                Flower flower = flowerOptional.get();
                                flowerList.add(flower);
                            }
                        }
                    }
                }
                return flowerList;
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(e.getMessage(), e);
            throw new XmlParserException(e);
        }
        return new ArrayList<>(0);
    }

    private Document buildDocument(String filePath) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(filePath);
    }

    private void putChildNodesValues(Node node, Map<String, String> fieldsValues) {
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {

                Node field = childNodes.item(j);

                String fieldName = field.getNodeName();
                String fieldValue = field.getTextContent();

                fieldsValues.put(fieldName, fieldValue);
            }
        }
    }

    private void putAttributesValues(Node node, Map<String, String> fieldsValues) {
        if (node.hasAttributes()) {
            NamedNodeMap attributes = node.getAttributes();

            Node attribute = attributes.getNamedItem(NAME);

            String name = attribute.getNodeName();
            String value = attribute.getNodeValue();

            fieldsValues.put(name, value);
        }
    }
}
