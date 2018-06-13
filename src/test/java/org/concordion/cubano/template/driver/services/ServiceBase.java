package org.concordion.cubano.template.driver.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.concordion.cubano.driver.http.XmlReader;
import org.concordion.ext.StoryboardMarkerFactory;
import org.concordion.ext.storyboard.CardImage;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.StockCardImage;
import org.concordion.slf4j.ext.MediaType;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;
import org.w3c.dom.Document;

public class ServiceBase {
    private final ReportLogger logger = ReportLoggerFactory.getReportLogger(this.getClass());

    protected ReportLogger getLogger() {
        return logger;
    }

    /**
     * Log service request details.
     */
    public void captureAction(Action action, String title, String description, String data) {
        captureAction(action, title, description, data, true);
    }

    public void captureAction(Action action, String title, String description, String data, boolean success) {
        logger.with()
                .locationAwareParent(ServiceBase.class)
                .message(title + ": " + description)
                .attachment(data, "data" + action.fileExtension, action.mediaType)
                .marker(StoryboardMarkerFactory.addCard(title, description, action.cardImage, success ? CardResult.SUCCESS : CardResult.FAILURE))
                .debug();
    }

    /**
     * Tentatively log service request details: allows log details be be overridden before it is finally pushed to the logs.
     * 
     * <p>
     * This log statement will get pushed to the log the next time a log request is made or when specifically requested.
     * </p>
     */
    public void captureBufferedAction(Action action, String title, String description, String data) {
        logger.withBuffered()
                .locationAwareParent(ServiceBase.class)
                .message(title + ": " + description)
                .attachment(data, "data" + action.fileExtension, action.mediaType)
                .marker(StoryboardMarkerFactory.addCard(title, description, action.cardImage, CardResult.SUCCESS))
                .debug();
    }

    public void clearBufferedAction() {
        logger.clearBufferedMessage();
    }

    public void writeBufferedAction() {
        logger.writeBufferedMessage();
    }

    public enum Action {
        XML_REQUEST(StockCardImage.XML_REQUEST, MediaType.XML, ".xml"), XML_RESPONSE(StockCardImage.XML, MediaType.XML, ".xml"), JSON_RESPONSE(StockCardImage.JSON, MediaType.JSON,
                ".json"), HTML(StockCardImage.HTML, MediaType.HTML, ".html");

        private CardImage cardImage;
        private MediaType mediaType;
        private String fileExtension;

        private Action(CardImage cardImage, MediaType mediaType, String fileExtension) {
            this.cardImage = cardImage;
            this.mediaType = mediaType;
            this.fileExtension = fileExtension;
        }
    }

    protected String xmlFromClass(Object jaxbElement) throws JAXBException, PropertyException, ParserConfigurationException, SOAPException, IOException {
        Class<?> jaxbClass = jaxbElement.getClass();

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Marshaller marshaller = JAXBContext.newInstance(jaxbClass).createMarshaller();
        marshaller.marshal(jaxbElement, document);
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();

        soapMessage.getSOAPBody().addDocument(document);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        String output = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);

        return XmlReader.prettyFormat(output, 2);
    }

    protected <R> R xmlToClass(XmlReader reader, Class<R> clazz) throws JAXBException {
        R reponse = reader.from(clazz);

        return reponse;
    }
}
