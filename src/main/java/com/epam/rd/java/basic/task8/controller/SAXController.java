package com.epam.rd.java.basic.task8.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for SAX parser.
 */
public class SAXController extends DefaultHandler {
    private String xmlFileName;
    private List<Flower> flowers;
    private StringBuilder currentValue = new StringBuilder();
    private Flower currentFlower;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    // PLACE YOUR CODE HERE
    public void parse() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(xmlFileName), this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String outputFile) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(outputFile));
            writer.writeStartDocument();
            // start flowers
            writer.writeStartElement("flowers");
            writer.writeAttribute("xmlns", "http://www.nure.ua");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xsi:schemaLocation", "http://www.nure.ua input.xsd ");

            for (Flower flower : flowers) {
                // new flower
                writer.writeStartElement("flower");
                // name
                writer.writeStartElement("name");
                writer.writeCharacters(flower.getName());
                writer.writeEndElement();
                // soil
                writer.writeStartElement("soil");
                writer.writeCharacters(flower.getSoil());
                writer.writeEndElement();
                // origin
                writer.writeStartElement("origin");
                writer.writeCharacters(flower.getOrigin());
                writer.writeEndElement();
                // new visualParameters
                writer.writeStartElement("visualParameters");
                // stemColour
                writer.writeStartElement("stemColour");
                writer.writeCharacters(flower.getVisualParameters().getStemColour());
                writer.writeEndElement();
                // leafColour
                writer.writeStartElement("leafColour");
                writer.writeCharacters(flower.getVisualParameters().getLeafColour());
                writer.writeEndElement();
                // aveLenFlower
                writer.writeStartElement("aveLenFlower");
                writer.writeAttribute("measure", flower.getVisualParameters().getAveLenFlowerMeasure());
                writer.writeCharacters(flower.getVisualParameters().getAveLenFlowerValue());
                writer.writeEndElement();
                // end visualParameters
                writer.writeEndElement();
                // new growingTips
                writer.writeStartElement("growingTips");
                // tempreture
                writer.writeStartElement("tempreture");
                writer.writeAttribute("measure", flower.getGrowingTips().getTempretureMeasure());
                writer.writeCharacters(flower.getGrowingTips().getTempretureValue());
                writer.writeEndElement();
                // lighting
                writer.writeStartElement("lighting");
                writer.writeAttribute("lightRequiring", flower.getGrowingTips().getLightRequiring());
                writer.writeEndElement();
                // watering
                writer.writeStartElement("watering");
                writer.writeAttribute("measure", flower.getGrowingTips().getWateringMeasure());
                writer.writeCharacters(flower.getGrowingTips().getWateringValue());
                writer.writeEndElement();
                // end growingTips
                writer.writeEndElement();
                // multiplying
                writer.writeStartElement("multiplying");
                writer.writeCharacters(flower.getMultiplying());
                writer.writeEndElement();
                // end flower
                writer.writeEndElement();
            }

            // end flowers
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startDocument() {
        flowers = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("flower"))
            currentFlower = new Flower();
        if (qName.equalsIgnoreCase("aveLenFlower"))
            currentFlower.getVisualParameters().setAveLenFlowerMeasure(attributes.getValue("measure"));
        if (qName.equalsIgnoreCase("tempreture"))
            currentFlower.getGrowingTips().setTempretureMeasure(attributes.getValue("measure"));
        if (qName.equalsIgnoreCase("lighting"))
            currentFlower.getGrowingTips().setLightRequiring(attributes.getValue("lightRequiring"));
        if (qName.equalsIgnoreCase("watering"))
            currentFlower.getGrowingTips().setWateringMeasure(attributes.getValue("measure"));
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("name"))
            currentFlower.setName(currentValue.toString());
        if (qName.equalsIgnoreCase("soil"))
            currentFlower.setSoil(currentValue.toString());
        if (qName.equalsIgnoreCase("origin"))
            currentFlower.setOrigin(currentValue.toString());

        if (qName.equalsIgnoreCase("stemColour"))
            currentFlower.getVisualParameters().setStemColour(currentValue.toString());
        if (qName.equalsIgnoreCase("leafColour"))
            currentFlower.getVisualParameters().setLeafColour(currentValue.toString());
        if (qName.equalsIgnoreCase("aveLenFlower"))
            currentFlower.getVisualParameters().setAveLenFlowerValue(currentValue.toString());

        if (qName.equalsIgnoreCase("tempreture"))
            currentFlower.getGrowingTips().setTempretureValue(currentValue.toString());
        if (qName.equalsIgnoreCase("watering"))
            currentFlower.getGrowingTips().setWateringValue(currentValue.toString());

        if (qName.equalsIgnoreCase("multiplying"))
            currentFlower.setMultiplying(currentValue.toString());

        if (qName.equalsIgnoreCase("flower"))
            flowers.add(currentFlower);
    }
}