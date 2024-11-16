package Behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// Observable or Publisher
public class NewsAgency {
    private final List<Channel> channels = new ArrayList<>();
    private String news;

    public void addObserver(Channel channel) {
        channels.add(channel);
    }

    public void removeObserver(Channel channel) {
        channels.remove(channel);
    }

    public void setNews(String news) {
        this.news = news;
        for (Channel channel : channels) {
            channel.update(news);
        }
    }
}
