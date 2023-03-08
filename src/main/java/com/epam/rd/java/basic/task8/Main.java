package com.epam.rd.java.basic.task8;

import com.epam.rd.java.basic.task8.controller.*;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        String xmlFileName = args[0];
        //String xsdFileName = args[1];
        System.out.println("Input ==> " + xmlFileName);
        //System.out.println("Schema ==> " + xsdFileName);

        ////////////////////////////////////////////////////////
        // DOM
        ////////////////////////////////////////////////////////

        // get container
        DOMController domController = new DOMController(xmlFileName);
        // PLACE YOUR CODE HERE
        domController.parse();
        List<Flower> listDOM = domController.getFlowers();

        // sort (case 1)
        // PLACE YOUR CODE HERE
        listDOM.sort(Comparator.comparing(Flower::getName));

        // save
        String outputXmlFile = "output.dom.xml";
        // PLACE YOUR CODE HERE
        domController.save(outputXmlFile);

        ////////////////////////////////////////////////////////
        // SAX
        ////////////////////////////////////////////////////////

        // get
        SAXController saxController = new SAXController(xmlFileName);
        // PLACE YOUR CODE HERE
        saxController.parse();
        List<Flower> listSAX = saxController.getFlowers();

        // sort  (case 2)
        // PLACE YOUR CODE HERE
        listSAX.sort(Comparator.comparing(Flower::getSoil));

        // save
        outputXmlFile = "output.sax.xml";
        // PLACE YOUR CODE HERE
        saxController.save(outputXmlFile);

        ////////////////////////////////////////////////////////
        // StAX
        ////////////////////////////////////////////////////////

        // get
        STAXController staxController = new STAXController(xmlFileName);
        // PLACE YOUR CODE HERE
        staxController.parse();
        List<Flower> listSTAX = staxController.getFlowers();

        // sort  (case 3)
        // PLACE YOUR CODE HERE
        listSTAX.sort(Comparator.comparing(Flower::getOrigin));

        // save
        outputXmlFile = "output.stax.xml";
        // PLACE YOUR CODE HERE
        staxController.save(outputXmlFile);
    }
}
