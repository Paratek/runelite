package net.runelite.client.plugins.logger.listeners;

import net.runelite.client.input.MouseListener;
import net.runelite.client.plugins.logger.LogEntry;
import net.runelite.client.plugins.logger.Logger;

import java.awt.event.MouseEvent;

public class LoggerMouseListener implements MouseListener {

    @Override
    public MouseEvent mouseClicked(MouseEvent e) {
        Logger.write(LogEntry.MOUSE_CLICKED, e.getX(), e.getY());
        return e;
    }

    @Override
    public MouseEvent mousePressed(MouseEvent e) {
        Logger.write(LogEntry.MOUSE_PRESSED, e.getX(), e.getY());
        return e;
    }

    @Override
    public MouseEvent mouseReleased(MouseEvent e) {
        Logger.write(LogEntry.MOUSE_RELEASED, e.getX(), e.getY());
        return e;
    }

    @Override
    public MouseEvent mouseEntered(MouseEvent e) {
        Logger.write(LogEntry.MOUSE_ENTERED, e.getX(), e.getY());
        return e;
    }

    @Override
    public MouseEvent mouseExited(MouseEvent e) {
        Logger.write(LogEntry.MOUSE_EXITED, e.getX(), e.getY());
        return e;
    }

    @Override
    public MouseEvent mouseDragged(MouseEvent e) {
        Logger.write(LogEntry.MOUSE_DRAGGED, e.getX(), e.getY());
        return e;
    }

    @Override
    public MouseEvent mouseMoved(MouseEvent e) {
        Logger.write(LogEntry.MOUSE_MOVED, e.getX(), e.getY());
        return e;
    }

}
