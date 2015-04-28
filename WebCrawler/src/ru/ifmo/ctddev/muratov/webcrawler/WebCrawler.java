package ru.ifmo.ctddev.muratov.webcrawler;

import info.kgeorgiy.java.advanced.crawler.CachingDownloader;
import info.kgeorgiy.java.advanced.crawler.Downloader;
import info.kgeorgiy.java.advanced.crawler.URLUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * WebCrawler was created by amir on 16.04.15.
 */
public class WebCrawler implements info.kgeorgiy.java.advanced.crawler.Crawler {

    Downloader downloader;
    ExecutorService downloaders;
    ExecutorService extractors;
    int perHost;
    ConcurrentMap<String, Integer> DownloadsPerHost;



    private class Call {


        BlockingQueue<String> linksToProcess;
        BlockingQueue<String> result;
        int depth;

        public Call() {

        }




    }


    


    public WebCrawler(Downloader downloader, int downloaders, int extractors, int perHost) {
        this.downloader = downloader;
        this.perHost = perHost;

        DownloadsPerHost = new ConcurrentHashMap<String, Integer>();
        this.downloaders = Executors.newFixedThreadPool(downloaders);
        this.extractors = Executors.newFixedThreadPool(extractors);

    }



    @Override
    public List<String> download(String s, int i) throws IOException {
        //info.kgeorgiy.java.advanced.crawler.Document d = downloader.download(s);
        //return d.extractLinks();
        Queue<String> result = new ConcurrentLinkedQueue<String>();
        Call currentCall = new Call();

        return new ArrayList<String>(result);
    }

    @Override
    public void close() {

    }

    public static void main(String[] args) throws IOException {
        Downloader d = new CachingDownloader();
        List<String> result = new WebCrawler(d, 1, 1, 1).download(args[0], 1);
        result.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
