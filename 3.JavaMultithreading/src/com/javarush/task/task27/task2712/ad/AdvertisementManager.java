package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Данил on 08.06.2017.
 */
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private final List<AdvertisementVideoList> videoLists = new ArrayList<>();
    private static int minVideoLength = Integer.MAX_VALUE;
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> bestVariant = getBestVariant(storage.list());
        if (bestVariant.isEmpty()) throw new NoVideoAvailableException();
        else {

            Collections.sort(bestVariant);
            for (Advertisement ad : bestVariant) {
                ConsoleHelper.writeMessage(ad.toString());
                ad.revalidate();
            }
        }

    }

    private List<Advertisement> getBestVariant(List<Advertisement> allVideos) {
        fillAdvertisementVariants(allVideos);
        // удаляем последний элемент, так как он пуст
        videoLists.remove(videoLists.size() - 1);
        Collections.sort(videoLists);
        for (AdvertisementVideoList list :
                videoLists) {
            System.out.println(list);
        }
        AdvertisementVideoList bestVariant = Collections.max(videoLists);
        System.out.println(String.format("В лучшем варианте %d видео, общая длина %d минут (%d сек)", bestVariant.size, bestVariant.length / 60, bestVariant.length));
        return bestVariant.getAdvertisements();
    }

    private void fillAdvertisementVariants(List<Advertisement> candidatesList) {
        if (videoLists.isEmpty()) {
            videoLists.add(new AdvertisementVideoList());

            for (Advertisement ad : // находим минимальную длину ролика
                    storage.list()) {
                if (ad.getDuration() < minVideoLength) minVideoLength = ad.getDuration();
            }
        }

        // работаем с последним списком в листе
        AdvertisementVideoList videoList = videoLists.get(videoLists.size() - 1);
        int remainingTime = timeSeconds - videoList.getLength();
  //      if

        for (int i = 0; i < candidatesList.size(); i++) {
            if (candidatesList.get(i).getDuration() <= (timeSeconds - videoList.getLength()) &&
                    candidatesList.get(i).getHits() > 0) {
                if (!videoList.getAdvertisements().contains(candidatesList.get(i))) {
                    videoList.add(candidatesList.get(i));
                    List<Advertisement> candidatesTail = new ArrayList<>();
                    candidatesTail.addAll(candidatesList);
                    candidatesTail.remove(i);
                    if (!candidatesTail.isEmpty()) fillAdvertisementVariants(candidatesTail); // вызов рекурсии
                }
            }
        }
        if (videoList.getLength() > 0) videoLists.add(new AdvertisementVideoList());

    }

    private class AdvertisementVideoList implements Comparable {
        private int length; // длина в секундах
        private int size;   // количество роликов
        private int profit; // стоимость набора
        private List<Advertisement> advertisements = new ArrayList<>();

        public void add(Advertisement advertisement) {
            advertisements.add(advertisement);
            profit += advertisement.getAmountPerOneDisplaying();
            length += advertisement.getDuration();
            size = advertisements.size();
        }

        @Override
        public int compareTo(Object o) {
            AdvertisementVideoList other = (AdvertisementVideoList) o;
            if (this.profit == other.profit) {
                if (this.length == other.length) {
                    return this.size - other.size;
                }
                return this.length - other.length;
            }
            return this.profit - other.profit;
        }

        public int getLength() {
            return length;
        }

        public int getSize() {
            return size;
        }

        public int getProfit() {
            return profit;
        }

        public List<Advertisement> getAdvertisements() {
            return advertisements;
        }

        @Override
        public String toString() {
            return "AdvertisementVideoList{" +
                    "length=" + length +
                    ", size=" + size +
                    ", profit=" + profit +
                    ", advertisements=" + advertisements +
                    '}';
        }
    }

}
