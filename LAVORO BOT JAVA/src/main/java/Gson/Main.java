package Gson;//package readers; // che cazzo è diocazzo??

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.io.XmlReader;
import org.xml.sax.InputSource;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;


public class Main
{
    public static void main(String[] args) throws FeedException, IOException {


        FeedObj vaffanculo = new FeedObj("https://www.repubblica.it/rss/homepage/rss2.0.xml");


    }

}
