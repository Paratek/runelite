package net.runelite.client.plugins.logger.listeners;

import net.runelite.api.Client;
import net.runelite.client.plugins.logger.LogEntry;
import net.runelite.client.plugins.logger.Logger;

public class CameraMovementListener extends GameListener {

    private int yaw = -1, pitch = -1;

    public CameraMovementListener(Client client) {
        super(client);
    }

    @Override
    public void update() {
        int localYaw = super.client.getCameraYaw();
        int localPitch = super.client.getCameraPitch();
        if (localPitch != this.pitch || localYaw != this.yaw) {
            Logger.write(LogEntry.CAMERA_CHANGED, localYaw, localPitch);
            this.yaw = localYaw;
            this.pitch = localPitch;
        }
    }

}
