package com.epam.rd.java.basic.task8.controller;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {
    private String xmlFileName;
    private List<Flower> flowers;

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        flowers = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    // PLACE YOUR CODE HERE
    public void parse() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(xmlFileName));
            Flower flower = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("flower")) {
                        flower = new Flower();
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        event = reader.nextEvent();
                        flower.setName(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("soil")) {
                        event = reader.nextEvent();
                        flower.setSoil(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("origin")) {
                        event = reader.nextEvent();
                        flower.setOrigin(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("visualParameters")) {
                        event = reader.nextEvent();
                    } else if (startElement.getName().getLocalPart().equals("stemColour")) {
                        event = reader.nextEvent();
                        flower.getVisualParameters().setStemColour(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("leafColour")) {
                        event = reader.nextEvent();
                        flower.getVisualParameters().setLeafColour(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("aveLenFlower")) {
                        event = reader.nextEvent();
                        flower.getVisualParameters().setAveLenFlowerValue(event.asCharacters().getData());
                        Attribute attr = startElement.getAttributeByName(new QName("measure"));
                        if (attr != null)
                            flower.getVisualParameters().setAveLenFlowerMeasure(attr.getValue());
                    } else if (startElement.getName().getLocalPart().equals("growingTips")) {
                        event = reader.nextEvent();
                    } else if (startElement.getName().getLocalPart().equals("tempreture")) {
                        event = reader.nextEvent();
                        flower.getGrowingTips().setTempretureValue(event.asCharacters().getData());
                        Attribute attr = startElement.getAttributeByName(new QName("measure"));
                        if (attr != null)
                            flower.getGrowingTips().setTempretureMeasure(attr.getValue());
                    } else if (startElement.getName().getLocalPart().equals("lighting")) {
                        event = reader.nextEvent();
                        Attribute attr = startElement.getAttributeByName(new QName("lightRequiring"));
                        if (attr != null)
                            flower.getGrowingTips().setLightRequiring(attr.getValue());
                    } else if (startElement.getName().getLocalPart().equals("watering")) {
                        event = reader.nextEvent();
                        flower.getGrowingTips().setWateringValue(event.asCharacters().getData());
                        Attribute attr = startElement.getAttributeByName(new QName("measure"));
                        if (attr != null)
                            flower.getGrowingTips().setWateringMeasure(attr.getValue());
                    } else if (startElement.getName().getLocalPart().equals("multiplying")) {
                        event = reader.nextEvent();
                        flower.setMultiplying(event.asCharacters().getData());
                    }
                }

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("flower"))
                        flowers.add(flower);
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(String outputFile) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEventWriter writer = factory.createXMLEventWriter(new FileOutputStream(outputFile));
            XMLEvent event = eventFactory.createStartDocument();

            writer.add(event);
            writer.add(eventFactory.createStartElement("", "", "flowers"));
            writer.add(eventFactory.createAttribute("xmlns", "http://www.nure.ua"));
            writer.add(eventFactory.createAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance"));
            writer.add(eventFactory.createAttribute("xsi:schemaLocation", "http://www.nure.ua input.xsd "));
            for (Flower flower : flowers) {
                // create flower
                writer.add(eventFactory.createStartElement("", "", "flower"));
                // create name
                writer.add(eventFactory.createStartElement("", "", "name"));
                writer.add(eventFactory.createCharacters(flower.getName()));
                writer.add(eventFactory.createEndElement("", "", "name"));
                // create soil
                writer.add(eventFactory.createStartElement("", "", "soil"));
                writer.add(eventFactory.createCharacters(flower.getSoil()));
                writer.add(eventFactory.createEndElement("", "", "soil"));
                // create origin
                writer.add(eventFactory.createStartElement("", "", "origin"));
                writer.add(eventFactory.createCharacters(flower.getOrigin()));
                writer.add(eventFactory.createEndElement("", "", "origin"));
                // create visual parameters
                writer.add(eventFactory.createStartElement("", "", "visualParameters"));
                // create vp.stemColour
                writer.add(eventFactory.createStartElement("", "", "stemColour"));
                writer.add(eventFactory.createCharacters(flower.getVisualParameters().getStemColour()));
                writer.add(eventFactory.createEndElement("", "", "stemColour"));
                // create vp.leafColour
                writer.add(eventFactory.createStartElement("", "", "leafColour"));
                writer.add(eventFactory.createCharacters(flower.getVisualParameters().getLeafColour()));
                writer.add(eventFactory.createEndElement("", "", "leafColour"));
                // create vp.aveLenFlower
                writer.add(eventFactory.createStartElement("", "", "aveLenFlower"));
                writer.add(eventFactory.createAttribute("measure", flower.getVisualParameters().getAveLenFlowerMeasure()));
                writer.add(eventFactory.createCharacters(flower.getVisualParameters().getAveLenFlowerValue()));
                writer.add(eventFactory.createEndElement("", "", "aveLenFlower"));
                // end visual parameters
                writer.add(eventFactory.createEndElement("", "", "visualParameters"));
                // create growing tips
                writer.add(eventFactory.createStartElement("", "", "growingTips"));
                // create gt.tempreture
                writer.add(eventFactory.createStartElement("", "", "tempreture"));
                writer.add(eventFactory.createAttribute("measure", flower.getGrowingTips().getTempretureMeasure()));
                writer.add(eventFactory.createCharacters(flower.getGrowingTips().getTempretureValue()));
                writer.add(eventFactory.createEndElement("", "", "tempreture"));
                // create gt.lighting
                writer.add(eventFactory.createStartElement("", "", "lighting"));
                writer.add(eventFactory.createAttribute("lightRequiring", flower.getGrowingTips().getLightRequiring()));
                writer.add(eventFactory.createEndElement("", "", "lighting"));
                // create gt.watering
                writer.add(eventFactory.createStartElement("", "", "watering"));
                writer.add(eventFactory.createAttribute("measure", flower.getGrowingTips().getWateringMeasure()));
                writer.add(eventFactory.createCharacters(flower.getGrowingTips().getWateringValue()));
                writer.add(eventFactory.createEndElement("", "", "watering"));
                // end growing tips
                writer.add(eventFactory.createEndElement("", "", "growingTips"));
                // create multiplying
                writer.add(eventFactory.createStartElement("", "", "multiplying"));
                writer.add(eventFactory.createCharacters(flower.getMultiplying()));
                writer.add(eventFactory.createEndElement("", "", "multiplying"));
                // end flower
                writer.add(eventFactory.createEndElement("", "", "flower"));
            }

            writer.add(eventFactory.createEndElement("", "", "flowers"));
            writer.add(eventFactory.createEndDocument());

            writer.flush();
            writer.close();
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}