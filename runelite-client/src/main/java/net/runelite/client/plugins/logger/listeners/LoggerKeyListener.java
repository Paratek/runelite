package net.runelite.client.plugins.logger.listeners;

import net.runelite.client.input.KeyListener;
import net.runelite.client.plugins.logger.LogEntry;
import net.runelite.client.plugins.logger.Logger;

import java.awt.event.KeyEvent;

public class LoggerKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        Logger.write(LogEntry.KEY_TYPED, e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Logger.write(LogEntry.KEY_PRESSED, e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Logger.write(LogEntry.KEY_RELEASED, e.getKeyCode());
    }

}
