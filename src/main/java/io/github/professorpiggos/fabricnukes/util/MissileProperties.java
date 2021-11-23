package io.github.professorpiggos.fabricnukes.util;

import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui.LaunchpadGui;
import net.minecraft.screen.PropertyDelegate;

public class MissileProperties implements PropertyDelegate {
    private int destinationX = 0;
    private int destinationY = 0;
    private int missileType = 0;
    private int isReady = 0;
    @Override
    public int get(int index) {
        return switch(index) {
            case 0 -> destinationX;
            case 1 -> destinationY;
            case 2 -> missileType;
            case 3 -> isReady;
            default -> -1;
        };
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case 0 -> destinationX = value;
            case 1 -> destinationY = value;
            case 2 -> missileType = value;
            case 3 -> isReady = value;
        }
    }

    @Override
    public int size() {
        return LaunchpadGui.PROPERTY_COUNT;
    }
}