package com.example.proyecto3;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejoXML {
    public static void creaArchivo(String[] lista, String nombreArchivo){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null, "juegos", null);
            documento.setXmlVersion("1.0");

            Element juego = documento.createElement("Juego");

            Element numeroNodos = documento.createElement("CantidadDeNodos");
            Text textNumeroNodos = documento.createTextNode(lista[0]);
            numeroNodos.appendChild(textNumeroNodos);
            juego.appendChild(numeroNodos);

            Element puntaje = documento.createElement("PuntajeAzul");
            Text textPuntaje = documento.createTextNode(lista[1]);
            puntaje.appendChild(textPuntaje);
            juego.appendChild(puntaje);

            Element fecha = documento.createElement("PuntajeVerde");
            Text textFecha = documento.createTextNode(lista[2]);
            fecha.appendChild(textFecha);
            juego.appendChild(fecha);


            documento.getDocumentElement().appendChild(juego);

            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File(nombreArchivo));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);




        }catch (ParserConfigurationException ex){
            Logger.getLogger(ManejoXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        }

    public static void main(String[] args){
        String[] argumentos = new String[3];
        argumentos[0] = "7";
        argumentos[1] = "4";
        argumentos[2] = "8";

        String[] argumentos2 = new String[3];
        argumentos[0] = "25";
        argumentos[1] = "6";
        argumentos[2] = "9";

        String[] argumentos3 = new String[3];
        argumentos[0] = "15";
        argumentos[1] = "6";
        argumentos[2] = "13";

        String[] argumentos4 = new String[3];
        argumentos[0] = "5";
        argumentos[1] = "4";
        argumentos[2] = "2";

        String[] argumentos5 = new String[3];
        argumentos[0] = "21";
        argumentos[1] = "7";
        argumentos[2] = "18";

        String[] argumentos6 = new String[3];
        argumentos[0] = "40";
        argumentos[1] = "30";
        argumentos[2] = "2";

        ManejoXML.creaArchivo(argumentos, "Partidas1.xml");
        ManejoXML.creaArchivo(argumentos2, "Partidas2.xml");
        ManejoXML.creaArchivo(argumentos3, "Partidas3.xml");
        ManejoXML.creaArchivo(argumentos4, "Partidas4.xml");
        ManejoXML.creaArchivo(argumentos5, "Partidas5.xml");
        ManejoXML.creaArchivo(argumentos6, "Partidas6.xml");
        ReadXmlDomParser.lee("Partidas.xml");
    }

}
