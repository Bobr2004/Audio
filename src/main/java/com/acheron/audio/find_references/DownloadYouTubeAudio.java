package com.acheron.audio.find_references;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.AudioFormat;
import com.github.kiulian.downloader.model.videos.formats.Format;

import java.util.*;

public class DownloadYouTubeAudio {
    public static void main(String[] args) {
        System.out.println(downloadAudio(SearchAudioEntity.searchIdURL("lxner night vision",5)));
    }
    public static List<Map<String,String>> downloadAudio( List<Map<String,String>> videoId) {
        List<Map<String,String>> list =new ArrayList<>();
        for (int i = 0; i <videoId.size(); i++) {
            if (videoId != null) {
                YoutubeDownloader downloader = new YoutubeDownloader();
                RequestVideoInfo request1 = new RequestVideoInfo(videoId.get(0).get(i));
                Response<VideoInfo> response1 = downloader.getVideoInfo(request1);
                VideoInfo video = response1.data();
                List<AudioFormat> audioFormats = video.audioFormats();
                audioFormats.sort(Comparator.comparing(Format::contentLength).reversed());
                audioFormats.forEach(it -> {
                });
                int i1=0;
                for (AudioFormat format : audioFormats) {
                    i1++;
                    if (format.contentLength() < 60000000 && format.mimeType().charAt(6) == 'm') {
                        Map<String,String> map =new HashMap<>();
                        map.put(format.url(),videoId.get(i).get(1));
                        list.add(map);
                        break;
                    }
                }
            }
        }
        return list;
    }
}
