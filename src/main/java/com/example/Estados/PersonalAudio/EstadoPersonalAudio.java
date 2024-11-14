package com.example.Estados.PersonalAudio;
import java.util.HashMap;
import java.util.Map;

import com.example.Data.PersonalAudioData;
import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;

public class EstadoPersonalAudio extends Estado implements IPersonalAudio{

    private enum Mode { CD, MP3, SPOTIFY }
    private Mode currentMode;
    private PersonalAudioData audioData;
    private Map<Integer, String> currentMap;
    private Map<Mode, Integer> modeIndices;
    private int currentIndex;


    public EstadoPersonalAudio() {
        this.audioData = new PersonalAudioData();
        this.currentMode = Mode.CD;
        this.currentMap = audioData.getCDMap();
        this.modeIndices = new HashMap<>();
        this.modeIndices.put(Mode.CD, 1);
        this.modeIndices.put(Mode.MP3, 1);
        this.modeIndices.put(Mode.SPOTIFY, 1);
        this.currentIndex = modeIndices.get(currentMode);
    }

    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("===== RADIO MENU =====\n");
        menu.append("Current Mode: ").append(currentMode.name()).append("\n\n");
        menu.append("1. Switch Mode\n");
        menu.append("2. Move up song\n");
        menu.append("3. Move down song\n");
        menu.append("4. Info About current Song\n");
        menu.append("0. GOTO MENU\n");
        
        return menu.toString();
    }

    @Override
    public Estado transition(int action) {
        switch (action) {
            case 1:
                System.out.println(typeMode());
                return this;
            case 2:
                System.out.println(movoSongUp());
                return this;
            case 3: 
                System.out.println(moveSongDown());
                return this;
            case 4:
                System.out.println(escucharSong());
                return this;
            case 0:
                return new MenuPrincipal();
            default:
                return this;
        }
    }
    
    public void switchMode(Mode newMode) {
        this.currentMode = newMode;
        currentMap = getCurrentMapForMode(currentMode);
        currentIndex = modeIndices.getOrDefault(currentMode, 1); 
    }

    private Map<Integer, String> getCurrentMapForMode(Mode mode) {
        switch (mode) {
            case CD: return audioData.getCDMap();
            case MP3: return audioData.getMP3Map();
            case SPOTIFY: return audioData.getSpotifyMap();
            default: return null;
        }
    }
    @Override
    public String typeMode() {
        currentMode = Mode.values()[(currentMode.ordinal() + 1) % Mode.values().length];
        currentMap = getCurrentMapForMode(currentMode);
        currentIndex = modeIndices.getOrDefault(currentMode, 1);
        return "Current Mode: " + currentMode.name();
    }

    @Override
    public String movoSongUp() {
        if (currentMap.containsKey(currentIndex + 1)) {
            currentIndex++;
            modeIndices.put(currentMode, currentIndex);
            return " Next Song... ";
        } else {
            return " No more songs to move up";
        }
}

    @Override
    public String moveSongDown() {
        if (currentIndex > 1) {
            currentIndex--;
            modeIndices.put(currentMode, currentIndex);
            return " Next Song... ";
        } else {
            return " No more songs to move down";
        }
    }

    @Override
    public String escucharSong() {
        return currentMap.getOrDefault(currentIndex, " No song available");
    }         
    
    
}
