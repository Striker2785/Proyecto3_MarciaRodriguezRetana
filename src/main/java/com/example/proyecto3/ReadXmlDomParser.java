package com.example.proyecto3;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
public class ReadXmlDomParser {
    private static String FILENAME;

    public static String[] lee(String filename){

        FILENAME = filename;

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String[] listaElementos = new String[3];
        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));



            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("Juego");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    //String id = element.getAttribute("id");

                    // get text
                    String firstname = element.getElementsByTagName("CantidadDeNodos").item(0).getTextContent();
                    listaElementos[0] = firstname;
                    String lastname = element.getElementsByTagName("PuntajeAzul").item(0).getTextContent();
                    listaElementos[1] = lastname;
                    String nickname = element.getElementsByTagName("PuntajeVerde").item(0).getTextContent();
                    listaElementos[2] = lastname;


                    System.out.println("Current Element :" + node.getNodeName());

                   // System.out.println("Staff Id : " + id);
                    System.out.println("Cantidad de Nodos : " + firstname);
                    System.out.println("Puntaje Azul: " + lastname);
                    System.out.println("Puntaje Verde : " + nickname);


                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return listaElementos;
    }


}
