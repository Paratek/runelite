package net.runelite.client.plugins.logger.listeners;

import net.runelite.api.Client;

public abstract class GameListener {

    protected final Client client;

    public GameListener(Client client) {
        this.client = client;
    }

    public abstract void update();

}
