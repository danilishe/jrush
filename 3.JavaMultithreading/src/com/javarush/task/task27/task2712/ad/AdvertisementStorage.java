package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Данил on 08.06.2017.
 */
public class AdvertisementStorage {
    private final List<Advertisement> videos = new ArrayList();

    private static AdvertisementStorage ourInstance = new AdvertisementStorage();

    public static AdvertisementStorage getInstance() {
        return ourInstance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.addAll(Arrays.asList(
            new Advertisement(someContent, "First Video", 5000, 100, 3 * 60), // 3 min
            new Advertisement(someContent, "Second Video", 100, 10, 15 * 60), //15 min
            new Advertisement(someContent, "Third Video", 400, 2, 10 * 60) //10 min
        ));
    }
}
