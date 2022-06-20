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
    public static void creaArchivo(String[] lista){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null, "juegos", null);
            documento.setXmlVersion("1.0");

            Element juego = documento.createElement("juego");

            Element numeroNodos = documento.createElement("Cantidad de Nodos");
            Text textNumeroNodos = documento.createTextNode(lista[0]);
            numeroNodos.appendChild(textNumeroNodos);
            juego.appendChild(numeroNodos);

            Element puntaje = documento.createElement("Puntaje");
            Text textPuntaje = documento.createTextNode(lista[1]);
            puntaje.appendChild(textPuntaje);
            juego.appendChild(puntaje);

            Element fecha = documento.createElement("Fecha");
            Text textFecha = documento.createTextNode(lista[2]);
            fecha.appendChild(textFecha);
            juego.appendChild(fecha);


            documento.getDocumentElement().appendChild(juego);

            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("Partidas.xml"));

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

}
