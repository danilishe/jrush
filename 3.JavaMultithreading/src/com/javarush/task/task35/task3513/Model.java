package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Данил on 10.06.2017.
 */
public class Model {
    final private static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score = 0;
    protected int maxTile = 2;

    public Model() {
        resetGameTiles();
    }


    public static void main(String[] args) {
        // Для теста
        Model model = new Model();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            System.out.println(Arrays.asList(model.gameTiles[i]));
        }
        System.out.println();
        model.right();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            System.out.println(Arrays.asList(model.gameTiles[i]));
        }

    }

    public void down() {
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }
    public void right() {
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }
    public void up() {
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public void left() {
        boolean hasChanges = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
                hasChanges = true;
        }
        if (hasChanges) addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isCompressed = false;
        for (int i = 1; i < tiles.length; i++) {
            if (!tiles[i].isEmpty()) {
                for (int j = i; j > 0; j--) {
                    if (tiles[j - 1].isEmpty()) {
                        tiles[j - 1].value = tiles[j].value;
                        tiles[j].value = 0;
                        isCompressed = true;
                    }
                }
            }
        }
        return isCompressed;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isMerged = false;
        for (int i = 0; i < tiles.length - 2; i++) {
            if (tiles[i].value != 0 && tiles[i].value == tiles[i + 1].value) {
                tiles[i].value *= 2;
                score += tiles[i].value;
                if (maxTile < tiles[i].value) maxTile = tiles[i].value;
                tiles[i + 1].value = 0;
                isMerged = true;
            }
            compressTiles(tiles);
        }
        return isMerged;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles != null && !emptyTiles.isEmpty()) {
            // Random random = new Random();
            int randomTileNumber = (int) (Math.random() * emptyTiles.size()); //random.nextInt(emptyTiles.size());
            emptyTiles.get(randomTileNumber).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    private void rotate() {
        Tile[][] temp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                int newY = x;
                int newX = FIELD_WIDTH - y - 1;
                temp[y][x] = gameTiles[newY][newX];
            }
        }
        gameTiles = temp;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) emptyTiles.add(gameTiles[i][j]);
            }
        }
        return emptyTiles;
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
}
