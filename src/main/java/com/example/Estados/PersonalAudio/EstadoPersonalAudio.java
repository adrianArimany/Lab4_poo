package com.example.Estados.PersonalAudio;
import com.example.Estados.Estado;

public class EstadoPersonalAudio extends Estado implements IPersonalAudio{

    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Mode List\n");
        menu.append("2. Move up song\n");
        menu.append("3. Move down song\n");
        menu.append("4. Listen to Song\n");
        menu.append("0. GOTO MENU\n");
        
        return menu.toString();
    }

    @Override
    public Estado transition(int action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transition'");
    }

    @Override
    public String typeMode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'typeMode'");
    }

    @Override
    public String movoSongUp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movoSongUp'");
    }

    @Override
    public String moveSongDown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveSongDown'");
    }

    @Override
    public String escucharSong() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escucharSong'");
    }          
    
    
}
