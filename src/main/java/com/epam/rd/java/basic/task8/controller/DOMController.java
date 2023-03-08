package com.epam.rd.java.basic.task8.controller;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for DOM parser.
 */
public class DOMController {
    private String xmlFileName;
    private List<Flower> flowers;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        flowers = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    // PLACE YOUR CODE HERE
    public void parse() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFileName));
            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("flower");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    Element visualParameters = (Element) eElement.getElementsByTagName("visualParameters").item(0);
                    Element aveLenFlowerMeasure = (Element) visualParameters.getElementsByTagName("aveLenFlower").item(0);

                    Element growingTips = (Element) eElement.getElementsByTagName("growingTips").item(0);
                    Element tempretureMeasure = (Element) growingTips.getElementsByTagName("tempreture").item(0);
                    Element lightRequiring = (Element) growingTips.getElementsByTagName("lighting").item(0);
                    Element wateringMeasure = (Element) growingTips.getElementsByTagName("watering").item(0);

                    Flower flower = new Flower();
                    flower.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    flower.setSoil(eElement.getElementsByTagName("soil").item(0).getTextContent());
                    flower.setOrigin(eElement.getElementsByTagName("origin").item(0).getTextContent());

                    flower.getVisualParameters().setStemColour(visualParameters.getElementsByTagName("stemColour").item(0).getTextContent());
                    flower.getVisualParameters().setLeafColour(visualParameters.getElementsByTagName("leafColour").item(0).getTextContent());
                    flower.getVisualParameters().setAveLenFlowerMeasure(aveLenFlowerMeasure.getAttribute("measure"));
                    flower.getVisualParameters().setAveLenFlowerValue(visualParameters.getElementsByTagName("aveLenFlower").item(0).getTextContent());

                    flower.getGrowingTips().setTempretureMeasure(tempretureMeasure.getAttribute("measure"));
                    flower.getGrowingTips().setTempretureValue(growingTips.getElementsByTagName("tempreture").item(0).getTextContent());
                    flower.getGrowingTips().setLightRequiring(lightRequiring.getAttribute("lightRequiring"));
                    flower.getGrowingTips().setWateringMeasure(wateringMeasure.getAttribute("measure"));
                    flower.getGrowingTips().setWateringValue(growingTips.getElementsByTagName("watering").item(0).getTextContent());

                    flower.setMultiplying(eElement.getElementsByTagName("multiplying").item(0).getTextContent());

                    flowers.add(flower);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    public void save(String outputFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("flowers");
            root.setAttribute("xmlns", "http://www.nure.ua");
            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xsi:schemaLocation", "http://www.nure.ua input.xsd ");
            document.appendChild(root);

            Element element;
            Element flowerElement;
            Element visualParametersElement;
            Element growingTipsElement;

            for (Flower flower : flowers) {
                // flower
                flowerElement = document.createElement("flower");
                // name
                element = document.createElement("name");
                element.setTextContent(flower.getName());
                flowerElement.appendChild(element);
                // soil
                element = document.createElement("soil");
                element.setTextContent(flower.getSoil());
                flowerElement.appendChild(element);
                // origin
                element = document.createElement("origin");
                element.setTextContent(flower.getOrigin());
                flowerElement.appendChild(element);
                // visualParameters
                visualParametersElement = document.createElement("visualParameters");
                Flower.VisualParameters vp = flower.getVisualParameters();
                // visualParameters - stemColour
                element = document.createElement("stemColour");
                element.setTextContent(vp.getStemColour());
                visualParametersElement.appendChild(element);
                // visualParameters - leafColour
                element = document.createElement("leafColour");
                element.setTextContent(vp.getLeafColour());
                visualParametersElement.appendChild(element);
                // visualParameters - aveLenFlower
                element = document.createElement("aveLenFlower");
                element.setTextContent(vp.getAveLenFlowerValue());
                element.setAttribute("measure", vp.getAveLenFlowerMeasure());
                visualParametersElement.appendChild(element);
                flowerElement.appendChild(visualParametersElement);

                // growingTips
                growingTipsElement = document.createElement("growingTips");
                Flower.GrowingTips gt = flower.getGrowingTips();
                // growingTips - tempreture
                element = document.createElement("tempreture");
                element.setTextContent(gt.getTempretureValue());
                element.setAttribute("measure", gt.getTempretureMeasure());
                growingTipsElement.appendChild(element);
                // growingTips - lighting
                element = document.createElement("lighting");
                element.setAttribute("lightRequiring", gt.getLightRequiring());
                growingTipsElement.appendChild(element);
                // growingTips - watering
                element = document.createElement("watering");
                element.setTextContent(gt.getWateringValue());
                element.setAttribute("measure", gt.getWateringMeasure());
                growingTipsElement.appendChild(element);
                flowerElement.appendChild(growingTipsElement);

                // multiplying
                element = document.createElement("multiplying");
                element.setTextContent(flower.getMultiplying());
                flowerElement.appendChild(element);

                // root
                root.appendChild(flowerElement);
            }

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File file = new File(outputFile);
            file.createNewFile();
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
