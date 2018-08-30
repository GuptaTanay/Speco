package com.example.tanaygupta.speco;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssReader {

    private String rssUrl;

    /**
     * Constructor
     *
     * @param rssUrl
     */
    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    public List<RssItem> getItems() throws Exception {
        // SAX parse RSS data
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        //SAXParsers are Java XML parser which parse the xml data
        RssParseHandler handler = new RssParseHandler();

        saxParser.parse(rssUrl, handler);

        return handler.getItems();

    }
}
