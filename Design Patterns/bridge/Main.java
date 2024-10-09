package bridge;

public class Main {
    public static void main(String[] args) {
        Video youtubeVideo = new YoutubeVideo(new HDProcessor());
        youtubeVideo.play("abc.mp4");
        Video netflixVideo = new NetflixVideo(new UHD4KProcessor());
        netflixVideo.play("abc.mp4");
    }
}
