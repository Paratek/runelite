package net.runelite.client.plugins.logger.listeners;

import net.runelite.api.Client;
import net.runelite.client.plugins.logger.LogEntry;
import net.runelite.client.plugins.logger.Logger;

public class CameraMovementListener extends GameListener {

    private int yaw = -1, pitch = -1, x = -1, y = -1, z = -1;

    public CameraMovementListener(Client client) {
        super(client);
    }

    @Override
    public void update() {
        int localYaw = super.client.getCameraYaw();
        int localPitch = super.client.getCameraPitch();
        int localX = super.client.getCameraX();
        int localY = super.client.getCameraY();
        int localZ = super.client.getCameraZ();
        if (localPitch != this.pitch || localYaw != this.yaw
                || localX != this.x || localY != this.y || localZ != this.z) {
            Logger.write(LogEntry.CAMERA_CHANGED, localYaw, localPitch, localX, localY, localZ);
            this.yaw = localYaw;
            this.pitch = localPitch;
            this.x = localX;
            this.y = localY;
            this.z = localZ;
        }
    }

}
