package net.runelite.client.plugins.logger.listeners;

import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.plugins.logger.LogEntry;
import net.runelite.client.plugins.logger.Logger;

import java.util.HashMap;
import java.util.Map;

public class PlayerMovementListener extends GameListener {

    private final Map<String, LocalPoint> positionMap = new HashMap<>();

    public PlayerMovementListener(Client client) {
        super(client);
    }

    @Override
    public void update() {
        super.client.getPlayers().forEach(player -> {
            if (positionMap.containsKey(player.getName())) {
                final LocalPoint cur = positionMap.get(player.getName());
                final LocalPoint p = player.getLocalLocation();
                if (cur.getX() != p.getX() || cur.getY() != p.getY()) {
                    Logger.write(LogEntry.PLAYER_MOVED, player.getName(), p.getX(), p.getY(), p.getSceneX(), p.getSceneY());
                    positionMap.put(player.getName(), new LocalPoint(p.getX(), p.getY()));
                }

            } else {
                final LocalPoint cur = player.getLocalLocation();
                final LocalPoint p = new LocalPoint(cur.getX(), cur.getY());
                Logger.write(LogEntry.PLAYER_MOVED, player.getName(), p.getX(), p.getY(), p.getSceneX(), p.getSceneY());
                positionMap.put(player.getName(), p);
            }
        });

    }

    public Map<String, LocalPoint> getPositionMap() {
        return positionMap;
    }

}
