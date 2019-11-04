package net.runelite.client.plugins.logger.listeners;

import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.plugins.logger.LogEntry;
import net.runelite.client.plugins.logger.Logger;

import java.util.HashMap;
import java.util.Map;

public class NpcMovementListener extends GameListener {

    private final Map<Integer, LocalPoint> positionMap = new HashMap<>();

    public NpcMovementListener(Client client) {
        super(client);
    }

    @Override
    public void update() {
        super.client.getNpcs().forEach(npc -> {
            if (positionMap.containsKey(npc.getIndex())) {
                final LocalPoint cur = positionMap.get(npc.getIndex());
                final LocalPoint p = npc.getLocalLocation();
                if (cur.getX() != p.getX() || cur.getY() != p.getY()) {
                    Logger.write(LogEntry.NPC_MOVED, npc.getName(), p.getX(), p.getY(), p.getSceneX(), p.getSceneY());
                    positionMap.put(npc.getIndex(), new LocalPoint(p.getX(), p.getY()));
                }
            } else {
                final LocalPoint cur = npc.getLocalLocation();
                final LocalPoint p = new LocalPoint(cur.getX(), cur.getY());
                Logger.write(LogEntry.NPC_MOVED, npc.getName(), p.getX(), p.getY(), p.getSceneX(), p.getSceneY());
                positionMap.put(npc.getIndex(), p);
            }
        });
    }

    public Map<Integer, LocalPoint> getPositionMap() {
        return positionMap;
    }

}
